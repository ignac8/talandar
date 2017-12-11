package player.executor;

import jnibwapi.Unit;
import jnibwapi.types.UnitType;

import java.util.Collection;

public interface Executor<State, Unit, Position> {

    Unit getClosestEnemyUnit(Unit unit, State state);

    Unit getLowestHpEnemyUnit(Unit unit, State state);

    Position getRunawayPosition(Unit unit, State state, Position closestEnemyUnitPosition);

    boolean canAttack(Unit unit, Unit enemyUnit);

    void setAttackOrder(Unit unit, Unit enemyUnit);

    void setMoveOrder(Unit unit, Position position);

    boolean canUnitBeAttackedByEnemy(Unit unit, State state);

    Collection<Unit> getUnits(State state);

    boolean isPlayerUnit(Unit unit, int playerId);

    double getHitPoints(Unit unit);

    UnitType getUnitType(Unit unit);

    Position getPosition(Unit unit);

    double getTime(State state);

    double getShields(Unit unit);

    double getDistance(Position position, Position enemyPosition);

    boolean isOnWeaponCooldown(State state, Unit unit);
}
