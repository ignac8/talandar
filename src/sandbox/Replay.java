package sandbox;

import jnibwapi.types.UnitType;
import neuralnetwork.NeuralNetwork;
import player.MyPlayer;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;
import solver.fitnessevaluator.FitnessEvaluator;
import solver.fitnessevaluator.JarcraftEvaluator;

import java.util.ArrayList;
import java.util.List;

import static jnibwapi.Map.TILE_SIZE;
import static utils.FileUtils.fromJson;
import static utils.FileUtils.loadFile;

public class Replay {

    public static void main(String... args) {

        String fileName = "debug.json";
        String json = loadFile(fileName);
        NeuralNetwork bestOne = fromJson(json, NeuralNetwork.class);
        boolean graphics = true;
        int limit = Integer.MAX_VALUE;
        int mapHeight = TILE_SIZE * 20;
        int mapWidth = TILE_SIZE * 20;
        int gapHeight = 40;
        int gapWidth = 120;
        MyPlayer firstPlayer = new NeuralNetworkPlayer(0);
        MyPlayer secondPlayer = new SimplePlayer(1);
        List<List<UnitType>> firstPlayerUnits;
        List<List<UnitType>> secondPlayerUnits;
        List<FitnessEvaluator> fitnessEvaluators = new ArrayList<>();
        List<UnitType> unitTypesColumn;
        FitnessEvaluator fitnessEvaluator;
        NeuralNetworkPlayer neuralNetworkPlayer;

        firstPlayerUnits = new ArrayList<>();
        secondPlayerUnits = new ArrayList<>();
        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Dragoon);
        }
        firstPlayerUnits.add(unitTypesColumn);
        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Zealot);
        }
        firstPlayerUnits.add(unitTypesColumn);
        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Dragoon);
        }
        secondPlayerUnits.add(unitTypesColumn);
        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Zealot);
        }
        secondPlayerUnits.add(unitTypesColumn);
        fitnessEvaluator = new JarcraftEvaluator(graphics, limit, mapHeight, mapWidth,
                gapHeight, gapWidth, firstPlayer, secondPlayer, firstPlayerUnits, secondPlayerUnits);
        neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayer());
        neuralNetworkPlayer.setNeuralNetwork(bestOne);
        fitnessEvaluators.add(fitnessEvaluator);

        firstPlayerUnits = new ArrayList<>();
        secondPlayerUnits = new ArrayList<>();
        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Dragoon);
        }
        firstPlayerUnits.add(unitTypesColumn);
        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Zealot);
        }
        firstPlayerUnits.add(unitTypesColumn);
        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Dragoon);
        }
        secondPlayerUnits.add(unitTypesColumn);
        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Zealot);
        }
        secondPlayerUnits.add(unitTypesColumn);
        fitnessEvaluator = new JarcraftEvaluator(graphics, limit, mapHeight, mapWidth,
                gapHeight, gapWidth, firstPlayer, secondPlayer, firstPlayerUnits, secondPlayerUnits);
        neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayer());
        neuralNetworkPlayer.setNeuralNetwork(bestOne);
        fitnessEvaluators.add(fitnessEvaluator);


        firstPlayerUnits = new ArrayList<>();
        secondPlayerUnits = new ArrayList<>();
        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Dragoon);
        }
        firstPlayerUnits.add(unitTypesColumn);
        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Zealot);
        }
        firstPlayerUnits.add(unitTypesColumn);
        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Dragoon);
        }
        secondPlayerUnits.add(unitTypesColumn);
        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Zealot);
        }
        secondPlayerUnits.add(unitTypesColumn);
        fitnessEvaluator = new JarcraftEvaluator(graphics, limit, mapHeight, mapWidth,
                gapHeight, gapWidth, firstPlayer, secondPlayer, firstPlayerUnits, secondPlayerUnits);
        neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayer());
        neuralNetworkPlayer.setNeuralNetwork(bestOne);
        fitnessEvaluators.add(fitnessEvaluator);

        while (true) {
            for (int counter = 0; counter < fitnessEvaluators.size(); counter++) {
                fitnessEvaluators.get(counter).evaluate();
            }
        }


    }
}
