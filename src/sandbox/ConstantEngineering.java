package sandbox;

import fitnessevaluator.simulation.SimulationEvaluator;
import jnibwapi.types.UnitType;
import neuralnetwork.ConstantNeuralNetwork;
import neuralnetwork.NeuralNetwork;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;
import player.factory.PlayerFactory;
import simulation.Position;
import simulation.SimulationState;
import simulation.Unit;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

import static fitnessevaluator.simulation.unitselection.UnitSelectionGenerator.generateUnitSelections;

public class ConstantEngineering {

    public static void main(String... args) {

        int inputLayerSize = 1000;
        int outputLayerSize = 14;
        int value = 1;
        boolean graphics = false;
        double simulationTimeStep = 1.0;
        double simulationTimeLimit = Double.MAX_VALUE;
        double mapHeight = 640.0;
        double mapWidth = 640.0;
        double gapHeight = 40.0;
        double gapWidth = 120.0;

        List<NeuralNetwork> neuralNetworks = new ArrayList<>();
        for (int counter = 0; counter < outputLayerSize; counter++) {
            neuralNetworks.add(new ConstantNeuralNetwork(inputLayerSize, outputLayerSize, counter, value));
        }
        NeuralNetworkPlayer<SimulationState, Unit, Position> neuralNetworkPlayer = PlayerFactory.getSimulationNeuralNetworkPlayer(0);
        SimplePlayer<SimulationState, Unit, Position> simplePlayer = PlayerFactory.getSimulationSimplePlayer(1);
        SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep,
                simulationTimeLimit, mapHeight, mapWidth,
                gapHeight, gapWidth, neuralNetworkPlayer, simplePlayer);
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections = generateUnitSelections();

        for (int counter = 0; counter < neuralNetworks.size(); counter++) {
            NeuralNetwork neuralNetwork = neuralNetworks.get(counter);
            neuralNetworkPlayer.setNeuralNetwork(neuralNetwork);
            double totalFitness = 0;
            for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
                fitnessEvaluator.setUnitSelection(unitSelection);
                totalFitness += fitnessEvaluator.evaluate();
            }
            totalFitness /= unitSelections.size();
            System.out.println("Total fitness for run: " + counter + " equals: " + totalFitness);
        }
    }
}