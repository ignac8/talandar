package player.executor;

import jnibwapi.JNIBWAPI;
import jnibwapi.Position;
import jnibwapi.Unit;
import jnibwapi.types.UnitType;
import jnibwapi.types.WeaponType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import static jnibwapi.Position.PosType.PIXEL;

public class StarcraftExecutor implements Executor<JNIBWAPI, Unit, Position> {

    private Map<Unit, Unit> attackOrders = new HashMap<>();

    @Override
    public Unit getClosestEnemyUnit(Unit unit, JNIBWAPI jnibwapi) {
        double minDistance = Double.MAX_VALUE;
        Unit closestEnemyUnit = null;
        Collection<Unit> allUnits = jnibwapi.getAllUnits();
        for (Unit possibleEnemyUnit : allUnits) {
            if (possibleEnemyUnit.getHitPoints() > 0 && possibleEnemyUnit.getPlayer().isEnemy()) {
                double distance = unit.getDistance(possibleEnemyUnit);
                if (distance < minDistance) {
                    closestEnemyUnit = possibleEnemyUnit;
                    minDistance = distance;
                }
            }
        }
        return closestEnemyUnit;
    }

    @Override
    public Unit getLowestHpEnemyUnit(Unit unit, JNIBWAPI jnibwapi) {
        double minHp = Double.MAX_VALUE;
        Unit lowestHpEnemyUnit = null;
        for (Unit possibleEnemyUnit : jnibwapi.getAllUnits()) {
            double hp = possibleEnemyUnit.getHitPoints() + possibleEnemyUnit.getShields();
            if (hp > 0 && hp < minHp && possibleEnemyUnit.getPlayer().isEnemy()) {
                lowestHpEnemyUnit = possibleEnemyUnit;
                minHp = hp;
            }
        }
        return lowestHpEnemyUnit;
    }

    @Override
    public Position getRunawayPosition(Unit unit, JNIBWAPI jnibwapi, Position closestEnemyUnitPosition) {
        Position runAwayPosition = getRunAwayPositionFromAll(unit, jnibwapi);
        if (runAwayPosition == null || runAwayPosition.equals(unit.getPosition())) {
            runAwayPosition = getRunAwayPositionFromPosition(unit, closestEnemyUnitPosition);
        }
        if (runAwayPosition == null || runAwayPosition.equals(unit.getPosition())) {
            runAwayPosition = new Position(0, 0);
        }
        if (runAwayPosition == null || runAwayPosition.equals(unit.getPosition())) {
            runAwayPosition = new Position(0, jnibwapi.getMap().getSize().getY(PIXEL));
        }
        if (runAwayPosition == null || runAwayPosition.equals(unit.getPosition())) {
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
        WeaponType weaponType = unit.getType().getGroundWeapon();
        double distance = unit.getDistance(enemyUnit);
        return distance <= weaponType.getMaxRange() && distance >= weaponType.getMinRange();
    }

    @Override
    public void setAttackOrder(Unit unit, Unit enemyUnit) {
        if (!attackOrders.containsKey(unit)) {
            Unit attackOrder = attackOrders.get(unit);
            if (!Objects.equals(attackOrder, enemyUnit)) {
                unit.attack(enemyUnit, false);
                attackOrders.put(unit, enemyUnit);
            }
        }
    }

    @Override
    public void setMoveOrder(Unit unit, Position position) {
        if (!unit.isStartingAttack() && !unit.isAttackFrame()) {
            unit.move(position, false);
            attackOrders.remove(unit);
        }
    }

    @Override
    public boolean canUnitBeAttackedByEnemy(Unit unit, JNIBWAPI jnibwapi) {
        boolean canUnitBeAttackedByEnemy = false;
        for (Iterator<Unit> iterator = jnibwapi.getAllUnits().iterator(); iterator.hasNext() && !canUnitBeAttackedByEnemy; ) {
            Unit possibleEnemyUnit = iterator.next();
            if (possibleEnemyUnit.getPlayer().isEnemy() && possibleEnemyUnit.getHitPoints() > 0) {
                double distance = unit.getDistance(possibleEnemyUnit);
                double range = unit.getType().getGroundWeapon().getMaxRange();
                if (range >= distance) {
                    canUnitBeAttackedByEnemy = true;
                }
            }
        }
        return canUnitBeAttackedByEnemy;
    }

    @Override
    public Collection<Unit> getUnits(JNIBWAPI jnibwapi) {
        return jnibwapi.getAllUnits();
    }

    @Override
    public boolean isPlayerUnit(Unit unit, int playerId) {
        return unit.getPlayer().isSelf();
    }

    @Override
    public double getHitPoints(Unit unit) {
        return unit.getHitPoints();
    }

    @Override
    public UnitType getUnitType(Unit unit) {
        return unit.getType();
    }

    @Override
    public Position getPosition(Unit unit) {
        return unit.getPosition();
    }

    @Override
    public double getTime(JNIBWAPI jnibwapi) {
        return jnibwapi.getFrameCount();
    }

    @Override
    public double getShields(Unit unit) {
        return unit.getShields();
    }

    @Override
    public double getDistance(Position position, Position enemyPosition) {
        return position.getPDistance(enemyPosition);
    }

    @Override
    public boolean isOnWeaponCooldown(JNIBWAPI jnibwapi, Unit unit) {
        return unit.getGroundWeaponCooldown() > 0;
    }

    public Position getRunAwayPositionFromAll(Unit unit, JNIBWAPI jnibwapi) {
        double sumX = 0;
        double sumY = 0;
        int num = 0;
        for (Unit possibleEnemyUnit : jnibwapi.getAllUnits()) {
            if (possibleEnemyUnit.getHitPoints() > 0 && possibleEnemyUnit.getPlayer().isEnemy()) {
                Position position = possibleEnemyUnit.getPosition();
                sumX += position.getX(PIXEL);
                sumY += position.getY(PIXEL);
                num++;
            }
        }
        Position averagePosition = new Position((int) (sumX / num), (int) (sumY / num));
        return getRunAwayPositionFromPosition(unit, averagePosition);
    }

    public Position getRunAwayPositionFromPosition(Unit unit, Position position) {
        double enemyPositionX = position.getX(PIXEL);
        double enemyPositionY = position.getY(PIXEL);
        double positionX = unit.getPosition().getX(PIXEL);
        double positionY = unit.getPosition().getY(PIXEL);
        Position runAwayPosition = new Position((int) (positionX - (enemyPositionX - positionX)), (int) (positionY - (enemyPositionY - positionY)));
        double distance = runAwayPosition.getPDistance(position);
        double speed = unit.getType().getTopSpeed();
        if (Double.isFinite(distance) && distance > 0) {
            if (distance < speed) {
                double multiplier = speed / distance;
                runAwayPosition = new Position((int) (positionX - multiplier * (enemyPositionX - positionX)), (int) (positionY - multiplier * (enemyPositionY - positionY)));
            }
        } else {
            runAwayPosition = null;
        }
        return runAwayPosition;
    }
}
