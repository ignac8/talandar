package player;


import jnibwapi.types.UnitType;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.neuron.CalculableNeuron;
import neuralnetwork.neuron.ConstantNeuron;
import simulation.Position;
import simulation.SimulationState;
import simulation.Unit;
import simulation.order.AttackOrder;
import simulation.order.MoveOrder;

import java.util.List;
import java.util.Map;

import static java.lang.Double.MAX_VALUE;
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
    public void giveOrders(SimulationState simulationState) {
        for (Unit unit : simulationState.getUnits().values()) {
            if (unit.getPlayerId() == playerId && unit.getHitPoints() > 0) {
                UnitType unitType = unit.getUnitType();

                List<ConstantNeuron> inputLayer = neuralNetwork.getInputLayer();

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

                Unit closestEnemyUnit = simulationState.getClosestEnemyUnit(unit);
                UnitType closestEnemyUnitType = closestEnemyUnit.getUnitType();
                Unit lowestHpEnemyUnit = simulationState.getLowestHpEnemyUnit(unit);
                Position position = unit.getPosition();
                Position closestEnemyUnitPosition = closestEnemyUnit.getPosition();
                Position lowestHpEnemyUnitPosition = lowestHpEnemyUnit.getPosition();
                Position runAwayPosition = getRunawayPosition(unit, simulationState, closestEnemyUnitPosition);
                double time = simulationState.getTime();

                inputLayer.get(0).setValue(unit.getHitPoints());
                inputLayer.get(1).setValue(sqrt(unitType.getGroundWeapon().getMaxRange()));
                inputLayer.get(2).setValue(sqrt(closestEnemyUnitType.getGroundWeapon().getMaxRange()));
                inputLayer.get(3).setValue(sqrt(position.getDistance(closestEnemyUnitPosition)));
                inputLayer.get(4).setValue(sqrt(position.getDistance(lowestHpEnemyUnitPosition)));

//                for (int counter = 0; counter <= 5 * 2; counter += 2) {
//                    inputLayer.get(counter + 5).setValue(howMany(previousMaxIndexes, counter / 2));
//                    inputLayer.get(counter + 6).setValue(howMany(currentMaxIndexes, counter / 2));
//                }

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
                        if (unit.canAttack(lowestHpEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else if (unit.canAttack(closestEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, closestEnemyUnitPosition));
                        }
                        break;

                    case 1:
                        unit.setOrder(new MoveOrder(unit, runAwayPosition));
                        break;

                    case 2:
                        if (unit.canAttack(lowestHpEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, lowestHpEnemyUnitPosition));
                        }
                        break;

                    case 3:
                        if (unit.canAttack(lowestHpEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else if (unit.canAttack(closestEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, lowestHpEnemyUnitPosition));
                        }
                        break;

                    case 4:
                        if (unit.canAttack(lowestHpEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else if (unit.canAttack(closestEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, runAwayPosition));
                        }
                        break;

                    case 5:
                        if (unit.canAttack(lowestHpEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else if (unit.canAttack(closestEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, closestEnemyUnitPosition));
                        }
                        break;

                    case 6:
                        if (unit.canAttack(lowestHpEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, lowestHpEnemyUnitPosition));
                        }
                        break;

                    case 7:
                        if (unit.canAttack(lowestHpEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else if (unit.canAttack(closestEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            if (simulationState.canUnitBeAttackedByEnemy(unit) && unit.canAttack(closestEnemyUnit)) {
                                unit.setOrder(new MoveOrder(unit, runAwayPosition));
                            } else {
                                unit.setOrder(new MoveOrder(unit, closestEnemyUnitPosition));
                            }
                        }
                        break;

                    case 8:
                        if (unit.canAttack(closestEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, closestEnemyUnitPosition));
                        }
                        break;

                    case 9:
                        if (unit.canAttack(lowestHpEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, lowestHpEnemyUnitPosition));
                        }
                        break;

                    case 10:
                        if (unit.canAttack(closestEnemyUnit)) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, lowestHpEnemyUnitPosition));
                        }
                        break;

                    case 11:
                        if (unit.canAttack(closestEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, runAwayPosition));
                        }
                        break;

                    case 12:
                        if (unit.canAttack(closestEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, closestEnemyUnitPosition));
                        }
                        break;

                    case 13:
                        if (unit.canAttack(lowestHpEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, lowestHpEnemyUnit));
                        } else {
                            unit.setOrder(new MoveOrder(unit, lowestHpEnemyUnitPosition));
                        }
                        break;

                    case 14:
                        if (unit.canAttack(closestEnemyUnit) && unit.getCooldownTime() <= time) {
                            unit.setOrder(new AttackOrder(unit, closestEnemyUnit));
                        } else {
                            if (simulationState.canUnitBeAttackedByEnemy(unit) && unit.canAttack(closestEnemyUnit)) {
                                unit.setOrder(new MoveOrder(unit, runAwayPosition));
                            } else {
                                unit.setOrder(new MoveOrder(unit, closestEnemyUnitPosition));
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

    private Position getRunawayPosition(Unit unit, SimulationState simulationState, Position closestEnemyUnitPosition) {
        Position runAwayPosition = unit.getRunAwayPosition(simulationState);
        if (runAwayPosition == null || runAwayPosition.equals(unit.getPosition())) {
            runAwayPosition = unit.getRunAwayPosition(closestEnemyUnitPosition);
        }
        if (runAwayPosition == null || runAwayPosition.equals(unit.getPosition())) {
            if (playerId == 0) {
                runAwayPosition = new Position(0, 0);
            } else if (playerId == 1) {
                runAwayPosition = new Position(simulationState.getMaxX(), simulationState.getMaxY());
            }
        }
        if (runAwayPosition == null || runAwayPosition.equals(unit.getPosition())) {
            if (playerId == 0) {
                runAwayPosition = new Position(0, simulationState.getMaxY());
            } else if (playerId == 1) {
                runAwayPosition = new Position(simulationState.getMaxX(), 0);
            }
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