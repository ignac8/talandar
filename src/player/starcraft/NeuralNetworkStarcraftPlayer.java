package player.starcraft;

import jnibwapi.JNIBWAPI;
import jnibwapi.Position;
import jnibwapi.Unit;
import jnibwapi.types.UnitType;
import jnibwapi.types.WeaponType;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.neuron.CalculableNeuron;
import neuralnetwork.neuron.ConstantNeuron;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Math.sqrt;
import static jnibwapi.Position.PosType.PIXEL;

public final class NeuralNetworkStarcraftPlayer extends StarcraftPlayer {

    private Map<Unit, Position> moveOrders = new HashMap<>();
    private Map<Unit, Unit> attackOrders = new HashMap<>();

    private NeuralNetwork neuralNetwork;

    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }

    public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public void giveOrders(JNIBWAPI jnibwapi) {
        for (Unit unit : jnibwapi.getAllUnits()) {
            if (unit.getPlayer().isSelf() && unit.getHitPoints() > 0) {
                UnitType unitType = unit.getType();

                List<ConstantNeuron> inputLayer = neuralNetwork.getInputLayer();

                Unit closestEnemyUnit = getClosestEnemyUnit(unit, jnibwapi);
                if (closestEnemyUnit != null) {
                    UnitType closestEnemyUnitType = closestEnemyUnit.getType();
                    Unit lowestHpEnemyUnit = getLowestHpEnemyUnit(jnibwapi);
                    Position position = unit.getPosition();
                    Position closestEnemyUnitPosition = closestEnemyUnit.getPosition();
                    Position lowestHpEnemyUnitPosition = lowestHpEnemyUnit.getPosition();
                    Position runAwayPosition = getRunawayPosition(unit, jnibwapi, closestEnemyUnitPosition);
                    double time = jnibwapi.getFrameCount();

                    inputLayer.get(0).setValue(unit.getHitPoints() + unit.getShields());
                    inputLayer.get(1).setValue(sqrt(unitType.getGroundWeapon().getMaxRange()));
                    inputLayer.get(2).setValue(sqrt(closestEnemyUnitType.getGroundWeapon().getMaxRange()));
                    inputLayer.get(3).setValue(sqrt(position.getPDistance(closestEnemyUnitPosition)));
                    inputLayer.get(4).setValue(sqrt(position.getPDistance(lowestHpEnemyUnitPosition)));

                    neuralNetwork.calculateOutput();

                    int maxIndex = -1;
                    double maxValue = -1 * MAX_VALUE;

                    List<CalculableNeuron> outputLayer = neuralNetwork.getOutputLayer();
                    for (int counter = 0; counter < outputLayer.size(); counter++) {
                        CalculableNeuron neuron = outputLayer.get(counter);
                        if (neuron.getValue() >= maxValue) {
                            maxIndex = counter;
                            maxValue = neuron.getValue();
                        }
                    }

                    switch (maxIndex) {
                        case 0:
                            if (canAttack(unit, lowestHpEnemyUnit)) {
                                setAttackOrder(unit, lowestHpEnemyUnit);
                            } else if (canAttack(unit, closestEnemyUnit)) {
                                setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                setMoveOrder(unit, closestEnemyUnitPosition);
                            }
                            break;

                        case 1:
                            setMoveOrder(unit, runAwayPosition);
                            break;

                        case 2:
                            if (canAttack(unit, lowestHpEnemyUnit)) {
                                setAttackOrder(unit, lowestHpEnemyUnit);
                            } else {
                                setMoveOrder(unit, lowestHpEnemyUnitPosition);
                            }
                            break;

                        case 3:
                            if (canAttack(unit, lowestHpEnemyUnit)) {
                                setAttackOrder(unit, lowestHpEnemyUnit);
                            } else if (canAttack(unit, closestEnemyUnit)) {
                                setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                setMoveOrder(unit, lowestHpEnemyUnitPosition);
                            }
                            break;

                        case 4:
                            if (canAttack(unit, lowestHpEnemyUnit) && unit.getGroundWeaponCooldown() <= time) {
                                setAttackOrder(unit, lowestHpEnemyUnit);
                            } else if (canAttack(unit, closestEnemyUnit) && unit.getGroundWeaponCooldown() <= time) {
                                setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                setMoveOrder(unit, runAwayPosition);
                            }
                            break;

                        case 5:
                            if (canAttack(unit, lowestHpEnemyUnit) && unit.getGroundWeaponCooldown() <= time) {
                                setAttackOrder(unit, lowestHpEnemyUnit);
                            } else if (canAttack(unit, closestEnemyUnit) && unit.getGroundWeaponCooldown() <= time) {
                                setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                setMoveOrder(unit, closestEnemyUnitPosition);
                            }
                            break;

                        case 6:
                            if (canAttack(unit, lowestHpEnemyUnit) && unit.getGroundWeaponCooldown() <= time) {
                                setAttackOrder(unit, lowestHpEnemyUnit);
                            } else {
                                setMoveOrder(unit, lowestHpEnemyUnitPosition);
                            }
                            break;

                        case 7:
                            if (canAttack(unit, lowestHpEnemyUnit) && unit.getGroundWeaponCooldown() <= time) {
                                setAttackOrder(unit, lowestHpEnemyUnit);
                            } else if (canAttack(unit, closestEnemyUnit) && unit.getGroundWeaponCooldown() <= time) {
                                setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                if (canUnitBeAttackedByEnemy(unit, jnibwapi) && canAttack(unit, closestEnemyUnit)) {
                                    setMoveOrder(unit, runAwayPosition);
                                } else {
                                    setMoveOrder(unit, closestEnemyUnitPosition);
                                }
                            }
                            break;

                        case 8:
                            if (canAttack(unit, closestEnemyUnit)) {
                                setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                setMoveOrder(unit, closestEnemyUnitPosition);
                            }
                            break;

                        case 9:
                            if (canAttack(unit, closestEnemyUnit)) {
                                setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                setMoveOrder(unit, lowestHpEnemyUnitPosition);
                            }
                            break;

                        case 10:
                            if (canAttack(unit, closestEnemyUnit) && unit.getGroundWeaponCooldown() <= time) {
                                setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                setMoveOrder(unit, runAwayPosition);
                            }
                            break;

                        case 11:
                            if (canAttack(unit, closestEnemyUnit) && unit.getGroundWeaponCooldown() <= time) {
                                setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                setMoveOrder(unit, closestEnemyUnitPosition);
                            }
                            break;

                        case 12:
                            if (canAttack(unit, lowestHpEnemyUnit) && unit.getGroundWeaponCooldown() <= time) {
                                setAttackOrder(unit, lowestHpEnemyUnit);
                            } else {
                                setMoveOrder(unit, lowestHpEnemyUnitPosition);
                            }
                            break;

                        case 13:
                            if (canAttack(unit, closestEnemyUnit) && unit.getGroundWeaponCooldown() <= time) {
                                setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                if (canUnitBeAttackedByEnemy(unit, jnibwapi) && canAttack(unit, closestEnemyUnit)) {
                                    setMoveOrder(unit, runAwayPosition);
                                } else {
                                    setMoveOrder(unit, closestEnemyUnitPosition);
                                }
                            }
                            break;
                    }

                    if (unit.getOrder() == null) {
                        throw new NullPointerException("Unit order cannot be null");
                    }
                }
            }
        }
    }

    private Unit getClosestEnemyUnit(Unit unit, JNIBWAPI jnibwapi) {
        double minDistance = Double.MAX_VALUE;
        Unit closestEnemyUnit = null;
        Collection<Unit> allUnits = jnibwapi.getAllUnits();
        for (Unit possibleEnemyUnit : allUnits) {
            if (possibleEnemyUnit.getHitPoints() > 0 && possibleEnemyUnit.getPlayer().isEnemy()) {
                double distance = unit.getDistance(possibleEnemyUnit);
                if (distance < minDistance) {
                    closestEnemyUnit = possibleEnemyUnit;
                    minDistance = distance;
                }
            }
        }
        return closestEnemyUnit;
    }

    private Unit getLowestHpEnemyUnit(JNIBWAPI jnibwapi) {
        double minHp = Double.MAX_VALUE;
        Unit lowestHpEnemyUnit = null;
        for (Unit possibleEnemyUnit : jnibwapi.getAllUnits()) {
            double hp = possibleEnemyUnit.getHitPoints() + possibleEnemyUnit.getShields();
            if (hp > 0 && hp < minHp && possibleEnemyUnit.getPlayer().isEnemy()) {
                lowestHpEnemyUnit = possibleEnemyUnit;
                minHp = hp;
            }
        }
        return lowestHpEnemyUnit;
    }

    private Position getRunawayPosition(Unit unit, JNIBWAPI jnibwapi, Position closestEnemyUnitPosition) {
        Position runAwayPosition = getRunAwayPosition(unit, jnibwapi);
        if (runAwayPosition == null || runAwayPosition.equals(unit.getPosition())) {
            runAwayPosition = getRunAwayPosition(unit, closestEnemyUnitPosition);
        }
        if (runAwayPosition == null || runAwayPosition.equals(unit.getPosition())) {
            runAwayPosition = new Position(0, 0);
        }
        if (runAwayPosition == null || runAwayPosition.equals(unit.getPosition())) {
            runAwayPosition = new Position(0, jnibwapi.getMap().getSize().getY(PIXEL));
        }
        if (runAwayPosition == null || runAwayPosition.equals(unit.getPosition())) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return runAwayPosition;
    }

    private boolean canAttack(Unit unit, Unit enemyUnit) {
        WeaponType weaponType = unit.getType().getGroundWeapon();
        double distance = unit.getDistance(enemyUnit);
        return distance <= weaponType.getMaxRange() && distance >= weaponType.getMinRange();
    }

    private void setAttackOrder(Unit unit, Unit enemyUnit) {
       if(attackOrders.containsKey(unit)) {
           Unit attackOrder = attackOrders.get(unit);
           if (!Objects.equals(attackOrder, enemyUnit)) {
               unit.attack(enemyUnit, false);
               attackOrders.put(unit, enemyUnit);
               moveOrders.remove(unit);
           }
       }
    }

    private void setMoveOrder(Unit unit, Position position) {
        if(moveOrders.containsKey(unit)) {
            Position moverOrder = moveOrders.get(unit);
            if (!Objects.equals(moverOrder, position)) {
                unit.move(position, true);
                moveOrders.put(unit, position);
                attackOrders.remove(unit);
            }
        }
    }

    private boolean canUnitBeAttackedByEnemy(Unit unit, JNIBWAPI jnibwapi) {
        boolean canUnitBeAttackedByEnemy = false;
        for (Iterator<Unit> iterator = jnibwapi.getAllUnits().iterator(); iterator.hasNext() && !canUnitBeAttackedByEnemy; ) {
            Unit possibleEnemyUnit = iterator.next();
            if (possibleEnemyUnit.getPlayer().isEnemy() && possibleEnemyUnit.getHitPoints() > 0) {
                double distance = unit.getDistance(possibleEnemyUnit);
                double range = unit.getType().getGroundWeapon().getMaxRange();
                if (range >= distance) {
                    canUnitBeAttackedByEnemy = true;
                }
            }
        }
        return canUnitBeAttackedByEnemy;
    }

    private Position getRunAwayPosition(Unit unit, JNIBWAPI jnibwapi) {
        double sumX = 0;
        double sumY = 0;
        int num = 0;
        for (Unit possibleEnemyUnit : jnibwapi.getAllUnits()) {
            if (possibleEnemyUnit.getHitPoints() > 0 && possibleEnemyUnit.getPlayer().isEnemy()) {
                Position position = possibleEnemyUnit.getPosition();
                sumX += position.getX(PIXEL);
                sumY += position.getY(PIXEL);
                num++;
            }
        }
        Position averagePosition = new Position((int) (sumX / num), (int) (sumY / num));
        return getRunAwayPosition(unit, averagePosition);
    }

    private Position getRunAwayPosition(Unit unit, Position position) {
        double enemyPositionX = position.getX(PIXEL);
        double enemyPositionY = position.getY(PIXEL);
        double positionX = unit.getPosition().getX(PIXEL);
        double positionY = unit.getPosition().getY(PIXEL);
        Position runAwayPosition = new Position((int) (enemyPositionX - positionX), (int) (enemyPositionY - positionY));
        double distance = runAwayPosition.getPDistance(position);
        double speed = unit.getType().getTopSpeed();
        if (Double.isFinite(distance) && distance > 0) {
            if (distance < speed) {
                double multiplier = speed / distance;
                runAwayPosition = new Position((int) (enemyPositionX * multiplier - positionX), (int) (enemyPositionY * multiplier - positionY));
            }
        } else {
            runAwayPosition = null;
        }
        return runAwayPosition;
    }
}