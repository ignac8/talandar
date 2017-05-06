/**
 * This file is based on and translated from the open source project: Sparcraft
 * https://code.google.com/p/sparcraft/
 * author of the source: David Churchill
 **/
package bwmcts.sparcraft.players;

import bwmcts.sparcraft.GameState;
import bwmcts.sparcraft.UnitAction;

import java.util.HashMap;
import java.util.List;

public abstract class Player {
    //Sparcraft players

    public int playerId = 0;

    public Player() {

    }

    public Player(int id) {
        playerId = id;
    }

    public void getMoves(GameState state, HashMap<Integer, List<UnitAction>> moves, List<UnitAction> moveVec) {

    }

    public int getId() {
        return playerId;
    }

    public void setID(int id) {
        playerId = id;
    }
}
