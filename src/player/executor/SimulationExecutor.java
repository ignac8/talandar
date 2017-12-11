package player.executor;

import jnibwapi.types.UnitType;
import simulation.Position;
import simulation.SimulationState;
import simulation.Unit;
import simulation.order.AttackOrder;
import simulation.order.MoveOrder;

import java.util.Collection;

public class SimulationExecutor implements Executor<SimulationState, Unit, Position> {

    @Override
    public Unit getClosestEnemyUnit(Unit unit, SimulationState simulationState) {
        return simulationState.getClosestEnemyUnit(unit);
    }

    @Override
    public Unit getLowestHpEnemyUnit(Unit unit, SimulationState simulationState) {
        return simulationState.getLowestHpEnemyUnit(unit);
    }

    @Override
    public Position getRunawayPosition(Unit unit, SimulationState simulationState, Position closestEnemyUnitPosition) {
        Position runAwayPosition = unit.getRunAwayPosition(simulationState);
        if (runAwayPosition == null || runAwayPosition.equals(unit.getPosition())) {
            runAwayPosition = unit.getRunAwayPosition(closestEnemyUnitPosition);
        }
        if (runAwayPosition == null || runAwayPosition.equals(unit.getPosition())) {
            runAwayPosition = new Position(0, 0);
        }
        if (runAwayPosition.equals(unit.getPosition())) {
            runAwayPosition = new Position(0, simulationState.getMaxY());
        }
        if (runAwayPosition.equals(unit.getPosition())) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return runAwayPosition;
    }

    @Override
    public boolean canAttack(Unit unit, Unit enemyUnit) {
        return unit.canAttack(enemyUnit);
    }

    @Override
    public void setAttackOrder(Unit unit, Unit enemyUnit) {
        unit.setOrder(new AttackOrder(unit, enemyUnit));
    }

    @Override
    public void setMoveOrder(Unit unit, Position position) {
        unit.setOrder(new MoveOrder(unit, position));
    }

    @Override
    public boolean canUnitBeAttackedByEnemy(Unit unit, SimulationState simulationState) {
        return simulationState.canUnitBeAttackedByEnemy(unit);
    }

    @Override
    public Collection<Unit> getUnits(SimulationState simulationState) {
        return simulationState.getUnits().values();
    }

    @Override
    public boolean isPlayerUnit(Unit unit, int playerId) {
        return unit.getPlayerId() == playerId;
    }

    @Override
    public double getHitPoints(Unit unit) {
        return unit.getHitPoints();
    }

    @Override
    public UnitType getUnitType(Unit unit) {
        return unit.getUnitType();
    }

    @Override
    public Position getPosition(Unit unit) {
        return unit.getPosition();
    }

    @Override
    public double getTime(SimulationState simulationState) {
        return simulationState.getTime();
    }

    @Override
    public double getShields(Unit unit) {
        return unit.getShields();
    }

    @Override
    public double getDistance(Position position, Position enemyPosition) {
        return position.getDistance(enemyPosition);
    }

    @Override
    public boolean isOnWeaponCooldown(SimulationState simulationState, Unit unit) {
        return simulationState.getTime() < unit.getCooldownTime();
    }
}
