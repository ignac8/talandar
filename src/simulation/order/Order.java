package simulation.order;

import simulation.GameState;
import simulation.Unit;

public abstract class Order {
    protected Unit unitToOrder;

    public Order(Unit unitToOrder) {
        this.unitToOrder = unitToOrder;
    }

    abstract public void execute(GameState currentGameState, GameState nextGameState);
}
