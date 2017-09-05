package simulation;

import java.util.HashMap;
import java.util.Map;

public class GameState {
    private Map<Integer, Unit> units;
    private double maxX;
    private double maxY;
    private int time;

    public GameState(double maxX, double maxY) {
        this.units = new HashMap<>();
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public GameState copy() {
        GameState gameState = new GameState(this.maxX, this.maxY);
        gameState.time = this.time;
        Map<Integer, Unit> units = new HashMap<>();
        for (Unit unit : this.units.values()) {
            units.put(unit.getUnitId(), unit.copy());
        }
        gameState.units = units;
        return gameState;
    }

    public Map<Integer, Unit> getUnits() {
        return units;
    }

    public void putUnit(Unit unit) {
        units.put(unit.getUnitId(), unit);
    }

    public int getTime() {
        return time;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public Unit getClosestEnemyUnit(Unit unit) {
        double minDistance = Double.MAX_VALUE;
        Position position = unit.getPosition();
        Unit closestEnemyUnit = null;
        for (Unit possibleEnemyUnit : units.values()) {
            double distance = position.getDistance(possibleEnemyUnit.getPosition());
            if (distance < minDistance && unit.getPlayerId() != possibleEnemyUnit.getPlayerId()) {
                closestEnemyUnit = possibleEnemyUnit;
                minDistance = distance;
            }
        }
        return closestEnemyUnit;
    }
}
