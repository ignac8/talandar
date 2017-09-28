package player;


import jnibwapi.types.UnitType;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.neuron.CalculableNeuron;
import neuralnetwork.neuron.InputNeuron;
import simulation.GameState;
import simulation.Position;
import simulation.Unit;
import simulation.order.AttackOrder;
import simulation.order.MoveOrder;

import java.util.List;
import java.util.Map;

import static java.lang.Double.MIN_VALUE;
import static java.lang.Math.sqrt;


public final class NeuralNetworkPlayer extends Player {

    private NeuralNetwork neuralNetwork;

    public NeuralNetworkPlayer(int id) {
        super(id);
    }

    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }

    public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    @Override
    public void giveOrders(GameState gameState) {
        for (Unit unit : gameState.getUnits().values()) {
            if (unit.getPlayerId() == playerId && unit.getHitPoints() > 0) {
                UnitType unitType = unit.getUnitType();

                List<InputNeuron> inputLayer = neuralNetwork.getInputLayer();

//                inputLayer.get(i++).setValue(getRemainingCooldown(unit, state));

//                inputLayer.get(i++).setValue(unit.getCurrentHP());
//                inputLayer.get(i++).setValue(getClosestUnit(unit, getEnemyUnits(state)).getCurrentHP());
//                inputLayer.get(i++).setValue(getLowestHPEnemyUnit(getEnemyUnits(state)).getCurrentHP());
//                inputLayer.get(i++).setValue(getAllHP(getMyUnits(state)));
//                inputLayer.get(i++).setValue(getAllHP(getEnemyUnits(state)));

//                inputLayer.get(i++).setValue(unit.getRange());
//                inputLayer.get(i++).setValue(getClosestUnit(unit, getEnemyUnits(state)).getRange());
//                inputLayer.get(i++).setValue(getLowestHPEnemyUnit(getEnemyUnits(state)).getRange());
//                inputLayer.get(i++).setValue(getAllRange(getMyUnits(state)));
//                inputLayer.get(i++).setValue(getAllRange(getEnemyUnits(state)));
//
//                inputLayer.get(i++).setValue(getLowestHPEnemyUnit(getEnemyUnits(state)).getDistanceSq(unit));
//                inputLayer.get(i++).setValue(getClosestUnit(unit, getEnemyUnits(state)).getDistanceSq(unit));
//                inputLayer.get(i++).setValue(getAllDistance(unit, getMyUnits(state)));
//                inputLayer.get(i++).setValue(getAllDistance(unit, getEnemyUnits(state)));

                Unit closestEnemyUnit = gameState.getClosestEnemyUnit(unit);
                UnitType closestEnemyUnitType = closestEnemyUnit.getUnitType();
                Unit lowestHpEnemyUnit = gameState.getLowestHpEnemyUnit(unit);
                Position position = unit.getPosition();
                int time = gameState.getTime();

                inputLayer.get(0).setValue(unit.getHitPoints());
                inputLayer.get(1).setValue(sqrt(unitType.getGroundWeapon().getMaxRange()));
                inputLayer.get(2).setValue(sqrt(closestEnemyUnitType.getGroundWeapon().getMaxRange()));
                inputLayer.get(3).setValue(sqrt(position.getDistance(closestEnemyUnit.getPosition())));
                inputLayer.get(4).setValue(sqrt(position.getDistance(lowestHpEnemyUnit.getPosition())));

//                for (int counter = 0; counter <= 5 * 2; counter += 2) {
//                    inputLayer.get(counter + 5).setValue(howMany(previousMaxIndexes, counter / 2));
//                    inputLayer.get(counter + 6).setValue(howMany(currentMaxIndexes, counter / 2));
//                }

                neuralNetwork.calculateOutput();

                int maxIndex = -1;
                double maxValue = MIN_VALUE;

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
                        if (unit.canAttack(lowestHpEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else if (unit.canAttack(closestEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, closestEnemyUnit.getPosition()));
                        }
                        break;

                    case 1:
                        Position runAwayPosition = unit.getRunAwayPosition(closestEnemyUnit);
                        unit.setOrder(new MoveOrder(unit, runAwayPosition));
                        break;

                    case 2:
                        if (unit.canAttack(lowestHpEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, lowestHpEnemyUnit.getPosition()));
                        }
                        break;

                    case 3:
                        if (unit.canAttack(lowestHpEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else if (unit.canAttack(closestEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, lowestHpEnemyUnit.getPosition()));
                        }
                        break;

                    case 4:
                        if (unit.canAttack(lowestHpEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else if (unit.canAttack(closestEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, unit.getRunAwayPosition(closestEnemyUnit)));
                        }
                        break;

                    case 5:
                        if (unit.canAttack(lowestHpEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else if (unit.canAttack(closestEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, closestEnemyUnit.getPosition()));
                        }
                        break;

                    case 6:
                        if (unit.canAttack(lowestHpEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, lowestHpEnemyUnit.getPosition()));
                        }
                        break;

                    case 7:
                        if (unit.canAttack(lowestHpEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else if (unit.canAttack(closestEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            if (gameState.canUnitBeAttackedByEnemy(unit) && unit.canAttack(closestEnemyUnit)) {
                                unit.setOrder(new MoveOrder(unit, unit.getRunAwayPosition(closestEnemyUnit)));
                            } else {
                                unit.setOrder(new MoveOrder(unit, closestEnemyUnit.getPosition()));
                            }
                        }
                        break;

                    case 8:
                        if (unit.canAttack(closestEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, closestEnemyUnit.getPosition()));
                        }
                        break;

                    case 9:
                        if (unit.canAttack(lowestHpEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, lowestHpEnemyUnit.getPosition()));
                        }
                        break;

                    case 10:
                        if (unit.canAttack(closestEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, lowestHpEnemyUnit.getPosition()));
                        }
                        break;

                    case 11:
                        if (unit.canAttack(closestEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, unit.getRunAwayPosition(closestEnemyUnit)));
                        }
                        break;

                    case 12:
                        if (unit.canAttack(closestEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, closestEnemyUnit.getPosition()));
                        }
                        break;

                    case 13:
                        if (unit.canAttack(lowestHpEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, lowestHpEnemyUnit.getPosition()));
                        }
                        break;

                    case 14:
                        if (unit.canAttack(closestEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            if (gameState.canUnitBeAttackedByEnemy(unit) && unit.canAttack(closestEnemyUnit)) {
                                unit.setOrder(new MoveOrder(unit, unit.getRunAwayPosition(closestEnemyUnit)));
                            } else {
                                unit.setOrder(new MoveOrder(unit, closestEnemyUnit.getPosition()));
                            }
                        }
                        break;
                }

                if (unit.getOrder() == null) {
                    throw new NullPointerException();
                }
                unit.setOutputId(maxIndex);
            }
        }
    }

    private int howMany(Map<Unit, Integer> map, Integer integer) {
        int sum = 0;
        for (Integer value : map.values()) {
            if (integer.equals(value)) {
                sum++;
            }
        }
        return sum;
    }


}