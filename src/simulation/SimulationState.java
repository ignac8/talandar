package simulation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SimulationState {
    private Map<Integer, Unit> units;
    private double maxX;
    private double maxY;
    private double time;
    private double timeStep;

    public SimulationState(double maxX, double maxY, double timeStep) {
        this.units = new HashMap<>();
        this.maxX = maxX;
        this.maxY = maxY;
        this.timeStep = timeStep;
    }

    public SimulationState(double maxX, double maxY, double time, double timeStep) {
        this.units = new HashMap<>();
        this.maxX = maxX;
        this.maxY = maxY;
        this.time = time;
        this.timeStep = timeStep;
    }

    public SimulationState copy() {
        SimulationState simulationState = new SimulationState(this.maxX, this.maxY, this.time, this.timeStep);
        Map<Integer, Unit> units = new HashMap<>();
        for (Unit unit : this.units.values()) {
            units.put(unit.getUnitId(), unit.copy());
        }
        simulationState.units = units;
        return simulationState;
    }

    public Map<Integer, Unit> getUnits() {
        return units;
    }

    public void putUnit(Unit unit) {
        units.put(unit.getUnitId(), unit);
    }

    public double getTime() {
        return time;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public void incrementTime() {
        time += this.timeStep;
    }

    public Unit getClosestEnemyUnit(Unit unit) {
        double minDistance = Double.MAX_VALUE;
        Position position = unit.getPosition();
        Unit closestEnemyUnit = null;
        for (Unit possibleEnemyUnit : units.values()) {
            if (unit.getPlayerId() != possibleEnemyUnit.getPlayerId() && possibleEnemyUnit.getHitPoints() > 0) {
                double distance = position.getDistance(possibleEnemyUnit.getPosition());
                if (distance < minDistance) {
                    closestEnemyUnit = possibleEnemyUnit;
                    minDistance = distance;
                }
            }
        }
        return closestEnemyUnit;
    }

    public Unit getLowestHpEnemyUnit(Unit unit) {
        double minHp = Double.MAX_VALUE;
        Unit lowestHpEnemyUnit = null;
        for (Unit possibleEnemyUnit : units.values()) {
            double hp = possibleEnemyUnit.getHitPoints() + possibleEnemyUnit.getShields();
            if (hp > 0 && hp < minHp && unit.getPlayerId() != possibleEnemyUnit.getPlayerId()) {
                lowestHpEnemyUnit = possibleEnemyUnit;
                minHp = hp;
            }
        }
        return lowestHpEnemyUnit;
    }

    public boolean canUnitBeAttackedByEnemy(Unit unit) {
        boolean canUnitBeAttackedByEnemy = false;
        for (Iterator<Unit> iterator = units.values().iterator(); iterator.hasNext() && !canUnitBeAttackedByEnemy; ) {
            Unit possibleEnemyUnit = iterator.next();
            if (unit.getPlayerId() != possibleEnemyUnit.getPlayerId() && possibleEnemyUnit.getHitPoints() > 0) {
                if (unit.getPlayerId() != possibleEnemyUnit.getPlayerId()) {
                    double distance = unit.getPosition().getDistance(possibleEnemyUnit.getPosition());
                    double range = unit.getUnitType().getGroundWeapon().getMaxRange();
                    if (range >= distance) {
                        canUnitBeAttackedByEnemy = true;
                    }
                }
            }
        }
        return canUnitBeAttackedByEnemy;
    }

    public double getTimeStep() {
        return timeStep;
    }
}
