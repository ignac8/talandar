package player;

import bwmcts.sparcraft.GameState;
import bwmcts.sparcraft.Position;
import bwmcts.sparcraft.Unit;
import bwmcts.sparcraft.UnitAction;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.neuron.CalculableNeuron;
import neuralnetwork.neuron.InputNeuron;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static bwmcts.sparcraft.UnitActionTypes.ATTACK;
import static bwmcts.sparcraft.UnitActionTypes.MOVE;
import static bwmcts.sparcraft.UnitActionTypes.RELOAD;
import static java.lang.Double.MIN_VALUE;
import static java.lang.Math.sqrt;


public final class NeuralNetworkPlayer extends JarcraftPlayer {

    private final ConcurrentHashMap<Unit, Integer> maxIndexes = new ConcurrentHashMap<>();
    private NeuralNetwork neuralNetwork;

    public NeuralNetworkPlayer(int id) {
        super(id);
    }

    public ConcurrentHashMap<Unit, Integer> getMaxIndexes() {
        return maxIndexes;
    }

    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }

    public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }


    @Override
    public void getMoves(GameState state, HashMap<Integer, List<UnitAction>> unitActions, List<UnitAction> finalUnitActions) {
        finalUnitActions.clear();
        maxIndexes.clear();


        for (int unitIndex = 0; unitIndex < unitActions.size(); unitIndex++) {
            Unit currentUnit = state.getUnit(playerId, unitIndex);
            if (currentUnit != null) {
                List<InputNeuron> inputLayer = neuralNetwork.getInputLayer();
                int i = 0;

//                inputLayer.get(i++).setValue(getRemainingCooldown(currentUnit, state));

//                inputLayer.get(i++).setValue(currentUnit.getCurrentHP());
//                inputLayer.get(i++).setValue(getClosestUnit(currentUnit, getEnemyUnits(state)).getCurrentHP());
//                inputLayer.get(i++).setValue(getLowestHPEnemyUnit(getEnemyUnits(state)).getCurrentHP());
//                inputLayer.get(i++).setValue(getAllHP(getMyUnits(state)));
//                inputLayer.get(i++).setValue(getAllHP(getEnemyUnits(state)));

//                inputLayer.get(i++).setValue(currentUnit.getRange());
//                inputLayer.get(i++).setValue(getClosestUnit(currentUnit, getEnemyUnits(state)).getRange());
//                inputLayer.get(i++).setValue(getLowestHPEnemyUnit(getEnemyUnits(state)).getRange());
//                inputLayer.get(i++).setValue(getAllRange(getMyUnits(state)));
//                inputLayer.get(i++).setValue(getAllRange(getEnemyUnits(state)));
//
//                inputLayer.get(i++).setValue(getLowestHPEnemyUnit(getEnemyUnits(state)).getDistanceSq(currentUnit));
//                inputLayer.get(i++).setValue(getClosestUnit(currentUnit, getEnemyUnits(state)).getDistanceSq(currentUnit));
//                inputLayer.get(i++).setValue(getAllDistance(currentUnit, getMyUnits(state)));
//                inputLayer.get(i++).setValue(getAllDistance(currentUnit, getEnemyUnits(state)));

                inputLayer.get(0).setValue(currentUnit.getCurrentHP());
                inputLayer.get(1).setValue(sqrt(currentUnit.getRange()));
                inputLayer.get(2).setValue(sqrt(getClosestUnit(currentUnit, getEnemyUnits(state)).getRange()));
                inputLayer.get(3).setValue(sqrt(getClosestUnit(currentUnit, getEnemyUnits(state)).getDistanceSq(currentUnit)));
                inputLayer.get(4).setValue(sqrt(getLowestHPEnemyUnit(getEnemyUnits(state)).getDistanceSq(currentUnit)));

                neuralNetwork.calculateOutput();

                int maxIndex = -1;
                double maxValue = MIN_VALUE;

                List<CalculableNeuron> outputLayer = neuralNetwork.getOutputLayer();
                for (int j = 0; j < outputLayer.size(); j++) {
                    CalculableNeuron neuron = outputLayer.get(j);
                    if (neuron.getValue() >= maxValue) {
                        maxIndex = j;
                        maxValue = neuron.getValue();
                    }
                }

                List<UnitAction> possibleUnitActions = unitActions.get(unitIndex);

                Unit closestEnemyUnit = state.getClosestEnemyUnit(playerId, unitIndex);
                Position closestEnemyUnitPosition = closestEnemyUnit.getPosition();

                Unit lowestHPUnit = getLowestHPEnemyUnit(getEnemyUnits(state));
                Position lowestHPEnemyUnitPosition = lowestHPUnit.getPosition();

                UnitAction unitAction = null;

                switch (maxIndex) {
                    case 0:
                        unitAction = getExtremeActions(
                                getActionsWithType(possibleUnitActions, RELOAD, ATTACK), closestEnemyUnitPosition)
                                .getClosestAction();
                        if (unitAction == null) {
                            unitAction = getExtremeActions(
                                    getActionsWithType(possibleUnitActions, MOVE), closestEnemyUnitPosition)
                                    .getClosestAction();
                        }
                        break;

                    case 1:
                        unitAction = getExtremeActions(
                                getActionsWithType(possibleUnitActions, MOVE), closestEnemyUnitPosition)
                                .getFurthestAction();
                        break;

                    case 2:
                        unitAction = focusEnemyUnit(getActionsWithType(possibleUnitActions, ATTACK, MOVE, RELOAD),
                                lowestHPEnemyUnitPosition);
                        break;

                    case 3:
                        unitAction = getExtremeActions(
                                getActionsWithType(possibleUnitActions, RELOAD, ATTACK), lowestHPEnemyUnitPosition)
                                .getClosestAction();
                        if (unitAction == null) {
                            unitAction = getExtremeActions(
                                    getActionsWithType(possibleUnitActions, MOVE), lowestHPEnemyUnitPosition)
                                    .getClosestAction();
                        }
                        break;

                    case 4:
                        unitAction = getExtremeActions(
                                getActionsWithType(possibleUnitActions, ATTACK), closestEnemyUnitPosition)
                                .getClosestAction();
                        if (unitAction == null) {
                            unitAction = getExtremeActions(
                                    getActionsWithType(possibleUnitActions, MOVE), closestEnemyUnitPosition)
                                    .getFurthestAction();
                        }
                        break;

                    case 5:
                        unitAction = getExtremeActions(
                                getActionsWithType(possibleUnitActions, ATTACK), closestEnemyUnitPosition)
                                .getClosestAction();
                        if (unitAction == null) {
                            unitAction = getExtremeActions(
                                    getActionsWithType(possibleUnitActions, MOVE), closestEnemyUnitPosition)
                                    .getClosestAction();
                        }
                        break;

                    case 6:
                        unitAction = getExtremeActions(
                                getActionsWithType(possibleUnitActions, ATTACK), lowestHPEnemyUnitPosition)
                                .getClosestAction();
                        if (unitAction == null) {
                            unitAction = getExtremeActions(
                                    getActionsWithType(possibleUnitActions, MOVE), lowestHPEnemyUnitPosition)
                                    .getClosestAction();
                        }
                        break;

                    case 7:
                        unitAction = getExtremeActions(
                                getActionsWithType(possibleUnitActions, ATTACK), closestEnemyUnitPosition)
                                .getClosestAction();
                        if (unitAction == null) {
                            if (currentUnit.canBeAttackedByUnits(getEnemyUnits(state), state.getCurrentTime())
                                    && currentUnit.canAttackUnit(closestEnemyUnit, state.getCurrentTime())) {
                                unitAction = getExtremeActions(
                                        getActionsWithType(possibleUnitActions, MOVE), closestEnemyUnitPosition)
                                        .getFurthestAction();
                            } else {
                                unitAction = getExtremeActions(
                                        getActionsWithType(possibleUnitActions, MOVE), closestEnemyUnitPosition)
                                        .getClosestAction();
                            }
                        }
                        break;
                }

                if (unitAction == null) {
                    throw new NullPointerException();
                }

                finalUnitActions.add(unitAction);
                maxIndexes.put(currentUnit, maxIndex);

            }
        }
    }
}