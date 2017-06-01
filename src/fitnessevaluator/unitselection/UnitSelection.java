package fitnessevaluator.unitselection;

import jnibwapi.types.UnitType;

import java.util.ArrayList;
import java.util.List;

public class UnitSelection {

    private List<List<UnitType>> firstPlayerUnitSelection;
    private List<List<UnitType>> secondPlayerUnitSelection;

    public UnitSelection() {
        firstPlayerUnitSelection = new ArrayList<>();
        secondPlayerUnitSelection = new ArrayList<>();
    }

    public UnitSelection(List<List<UnitType>> firstPlayerUnitSelection, List<List<UnitType>> secondPlayerUnitSelection) {
        this.firstPlayerUnitSelection = firstPlayerUnitSelection;
        this.secondPlayerUnitSelection = secondPlayerUnitSelection;
    }

    public List<List<UnitType>> getFirstPlayerUnitSelection() {
        return firstPlayerUnitSelection;
    }

    public List<List<UnitType>> getSecondPlayerUnitSelection() {
        return secondPlayerUnitSelection;
    }

}
