/**
 * This file is based on and translated from the open source project: Sparcraft
 * https://code.google.com/p/sparcraft/
 * author of the source: David Churchill
 **/
package bwmcts.sparcraft;

import jnibwapi.JNIBWAPI;
import jnibwapi.types.UnitCommandType;
import jnibwapi.types.UnitCommandType.UnitCommandTypes;
import jnibwapi.types.UnitType;
import jnibwapi.types.UnitType.UnitTypes;
import jnibwapi.types.WeaponType;

public class Unit implements Comparable<Unit> {
    public int currentHP;                // current HP of the unit
    public int damage;
    public float dpf;
    UnitType unitType;                // the BWAPI unit getType that we are mimicing
    int range;
    Position position;                // current location in a possibly infinite space
    int unitId;                // unique unit getId to the state it's contained in
    int playerId;                // the player who controls the unit
    int currentEnergy;
    int timeCanMove;            // time the unit can next move
    int timeCanAttack;            // time the unit can next attack
    UnitAction previousAction = new UnitAction();        // the previous move that the unit performed
    int previousActionTime;    // the time the previous move was performed
    Position previousPosition;
    int prevCurrentPosTime;
    Position prevCurrentPos;
    int moveCoolDown;
    int attackCoolDown;


    public Unit(UnitType unitType, int playerID, Position pos) {
        this.unitType = unitType;
        range = new PlayerWeapon(PlayerProperties.Get(playerID), WeaponProperties.Get(this.unitType.getGroundWeaponID()).type).GetMaxRange() + Constants.Range_Addition;
        unitId = 0;
        playerId = playerID;
        currentHP = unitType.getMaxHitPoints() + unitType.getMaxShields();
        currentEnergy = unitType.getMaxEnergy() > 0 ? 50 : 0;
        timeCanMove = 0;
        timeCanAttack = 0;
        previousActionTime = 0;
        prevCurrentPosTime = 0;
        position = pos;
        prevCurrentPos = pos.clone();
        previousPosition = pos.clone();
        moveCoolDown = moveCooldown();
        attackCoolDown = attackCooldown();
        damage = damage();
        dpf = dpf();
        range = range * range;
    }

    //Unit(BWAPI::Unit * unit, BWAPI::Game * game, const IDType & playerID, const TimeType & gameTime);
    public Unit(UnitTypes unitType, int playerID, Position pos) {


    }

    public Unit(UnitType unitType, Position pos, int unitID, int playerID, int hp, int energy, int tm, int ta) {

        this.unitType = unitType;
        range = new PlayerWeapon(PlayerProperties.Get(playerID), WeaponProperties.Get(this.unitType.getGroundWeaponID()).type).GetMaxRange() + Constants.Range_Addition;
        //, range                (unitType.groundWeapon().maxRange() + Constants::Range_Addition)
        position = pos;
        unitId = unitID;
        playerId = playerID;
        currentHP = hp;
        currentEnergy = energy;
        timeCanMove = tm;
        timeCanAttack = ta;
        previousActionTime = 0;
        prevCurrentPosTime = 0;
        prevCurrentPos = pos.clone();
        previousPosition = pos.clone();
        moveCoolDown = moveCooldown();
        attackCoolDown = attackCooldown();
        damage = damage();
        dpf = dpf();
        range = range * range;
    }


    public Unit() {
        // TODO Auto-generated constructor stub
    }

    // action functions
    public void setPreviousAction(UnitAction m, int previousMoveTime) {
        previousAction = m;
        previousActionTime = previousMoveTime;

    }

    public void updateAttackActionTime(int newTime) {
        timeCanAttack = newTime;
    }

    public void updateMoveActionTime(int newTime) {
        timeCanMove = newTime;
    }

    public void attack(UnitAction move, int gameTime) {

        if (previousAction.moveType == UnitActionTypes.ATTACK || previousAction.moveType == UnitActionTypes.RELOAD) {

            // add the repeat attack animation duration
            // can't attack again until attack cooldown is up
            timeCanMove = gameTime + attackRepeatFrameTime();
            timeCanAttack = gameTime + attackCoolDown;
        }
        // if there previous action was a MOVE action, add the move penalty
        else if (previousAction.moveType == UnitActionTypes.MOVE) {
            timeCanMove = gameTime + attackInitFrameTime() + 2;
            timeCanAttack = gameTime + attackCoolDown + Constants.Move_Penalty;
        } else {
            // add the initial attack animation duration
            timeCanMove = gameTime + attackInitFrameTime() + 2;
            timeCanAttack = gameTime + attackCoolDown;
        }
        setPreviousAction(move, gameTime);
    }

