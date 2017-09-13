package simulation;

import jnibwapi.types.UnitType;
import jnibwapi.types.WeaponType;
import simulation.order.Order;

public class Unit {
    private static int idCounter = 0;
    private int unitId;
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
        this.unitId = idCounter++;
        this.hitPoints = unitType.getMaxHitPoints();
        this.shields = unitType.getMaxShields();
    }

    public Unit copy() {
        Unit unit = new Unit();
        unit.unitId = this.unitId;
        unit.playerId = this.playerId;
        unit.unitType = this.unitType;
        unit.hitPoints = this.hitPoints;
        unit.shields = this.shields;
        unit.position = this.position.copy();
        unit.cooldownTime = this.cooldownTime;
        return unit;
    }

    public int getUnitId() {
        return unitId;
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

    public boolean canAttack(Unit unitToAttack) {
        WeaponType weaponType;
        UnitType unitToAttackUnitType = unitToAttack.getUnitType();
        if (unitToAttackUnitType.isFlyer()) {
            weaponType = unitType.getAirWeapon();
        } else {
            weaponType = unitType.getGroundWeapon();
        }
        double distance = position.getDistance(unitToAttack.getPosition());
        return distance <= weaponType.getMaxRange() && distance >= weaponType.getMinRange();
    }

    public Position getRunAwayPosition(Unit enemyUnit) {
        Position enemyPosition = enemyUnit.getPosition();
        double enemyPositionX = enemyPosition.getX();
        double enemyPositionY = enemyPosition.getY();
        double positionX = position.getX();
        double positionY = position.getY();
        Position runAwayPosition = new Position(enemyPositionX - positionX, enemyPositionY - positionY);
        double distance = runAwayPosition.getDistance(enemyPosition);
        double speed = unitType.getTopSpeed();
        if (distance < speed) {
            double multiplier = speed / distance;
            runAwayPosition = new Position(enemyPositionX * multiplier - positionX, enemyPositionY * multiplier - positionY);
        }
        return runAwayPosition;
    }


}

