import fitnessevaluator.FitnessEvaluator;
import fitnessevaluator.SimulationEvaluator;
import fitnessevaluator.unitselection.UnitSelectionGenerator;
import jnibwapi.types.UnitType;
import neuralnetwork.NeuralNetwork;
import org.junit.Test;
import player.NeuralNetworkPlayer;
import player.Player;
import player.SimplePlayer;
import utils.Pair;

import java.util.ArrayList;
import java.util.List;

import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateRandomUnitSelections;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.FileUtils.fromJson;
import static utils.FileUtils.loadFile;

public class FitnessEvaluatorTests {

    @Test
    public void consistentResultsTestForNeuralNetworkPlayer() {
        boolean graphics = false;
        double simulationTimeStep = 1.0;
        double simulationTimeLimit = Double.MAX_VALUE;
        double mapHeight = 640.0;
        double mapWidth = 640.0;
        double gapHeight = 40.0;
        double gapWidth = 120.0;
        int numberOfUnitSelections = 1;

        NeuralNetwork neuralNetwork = fromJson(loadFile("testNeuralWeb.json"), NeuralNetwork.class);

        List<FitnessEvaluator> fitnessEvaluators = new ArrayList<>();

        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections
                = generateRandomUnitSelections(numberOfUnitSelections);

        unitSelections.addAll(UnitSelectionGenerator.generateMirrorUnitSelections(unitSelections));

        Player firstPlayer = new NeuralNetworkPlayer(0);
        Player secondPlayer = new SimplePlayer(1);
        NeuralNetworkPlayer neuralNetworkPlayer = (NeuralNetworkPlayer) (firstPlayer);
        neuralNetworkPlayer.setNeuralNetwork(neuralNetwork);

        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            FitnessEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep,
                    simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, firstPlayer, secondPlayer,
                    unitSelection);
            fitnessEvaluators.add(fitnessEvaluator);
        }

        for (FitnessEvaluator fitnessEvaluator : fitnessEvaluators) {
            double previousFitness = fitnessEvaluator.evaluate();
            for (int counter = 0; counter < 100; counter++) {
                double currentFitness = fitnessEvaluator.evaluate();
                assertThat(currentFitness, is(previousFitness));
                previousFitness = currentFitness;
            }
        }
    }

    @Test
    public void consistentResultsTestForSimplePlayer() {
        boolean graphics = false;
        double simulationTimeStep = 1.0;
        double simulationTimeLimit = Double.MAX_VALUE;
        double mapHeight = 640.0;
        double mapWidth = 640.0;
        double gapHeight = 40.0;
        double gapWidth = 120.0;
        int numberOfUnitSelections = 10;

        List<FitnessEvaluator> fitnessEvaluators = new ArrayList<>();

        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections
                = generateRandomUnitSelections(numberOfUnitSelections);

        unitSelections.addAll(UnitSelectionGenerator.generateMirrorUnitSelections(unitSelections));

        Player firstPlayer = new SimplePlayer(0);
        Player secondPlayer = new SimplePlayer(1);

        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            FitnessEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep,
                    simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, firstPlayer, secondPlayer,
                    unitSelection);
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
}
