package sandbox;

import jnibwapi.types.UnitType;
import player.MyPlayer;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;
import solver.Individual;
import solver.fitnessevaluator.FitnessEvaluator;
import solver.fitnessevaluator.JarcraftEvaluator;

import java.util.ArrayList;
import java.util.List;

import static jnibwapi.Map.TILE_SIZE;
import static utils.FileUtils.fromJson;
import static utils.FileUtils.loadFile;

public class Replay {

    public static void main(String... args) {

        String fileName = "testNeuralWeb.json";
        boolean graphics = true;
        int limit = Integer.MAX_VALUE;
        int mapHeight = TILE_SIZE * 20;
        int mapWidth = TILE_SIZE * 20;
        int gapHeight = 40;
        int gapWidth = 120;
        MyPlayer firstPlayer = new NeuralNetworkPlayer(0, true);
        MyPlayer secondPlayer = new SimplePlayer(1);
        List<List<UnitType>> firstPlayerUnits = new ArrayList<>();
        List<List<UnitType>> secondPlayerUnits = new ArrayList<>();
        List<UnitType> unitTypesColumn;

        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Dragoon);
        }
        firstPlayerUnits.add(unitTypesColumn);

        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Zealot);
        }
        firstPlayerUnits.add(unitTypesColumn);

        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Dragoon);
        }
        secondPlayerUnits.add(unitTypesColumn);

        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Zealot);
        }
        secondPlayerUnits.add(unitTypesColumn);

        FitnessEvaluator fitnessEvaluator = new JarcraftEvaluator(graphics, limit, mapHeight, mapWidth,
                gapHeight, gapWidth, firstPlayer, secondPlayer, firstPlayerUnits, secondPlayerUnits);
        String json = loadFile(fileName).get(0);
        Individual bestOne = fromJson(json, Individual.class);
        NeuralNetworkPlayer neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayer());
        neuralNetworkPlayer.setNeuralNetwork(bestOne.getNeuralNetwork());
        fitnessEvaluator.evaluate();
    }
}
