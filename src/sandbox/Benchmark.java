package sandbox;

import fitnessevaluator.simulation.SimulationEvaluator;
import jnibwapi.types.UnitType;
import neuralnetwork.NeuralNetwork;
import player.simulation.NeuralNetworkSimulationPlayer;
import player.simulation.SimpleSimulationPlayer;
import util.Pair;

import java.util.List;

import static fitnessevaluator.simulation.unitselection.UnitSelectionGenerator.generateAllUnitSelections;
import static jnibwapi.Map.TILE_SIZE;
import static util.FileUtils.fromJson;
import static util.FileUtils.loadFile;

public class Benchmark {

    public static void main(String... args) {

        String fileName = "best-0.4,0.001,100,9.json";
        String json = loadFile(fileName);
        NeuralNetwork bestOne = fromJson(json, NeuralNetwork.class);
        boolean graphics = false;
        double simulationTimeStep = 1.0;
        double simulationTimeLimit = 10000000;
        int mapHeight = TILE_SIZE * 20;
        int mapWidth = TILE_SIZE * 20;
        int gapHeight = 40;
        int gapWidth = 120;
        NeuralNetworkSimulationPlayer neuralNetworkSimulationPlayer = new NeuralNetworkSimulationPlayer(0);
        SimpleSimulationPlayer simpleSimulationPlayer = new SimpleSimulationPlayer(1);

        double totalFitness = 0;
        SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep, simulationTimeLimit, mapHeight, mapWidth,
                gapHeight, gapWidth, neuralNetworkSimulationPlayer, simpleSimulationPlayer);
        neuralNetworkSimulationPlayer.setNeuralNetwork(bestOne);
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> allUnitSelections = generateAllUnitSelections();
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : allUnitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            totalFitness += fitnessEvaluator.evaluate();
        }
        totalFitness /= allUnitSelections.size();

        System.out.println("Total fitness equals: " + totalFitness);
    }
}