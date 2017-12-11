package player;

import jnibwapi.types.UnitType;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.neuron.CalculableNeuron;
import neuralnetwork.neuron.ConstantNeuron;
import player.executor.Executor;

import java.util.List;

import static java.lang.Double.MAX_VALUE;

public class NeuralNetworkPlayer<State, Unit, Position> extends Player<State, Unit, Position> {

    private NeuralNetwork neuralNetwork;

    public NeuralNetworkPlayer(Executor<State, Unit, Position> executor, int playerId) {
        super(executor, playerId);
    }

    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }

    public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public void giveOrders(State state) {
        for (Unit unit : executor.getUnits(state)) {
            if (executor.isPlayerUnit(unit, playerId) && executor.getHitPoints(unit) > 0) {
                UnitType unitType = executor.getUnitType(unit);

                List<ConstantNeuron> inputLayer = neuralNetwork.getInputLayer();

                Unit closestEnemyUnit = executor.getClosestEnemyUnit(unit, state);
                if (closestEnemyUnit != null) {
                    UnitType closestEnemyUnitType = executor.getUnitType(closestEnemyUnit);
                    Unit lowestHpEnemyUnit = executor.getLowestHpEnemyUnit(unit, state);
                    Position position = executor.getPosition(unit);
                    Position closestEnemyUnitPosition = executor.getPosition(closestEnemyUnit);
                    Position lowestHpEnemyUnitPosition = executor.getPosition(lowestHpEnemyUnit);
                    Position runAwayPosition = executor.getRunawayPosition(unit, state, closestEnemyUnitPosition);
                    double time = executor.getTime(state);

                    inputLayer.get(0).setValue(executor.getHitPoints(unit) + executor.getShields(unit));
                    inputLayer.get(1).setValue(unitType.getGroundWeapon().getMaxRange());
                    inputLayer.get(2).setValue(closestEnemyUnitType.getGroundWeapon().getMaxRange());
                    inputLayer.get(3).setValue(executor.getDistance(position, closestEnemyUnitPosition));
                    inputLayer.get(4).setValue(executor.getDistance(position, lowestHpEnemyUnitPosition));

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
                            if (executor.canAttack(unit, lowestHpEnemyUnit)) {
                                executor.setAttackOrder(unit, lowestHpEnemyUnit);
                            } else if (executor.canAttack(unit, closestEnemyUnit)) {
                                executor.setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                executor.setMoveOrder(unit, closestEnemyUnitPosition);
                            }
                            break;

                        case 1:
                            executor.setMoveOrder(unit, runAwayPosition);
                            break;

                        case 2:
                            if (executor.canAttack(unit, lowestHpEnemyUnit)) {
                                executor.setAttackOrder(unit, lowestHpEnemyUnit);
                            } else {
                                executor.setMoveOrder(unit, lowestHpEnemyUnitPosition);
                            }
                            break;

                        case 3:
                            if (executor.canAttack(unit, lowestHpEnemyUnit)) {
                                executor.setAttackOrder(unit, lowestHpEnemyUnit);
                            } else if (executor.canAttack(unit, closestEnemyUnit)) {
                                executor.setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                executor.setMoveOrder(unit, lowestHpEnemyUnitPosition);
                            }
                            break;

                        case 4:
                            if (executor.canAttack(unit, lowestHpEnemyUnit) && !executor.isOnWeaponCooldown(state, unit)) {
                                executor.setAttackOrder(unit, lowestHpEnemyUnit);
                            } else if (executor.canAttack(unit, closestEnemyUnit) && !executor.isOnWeaponCooldown(state, unit)) {
                                executor.setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                executor.setMoveOrder(unit, runAwayPosition);
                            }
                            break;

                        case 5:
                            if (executor.canAttack(unit, lowestHpEnemyUnit) && !executor.isOnWeaponCooldown(state, unit)) {
                                executor.setAttackOrder(unit, lowestHpEnemyUnit);
                            } else if (executor.canAttack(unit, closestEnemyUnit) && !executor.isOnWeaponCooldown(state, unit)) {
                                executor.setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                executor.setMoveOrder(unit, closestEnemyUnitPosition);
                            }
                            break;

                        case 6:
                            if (executor.canAttack(unit, lowestHpEnemyUnit) && !executor.isOnWeaponCooldown(state, unit)) {
                                executor.setAttackOrder(unit, lowestHpEnemyUnit);
                            } else {
                                executor.setMoveOrder(unit, lowestHpEnemyUnitPosition);
                            }
                            break;

                        case 7:
                            if (executor.canAttack(unit, lowestHpEnemyUnit) && !executor.isOnWeaponCooldown(state, unit)) {
                                executor.setAttackOrder(unit, lowestHpEnemyUnit);
                            } else if (executor.canAttack(unit, closestEnemyUnit) && !executor.isOnWeaponCooldown(state, unit)) {
                                executor.setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                if (executor.canUnitBeAttackedByEnemy(unit, state) && executor.canAttack(unit, closestEnemyUnit)) {
                                    executor.setMoveOrder(unit, runAwayPosition);
                                } else {
                                    executor.setMoveOrder(unit, closestEnemyUnitPosition);
                                }
                            }
                            break;

                        case 8:
                            if (executor.canAttack(unit, closestEnemyUnit)) {
                                executor.setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                executor.setMoveOrder(unit, closestEnemyUnitPosition);
                            }
                            break;

                        case 9:
                            if (executor.canAttack(unit, closestEnemyUnit)) {
                                executor.setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                executor.setMoveOrder(unit, lowestHpEnemyUnitPosition);
                            }
                            break;

                        case 10:
                            if (executor.canAttack(unit, closestEnemyUnit) && !executor.isOnWeaponCooldown(state, unit)) {
                                executor.setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                executor.setMoveOrder(unit, runAwayPosition);
                            }
                            break;

                        case 11:
                            if (executor.canAttack(unit, closestEnemyUnit) && !executor.isOnWeaponCooldown(state, unit)) {
                                executor.setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                executor.setMoveOrder(unit, closestEnemyUnitPosition);
                            }
                            break;

                        case 12:
                            if (executor.canAttack(unit, lowestHpEnemyUnit) && !executor.isOnWeaponCooldown(state, unit)) {
                                executor.setAttackOrder(unit, lowestHpEnemyUnit);
                            } else {
                                executor.setMoveOrder(unit, lowestHpEnemyUnitPosition);
                            }
                            break;

                        case 13:
                            if (executor.canAttack(unit, closestEnemyUnit) && !executor.isOnWeaponCooldown(state, unit)) {
                                executor.setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                if (executor.canUnitBeAttackedByEnemy(unit, state) && executor.canAttack(unit, closestEnemyUnit)) {
                                    executor.setMoveOrder(unit, runAwayPosition);
                                } else {
                                    executor.setMoveOrder(unit, closestEnemyUnitPosition);
                                }
                            }
                            break;

                        default:
                            if (executor.canAttack(unit, lowestHpEnemyUnit)) {
                                executor.setAttackOrder(unit, lowestHpEnemyUnit);
                            } else if (executor.canAttack(unit, closestEnemyUnit)) {
                                executor.setAttackOrder(unit, closestEnemyUnit);
                            } else {
                                executor.setMoveOrder(unit, closestEnemyUnitPosition);
                            }
                            break;
                    }
                }
            }
        }
    }

}