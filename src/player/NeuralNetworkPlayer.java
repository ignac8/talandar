package player;

import bwmcts.sparcraft.GameState;
import bwmcts.sparcraft.Position;
import bwmcts.sparcraft.Unit;
import bwmcts.sparcraft.UnitAction;
import jnibwapi.JNIBWAPI;
import neuralnetwork.NeuralNetwork;

import java.util.HashMap;
import java.util.List;

public final class NeuralNetworkPlayer extends MyPlayer {

    private NeuralNetwork neuralNetwork;

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

            UnitAction unitAction = getExtremeActions(getAllAttackActions(possibleUnitActions), closestEnemyUnitPosition).getClosestAction();
            if (unitAction == null) {
                unitAction = getExtremeActions(getAllMoveActions(possibleUnitActions), closestEnemyUnitPosition).getClosestAction();
            }
            finalUnitActions.add(unitAction);
        }
    }
}
