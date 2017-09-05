package simulation;

import simulation.order.Order;
import simulation.player.Player;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Game {

    private static final SimulationUI SIMULATION_UI = SimulationUI.getInstance();
    private GameState currentGameState;
    private List<Player> players;
    private boolean displayed;

    public Game(GameState currentGameState, List<Player> players, boolean displayed) {
        this.currentGameState = currentGameState;
        this.players = players;
        this.displayed = displayed;
    }

    public GameState play() {
        GameState nextGameState = currentGameState.copy();
        while (!finished()) {
            for (Player player : players) {
                player.giveOrders(currentGameState);
            }
            for (Unit unit : currentGameState.getUnits().values()) {
                Order order = unit.getOrder();
                if (order != null) {
                    order.execute(currentGameState, nextGameState);
                }
            }
            if (displayed) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
                SIMULATION_UI.setGameState(currentGameState);
                SIMULATION_UI.repaint();
            }
            currentGameState = nextGameState;
        }
        return currentGameState;
    }

    private boolean finished() {
        return getPlayersWithUnits() <= 1;
    }

    private int getPlayersWithUnits() {
        Set<Integer> playersSet = new TreeSet<>();
        for (Unit unit : currentGameState.getUnits().values()) {
            if (unit.getHitPoints() > 0) {
                playersSet.add(unit.getPlayerId());
            }
        }
        return playersSet.size();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
