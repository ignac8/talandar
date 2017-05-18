package bwmcts.combat;


import bwmcts.sparcraft.AnimationFrameData;
import bwmcts.sparcraft.GameState;
import bwmcts.sparcraft.PlayerProperties;
import bwmcts.sparcraft.Unit;
import bwmcts.sparcraft.UnitAction;
import bwmcts.sparcraft.UnitActionTypes;
import bwmcts.sparcraft.UnitProperties;
import bwmcts.sparcraft.WeaponProperties;
import bwmcts.sparcraft.players.Player;
import bwmcts.uct.UCT;
import bwmcts.uct.flatguctcd.FlatGUCTCD;
import bwmcts.uct.guctcd.GUCTCD;
import bwmcts.uct.rguctcd.RGUCTCD;
import jnibwapi.JNIBWAPI;
import jnibwapi.util.BWColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UctLogic extends Player implements ICombatLogic {

    private UCT uct;
    //private HashMap<Integer,UnitAction> actions=new HashMap<Integer,UnitAction>();
    private int timeBudget;
    private List<List<Unit>> clusters;
    private HashMap<Integer, UnitAction> firstAttack = new HashMap<>();

    public UctLogic(JNIBWAPI bwapi, UCT uct, int timeBudget) {

        this.uct = uct;

        bwapi.loadTypeData();
        AnimationFrameData.Init();
        PlayerProperties.Init();
        WeaponProperties.Init(bwapi);
        UnitProperties.Init(bwapi);
        this.timeBudget = timeBudget;

    }

    @Override
    public void act(JNIBWAPI bwapi, int time) {

        try {
            GameState state = new GameState(bwapi);
            //state.print();
            List<UnitAction> move = new ArrayList<>();
            move = uct.search(state.clone(), timeBudget);
            if (uct instanceof GUCTCD) {
                clusters = ((GUCTCD) uct).getClusters();
            }
            System.out.println();
            executeActions(bwapi, state, move);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawClusters(JNIBWAPI bwapi, HashMap<Integer, List<Unit>> clusters) {

        if (clusters == null)
            return;

        for (Integer i : clusters.keySet()) {

            for (Unit u : clusters.get(i)) {

                bwapi.drawCircle(u.getPosition().getX(), u.getPosition().getY(), 6, getColor(i), false, false);

            }

        }

    }

    private int getColor(Integer i) {
        switch (i) {
            case 0:
                return BWColor.Cyan.getID();
            case 1:
                return BWColor.Green.getID();
            case 2:
                return BWColor.White.getID();
            case 3:
                return BWColor.Purple.getID();
            case 4:
                return BWColor.Orange.getID();
            case 5:
                return BWColor.Yellow.getID();
            case 6:
                return BWColor.Teal.getID();
            case 7:
                return BWColor.Red.getID();
            case 8:
                return BWColor.Blue.getID();
            default:
                return BWColor.Black.getID();
        }
    }

    private void executeActions(JNIBWAPI bwapi, GameState state, List<UnitAction> moves) {
        if (moves != null && !moves.isEmpty()) {
            for (UnitAction move : moves) {

                Unit ourUnit = state.getUnit(move.playerId, move.unitId);
                int player = ourUnit.player();
                int enemyPlayer = GameState.getEnemy(player);
                if (firstAttack.get(ourUnit.getId()) != null) {
                    if (bwapi.getUnit(firstAttack.get(ourUnit.getId()).moveIndex) == null) {
                        firstAttack.remove(ourUnit.getId());
                    }
                    if (bwapi.getUnit(ourUnit.getId()).isAttackFrame()) {
                        firstAttack.remove(ourUnit.getId());
                    }
                    if (bwapi.getUnit(ourUnit.getId()).getLastCommandFrame() + 10 < bwapi.getFrameCount()) {
                        firstAttack.remove(ourUnit.getId());
                    }

                }
                if (firstAttack.get(ourUnit.getId()) != null) {
                    continue;
                }

                if (bwapi.getUnit(ourUnit.getId()).isAttackFrame()) {
                    continue;
                }

                if (move.moveType == UnitActionTypes.ATTACK && bwapi.getUnit(ourUnit.getId()).getGroundWeaponCooldown() == 0) {
                    Unit enemyUnit = state.getUnit(enemyPlayer, move.moveIndex);

                    bwapi.attack(ourUnit.getId(), enemyUnit.getId());
                    firstAttack.put(ourUnit.getId(), move.clone());

                } else if (move.moveType == UnitActionTypes.MOVE) {
                    bwapi.move(ourUnit.getId(), move.pos().getX(), move.pos().getY());
                } else if (move.moveType == UnitActionTypes.HEAL) {
                    Unit ourOtherUnit = state.getUnit(player, move.moveIndex);

                    bwapi.rightClick(ourUnit.getId(), ourOtherUnit.getId());

                } else if (move.moveType == UnitActionTypes.RELOAD) {
                } else if (move.moveType == UnitActionTypes.PASS) {
                }
            }
        } else {
            System.out.println("---------------------NO MOVES----------------------------");

        }
    }

    public void getMoves(GameState state, HashMap<Integer, List<UnitAction>> moves, List<UnitAction> moveVec) {

        moveVec.clear();
        List<UnitAction> move = new ArrayList<>();
        move = uct.search(state.clone(), timeBudget);
        if (uct instanceof GUCTCD) {
            clusters = ((GUCTCD) uct).getClusters();
        }
        if (uct instanceof RGUCTCD) {
            clusters = ((RGUCTCD) uct).getClusters();
        }
        if (uct instanceof FlatGUCTCD) {
            clusters = ((FlatGUCTCD) uct).getClusters();
        }
        /*
		if (guctcd!=null){
			
			try{
				long start = System.currentTimeMillis();
				UPGMA upgmaPlayerA = new UPGMA(state.getAllUnits()[getId()], guctcd.getHpMulitplier(), 1);
				UPGMA upgmaPlayerB = new UPGMA(state.getAllUnits()[state.getEnemy(getId())], guctcd.getHpMulitplier(), 1);
				long end = System.currentTimeMillis();
				//move = guctcd.search(state, upgmaPlayerA, upgmaPlayerB, timeBudget - (end-start));
				move = guctcd.search(state, upgmaPlayerA, upgmaPlayerB, timeBudget);
			} catch (Exception e){
				
			}
		}
		*/
        for (UnitAction action : move)
            moveVec.add(action.clone());

    }

    public void drawUnitOneInfo(JNIBWAPI bwapi) {
        jnibwapi.Unit my = bwapi.getUnit(0);

        bwapi.drawText(0, 0, "isMoving: " + my.isMoving(), false);
        bwapi.drawText(0, 20, "isattacking: " + my.isAttacking(), false);
        bwapi.drawText(0, 40, "isattackframe: " + my.isAttackFrame(), false);
        bwapi.drawText(0, 60, "isacc: " + my.isAccelerating(), false);
        bwapi.drawText(0, 80, "isIdle: " + my.isIdle(), false);
        bwapi.drawText(0, 100, "isStartingAttack: " + my.isStartingAttack(), false);
    }

    public UCT getUct() {
        return uct;
    }

    public void setUct(UCT uct) {
        this.uct = uct;
    }

    public List<List<Unit>> getClusters() {
        return clusters;
    }

    public void setClusters(List<List<Unit>> clusters) {
        this.clusters = clusters;
    }

    public String toString() {

        return uct.toString();
    }

}
