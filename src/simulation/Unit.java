package simulation;

import jnibwapi.types.UnitType;
import simulation.order.Order;

public class Unit {
    private static int idCounter = 0;
    private int id;
    private int playerId;
    private UnitType unitType;
    private double hitPoints;
    private double shields;
    private Position position;
    private Order order;
    private double cooldownTime;

    private Unit() {
    }

    public Unit(int playerId, UnitType unitType, Position position) {
        this.playerId = playerId;
        this.unitType = unitType;
        this.position = position;
        this.id = idCounter++;
        this.hitPoints = unitType.getMaxHitPoints();
        this.shields = unitType.getMaxShields();
    }

    public Unit copy() {
        Unit unit = new Unit();
        unit.id = this.id;
        unit.playerId = this.playerId;
        unit.unitType = this.unitType;
        unit.hitPoints = this.hitPoints;
        unit.shields = this.shields;
        unit.position = this.position.copy();
        unit.cooldownTime = this.cooldownTime;
        return unit;
    }

    public int getId() {
        return id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(double hitPoints) {
        this.hitPoints = hitPoints;
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

    public double getShields() {
        return shields;
    }

    public void setShields(double shields) {
        this.shields = shields;
    }

    public double getCooldownTime() {
        return cooldownTime;
    }

    public void setCooldownTime(double cooldownTime) {
        this.cooldownTime = cooldownTime;
    }


}

