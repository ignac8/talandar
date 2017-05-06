/**
 * This file is based on and translated from the open source project: Sparcraft
 * https://code.google.com/p/sparcraft/
 * author of the source: David Churchill
 **/
package bwmcts.sparcraft.players;

import bwmcts.sparcraft.Constants;
import bwmcts.sparcraft.GameState;
import bwmcts.sparcraft.Position;
import bwmcts.sparcraft.Unit;
import bwmcts.sparcraft.UnitAction;
import bwmcts.sparcraft.UnitActionTypes;

import java.util.HashMap;
import java.util.List;

public class Player_AttackClosest extends Player {

    private int id = 0;

    public Player_AttackClosest(int playerID) {
        id = playerID;
        setID(playerID);
    }

    public void getMoves(GameState state, HashMap<Integer, List<UnitAction>> moves, List<UnitAction> moveVec) {
        moveVec.clear();
        for (int unitIndex = 0; unitIndex < moves.size(); unitIndex++) {
            boolean foundUnitAction = false;
            int actionMoveIndex = 0;
            int closestMoveIndex = 0;
            int actionDistance = Integer.MAX_VALUE;
            int closestMoveDist = Integer.MAX_VALUE;

            Unit ourUnit = state.getUnit(getId(), unitIndex);

            Unit closestUnit = ourUnit.canHeal() ? state.getClosestOurUnit(getId(), unitIndex) : state.getClosestEnemyUnit(id, unitIndex);
            for (int moveIndex = 0; moveIndex < moves.get(unitIndex).size(); moveIndex++) {
                UnitAction move = moves.get(unitIndex).get(moveIndex);

                if (move.getType() == UnitActionTypes.ATTACK) {
                    Unit target = state.getUnit(GameState.getEnemy(move.player()), move.index());
                    int dist = ourUnit.getDistanceSq(target, state.getTime());

                    if (dist < actionDistance) {
                        actionDistance = dist;
                        actionMoveIndex = moveIndex;
                        foundUnitAction = true;
                    }
                }
                if (move.getType() == UnitActionTypes.HEAL) {
                    Unit target = state.getUnit(move.player(), move.index());
                    int dist = ourUnit.getDistanceSq(target, state.getTime());

                    if (dist < actionDistance) {
                        actionDistance = dist;
                        actionMoveIndex = moveIndex;
                        foundUnitAction = true;
                    }
                } else if (move.getType() == UnitActionTypes.RELOAD) {
                    if (ourUnit.canAttackUnit(closestUnit, state.getTime())) {
                        closestMoveIndex = moveIndex;
                        break;
                    }
                } else if (move.getType() == UnitActionTypes.MOVE) {
                    Position ourDest = new Position(ourUnit.getPosition().getX() + Constants.Move_Dir[move.index()][0], ourUnit.getPosition().getY() + Constants.Move_Dir[move.index()][1]);
                    int dist = closestUnit.getDistanceSq(ourDest, state.getTime());

                    if (dist < closestMoveDist) {
                        closestMoveDist = dist;
                        closestMoveIndex = moveIndex;
                    }
                }
            }

            int bestMoveIndex = foundUnitAction ? actionMoveIndex : closestMoveIndex;

            moveVec.add(moves.get(unitIndex).get(bestMoveIndex));
        }
    }

    public String toString() {
        return "AttackClosest";
    }
}
