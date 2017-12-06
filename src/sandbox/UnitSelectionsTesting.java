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
        int outputLayerSize = 14;
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
        for (int counter = 0; counter < 1000; counter++) {
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

        System.out.println(calculateFitness(neuralNetworkPlayer, neuralNetworks, fitnessEvaluator, generateUnitSelections(
                Arrays.asList(Race.TERRAN), Arrays.asList(Quantity.values()), Arrays.asList(Quantity.values()))));

        System.out.println(calculateFitness(neuralNetworkPlayer, neuralNetworks, fitnessEvaluator, generateUnitSelections(
                Arrays.asList(Race.PROTOSS), Arrays.asList(Quantity.values()), Arrays.asList(Quantity.values()))));

        System.out.println(calculateFitness(neuralNetworkPlayer, neuralNetworks, fitnessEvaluator, generateUnitSelections(
                Arrays.asList(Race.ZERG), Arrays.asList(Quantity.values()), Arrays.asList(Quantity.values()))));

        System.out.println(calculateFitness(neuralNetworkPlayer, neuralNetworks, fitnessEvaluator, generateUnitSelections(
                Arrays.asList(Race.TERRAN), Arrays.asList(Quantity.LESS), Arrays.asList(Quantity.LESS))));

        System.out.println(calculateFitness(neuralNetworkPlayer, neuralNetworks, fitnessEvaluator, generateUnitSelections(
                Arrays.asList(Race.PROTOSS), Arrays.asList(Quantity.LESS), Arrays.asList(Quantity.LESS))));

        System.out.println(calculateFitness(neuralNetworkPlayer, neuralNetworks, fitnessEvaluator, generateUnitSelections(
                Arrays.asList(Race.ZERG), Arrays.asList(Quantity.LESS), Arrays.asList(Quantity.LESS))));
    }

    private static double calculateFitness(NeuralNetworkPlayer neuralNetworkPlayer,
                                           List<NeuralNetwork> neuralNetworks,
                                           SimulationEvaluator fitnessEvaluator,
                                           List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections) {
        double fitness;
        fitness = 0;
        for (int counter = 0; counter < neuralNetworks.size(); counter++) {
            NeuralNetwork neuralNetwork = neuralNetworks.get(counter);
            neuralNetworkPlayer.setNeuralNetwork(neuralNetwork);
            for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
                fitnessEvaluator.setUnitSelection(unitSelection);
                fitness += fitnessEvaluator.evaluate();
            }
            System.out.println(counter);
        }
        fitness /= neuralNetworks.size() * unitSelections.size();
        return fitness;
    }
}
