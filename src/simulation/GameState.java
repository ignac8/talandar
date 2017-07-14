package simulation;

import java.util.Map;
import java.util.Map.Entry;

public class GameState {
    private Map<Integer, Unit> units;
    private double maxX;
    private double maxY;

    public GameState copy() {
        GameState gameState = new GameState();

        return gameState;
    }

    public Map<Integer, Unit> getUnits() {
        return units;
    }

    public void removeDeadUnits() {
        for (Entry<Integer, Unit> entry : units.entrySet()) {
            int id = entry.getKey();
            Unit unit = entry.getValue();
                if (unit.getCurrentHitPoints() <= 0) {
                    units.remove(id);
                }
            }
    }
}
