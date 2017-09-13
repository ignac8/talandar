package player.simulation;

import simulation.GameState;

public abstract class Player {
    protected int playerId;

    public Player(int playerId) {
        this.playerId = playerId;
    }

    public abstract void giveOrders(GameState gameState);

}
