/**
 * This file is based on and translated from the open source project: Sparcraft
 * https://code.google.com/p/sparcraft/
 * author of the source: David Churchill
 **/
package bwmcts.sparcraft;

import jnibwapi.JNIBWAPI;
import jnibwapi.types.UnitType;
import jnibwapi.types.UnitType.UnitTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class GameState {

    private int[] numUnits = new int[Constants.Num_Players];
    private int currentTime;
    private Unit[][] units = new Unit[Constants.Num_Players][Constants.Max_Moves];
    private int[][] unitIndex = new int[Constants.Num_Players][Constants.Max_Moves];
    private List<Unit> neutralUnits;
    private int[] prevNumUnits = new int[Constants.Num_Players];

    private float[] totalLTD = new float[Constants.Num_Players];
    private float[] totalSumSQRT = new float[Constants.Num_Players];


    private int[][] closestMoveIndex = new int[Constants.Num_Players][Constants.Max_Moves];
    private Map map;


    public GameState() {
        map = null;
        currentTime = 0;

        for (int u = 0; u < Constants.Max_Moves; u++) {
            unitIndex[0][u] = u;
            unitIndex[1][u] = u;
        }
    }

    public GameState(String filename) {
    }


    public GameState(JNIBWAPI bwapi) {

        //Update PlayerProperties
        PlayerProperties.props[0] = new PlayerProperties(bwapi.getPlayer(0));
        PlayerProperties.props[1] = new PlayerProperties(bwapi.getPlayer(1));

        units = new Unit[Constants.Num_Players][Constants.Max_Moves];
        int i = 0;
        for (jnibwapi.Unit u : bwapi.getMyUnits()) {
            //System.out.println(bwapi.getFrameCount()+" - "+u.getLastCommandFrame()+": "+u.getGroundWeaponCooldown()+": "+u.getAirWeaponCooldown());
            units[bwapi.getSelf().getID()][i] = new Unit(UnitProperties.Get(u.getTypeID()).type, new Position(u.getX(), u.getY()), u.getID(), u.getPlayerID(), u.getHitPoints() + u.getShields(), u.getEnergy(), bwapi.getFrameCount(), bwapi.getFrameCount());
            units[bwapi.getSelf().getID()][i].setUnitCooldown(bwapi, u);

            i++;
        }
        i = 0;
        for (jnibwapi.Unit u : bwapi.getEnemyUnits()) {

            //TODO
            //System.out.println(bwapi.getFrameCount()+" - "+u.getLastCommandFrame()+": "+u.getGroundWeaponCooldown()+": "+u.getAirWeaponCooldown());

            units[bwapi.getEnemies().iterator().next().getID()][i] = new Unit(UnitProperties.Get(u.getTypeID()).type, new Position(u.getX(), u.getY()), u.getID(), u.getPlayerID(), u.getHitPoints() + u.getShields(), u.getEnergy(), bwapi.getFrameCount(), bwapi.getFrameCount() + u.getGroundWeaponCooldown());

            i++;
        }

        //_maxUnits=Constants.Max_Moves;
        unitIndex = new int[Constants.Num_Players][Constants.Max_Moves];
        //TODO check!!!!
        //for (int u=0; u<_maxUnits; u++)
        for (int u = 0; u < Constants.Max_Moves; u++) {
            unitIndex[0][u] = u;
            unitIndex[1][u] = u;
        }
        neutralUnits = new ArrayList<>();

        numUnits = new int[2];
        numUnits[bwapi.getEnemies().iterator().next().getID()] = bwapi.getEnemyUnits().size();
        numUnits[bwapi.getSelf().getID()] = bwapi.getMyUnits().size();
        //numUnits=new int[]{bwapi.getMyUnits().size(),bwapi.getEnemyUnits().size()};
        prevNumUnits = new int[]{numUnits[0], numUnits[1]};

        totalLTD = new float[Constants.Num_Players];
        totalSumSQRT = new float[Constants.Num_Players];

        //_numMovements=new int[]{0,0};
        //_prevHPSum=new int[]{0,0};//TODO

        currentTime = bwapi.getFrameCount();

        //_sameHPFrames=0;


        calculateStartingHealth();
        this.map = new Map(bwapi.getMap());
        sortUnits();
    }

    public static int getEnemy(int player) {
        return (player + 1) % 2;
    }

    // checks to see if the unit array is full before adding a unit to the state
    private boolean checkFull(int player) {
        return true;
    }

    private boolean checkUniqueUnitIDs() {
        return true;
    }

    // misc functions
    public void finishedMoving() {
        //sort units
        for (int p = 0; p < Constants.Num_Players; p++) {
            if (prevNumUnits[p] <= 1) {
                prevNumUnits[p] = numUnits[p];
                continue;
            } else {
                Arrays.sort(units[p], 0, prevNumUnits[p]);
                prevNumUnits[p] = numUnits[p];
            }
        }

        //Update game time
        currentTime = Math.min(getUnit(0, 0).firstTimeFree(), getUnit(1, 0).firstTimeFree());

    }

    public boolean playerDead(int player) {
        if (numUnits(player) <= 0) {
            return true;
        }

        for (int u = 0; u < numUnits[player]; u++) {
            if (getUnit(player, u).damage > 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isTerminal() {
        if (playerDead(Players.Player_One.ordinal()) || playerDead(Players.Player_Two.ordinal())) {
            return true;
        }


        for (int p = 0; p < Constants.Num_Players; p++) {
            for (int u = 0; u < numUnits(p); u++) {
                // if any unit on any team is a mobile attacker
                if (getUnit(p, u).isMobile() && !getUnit(p, u).canHeal()) {
                    // there is no deadlock, so return false
                    return false;
                }
            }
        }

        // at this point we know everyone must be immobile, so check for attack deadlock
        Unit unit1, unit2;
        for (int u1 = 0; u1 < numUnits(Players.Player_One.ordinal()); u1++) {
            unit1 = getUnit(Players.Player_One.ordinal(), u1);

            for (int u2 = 0; u2 < numUnits(Players.Player_Two.ordinal()); u2++) {
                unit2 = getUnit(Players.Player_Two.ordinal(), u2);

                // if anyone can attack anyone else
                if (unit1.canAttackUnit(unit2, currentTime) || unit2.canAttackUnit(unit1, currentTime)) {
                    // then there is no deadlock
                    return false;
                }
            }
        }

        // if everyone is immobile and nobody can attack, then there is a deadlock
        return true;
    }

    // unit data functions
    public int numUnits(int player) {
        return numUnits[player];
    }

    public int prevNumUnits(int player) {
        return prevNumUnits[player];
    }

    public int numNeutralUnits() {
        return neutralUnits.size();
    }

    public int closestEnemyUnitDistance(Unit unit) {
        int enemyPlayer = getEnemy(unit.player());

        int closestDist = 0;

        for (int u = 0; u < numUnits(enemyPlayer); u++) {
            int dist = unit.getDistanceSq(getUnit(enemyPlayer, u), currentTime);

            if (dist > closestDist) {
                closestDist = dist;
            }
        }

        return closestDist;
    }

    // Unit functions
    public void sortUnits() {
        for (int p = 0; p < Constants.Num_Players; p++) {
            if (prevNumUnits[p] <= 1) {
                prevNumUnits[p] = numUnits[p];
                continue;
            } else {
                Arrays.sort(units[p], 0, prevNumUnits[p]);

                prevNumUnits[p] = numUnits[p];
            }
        }
    }

    public void addUnit(Unit u) throws Exception {
        checkFull(u.player());
        //System::checkSupportedUnitType(u.getType());

        // Calculate the unitId for this unit
        // This will just be the current total number of units in the state
        int unitID = numUnits[Players.Player_One.ordinal()] + numUnits[Players.Player_Two.ordinal()];

        // Set the unit and it's unitId
        u.setUnitID(unitID);

        units[u.player()][numUnits[u.player()]] = u;

        // Increment the number of units this player has
        numUnits[u.player()]++;
        prevNumUnits[u.player()]++;

        // And do the clean-up
        finishedMoving();
        calculateStartingHealth();

        if (!checkUniqueUnitIDs()) {
            throw new Exception("GameState has non-unique Unit getId values");
        }

    }

    public void addUnit(UnitType unitType, int playerID, Position pos) {
        checkFull(playerID);
        //System::checkSupportedUnitType(getType);

        // Calculate the unitId for this unit
        // This will just be the current total number of units in the state
        int unitID = numUnits[Players.Player_One.ordinal()] + numUnits[Players.Player_Two.ordinal()];

        // Set the unit and it's unitId
        units[playerID][numUnits[playerID]] = new Unit(unitType, playerID, pos);
        units[playerID][numUnits[playerID]].setUnitID(unitID);
        // Increment the number of units this player has
        numUnits[playerID]++;
        prevNumUnits[playerID]++;

        // And do the clean-up
        try {
            finishedMoving();
            calculateStartingHealth();
        } catch (Exception e) {
        }
    }

    public void addUnitWithID(Unit u) {
        checkFull(u.player());
        //  System::checkSupportedUnitType(u.getType());

        // Simply add the unit to the array
        units[u.player()][numUnits[u.player()]] = u;

        // Increment the number of units this player has
        numUnits[u.player()]++;
        prevNumUnits[u.player()]++;

        // And do the clean-up
        finishedMoving();
        calculateStartingHealth();
    }

    public void addNeutralUnit(Unit unit) {
        neutralUnits.add(unit);
    }

    public Unit getUnit(int player, int unitIndex) {
        return units[player][this.unitIndex[player][unitIndex]];
    }

    public Unit getUnitByID(int unitID) {
        for (int p = 0; p < Constants.Num_Players; p++) {
            for (int u = 0; u < numUnits(p); u++) {
                if (getUnit(p, u).getId() == unitID) {
                    return getUnit(p, u);
                }
            }
        }

        System.out.println("GameState Error: getUnitByID() Unit not found");
        return null;
    }

    //public Unit getUnit(int player, int unitIndex){return null;}
    public Unit getUnitByID(int player, int unitID) {
        for (int u = 0; u < numUnits(player); u++) {
            if (getUnit(player, u).getId() == unitID) {
                return getUnit(player, u);
            }
        }

        System.out.println("GameState Error: getUnitByID() Unit not found");
        return null;
    }

    public Unit getClosestEnemyUnit(int player, int unitIndex) {
        int enemyPlayer = getEnemy(player);
        Position myUnitPosition = getUnit(player, unitIndex).currentPosition(currentTime);

        int minDist = Integer.MAX_VALUE;
        int minUnitInd = 0;
        //int minUnitID=255;

        //Position currentPos = myUnit.currentPosition(currentTime);
        int distSq = 0;
        for (int u = 0; u < numUnits[enemyPlayer]; u++) {
            distSq = myUnitPosition.getDistanceSq(getUnit(enemyPlayer, u).currentPosition(currentTime));
            if ((distSq < minDist)) {
                minDist = distSq;
                minUnitInd = u;

            }

        }

        return getUnit(enemyPlayer, minUnitInd);
        //return getUnit(getEnemy(player),closestMoveIndex[player][unitIndex]);
    }

    public Unit getClosestEnemyUnit(Position myUnitPosition, int enemyPlayer, int minDist, int minUnitInd) {


        for (int u = 0; u < numUnits[enemyPlayer]; u++) {
            int distSq = myUnitPosition.getDistanceSq(getUnit(enemyPlayer, u).currentPosition(currentTime));
            if (distSq < minDist) {
                minDist = distSq;
                minUnitInd = u;
            }
        }

        return getUnit(enemyPlayer, minUnitInd);
    }

    public Unit getClosestOurUnit(int player, int unitIndex) {
        Unit myUnit = getUnit(player, unitIndex);

        int minDist = Integer.MAX_VALUE;
        int minUnitInd = 0;

        Position currentPos = myUnit.currentPosition(currentTime);

        for (int u = 0; u < numUnits[player]; u++) {
            if (u == unitIndex || getUnit(player, u).canHeal()) {
                continue;
            }

            //size_t distSq(myUnit.distSq(getUnit(enemyPlayer,u)));
            int distSq = currentPos.getDistanceSq(getUnit(player, u).currentPosition(currentTime));

            if (distSq < minDist) {
                minDist = distSq;
                minUnitInd = u;
            }
        }

        return getUnit(player, minUnitInd);
    }

    public Unit getUnitDirect(int player, int unit) {
        return units[player][unit];
    }

    public Unit getNeutralUnit(int u) {
        return neutralUnits.get(u);
    }

    public int getCurrentTime() {
        return currentTime;
    }

    // game time functions
    public void setTime(int time) {
        currentTime = time;
    }

    // evaluation functions
    public StateEvalScore eval(int player, EvaluationMethods evalMethod) {
        StateEvalScore score = new StateEvalScore(0, 0);
        int enemyPlayer = getEnemy(player);

        // if both players are dead, return 0
        if (playerDead(enemyPlayer) && playerDead(player)) {
            return new StateEvalScore(0, 0);
        }

        if (evalMethod == EvaluationMethods.LTD) {
            score = new StateEvalScore(evalLTD(player), 0);
        } else if (evalMethod == EvaluationMethods.LTD2) {
            score = new StateEvalScore(evalLTD2(player), 0);
        }

        if (score._val == 0) {
            return score;
        }

		/*int winBonus=0;

		if (playerDead(enemyPlayer) && !playerDead(player))
		{
			//winBonus = 500-getCurrentTime();
		}
		else if (playerDead(player) && !playerDead(enemyPlayer))
		{
			//winBonus = -100000;
		}*/

        return new StateEvalScore(score._val /*+ winBonus*/, score._numMoves);
    }

    // evaluate the state for _playerToMove
    private int evalLTD(int player) {
        return LTD(player) - LTD(getEnemy(player));
    }

    // evaluate the state for _playerToMove
    private int evalLTD2(int player) {
        return LTD2(player) - LTD2(getEnemy(player));
    }

    public int LTD(int player) {
        if (numUnits(player) == 0) {
            return 0;
        }

        float sum = 0;
        Unit unit;
        for (int u = 0; u < numUnits[player]; ++u) {
            unit = getUnit(player, u);

            sum += unit.getCurrentHP() * unit.dpf;
        }

        return (int) (1000 * sum / totalLTD[player]);
    }

    public int LTD2(int player) {
        if (numUnits(player) == 0) {
            return 0;
        }

        float sum = 0;

        for (int u = 0; u < numUnits(player); ++u) {
            Unit unit = getUnit(player, u);

            sum += Math.sqrt(unit.getCurrentHP()) * unit.dpf;
        }

        //int ret = (int)(1000 * sum / totalSumSQRT[player]);
        //return ret;
        return (int) (1000 * sum / totalSumSQRT[player]);
    }

    // unit hitpoint calculations, needed for LTD2 evaluation
    public void calculateStartingHealth() {
        Unit unit;
        for (int p = 0; p < Constants.Num_Players; p++) {
            float totalHP = 0;
            float totalSQRT = 0;

            for (int u = 0; u < numUnits[p]; u++) {
                /*
				if (getUnit(p, u) == null){
					System.out.println("p="+p+" u="+u);
					continue;
				}
				*/
                unit = getUnit(p, u);
                totalHP += unit.getMaxHP() * unit.dpf;
                totalSQRT += Math.sqrt(unit.getMaxHP()) * unit.dpf;
            }

            totalLTD[p] = totalHP;
            totalSumSQRT[p] = totalSQRT;
        }
    }

    public void setTotalLTD(float p1, float p2) {
    }

    public void setTotalLTD2(float p1, float p2) {
    }

    public float getTotalLTD(int player) {
        return 0;
    }

    public float getTotalLTD2(int player) {
        return 0;
    }

    // move related functions


    public void generateMoves(HashMap<Integer, List<UnitAction>> moves, int playerIndex) {
        moves.clear();

        // which is the enemy player
        int enemyPlayer = getEnemy(playerIndex);

        // make sure this player can move right now
        //int canMove=whoCanMove().getID();
	    /*if (whoCanMove().getID() == enemyPlayer)
	    {
	    	System.out.println("GameState Error - Called generateMoves() for a player that cannot currently move");
	        return;//throw new Exception("GameState Error - Called generateMoves() for a player that cannot currently move");
	    }*/

        // we are interested in all simultaneous moves
        // so return all units which can move at the same time as the first
        int firstUnitMoveTime = getUnit(playerIndex, 0).firstTimeFree();
        Unit unit;
        Unit enemyUnit;
        int moveDistance = 0;
        double timeUntilAttack = 0;
        for (int unitIndex = 0; unitIndex < numUnits[playerIndex]; unitIndex++) {
            // unit reference
            unit = getUnit(playerIndex, unitIndex);
            if (unit == null || unit.firstTimeFree() != firstUnitMoveTime) {
                break;
            }
            // if this unit can't move at the same time as the first
			/*if (unit.firstTimeFree() != firstUnitMoveTime)
			{
				// stop checking
				break;
			}*/
/*
			if (unit.previousActionTime() == currentTime && currentTime != 0)
			{
	            System.out.println("Previous Move Took 0 Time: " + unit.previousAction().moveString());
	            return;
			}
			*/
            ArrayList<UnitAction> actionTemp = new ArrayList<>();


            // generate attack moves
            if (unit.canAttackNow()) {

                for (int u = 0; u < numUnits[enemyPlayer]; u++) {
                    enemyUnit = getUnit(enemyPlayer, u);

                    if (unit.canAttackUnit(enemyUnit, currentTime)) {
                        actionTemp.add(new UnitAction(unitIndex, playerIndex, UnitActionTypes.ATTACK, u, enemyUnit.getPosition()));
                        //moves.add(UnitAction(unitIndex, playerIndex, UnitActionTypes::ATTACK, unit.getId()));
                    }
                }
            } else if (unit.canHealNow()) {
                for (int u = 0; u < numUnits[playerIndex]; u++) {
                    // units cannot heal themselves in broodwar
                    if (u == unitIndex) {
                        continue;
                    }

                    Unit ourUnit = getUnit(playerIndex, u);
                    if (ourUnit != null && ourUnit.isAlive()) {
                        if (unit.canHealTarget(ourUnit, currentTime)) {
                            actionTemp.add(new UnitAction(unitIndex, playerIndex, UnitActionTypes.HEAL, u, unit.getPosition()));
                            //moves.add(UnitAction(unitIndex, playerIndex, UnitActionTypes::HEAL, unit.getId()));
                        }
                    } else {
                        break;
                    }
                }
            }
            // generate the wait move if it can't attack yet
            else if (unit.unitType.getID() != UnitTypes.Terran_Medic.getID()) {
                actionTemp.add(new UnitAction(unitIndex, playerIndex, UnitActionTypes.RELOAD, 0, unit.getPosition()));
            }

            // generate movement moves
            if (unit.isMobile()) {
                // In order to not move when we could be shooting, we want to move for the minimum of:
                // 1) default move distance move time
                // 2) time until unit can attack, or if it can attack, the next cooldown
                timeUntilAttack = unit.nextAttackActionTime() - currentTime;
                // timeUntilAttack                 = ;

                // the default move duration
                // double defaultMoveDuration      = (double)Constants.Move_Distance / unit.speed();

                // if we can currently attack
                //double chosenTime  = Math.min(timeUntilAttack, defaultMoveDuration);

                // the chosen movement distance
                moveDistance = (int) (Math.min(timeUntilAttack == 0 ? unit.attackCoolDown : timeUntilAttack, (double) Constants.Move_Distance / unit.speed()) * unit.speed());
                //moveDistance      = (int) (Math.min(timeUntilAttack == 0 ? unit.attackCoolDown : timeUntilAttack, unit.moveCoolDown) * unit.speed());
                // DEBUG: If chosen move distance is ever 0, something is wrong
                if (moveDistance == 0) {
                    System.out.println("Move Action with distance 0 generated");
                    continue;
                }

                // we are only generating moves in the cardinal direction specified in common.h
                for (int d = 0; d < Constants.Num_Directions; d++) {
                    // the direction of this movement
                    //Position dir= new Position(Constants.Move_Dir[d][0], Constants.Move_Dir[d][1]);
	            
	                /*if (moveDistance == 0)
	                {
	                    System.out.printf("%lf %lf %lf\n", timeUntilAttack, defaultMoveDuration, chosenTime);
	                }*/

                    // the final destination position of the unit
                    Position dest = new Position(unit.getPosition().x + moveDistance * Constants.Move_DirX[d], unit.getPosition().y + moveDistance * Constants.Move_DirY[d]);

                    // if that poisition on the map is walkable
                    if (isWalkable(dest) || (unit.getUnitType().isFlyer() && isFlyable(dest))) {
                        // add the move to the MoveArray
                        actionTemp.add(new UnitAction(unitIndex, playerIndex, UnitActionTypes.MOVE, d, dest));
                    }
                }
            }

            // if no moves were generated for this unit, it must be issued a 'PASS' move
            if (actionTemp.isEmpty()) {
                actionTemp.add(new UnitAction(unitIndex, playerIndex, UnitActionTypes.PASS, 0, unit.getPosition()));
            }
            moves.put(unitIndex, actionTemp);
        }

    }

    public void makeMoves(List<UnitAction> moves) {
        if (moves.size() > 0) {
            //if (getUnit(moves.get(0).playerId,moves.get(0).unitId).firstTimeFree()!=currentTime)
            if (whoCanMove().ordinal() == getEnemy(moves.get(0).player())) {
                //throw new Exception("GameState Error - Called makeMove() for a player that cannot currently move");
                //System.out.print(" GameState Error - Called makeMove() for a player that cannot currently move ");
                return;
            }
        }
        UnitAction move;
        Unit ourUnit, enemyUnit;
        HashMap<Unit, Boolean> moved = new HashMap<>();
        for (int m = 0; m < moves.size(); m++) {
            //performUnitAction(moves.get(m));
            move = moves.get(m);
            ourUnit = getUnit(move.playerId, move.unitId);
            //int player		= ourUnit.player();
            if (moved.containsKey(ourUnit)) {
                continue;
            } else {
                moved.put(ourUnit, true);
            }

            if (move.moveType == UnitActionTypes.ATTACK) {
                //int enemyPlayer  =;
                enemyUnit = getUnit(getEnemy(move.playerId), move.moveIndex);
                //Unit & enemyUnit(getUnitByID(enemyPlayer ,move.moveIndex));

                // attack the unit
                ourUnit.attack(move, currentTime);

                // enemy unit takes damage if it is alive
                if (enemyUnit.isAlive()) {
                    enemyUnit.takeAttack(ourUnit);

                    // check to see if enemy unit died
                    if (!enemyUnit.isAlive()) {
                        // if it died, remove it
                        numUnits[enemyUnit.player()]--;
                    }
                }
            } else if (move.moveType == UnitActionTypes.MOVE) {
                //_numMovements[move.playerId]++;

                ourUnit.move(move, currentTime);
            } else if (move.moveType == UnitActionTypes.RELOAD) {
                ourUnit.waitUntilAttack(move, currentTime);
            } else if (move.moveType == UnitActionTypes.HEAL) {
                Unit ourOtherUnit = getUnit(move.playerId, move.moveIndex);

                // attack the unit
                ourUnit.heal(move, ourOtherUnit, currentTime);

                if (ourOtherUnit.isAlive()) {
                    ourOtherUnit.takeHeal(ourUnit);
                }
            } else if (move.moveType == UnitActionTypes.PASS) {
                ourUnit.pass(move, currentTime);
            }

        }
    }

    public Players whoCanMove() {
        if (getUnit(0, 0) == null || getUnit(1, 0) == null)
            return Players.Player_None;

        int p1Time = getUnit(0, 0).firstTimeFree();
        int p2Time = getUnit(1, 0).firstTimeFree();

        // if player one is to move first
        if (p1Time < p2Time) {
            return Players.Player_One;
        }
        // if player two is to move first
        else if (p1Time > p2Time) {
            return Players.Player_Two;
        } else {
            return Players.Player_Both;
        }
    }

    public boolean bothCanMove() {
        return getUnit(0, 0).firstTimeFree() == getUnit(1, 0).firstTimeFree();
    }

    public Map getMap() {
        return map;
    }

    // map-related functions
    public void setMap(Map map) throws Exception {
        this.map = map;

        // check to see if all units are on walkable tiles
        for (int p = 0; p < Constants.Num_Players; p++) {
            for (int u = 0; u < numUnits(p); u++) {
                Position pos = getUnit(p, u).getPosition();

                if (!isWalkable(pos)) {
                    throw new Exception("Unit is on non-walkable map tile: " + pos.toString());
                }
            }
        }

    }

    public boolean isWalkable(Position pos) {
        if (map != null) {
            return map.isWalkable(pos);
        }

        // if there is no map, then return true
        return true;
    }

    public boolean isFlyable(Position pos) {
        if (map != null) {
            return map.isFlyable(pos);
        }

        // if there is no map, then return true
        return true;
    }

    public Unit[][] getAllUnits() {
        return units;
    }

    // state i/o functions
    public void print() {


        System.out.printf("State - Time: %d\n", currentTime);

        for (int p = 0; p < Constants.Num_Players; p++) {
            for (int u = 0; u < numUnits[p]; u++) {
                Unit unit = getUnit(p, u);

                System.out.printf("  P%d %5d %5d    (%3d, %3d)     %s_%d\n", unit.player(), unit.getCurrentHP(), unit.firstTimeFree(), unit.x(), unit.y(), unit.name(), unit.unitId);
            }
        }
    }

    public GameState clone() {
        GameState s = new GameState();
        s.map = this.map.clone();

        s.units = new Unit[Constants.Num_Players][Constants.Max_Moves];

        s.unitIndex = new int[Constants.Num_Players][Constants.Max_Moves];
        s.neutralUnits = new ArrayList<>();

        s.numUnits = new int[Constants.Num_Players];
        s.prevNumUnits = new int[Constants.Num_Players];

        s.totalLTD = new float[Constants.Num_Players];
        s.totalSumSQRT = new float[Constants.Num_Players];

        // s._numMovements=new int[Constants.Num_Players];
        //s._prevHPSum=new int[Constants.Num_Players];

        s.currentTime = this.currentTime;
        //s._maxUnits=this._maxUnits;
        //s._sameHPFrames=this._sameHPFrames;

        for (int i = 0; i < Constants.Num_Players; i++) {
            for (int j = 0; j < Constants.Max_Moves; j++) {
                if (this.units[i][j] != null)
                    s.units[i][j] = this.units[i][j].clone();
                s.unitIndex[i][j] = this.unitIndex[i][j];
            }
            s.numUnits[i] = this.numUnits[i];
            s.prevNumUnits[i] = this.prevNumUnits[i];
            s.totalLTD[i] = this.totalLTD[i];
            s.totalSumSQRT[i] = this.totalSumSQRT[i];
            //s._numMovements[i]=this._numMovements[i];
            //s._prevHPSum[i]=this._prevHPSum[i];

        }
        if (this.neutralUnits != null && !this.neutralUnits.isEmpty())
            for (Unit u : this.neutralUnits) {
                if (u != null)
                    s.neutralUnits.add(u.clone());
            }

        return s;
    }

    public int aliveUnits() {
        // TODO Auto-generated method stub
        return numUnits[0] + numUnits[1];
    }

}