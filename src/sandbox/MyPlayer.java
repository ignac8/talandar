package sandbox;


import bwmcts.sparcraft.AnimationFrameData;
import bwmcts.sparcraft.GameState;
import bwmcts.sparcraft.PlayerProperties;
import bwmcts.sparcraft.Position;
import bwmcts.sparcraft.Unit;
import bwmcts.sparcraft.UnitAction;
import bwmcts.sparcraft.UnitActionTypes;
import bwmcts.sparcraft.UnitProperties;
import bwmcts.sparcraft.WeaponProperties;
import bwmcts.sparcraft.players.Player;
import jnibwapi.JNIBWAPI;

import java.util.HashMap;
import java.util.List;

public class MyPlayer extends Player {

    public MyPlayer(int id, JNIBWAPI jnibwapi) {
        super(id);
        jnibwapi.loadTypeData();
        AnimationFrameData.Init();
        PlayerProperties.Init();
        WeaponProperties.Init(jnibwapi);
        UnitProperties.Init(jnibwapi);
    }

    @Override
    public void getMoves(GameState state, HashMap<Integer, List<UnitAction>> unitActions, List<UnitAction> finalUnitActions) {
        finalUnitActions.clear();
        for (int unitIndex = 0; unitIndex < unitActions.size(); unitIndex++) {
            List<UnitAction> possibleUnitActions = unitActions.get(unitIndex);
            Unit myUnit = state.getUnit(playerId, unitIndex);
            Unit closestEnemyUnit = state.getClosestEnemyUnit(playerId, unitIndex);

            UnitAction unitAction = attackClosestEnemyUnit(closestEnemyUnit, possibleUnitActions);
            if (unitAction == null) {
                unitAction = moveIntoClosestEnemyUnit(closestEnemyUnit, possibleUnitActions);
            } else if (unitAction.getType() == UnitActionTypes.RELOAD && myUnit.canBeAttackedByUnit(closestEnemyUnit, state.getTime())) {
                unitAction = moveAwayFromClosestEnemyUnit(closestEnemyUnit, possibleUnitActions);
            }
            finalUnitActions.add(unitAction);
        }
        finalUnitActions = finalUnitActions;


    }

    private UnitAction moveIntoClosestEnemyUnit(Unit closestEnemyUnit, List<UnitAction> possibleUnitActions) {
        UnitAction bestUnitAction = null;
        double minDistance = Double.MAX_VALUE;
        Position closestEnemyUnitPosition = closestEnemyUnit.getPosition();

        for (UnitAction possibleUnitAction : possibleUnitActions) {
            if (possibleUnitAction.getType().equals(UnitActionTypes.MOVE)) {
                double possibleDistance = closestEnemyUnitPosition.getDistanceSq(possibleUnitAction.getPosition());
                if (possibleDistance < minDistance) {
                    minDistance = possibleDistance;
                    bestUnitAction = possibleUnitAction;
                }
            }
        }
        return bestUnitAction;
    }

    private UnitAction moveAwayFromClosestEnemyUnit(Unit closestEnemyUnit, List<UnitAction> possibleUnitActions) {
        UnitAction bestUnitAction = null;
        double maxDistance = Double.MIN_VALUE;
        Position closestEnemyUnitPosition = closestEnemyUnit.getPosition();

        for (UnitAction possibleUnitAction : possibleUnitActions) {
            if (possibleUnitAction.getType().equals(UnitActionTypes.MOVE)) {
                double possibleDistance = closestEnemyUnitPosition.getDistanceSq(possibleUnitAction.getPosition());
                if (possibleDistance > maxDistance) {
                    maxDistance = possibleDistance;
                    bestUnitAction = possibleUnitAction;
                }
            }
        }
        return bestUnitAction;
    }

    private UnitAction attackClosestEnemyUnit(Unit closestEnemyUnit, List<UnitAction> possibleUnitActions) {
        UnitAction bestUnitAction = null;
        double minDistance = Double.MAX_VALUE;
        Position closestEnemyUnitPosition = closestEnemyUnit.getPosition();

        for (UnitAction possibleUnitAction : possibleUnitActions) {
            if (possibleUnitAction.getType().equals(UnitActionTypes.ATTACK) || possibleUnitAction.getType().equals(UnitActionTypes.RELOAD)) {
                double possibleDistance = closestEnemyUnitPosition.getDistanceSq(possibleUnitAction.getPosition());
                if (possibleDistance < minDistance) {
                    minDistance = possibleDistance;
                    bestUnitAction = possibleUnitAction;
                }
            }
        }
        return bestUnitAction;
    }


}
