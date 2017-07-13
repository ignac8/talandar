package simulation;

import jnibwapi.Position;
import jnibwapi.types.UnitType;
import simulation.order.Order;

public class Unit {
    private UnitType unitType;
    private double currentHitPoints;
    private double currentShields;
    private Position position;
    private Order order;

    public double getCurrentHitPoints() {
        return currentHitPoints;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void damageUnit(double damage) {
        currentShields -= damage;
        if (currentShields < 0) {
            currentHitPoints += currentShields;
            currentShields = 0;
        }
    }
}
