import fitnessevaluator.simulation.SimulationEvaluator;
import jnibwapi.types.UnitType;
import neuralnetwork.FCFSNeuralNetwork;
import neuralnetwork.NeuralNetwork;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import player.NeuralNetworkPlayer;
import player.Player;
import player.SimplePlayer;
import player.factory.PlayerFactory;
import simulation.Position;
import simulation.SimulationState;
import simulation.Unit;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

import static fitnessevaluator.simulation.unitselection.UnitSelectionGenerator.generateAllUnitSelections;
import static fitnessevaluator.simulation.unitselection.UnitSelectionGenerator.generateUnitSelections;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static util.FileUtils.fromJson;
import static util.FileUtils.toJson;

public class FitnessEvaluatorTests {

    @Test
    public void consistentResultsTestForNeuralNetworkPlayer() {
        int inputLayerSize = 5;
        int outputLayerSize = 14;
        double std = 1000;
        double mean = 0;
        List<Integer> hiddenLayerSizes = asList(10);

        NeuralNetwork neuralNetwork = new FCFSNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize, std, mean);

        NeuralNetworkPlayer<SimulationState, Unit, Position> neuralNetworkPlayer = PlayerFactory.getSimulationNeuralNetworkPlayer(0);
        neuralNetworkPlayer.setNeuralNetwork(neuralNetwork);

        testAgainstSimplePlayer(neuralNetworkPlayer);
    }

    private void testAgainstSimplePlayer(Player<SimulationState, Unit, Position> firstPlayer) {
        boolean graphics = false;
        double simulationTimeStep = 1.0;
        double simulationTimeLimit = Double.MAX_VALUE;
        double mapHeight = 640.0;
        double mapWidth = 640.0;
        double gapHeight = 40.0;
        double gapWidth = 120.0;

        List<SimulationEvaluator> simulationEvaluators = new ArrayList<>();
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections = generateUnitSelections();
        SimplePlayer<SimulationState, Unit, Position> simplePlayer = PlayerFactory.getSimulationSimplePlayer(1);

        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep,
                    simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, firstPlayer, simplePlayer);
            fitnessEvaluator.setUnitSelection(unitSelection);
            simulationEvaluators.add(fitnessEvaluator);
        }

        for (SimulationEvaluator simulationEvaluator : simulationEvaluators) {
            double previousFitness = simulationEvaluator.evaluate();
            for (int counter = 0; counter < 1000; counter++) {
                double currentFitness = simulationEvaluator.evaluate();
                assertThat(currentFitness, is(previousFitness));
                previousFitness = currentFitness;
            }
        }
    }

    @Test
    public void consistentResultsTestForSimplePlayer() {
        SimplePlayer<SimulationState, Unit, Position> firstPlayer = PlayerFactory.getSimulationSimplePlayer(0);
        testAgainstSimplePlayer(firstPlayer);
    }

    @Test
    public void serializationTest() {
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
        List<Integer> hiddenLayerSizes = asList(10);

        NeuralNetworkPlayer<SimulationState, Unit, Position> neuralNetworkPlayer = PlayerFactory.getSimulationNeuralNetworkPlayer(0);
        Player<SimulationState, Unit, Position> simplePlayer = PlayerFactory.getSimulationSimplePlayer(1);

        SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep,
                simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, neuralNetworkPlayer, simplePlayer);
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> allUnitSelections = generateAllUnitSelections();

        double oldFitness = 0;
        NeuralNetwork oldNeuralNetwork = new FCFSNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize, std, mean);
        neuralNetworkPlayer.setNeuralNetwork(oldNeuralNetwork);
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : allUnitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            oldFitness += fitnessEvaluator.evaluate();
        }
        oldFitness /= allUnitSelections.size();
        System.out.println("Total fitness equals: " + oldFitness);

        double newFitness = 0;
        NeuralNetwork newNeuralNetwork = fromJson(toJson(oldNeuralNetwork), NeuralNetwork.class);
        neuralNetworkPlayer.setNeuralNetwork(newNeuralNetwork);
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : allUnitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            newFitness += fitnessEvaluator.evaluate();
        }
        newFitness /= allUnitSelections.size();
        System.out.println("Total fitness equals: " + newFitness);

        assertThat(oldFitness, CoreMatchers.is(newFitness));
    }
}