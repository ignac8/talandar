package player;

import bwmcts.sparcraft.GameState;
import bwmcts.sparcraft.Position;
import bwmcts.sparcraft.Unit;
import bwmcts.sparcraft.UnitAction;
import jnibwapi.JNIBWAPI;
import neuralnetwork.NeuralNetwork;

import java.util.HashMap;
import java.util.List;

public class NeuralNetworkPlayer extends MyPlayer {

    NeuralNetwork neuralNetwork;

    public NeuralNetworkPlayer(int id, JNIBWAPI jnibwapi) {
        super(id, jnibwapi);
    }

    public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    @Override
    public void getMoves(GameState state, HashMap<Integer, List<UnitAction>> unitActions, List<UnitAction> finalUnitActions) {
        finalUnitActions.clear();
        for (int unitIndex = 0; unitIndex < unitActions.size(); unitIndex++) {
            List<UnitAction> possibleUnitActions = unitActions.get(unitIndex);
            Unit closestEnemyUnit = state.getClosestEnemyUnit(playerId, unitIndex);
            Position closestEnemyUnitPosition = closestEnemyUnit.getPosition();

            UnitAction unitAction = getExtremeActions(getAllAttackActions(possibleUnitActions), closestEnemyUnitPosition).closestAction;
            if (unitAction == null) {
                unitAction = getExtremeActions(getAllMoveActions(possibleUnitActions), closestEnemyUnitPosition).closestAction;
            }
            finalUnitActions.add(unitAction);
        }
    }
}