    public void heal(UnitAction move, Unit target, int gameTime) {
        currentEnergy -= healCost();

        // can't attack again until attack cooldown is up
        updateAttackActionTime(gameTime + healCooldown());
        updateMoveActionTime(gameTime + healCooldown());

        if (currentEnergy() < healCost()) {
            updateAttackActionTime(1000000);
        }

        setPreviousAction(move, gameTime);

    }

    public void move(UnitAction move, int gameTime) {

        previousPosition = getPosition();

        // get the distance to the move action destination
        // int dist = move.getPosition().getDistance(getPosition());

        // how long will this move take?
        //int moveDuration = (int)((double)move.getPosition().getDistance(getPosition()) / speed());

        // update the next time we can move, make sure a move always takes 1 time step

        timeCanMove = gameTime + Math.max((int) ((double) move.pos().getDistance(getPosition()) / unitType.getTopSpeed()), 1);

        // assume we need 4 frames to turn around after moving
        timeCanAttack = Math.max(timeCanAttack, timeCanMove);

        // update the position
        //position.addPosition(dist * dir.x(), dist * dir.y());
        position = move.pos().clone();

        setPreviousAction(move, gameTime);
    }

    public void waitUntilAttack(UnitAction move, int gameTime) {
        timeCanMove = timeCanAttack;
        setPreviousAction(move, gameTime);
    }

    public void pass(UnitAction move, int gameTime) {
        timeCanMove = gameTime + Constants.Pass_Move_Duration;
        timeCanAttack = gameTime + Constants.Pass_Move_Duration;
        setPreviousAction(move, gameTime);
    }

    public void takeAttack(Unit attacker) {
        PlayerWeapon weapon = attacker.getWeapon(this);
        //int      damage=attacker.getWeapon(this).GetDamageBase();

        //damage =Math.max((int)((attacker.getWeapon(this).GetDamageBase()-getArmor()) * attacker.getWeapon(this).GetDamageMultiplier(getSize())), 2);

        //std::cout << (int)attacker.player() << " " << damage << "\n";

        // setCurrentHP(getCurrentHP - Math.max((int)((attacker.getWeapon(this).GetDamageBase()-getArmor()) * attacker.getWeapon(this).GetDamageMultiplier(getSize())), 2));
        currentHP -= Math.max((int) ((weapon.GetDamageBase() - getArmor()) * weapon.GetDamageMultiplier(getSize())), 2);
    }

    public void takeHeal(Unit healer) {
        setCurrentHP(currentHP + healer.healAmount());
    }

    // conditional functions
    public boolean isMobile() {
        return unitType.isCanMove();
    }

    public boolean isOrganic() {
        return unitType.isOrganic();
    }

    public boolean isAlive() {
        return currentHP > 0;
    }

    public boolean canAttackNow() {
        return unitType.getID() != UnitTypes.Terran_Medic.getID() && timeCanAttack <= timeCanMove;
    }

    public boolean canMoveNow() {
        //return unitType.isCanMove() && timeCanMove <= timeCanAttack;
        return timeCanMove <= timeCanAttack;
    }

    public boolean canHealNow() {
        return canHeal() && (currentEnergy() >= healCost()) && (timeCanAttack <= timeCanMove);
    }

    public boolean canKite() {
        return timeCanMove < timeCanAttack;
    }

    public boolean canHeal() {
        return unitType.getID() == UnitTypes.Terran_Medic.getID();
    }

    public boolean canAttackUnit(Unit unit, int gameTime) {

        //WeaponType weapon =WeaponProperties.props[ unit.getType().isFlyer() ? getType().getAirWeaponID() : getType().getGroundWeaponID()].getType;

        //if (!(unit.unitType.isFlyer() ? unitType.isCanAttackAir() : unitType.isCanAttackGround()))
        if (WeaponProperties.props[unit.unitType.isFlyer() ? unitType.getAirWeaponID() : unitType.getGroundWeaponID()].type.getDamageAmount() == 0) {
            return false;
        }

        if (!unit.isAlive()) {
            return false;
        }

        // range of this unit attacking
        //int r = getRange();

        // return whether the target unit is in range
        return range >= getDistanceSq(unit.currentPosition(gameTime), gameTime);
    }

    public boolean canBeAttackedByUnits(Unit[] enemyUnits, int gameTime) {
        boolean found = false;

        for (int counter = 0; !found && counter < enemyUnits.length; counter++) {
            Unit enemyUnit = enemyUnits[counter];
            if (enemyUnit != null) {
                if (enemyUnit.canAttackUnit(this, gameTime)) {
                    found = true;
                }
            }

        }
        return found;
    }

