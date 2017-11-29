import fitnessevaluator.SimulationEvaluator;
import jnibwapi.types.UnitType;
import neuralnetwork.FCFSNeuralNetwork;
import neuralnetwork.NeuralNetwork;
import org.junit.Test;
import player.NeuralNetworkPlayer;
import player.Player;
import player.SimplePlayer;
import util.Pair;

import java.util.List;

import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateAllUnitSelections;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static util.FileUtils.fromJson;
import static util.FileUtils.toJson;

public class SerializationTests {

    @Test
    public void serializationTest() {
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
        List<Integer> hiddenLayerSizes = asList(10);

        NeuralNetworkPlayer neuralNetworkPlayer = new NeuralNetworkPlayer(0);
        Player simplePlayer = new SimplePlayer(1);

        SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep,
                simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, neuralNetworkPlayer, simplePlayer,
                null);
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

        assertThat(oldFitness, is(newFitness));
    }
}
