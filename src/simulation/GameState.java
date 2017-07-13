package simulation;

import java.util.Iterator;
import java.util.List;

public class GameState {
    private List<List<Unit>> units;

    public GameState copy() {
        GameState gameState = new GameState();

        return gameState;
    }

    public List<List<Unit>> getUnits() {
        return units;
    }

    public void removeDeadUnits() {
        for (List<Unit> playerUnits : units) {
            Iterator<Unit> iterator = playerUnits.iterator();
            while (iterator.hasNext()) {
                Unit unit = iterator.next();
                if (unit.getCurrentHitPoints() <= 0) {
                    iterator.remove();
                }
            }
        }
    }
}
