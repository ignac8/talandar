/**
 * This file is based on and translated from the open source project: Sparcraft
 * https://code.google.com/p/sparcraft/
 * author of the source: David Churchill
 **/
package bwmcts.sparcraft;


public class UnitAction {

    public int unitId;
    public int playerId;
    public int moveIndex;
    public UnitActionTypes moveType;
    public Position position;

    public UnitAction() {
        unitId = 255;
        playerId = 255;
        moveType = UnitActionTypes.NONE;
        moveIndex = 255;

    }

    public UnitAction(int unitIndex, int player, UnitActionTypes type, int moveIndex, Position dest) {
        unitId = unitIndex;
        playerId = player;
        moveType = type;
        this.moveIndex = moveIndex;
        position = dest;
    }

    public UnitAction(int unitIndex, int player, UnitActionTypes type, int moveIndex) {
        unitId = unitIndex;
        playerId = player;
        moveType = type;
        this.moveIndex = moveIndex;
    }


    public int unit() {
        return unitId;
    }

    public int player() {
        return playerId;
    }

    public int index() {
        return moveIndex;
    }

    public Position pos() {
        return position;
    }

    public UnitActionTypes getType() {
        return moveType;
    }

    public String moveString() {
        if (moveType == UnitActionTypes.ATTACK) {
            return "ATTACK";
        } else if (moveType == UnitActionTypes.MOVE) {
            return "MOVE";
        } else if (moveType == UnitActionTypes.RELOAD) {
            return "RELOAD";
        } else if (moveType == UnitActionTypes.PASS) {
            return "PASS";
        } else if (moveType == UnitActionTypes.HEAL) {
            return "HEAL";
        }

        return "NONE";
    }

    public Position getDir() {
        assert (moveType == UnitActionTypes.MOVE);

        return new Position(Constants.Move_Dir[moveIndex][0], Constants.Move_Dir[moveIndex][1]);
    }

    public String toString() {
        return this.moveIndex + "," + this.playerId + "," + this.unitId + "," + this.moveString() + "," + this.pos();

    }

    public UnitAction clone() {
        UnitAction a = new UnitAction();

        a.unitId = this.unitId;
        a.playerId = this.playerId;
        a.moveIndex = this.moveIndex;
        a.moveType = this.moveType;
        if (this.position != null)
            a.position = new Position(this.position.x, this.position.y);

        return a;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + moveIndex;
        result = prime * result
                + ((moveType == null) ? 0 : moveType.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + playerId;
        result = prime * result + unitId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UnitAction other = (UnitAction) obj;
        if (moveIndex != other.moveIndex)
            return false;
        if (moveType != other.moveType)
            return false;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (playerId != other.playerId)
            return false;
        if (unitId != other.unitId)
            return false;
        return true;
    }

    public int getUnitId() {
        return unitId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getMoveIndex() {
        return moveIndex;
    }

    public UnitActionTypes getActionType() {
        return moveType;
    }

    public Position getPosition() {
        return position;
    }
}
