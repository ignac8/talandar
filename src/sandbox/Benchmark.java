package sandbox;

import bwmcts.sparcraft.players.Player;
import fitnessevaluator.JarcraftEvaluator;
import jnibwapi.types.UnitType;
import neuralnetwork.NeuralNetwork;
import player.jarcraft.NeuralNetworkPlayer;
import player.jarcraft.SimplePlayer;
import utils.Pair;

import java.util.List;

import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateAllUnitSelections;
import static jnibwapi.Map.TILE_SIZE;
import static utils.FileUtils.fromJson;
import static utils.FileUtils.loadFile;

public class Benchmark {

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

        double totalFitness = 0;
        JarcraftEvaluator fitnessEvaluator = new JarcraftEvaluator(graphics, limit, mapHeight, mapWidth,
                gapHeight, gapWidth, firstPlayer, secondPlayer, null);
        NeuralNetworkPlayer neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayer());
        neuralNetworkPlayer.setNeuralNetwork(bestOne);
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> allUnitSelections = generateAllUnitSelections();
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : allUnitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            totalFitness += fitnessEvaluator.evaluate();
        }
        totalFitness /= allUnitSelections.size();

        System.out.println("Total fitness equals: " + totalFitness);


    }
}