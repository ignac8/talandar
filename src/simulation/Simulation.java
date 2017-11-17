package simulation;

import gui.component.SimulationUI;
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
    private double timeStep;
    private double timeLimit;

    public Simulation(SimulationState currentSimulationState, List<Player> players, boolean displayed,
                      double timeStep, double timeLimit) {
        this.currentSimulationState = currentSimulationState;
        this.players = players;
        this.displayed = displayed;
        this.timeStep = timeStep;
        this.timeLimit = timeLimit;
    }

    public SimulationState play() {
        while (!finished()) {
            SimulationState nextSimulationState = currentSimulationState.copy();
            nextSimulationState.setTime(nextSimulationState.getTime() + timeStep);
            for (Player player : players) {
                player.giveOrders(currentSimulationState);
            }
            for (Unit unit : currentSimulationState.getUnits().values()) {
                Order order = unit.getOrder();
                if (order != null) {
                    order.execute(currentSimulationState, nextSimulationState, timeStep);
                }
            }
            if (displayed) {
                SIMULATION_UI.setSimulationState(currentSimulationState);
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
