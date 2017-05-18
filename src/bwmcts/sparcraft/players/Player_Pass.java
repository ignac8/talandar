/**
 * This file is based on and translated from the open source project: Sparcraft
 * https://code.google.com/p/sparcraft/
 * author of the source: David Churchill
 **/
package bwmcts.sparcraft.players;

import bwmcts.sparcraft.Constants;
import bwmcts.sparcraft.GameState;
import bwmcts.sparcraft.Unit;
import bwmcts.sparcraft.UnitAction;
import bwmcts.sparcraft.UnitActionTypes;

import java.util.HashMap;
import java.util.List;

public class Player_Pass extends Player {

    private int _id = 0;
    private int enemy;

    public Player_Pass(int playerID) {
        _id = playerID;
        setID(playerID);
        enemy = GameState.getEnemy(_id);
    }

    public void getMoves(GameState state, HashMap<Integer, List<UnitAction>> moves, List<UnitAction> moveVec) {
        moveVec.clear();
        boolean foundUnitAction = false;
        int actionMoveIndex = 0;
        int furthestMoveIndex = 0;
        int furthestMoveDist = 0;
        int closestMoveIndex = 0;
        int actionDistance = Integer.MAX_VALUE;
        int closestMoveDist = Integer.MAX_VALUE;
        Unit ourUnit, closestUnit = null;
        List<UnitAction> actions;
        int dist = 0, bestMoveIndex = 0;
        UnitAction move;
        for (Integer u : moves.keySet()) {
            foundUnitAction = false;
            actionMoveIndex = 0;
            furthestMoveIndex = 0;
            furthestMoveDist = 0;
            closestMoveIndex = 0;
            actionDistance = Integer.MAX_VALUE;
            closestMoveDist = Integer.MAX_VALUE;

            ourUnit = (state.getUnit(_id, u));

            actions = moves.get(u);
            for (int m = 0; m < actions.size(); ++m) {
                move = actions.get(m);

                if (move.getType() == UnitActionTypes.MOVE) {
                    //Position ourDest = new Position(ourUnit.getPosition().getX() + Constants.Move_Dir[move.moveIndex][0],
                    //								 ourUnit.getPosition().getY() + Constants.Move_Dir[move.moveIndex][1]);
                    closestUnit = (ourUnit.canHeal() ? state.getClosestOurUnit(_id, u) : state.getClosestEnemyUnit(ourUnit.currentPosition(state._currentTime), enemy, Integer.MAX_VALUE, 0));
                    dist = closestUnit.getDistanceSq(ourUnit.getPosition().getX() + Constants.Move_DirX[move.moveIndex], ourUnit.getPosition().getY() + Constants.Move_DirY[move.moveIndex], state.getTime());

                    if (dist > furthestMoveDist) {
                        furthestMoveDist = dist;
                        furthestMoveIndex = m;
                    }

                    if (dist < closestMoveDist) {
                        closestMoveDist = dist;
                        closestMoveIndex = m;
                    }
                }
            }

            // the move we will be returning
            bestMoveIndex = furthestMoveIndex;

            moveVec.add(actions.get(bestMoveIndex));
        }
    }
}
