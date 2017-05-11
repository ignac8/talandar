package player;


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
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import static bwmcts.sparcraft.UnitActionTypes.*;
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

    protected List<UnitAction> getActionsWithType(List<UnitAction> possibleActions,
                                                  UnitActionTypes... possibleUnitActionTypes) {
        List<UnitAction> actionsWithType = new ArrayList<>();
        for (UnitAction action : possibleActions) {
            if (ArrayUtils.contains(possibleUnitActionTypes, action.getActionType())) {
                actionsWithType.add(action);
            }
        }
        return actionsWithType;
    }

    protected ExtremeActions getExtremeActions(List<UnitAction> possibleActions, Position unitPosition) {
        UnitAction closestAction = null;
        UnitAction furthestAction = null;
        int minDistance = MAX_VALUE;
        int maxDistance = MIN_VALUE;

        for (UnitAction action : possibleActions) {
            int possibleDistance = unitPosition.getDistanceSq(action.getPosition());
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

    protected UnitAction focusEnemyUnit(List<UnitAction> possibleActions, Position enemyUnitPosition) {
        UnitAction foundAction = null;
        int minDistance = MAX_VALUE;
        boolean foundAttackAction = false;

        for (int counter = 0; !foundAttackAction && counter < possibleActions.size(); counter++) {
            UnitAction action = possibleActions.get(counter);
            int possibleDistance = enemyUnitPosition.getDistanceSq(action.getPosition());
            if (action.moveType == ATTACK
                    && action.getPosition().equals(enemyUnitPosition) || action.moveType == RELOAD) {
                foundAttackAction = true;
                foundAction = action;
            } else if (action.moveType == MOVE
                    && possibleDistance < minDistance) {
                minDistance = possibleDistance;
                foundAction = action;
            }
        }

        return foundAction;
    }

    protected double getRemainingCooldown(Unit unit, GameState state) {
        int cooldown = unit.nextAttackActionTime() - state.getTime();
        return cooldown < 0 ? 0 : cooldown;
    }

    protected double getAllHP(Unit[] units) {
        double totalHP = 0;
        for (Unit unit : units) {
            if (unit != null) {
                totalHP += unit.getCurrentHP();
            }
        }
        return totalHP;
    }

    protected double getAllRange(Unit[] units) {
        double totalRange = 0;
        for (Unit unit : units) {
            if (unit != null) {
                totalRange += unit.getRange();
            }
        }
        return totalRange;
    }

    protected double getAllDistance(Unit fromUnit, Unit[] units) {
        double totalDistance = 0;
        for (Unit unit : units) {
            if (unit != null) {
                totalDistance += unit.getDistanceSq(fromUnit);
            }
        }
        return totalDistance;
    }

    protected Unit getLowestHPEnemyUnit(Unit[] units) {
        Unit lowestHPUnit = null;
        for (Unit currentUnit : units) {
            if (currentUnit != null && currentUnit.getCurrentHP() > 0 && (lowestHPUnit == null
                    || currentUnit.getCurrentHP() < lowestHPUnit.getCurrentHP())) {
                lowestHPUnit = currentUnit;
            }
        }
        return lowestHPUnit;
    }

    protected Unit getClosestUnit(Unit fromUnit, Unit[] units) {
        Unit closestUnit = null;
        int closestDistance = Integer.MAX_VALUE;
        for (Unit currentUnit : units) {
            if (currentUnit != null) {
                int currentDistance = currentUnit.getDistanceSq(fromUnit);
                if (currentDistance < closestDistance) {
                    closestUnit = currentUnit;
                    closestDistance = currentDistance;
                }
            }
        }
        return closestUnit;
    }

    protected Unit[] getMyUnits(GameState state) {
        return state.getAllUnits()[playerId];
    }

    protected Unit[] getEnemyUnits(GameState state) {
        return state.getAllUnits()[playerId ^ 1];
    }

}
