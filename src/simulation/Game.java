package simulation;

import simulation.order.Order;
import simulation.player.Player;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Game {

    private GameState currentGameState;
    private List<Player> players;

    public GameState play() {
        GameState nextGameState = currentGameState.copy();
        while (!finished()) {
            for (Player player : players) {
                player.giveOrders(currentGameState);
            }
                for (Entry<Integer, Unit> entry : currentGameState.getUnits().entrySet()) {
                    int id = entry.getKey();
                    Unit unit = entry.getValue();
                    Order order = unit.getOrder();
                    if (order != null) {
                        order.execute(currentGameState, nextGameState);
                    }
                }

            nextGameState.removeDeadUnits();
            currentGameState = nextGameState;
        }
        return currentGameState;
    }

    private boolean finished() {
        return getPlayersWithUnits() <= 1;
    }

    private int getPlayersWithUnits() {
        int playersWithUnits = 0;
        for (Entry<Integer, Unit> playerUnits : currentGameState.getUnits().entrySet()) {
            if (playerUnits.size() > 0)
                playersWithUnits++;
        }
        return playersWithUnits;
    }
}
