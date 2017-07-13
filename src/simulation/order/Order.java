package simulation.order;

import simulation.GameState;
import simulation.Unit;

public abstract class Order {
    private Unit unitToOrder;

    abstract public void execute(GameState currentGameState, GameState nextGameState);
}
