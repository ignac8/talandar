package sandbox;

import com.google.common.primitives.Ints;
import fitnessevaluator.SimulationEvaluator;
import fitnessevaluator.unitselection.Quantity;
import fitnessevaluator.unitselection.Race;
import jnibwapi.types.UnitType;
import neuralnetwork.FCFSNeuralNetwork;
import neuralnetwork.NeuralNetwork;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateAllUnitSelections;
import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateUnitSelections;

public class UnitSelectionsTesting {

    public static void main(String... args) {

        int inputLayerSize = 5;
        int outputLayerSize = 15;
        double std = 1000;
        double mean = 0;
        boolean graphics = false;
        double simulationTimeStep = 1.0;
        double simulationTimeLimit = Double.MAX_VALUE;
        double mapHeight = 640.0;
        double mapWidth = 640.0;
        double gapHeight = 40.0;
        double gapWidth = 120.0;

        List<Integer> hiddenLayerSizes = Ints.asList(10);

        NeuralNetworkPlayer neuralNetworkPlayer = new NeuralNetworkPlayer(0);

        List<NeuralNetwork> neuralNetworks = new ArrayList<>();
        for (int counter = 0; counter < 100; counter++) {
            NeuralNetwork neuralNetwork = new FCFSNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize, std, mean);
            neuralNetworks.add(neuralNetwork);
        }

        SimplePlayer simplePlayer = new SimplePlayer(1);

        SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep,
                simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, neuralNetworkPlayer, simplePlayer);

        System.out.println(calculateFitness(neuralNetworkPlayer, neuralNetworks, fitnessEvaluator, generateAllUnitSelections()));

        System.out.println(calculateFitness(neuralNetworkPlayer, neuralNetworks, fitnessEvaluator, generateUnitSelections()));

        System.out.println(calculateFitness(neuralNetworkPlayer, neuralNetworks, fitnessEvaluator, generateUnitSelections(
                Arrays.asList(Race.values()), Arrays.asList(Quantity.MORE), Arrays.asList(Quantity.MORE))));
    }

    private static double calculateFitness(NeuralNetworkPlayer neuralNetworkPlayer,
                                           List<NeuralNetwork> neuralNetworks,
                                           SimulationEvaluator fitnessEvaluator,
                                           List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections) {
        double fitness;
        fitness = 0;
        for (NeuralNetwork neuralNetwork : neuralNetworks) {
            neuralNetworkPlayer.setNeuralNetwork(neuralNetwork);
            for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
                fitnessEvaluator.setUnitSelection(unitSelection);
                fitness += fitnessEvaluator.evaluate();
            }
        }
        fitness /= neuralNetworks.size() * unitSelections.size();
        return fitness;
    }
}
