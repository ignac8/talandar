package player;

import player.executor.Executor;

public abstract class Player<State, Unit, Position> {

    protected Executor<State, Unit, Position> executor;
    protected int playerId;

    public Player(Executor<State, Unit, Position> executor, int playerId) {
        this.executor = executor;
        this.playerId = playerId;
    }

    public abstract void giveOrders(State state);
}