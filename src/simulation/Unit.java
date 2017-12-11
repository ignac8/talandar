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
    private int outputId;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public int getOutputId() {
        return outputId;
    }

    public void setOutputId(int outputId) {
        this.outputId = outputId;
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

    public UnitType getUnitType() {
        return unitType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getRunAwayPosition(SimulationState simulationState) {
        double sumX = 0;
        double sumY = 0;
        int num = 0;

        for (Unit unit : simulationState.getUnits().values()) {
            if (unit.getHitPoints() > 0 && unit.getPlayerId() != playerId) {
                Position position = unit.getPosition();
                sumX += position.getX();
                sumY += position.getY();
                num++;
            }
        }

        Position averagePosition = new Position(sumX / num, sumY / num);
        return getRunAwayPosition(averagePosition);
    }

    public double getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(double hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getPlayerId() {
        return playerId;
    }

    public Position getRunAwayPosition(Position enemyPosition) {
        double enemyPositionX = enemyPosition.getX();
        double enemyPositionY = enemyPosition.getY();
        double positionX = position.getX();
        double positionY = position.getY();
        Position runAwayPosition = new Position(positionX - (enemyPositionX - positionX), positionY - (enemyPositionY - positionY));
        double distance = runAwayPosition.getDistance(enemyPosition);
        double speed = unitType.getTopSpeed();
        if (Double.isFinite(distance) && distance > 0) {
            if (distance < speed) {
                double multiplier = speed / distance;
                runAwayPosition = new Position(positionX - multiplier * (enemyPositionX - positionX), positionY - multiplier * (enemyPositionY - positionY));
            }
        } else {
            runAwayPosition = null;
        }
        return runAwayPosition;
    }


}

