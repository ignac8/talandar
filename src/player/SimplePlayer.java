package player;


import simulation.SimulationState;
import simulation.Unit;
import simulation.order.AttackOrder;
import simulation.order.MoveOrder;

public final class SimplePlayer extends Player {

    public SimplePlayer(int playerId) {
        super(playerId);
    }

    @Override
    public void giveOrders(SimulationState simulationState) {
        for (Unit unit : simulationState.getUnits().values()) {
            if (unit.getPlayerId() == playerId && unit.getHitPoints() > 0) {
                Unit closestEnemyUnit = simulationState.getClosestEnemyUnit(unit);
                if (unit.canAttack(closestEnemyUnit)) {
                    unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                } else {
                    unit.setOrder(new MoveOrder(unit, closestEnemyUnit.getPosition()));
                }
            }
        }
    }
}
