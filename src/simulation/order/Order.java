package simulation.order;

import simulation.SimulationState;
import simulation.Unit;

public abstract class Order {
    protected Unit unitToOrder;
    protected boolean executed;

    public Order(Unit unitToOrder) {
        this.unitToOrder = unitToOrder;
        executed = false;
    }

    abstract public void execute(SimulationState currentSimulationState, SimulationState nextSimulationState);

    public boolean isExecuted() {
        return executed;
    }
}
