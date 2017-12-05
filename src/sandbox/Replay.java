package sandbox;

import fitnessevaluator.FitnessEvaluator;
import fitnessevaluator.SimulationEvaluator;
import jnibwapi.types.UnitType;
import neuralnetwork.NeuralNetwork;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateUnitSelections;
import static java.util.Collections.shuffle;
import static jnibwapi.Map.TILE_SIZE;
import static util.FileUtils.fromJson;
import static util.FileUtils.loadFile;

public class Replay {

    public static void main(String... args) {

        String fileName = "testNeuralWeb.json";
        String json = loadFile(fileName);
        NeuralNetwork neuralNetwork = fromJson(json, NeuralNetwork.class);
        boolean graphics = true;
        double simulationTimeStep = 1.0;
        double simulationTimeLimit = 10000;
        int mapHeight = TILE_SIZE * 20;
        int mapWidth = TILE_SIZE * 20;
        int gapHeight = 40;
        int gapWidth = 120;
        NeuralNetworkPlayer neuralNetworkPlayer = new NeuralNetworkPlayer(0);
        SimplePlayer simplePlayer = new SimplePlayer(1);

        List<FitnessEvaluator> fitnessEvaluators = new ArrayList<>();
        neuralNetworkPlayer.setNeuralNetwork(neuralNetwork);

        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : generateUnitSelections()) {
            SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep,
                    simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, neuralNetworkPlayer, simplePlayer);
            fitnessEvaluator.setUnitSelection(unitSelection);
            fitnessEvaluators.add(fitnessEvaluator);
        }

        while (true) {
            shuffle(fitnessEvaluators);
            for (FitnessEvaluator fitnessEvaluator : fitnessEvaluators) {
                try {
                    fitnessEvaluator.evaluate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}