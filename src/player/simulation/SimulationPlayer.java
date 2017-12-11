package player.simulation;

import simulation.SimulationState;

public abstract class SimulationPlayer {
    protected int playerId;

    public SimulationPlayer(int playerId) {
        this.playerId = playerId;
    }

    public abstract void giveOrders(SimulationState simulationState);

}
