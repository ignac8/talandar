package simulation;

import gui.component.SimulationUI;
import player.simulation.SimulationPlayer;
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
    private List<SimulationPlayer> simulationPlayers;
    private boolean displayed;
    private double timeStep;
    private double timeLimit;

    public Simulation(SimulationState currentSimulationState, List<SimulationPlayer> simulationPlayers, boolean displayed,
                      double timeStep, double timeLimit) {
        this.currentSimulationState = currentSimulationState;
        this.simulationPlayers = simulationPlayers;
        this.displayed = displayed;
        this.timeStep = timeStep;
        this.timeLimit = timeLimit;
    }

    public SimulationState play() {
        while (!finished()) {
            SimulationState nextSimulationState = currentSimulationState.copy();
            nextSimulationState.setTime(nextSimulationState.getTime() + timeStep);
            for (SimulationPlayer simulationPlayer : simulationPlayers) {
                simulationPlayer.giveOrders(currentSimulationState);
            }
            for (Unit unit : currentSimulationState.getUnits().values()) {
                Order order = unit.getOrder();
                if (order != null) {
                    order.execute(currentSimulationState, nextSimulationState, timeStep);
                }
            }
            if (displayed) {
                SIMULATION_UI.setSimulationState(currentSimulationState);
                SIMULATION_UI.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
