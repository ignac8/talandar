package fitnessevaluator.unitselection;

import jnibwapi.types.UnitType;
import simulation.bridge.JNIBWAPI_LOAD;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.shuffle;

public class UnitSelectionGenerator {

    static {
        JNIBWAPI_LOAD.loadIfNecessary();
    }

    public static List<Pair<List<List<UnitType>>, List<List<UnitType>>>> generateRandomUnitSelections(int number) {
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> allUnitSelections = generateAllUnitSelections();
        shuffle(allUnitSelections);
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> randomUnitSelections = new ArrayList<>();
        for (int counter = 0; counter < number && counter < allUnitSelections.size(); counter++) {
            randomUnitSelections.add(allUnitSelections.get(counter));
        }
        return randomUnitSelections;
    }

    public static List<Pair<List<List<UnitType>>, List<List<UnitType>>>> generateAllUnitSelections() {
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections = new ArrayList<>();

        List<List<List<UnitType>>> firstPlayerOptions = generatePossibleChoices();
        List<List<List<UnitType>>> secondPlayerOptions = generatePossibleChoices();

        for (List<List<UnitType>> firstPlayerOption : firstPlayerOptions) {
            for (List<List<UnitType>> secondPlayerOption : secondPlayerOptions) {
                Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection =
                        new Pair<>(firstPlayerOption, secondPlayerOption);
                unitSelections.add(unitSelection);
            }
        }
        return unitSelections;
    }

    private static List<List<List<UnitType>>> generatePossibleChoices() {
        List<List<List<UnitType>>> possibleChoices = new ArrayList<>();
        for (Race unitRace : Race.values()) {
            for (Quantity meleeUnitQuantity : Quantity.values()) {
                for (Quantity rangedUnitQuantity : Quantity.values()) {
                    List<List<UnitType>> playerUnits = generatePlayerUnits(unitRace, meleeUnitQuantity, rangedUnitQuantity);
                    if (playerUnits != null) {
                        possibleChoices.add(playerUnits);
                    }
                }
            }
        }
        return possibleChoices;
    }

    public static List<List<UnitType>> generatePlayerUnits(Race race,
                                                           Quantity meleeUnitQuantity,
                                                           Quantity rangedUnitQuantity) {
        List<List<UnitType>> playerUnits = new ArrayList<>();
        playerUnits.add(new ArrayList<>());
        playerUnits.add(new ArrayList<>());

        UnitType meleeUnitType = null;
        UnitType rangedUnitType = null;

        switch (race) {
            case PROTOSS:
                meleeUnitType = UnitType.UnitTypes.Protoss_Zealot;
                rangedUnitType = UnitType.UnitTypes.Protoss_Dragoon;
                break;
            case TERRAN:
                meleeUnitType = UnitType.UnitTypes.Terran_Firebat;
                rangedUnitType = UnitType.UnitTypes.Terran_Marine;
                break;
            case ZERG:
                meleeUnitType = UnitType.UnitTypes.Zerg_Zergling;
                rangedUnitType = UnitType.UnitTypes.Zerg_Hydralisk;
                break;
        }

        int meleeUnitNumber = 0;
        int rangedUnitNumber = 0;

        //noinspection Duplicates
        switch (meleeUnitQuantity) {
            case NONE:
                meleeUnitNumber = 0;
                break;
            case LESS:
                meleeUnitNumber = 6;
                break;
            case MORE:
                meleeUnitNumber = 12;
                break;
        }

        //noinspection Duplicates
        switch (rangedUnitQuantity) {
            case NONE:
                rangedUnitNumber = 0;
                break;
            case LESS:
                rangedUnitNumber = 6;
                break;
            case MORE:
                rangedUnitNumber = 12;
                break;
        }

        if (meleeUnitNumber == 0 && rangedUnitNumber == 0) {
            return null;
        }

        for (int counter = 0; counter < rangedUnitNumber; counter++) {
            playerUnits.get(0).add(rangedUnitType);
        }

        for (int counter = 0; counter < meleeUnitNumber; counter++) {
            playerUnits.get(1).add(meleeUnitType);
        }

        return playerUnits;
    }

    public static List<Pair<List<List<UnitType>>, List<List<UnitType>>>> generateMirrorUnitSelections(
            List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections) {
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> mirrorUnitSelections = new ArrayList<>();
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            mirrorUnitSelections.add(unitSelection.getSwappedPair());
        }
        return mirrorUnitSelections;
    }
}