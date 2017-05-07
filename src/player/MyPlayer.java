package player;


import bwmcts.sparcraft.AnimationFrameData;
import bwmcts.sparcraft.PlayerProperties;
import bwmcts.sparcraft.Position;
import bwmcts.sparcraft.UnitAction;
import bwmcts.sparcraft.UnitActionTypes;
import bwmcts.sparcraft.UnitProperties;
import bwmcts.sparcraft.WeaponProperties;
import bwmcts.sparcraft.players.Player;
import jnibwapi.JNIBWAPI;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

public abstract class MyPlayer extends Player {

    protected MyPlayer(int id, JNIBWAPI jnibwapi) {
        super(id);
        jnibwapi.loadTypeData();
        AnimationFrameData.Init();
        PlayerProperties.Init();
        WeaponProperties.Init(jnibwapi);
        UnitProperties.Init(jnibwapi);
    }

    protected List<UnitAction> getAllMoveActions(List<UnitAction> possibleActions) {
        List<UnitAction> moveActions = new ArrayList<>();
        for (UnitAction action : possibleActions) {
            if (action.getActionType() == UnitActionTypes.MOVE) {
                moveActions.add(action);
            }
        }
        return moveActions;
    }

    protected List<UnitAction> getAllAttackActions(List<UnitAction> possibleActions) {
        List<UnitAction> attackActions = new ArrayList<>();
        for (UnitAction action : possibleActions) {
            if (action.getActionType() == UnitActionTypes.ATTACK || action.getActionType() == UnitActionTypes.RELOAD) {
                attackActions.add(action);
            }
        }
        return attackActions;
    }

    protected ExtremeActions getExtremeActions(List<UnitAction> possibleActions, Position closestEnemyUnitPosition) {
        UnitAction closestAction = null;
        UnitAction furthestAction = null;
        int minDistance = MAX_VALUE;
        int maxDistance = MIN_VALUE;

        for (UnitAction action : possibleActions) {
            int possibleDistance = closestEnemyUnitPosition.getDistanceSq(action.getPosition());
            if (possibleDistance > maxDistance) {
                maxDistance = possibleDistance;
                furthestAction = action;
            }
            if (possibleDistance < minDistance) {
                minDistance = possibleDistance;
                closestAction = action;
            }
        }
        return new ExtremeActions(closestAction, furthestAction);
    }
}
