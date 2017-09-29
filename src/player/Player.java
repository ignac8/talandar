package player;

import simulation.SimulationState;

public abstract class Player {
    protected int playerId;

    public Player(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public abstract void giveOrders(SimulationState simulationState);

}