    public boolean canHealTarget(Unit unit, int gameTime) {
        if (!canHeal() || !unit.isOrganic() || !(unit.player() == player()) || (unit.getCurrentHP() == unit.getMaxHP())) {
            // then it can't heal the target
            return false;
        }

        // range of this unit attacking
        int r = healRange();

        // return whether the target unit is in range
        return (r * r) >= getDistanceSq(unit, gameTime);
    }

    // id related
    public void setUnitID(int id) {
        unitId = id;
    }

    public int getId() {
        return unitId;
    }

    public int player() {
        return playerId;
    }

    // position related functions
    public Position getPosition() {
        return position;
    }

    int x() {
        return position.getX();
    }

    int y() {
        return position.getY();
    }

    public int getRange() {
        return range;
    }

    public int healRange() {
        return canHeal() ? 96 : 0;
    }

    public int getDistanceSq(Unit u, int gameTime) {
        return getDistanceSq(u.currentPosition(gameTime), gameTime);
    }

    public int getDistanceSq(Unit u) {
        return position.getDistanceSq(u.getPosition());
    }

    public int getDistanceSq(Position p, int gameTime) {
        return currentPosition(gameTime).getDistanceSq(p);
    }

    public int getDistanceSq(int x, int y, int gameTime) {
        return currentPosition(gameTime).getDistanceSq(x, y);
    }

    public Position currentPosition(int gameTime) {
        if (previousAction.moveType == UnitActionTypes.MOVE) {
            // if gameTime is equal to previous move time then we haven't moved yet
            if (gameTime == previousActionTime) {

                return previousPosition;
            } else if (gameTime >= timeCanMove) {

                return position;
            }

            // else if game time is >= time we can move, then we have arrived at the destination

            // otherwise we are still moving, so calculate the current position
            else if (gameTime == prevCurrentPosTime) {

                return prevCurrentPos;
            } else {

                prevCurrentPosTime = gameTime;

                // calculate the new current position

                prevCurrentPos.moveTo(position.x - previousPosition.x, position.y - previousPosition.y);

                prevCurrentPos.scalePosition((float) (gameTime - previousActionTime) / (timeCanMove - previousActionTime));
                prevCurrentPos.addPosition(previousPosition);

                return prevCurrentPos;
            }
        }
        // if it wasn't a MOVE, then we just return the Unit position
        else {
            return position;
        }
    }

    // health and damage related functions
    public int damage() {
        return unitType.getID() == UnitTypes.Protoss_Zealot.getID() ?
                2 * WeaponProperties.Get(unitType.getGroundWeaponID()).type.getDamageAmount() :
                WeaponProperties.Get(unitType.getGroundWeaponID()).type.getDamageAmount();
    }

    public int damageGround() {
        return unitType.getID() == UnitTypes.Protoss_Zealot.getID() ?
                2 * WeaponProperties.Get(unitType.getGroundWeaponID()).type.getDamageAmount() :
                WeaponProperties.Get(unitType.getGroundWeaponID()).type.getDamageAmount();
    }

    public int damageAir() {
        return WeaponProperties.Get(unitType.getAirWeaponID()).type.getDamageAmount();
    }

    public int healAmount() {
        return canHeal() ? 6 : 0;
    }

