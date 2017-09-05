package simulation.player;


import simulation.GameState;
import simulation.Unit;
import simulation.order.AttackOrder;
import simulation.order.MoveOrder;

public final class SimplePlayer extends Player {

    public SimplePlayer(int playerId) {
        super(playerId);
    }

    @Override
    public void giveOrders(GameState gameState) {
        for (Unit unit : gameState.getUnits().values()) {
            if (unit.getPlayerId() == playerId) {
                Unit closestEnemyUnit = gameState.getClosestEnemyUnit(unit);
                if (unit.isInRangeToAttack(closestEnemyUnit)) {
                    unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                } else {
                    unit.setOrder(new MoveOrder(unit, closestEnemyUnit.getPosition()));
                }
            }
        }
    }
}
