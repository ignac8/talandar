package sandbox;

import neuralnetwork.NeuralNetwork;
import player.MyPlayer;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;
import fitnessevaluator.FitnessEvaluator;
import fitnessevaluator.JarcraftEvaluator;
import fitnessevaluator.unitselection.UnitSelection;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.shuffle;
import static jnibwapi.Map.TILE_SIZE;
import static fitnessevaluator.unitselection.JarcraftTestCaseGenerator.generateAllTestCases;
import static utils.FileUtils.fromJson;
import static utils.FileUtils.loadFile;

public class Replay {

    public static void main(String... args) {

        String fileName = "testNeuralWeb.json";
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
        List<FitnessEvaluator> fitnessEvaluators = new ArrayList<>();
        NeuralNetworkPlayer neuralNetworkPlayer;

        for (UnitSelection unitSelection : generateAllTestCases()) {
            FitnessEvaluator fitnessEvaluator = new JarcraftEvaluator(graphics, limit, mapHeight, mapWidth,
                    gapHeight, gapWidth, firstPlayer, secondPlayer, unitSelection);
            neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayer());
            neuralNetworkPlayer.setNeuralNetwork(bestOne);
            fitnessEvaluators.add(fitnessEvaluator);
        }

        while (true) {
            shuffle(fitnessEvaluators);
            for (FitnessEvaluator fitnessEvaluator : fitnessEvaluators) {
                fitnessEvaluator.evaluate();
            }
        }
    }
}