    public int getMaxHP() {
        return unitType.getMaxHitPoints() + unitType.getMaxShields();
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int currentEnergy() {
        return currentEnergy;
    }

    public int maxEnergy() {
        return unitType.getMaxEnergy();
    }

    public int healCost() {
        return 3;
    }

    public int getArmor() {
        return UnitProperties.Get(getUnitType()).GetArmor(PlayerProperties.Get(player()));
    }

    public float dpf() {
        return Math.max(Constants.Min_Unit_DPF, (float) damage / (attackCoolDown + 1));
    }

    public void setCurrentHP(int newHP) {
        currentHP = Math.min(getMaxHP(), newHP);
    }

    public int getSize() {
        return unitType.getSizeID();
    }

    public WeaponType getWeapon(UnitType target) {
        return target.isFlyer() ? WeaponProperties.Get(unitType.getAirWeaponID()).type : WeaponProperties.Get(unitType.getGroundWeaponID()).type;
    }

    public PlayerWeapon getWeapon(Unit target) {
        return new PlayerWeapon(PlayerProperties.Get(player()), target.getUnitType().isFlyer() ? WeaponProperties.Get(unitType.getAirWeaponID()).type : WeaponProperties.Get(unitType.getGroundWeaponID()).type);
    }

    // time and cooldown related functions
    public int moveCooldown() {
        return (int) ((float) Constants.Move_Distance / unitType.getTopSpeed());
    }

    public int attackCooldown() {
        return WeaponProperties.Get(unitType.getGroundWeaponID()).GetCooldown(PlayerProperties.Get(playerId));
    }

    public int healCooldown() {
        return 8;
    }

    public int nextAttackActionTime() {
        return timeCanAttack;
    }

    public int nextMoveActionTime() {
        return timeCanMove;
    }

    public int previousActionTime() {
        return previousActionTime;
    }

    public int getFirstTimeFree() {
        return timeCanAttack <= timeCanMove ? timeCanAttack : timeCanMove;
    }

    public int attackInitFrameTime() {
        return AnimationFrameData.getAttackFrames(unitType)[0];
    }

    public int attackRepeatFrameTime() {
        return AnimationFrameData.getAttackFrames(unitType)[1];
    }

    public void setCooldown(int attack, int move) {
        timeCanAttack = attack;
        timeCanMove = move;
    }

    // other functions
    public int typeID() {
        return unitType.getID();
    }

    public double speed() {
        return unitType.getTopSpeed();
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public UnitAction previousAction() {
        return previousAction;
    }

    public String name() {
        return unitType.getName().replaceAll(" ", "_");
    }

    public void print() {
        System.out.printf("%s %5d [%5d %5d] (%5d, %5d)\n", unitType.getName(), getCurrentHP(), nextAttackActionTime(), nextMoveActionTime(), x(), y());
    }

    public String toString() {
        return String.format("%s %5d [%5d %5d] (%5d, %5d)\n", unitType.getName(), getCurrentHP(), nextAttackActionTime(), nextMoveActionTime(), x(), y());
    }

    public int compareTo(Unit u) {
        if (!isAlive() && !u.isAlive()) {
            return 0;
        }

        if (!isAlive()) {
            return 1;
        } else if (!u.isAlive()) {
            return -1;
        }

        if (getFirstTimeFree() == u.getFirstTimeFree()) {
            return getId() >= u.getId() ? 1 : -1;
        } else {
            return getFirstTimeFree() >= u.getFirstTimeFree() ? 1 : -1;
        }

    }

    public Unit clone() {
        Unit u = new Unit();
        u.unitType = this.unitType;                // the BWAPI unit getType that we are mimicing
        u.range = this.range;

        if (this.position != null)
            u.position = new Position(this.position.x, this.position.y);                // current location in a possibly infinite space

        u.unitId = this.unitId;                // unique unit getId to the state it's contained in
        u.playerId = this.playerId;                // the player who controls the unit

        u.currentHP = this.currentHP;                // current HP of the unit
        u.currentEnergy = this.currentEnergy;

        u.timeCanMove = this.timeCanMove;            // time the unit can next move
        u.timeCanAttack = this.timeCanAttack;            // time the unit can next attack

        if (this.previousAction != null)
            u.previousAction = this.previousAction.clone();
        // the previous move that the unit performed
        u.previousActionTime = this.previousActionTime;    // the time the previous move was performed
        if (this.previousPosition != null)
            u.previousPosition = new Position(this.previousPosition.x, this.previousPosition.y);

        u.prevCurrentPosTime = this.prevCurrentPosTime;
        if (this.prevCurrentPos != null)
            u.prevCurrentPos = new Position(this.prevCurrentPos.x, this.prevCurrentPos.y);
        u.moveCoolDown = this.moveCoolDown;
        u.attackCoolDown = this.attackCoolDown;
        u.damage = this.damage;
        u.dpf = this.dpf;

        return u;
    }

    //Code from UalbertaBot
    public void setUnitCooldown(JNIBWAPI bwapi, jnibwapi.Unit unit) {
        int attackCooldown = 0;
        int moveCooldown = 0;

        UnitCommandType lastCommand = (UnitCommandType) UnitCommandTypes.getAllUnitCommandTypes().toArray()[unit.getLastCommandID()];
        int lastCommandFrame = unit.getLastCommandFrame();
        int currentFrame = bwapi.getFrameCount();
        int framesSinceCommand = currentFrame - lastCommandFrame;

        attackCooldown = currentFrame + Math.max(0, unit.getGroundWeaponCooldown() - 2);
        // if the last attack was an attack command
        if (lastCommand == UnitCommandTypes.Attack_Unit) {
            moveCooldown = currentFrame + Math.max(0, attackInitFrameTime() - framesSinceCommand);

        }
        // if the last command was a move command
        else if (lastCommand == UnitCommandTypes.Move) {

            moveCooldown = currentFrame + Math.max(0, moveCoolDown - framesSinceCommand);

        }

        if (moveCooldown - currentFrame < 4 || unit.isMoving()) {

            moveCooldown = currentFrame;
        }

        moveCooldown = Math.min(moveCooldown, attackCooldown);

        this.timeCanMove = moveCooldown;
        this.timeCanAttack = attackCooldown;
    }

    public int getMineralPrice() {
        return unitType.getMineralPrice();
    }

    public int getGasPrice() {
        return unitType.getGasPrice();
    }

}
