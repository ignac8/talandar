package sandbox;

import bwmcts.sparcraft.players.Player;
import fitnessevaluator.FitnessEvaluator;
import fitnessevaluator.JarcraftEvaluator;
import jnibwapi.types.UnitType;
import neuralnetwork.NeuralNetwork;
import player.jarcraft.NeuralNetworkPlayer;
import player.jarcraft.SimplePlayer;
import utils.Pair;

import java.util.ArrayList;
import java.util.List;

import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateAllUnitSelections;
import static java.util.Collections.shuffle;
import static jnibwapi.Map.TILE_SIZE;
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
        Player firstPlayer = new NeuralNetworkPlayer(0);
        Player secondPlayer = new SimplePlayer(1);

        List<FitnessEvaluator> fitnessEvaluators = new ArrayList<>();
        NeuralNetworkPlayer neuralNetworkPlayer;


        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : generateAllUnitSelections()) {
            FitnessEvaluator fitnessEvaluator = new JarcraftEvaluator(graphics, limit, mapHeight, mapWidth,
                    gapHeight, gapWidth, firstPlayer, secondPlayer, unitSelection);
            neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayer());
            neuralNetworkPlayer.setNeuralNetwork(bestOne);
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