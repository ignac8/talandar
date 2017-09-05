package simulation.player;

import simulation.GameState;

public abstract class Player {
    protected int playerId;

    public Player(int id) {
        this.playerId = playerId;
    }

    public abstract void giveOrders(GameState gameState);

}
