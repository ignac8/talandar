import fitnessevaluator.FitnessEvaluator;
import fitnessevaluator.SimulationEvaluator;
import jnibwapi.types.UnitType;
import neuralnetwork.FCFSNeuralNetwork;
import neuralnetwork.NeuralNetwork;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import player.NeuralNetworkPlayer;
import player.Player;
import player.SimplePlayer;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateAllUnitSelections;
import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateUnitSelections;
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

        Player firstPlayer = new NeuralNetworkPlayer(0);
        NeuralNetworkPlayer neuralNetworkPlayer = (NeuralNetworkPlayer) (firstPlayer);
        neuralNetworkPlayer.setNeuralNetwork(neuralNetwork);

        testAgainstSimplePlayer(firstPlayer);
    }

    private void testAgainstSimplePlayer(Player firstPlayer) {
        boolean graphics = false;
        double simulationTimeStep = 1.0;
        double simulationTimeLimit = Double.MAX_VALUE;
        double mapHeight = 640.0;
        double mapWidth = 640.0;
        double gapHeight = 40.0;
        double gapWidth = 120.0;

        List<FitnessEvaluator> fitnessEvaluators = new ArrayList<>();
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections = generateUnitSelections();
        Player secondPlayer = new SimplePlayer(1);

        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep,
                    simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, firstPlayer, secondPlayer);
            fitnessEvaluator.setUnitSelection(unitSelection);
            fitnessEvaluators.add(fitnessEvaluator);
        }

        for (FitnessEvaluator fitnessEvaluator : fitnessEvaluators) {
            double previousFitness = fitnessEvaluator.evaluate();
            for (int counter = 0; counter < 1000; counter++) {
                double currentFitness = fitnessEvaluator.evaluate();
                assertThat(currentFitness, is(previousFitness));
                previousFitness = currentFitness;
            }
        }
    }

    @Test
    public void consistentResultsTestForSimplePlayer() {
        Player firstPlayer = new SimplePlayer(0);
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

        NeuralNetworkPlayer neuralNetworkPlayer = new NeuralNetworkPlayer(0);
        Player simplePlayer = new SimplePlayer(1);

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