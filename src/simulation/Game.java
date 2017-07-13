package simulation;

import simulation.order.Order;
import simulation.player.Player;

import java.util.List;

public class Game {

    private GameState currentGameState;
    private List<Player> players;

    public GameState play() {
        GameState nextGameState = currentGameState.copy();
        while (!finished()) {
            for (Player player : players) {
                player.giveOrders(currentGameState);
            }
            for (List<Unit> playerUnits : currentGameState.getUnits()) {
                for (Unit unit : playerUnits) {
                    Order order = unit.getOrder();
                    if (order != null) {
                        order.execute(currentGameState, nextGameState);
                    }
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
        List<List<Unit>> units = currentGameState.getUnits();
        for (List<Unit> playerUnits : units) {
            if (playerUnits.size() > 0) {
                playersWithUnits++;
            }
        }
        return playersWithUnits;
    }
}
