package simulation;

import jnibwapi.types.UnitType;
import simulation.order.Order;

public class Unit {
    private static int idCounter = 0;
    private int id;
    private UnitType unitType;
    private double currentHitPoints;
    private double currentShields;
    private Position position;
    private Order order;
    private double cooldown;
    private 

    public double getCurrentHitPoints() {
        return currentHitPoints;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setCurrentHitPoints(double currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
    }

    public double getCurrentShields() {
        return currentShields;
    }

    public void setCurrentShields(double currentShields) {
        this.currentShields = currentShields;
    }

    public int getId() {
        return id;
    }
}
