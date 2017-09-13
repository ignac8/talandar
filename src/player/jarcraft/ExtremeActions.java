package player.jarcraft;

import bwmcts.sparcraft.UnitAction;

public class ExtremeActions {
    private UnitAction closestAction;
    private UnitAction furthestAction;

    public ExtremeActions(UnitAction closestAction, UnitAction furthestAction) {
        this.closestAction = closestAction;
        this.furthestAction = furthestAction;
    }

    public UnitAction getClosestAction() {
        return closestAction;
    }

    public UnitAction getFurthestAction() {
        return furthestAction;
    }
}