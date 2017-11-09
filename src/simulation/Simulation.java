package simulation;

import gui.SimulationUI;
import player.Player;
import simulation.bridge.JNIBWAPI_LOAD;
import simulation.order.Order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Simulation {

    private static final SimulationUI SIMULATION_UI = SimulationUI.getInstance();

    static {
        JNIBWAPI_LOAD.loadIfNecessary();
    }

    private SimulationState currentSimulationState;
    private List<Player> players;
    private boolean displayed;
    private double timeLimit;

    public Simulation(SimulationState currentSimulationState, List<Player> players, boolean displayed, double timeLimit) {
        this.currentSimulationState = currentSimulationState;
        this.players = players;
        this.displayed = displayed;
        this.timeLimit = timeLimit;
    }

    public SimulationState play() {
        while (!finished()) {
            SimulationState nextSimulationState = currentSimulationState.copy();
            nextSimulationState.incrementTime();
            for (Player player : players) {
                player.giveOrders(currentSimulationState);
            }
            for (Unit unit : currentSimulationState.getUnits().values()) {
                Order order = unit.getOrder();
                if (order != null) {
                    order.execute(currentSimulationState, nextSimulationState);
                }
            }
            if (displayed) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
                SIMULATION_UI.setSimulationState(currentSimulationState);
                SIMULATION_UI.repaint();
            }
            currentSimulationState = nextSimulationState;
        }
        return currentSimulationState;
    }

    private boolean finished() {
        return getPlayersWithUnits() <= 1 || currentSimulationState.getTime() > timeLimit;
    }

    private int getPlayersWithUnits() {
        Set<Integer> playersIdSet = new HashSet<>();
        for (Unit unit : currentSimulationState.getUnits().values()) {
            if (unit.getHitPoints() > 0) {
                playersIdSet.add(unit.getPlayerId());
            }
        }
        return playersIdSet.size();
    }
}
