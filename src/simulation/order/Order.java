package simulation.order;

import simulation.GameState;
import simulation.Unit;

public abstract class Order {
    protected Unit unitToOrder;
    protected boolean executed;

    public Order(Unit unitToOrder) {
        this.unitToOrder = unitToOrder;
        executed = false;
    }

    abstract public void execute(GameState currentGameState, GameState nextGameState);

    public boolean isExecuted() {
        return executed;
    }
}
