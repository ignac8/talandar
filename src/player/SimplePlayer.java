package player;

import player.executor.Executor;

public class SimplePlayer<State, Unit, Position> extends Player<State, Unit, Position> {

    public SimplePlayer(Executor<State, Unit, Position> executor, int playerId) {
        super(executor, playerId);
    }

    @Override
    public void giveOrders(State state) {
        for (Unit unit : executor.getUnits(state)) {
            if (executor.isPlayerUnit(unit, playerId) && executor.getHitPoints(unit) > 0) {
                Unit closestEnemyUnit = executor.getClosestEnemyUnit(unit, state);
                if (executor.canAttack(unit, closestEnemyUnit)) {
                    executor.setAttackOrder(unit, closestEnemyUnit);
                } else {
                    executor.setMoveOrder(unit, executor.getPosition(closestEnemyUnit));
                }
            }
        }
    }
}
