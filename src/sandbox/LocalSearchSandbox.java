package sandbox;

import fitnessevaluator.simulation.SimulationEvaluator;
import jnibwapi.types.UnitType;
import neuralnetwork.FCFSNeuralNetwork;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;
import player.factory.PlayerFactory;
import simulation.Position;
import simulation.SimulationState;
import simulation.Unit;
import solver.Individual;
import solver.Result;
import solver.Solver;
import solver.operator.Operator;
import solver.operator.localsearch.SimulatedAnnealing;
import solver.operator.mutation.NeuronMutation;
import solver.operator.mutation.mutator.GaussianAdditionMutator;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.primitives.Ints.asList;
import static fitnessevaluator.simulation.unitselection.UnitSelectionGenerator.generateAllUnitSelections;
import static fitnessevaluator.simulation.unitselection.UnitSelectionGenerator.generateUnitSelections;
import static util.FileUtils.saveFile;
import static util.FileUtils.saveGraphToFile;
import static util.FileUtils.toJson;

public class LocalSearchSandbox {

    public static void main(String... args) {

        int passLimit = 1000;
        int searchTimeLimit = 600 * 1 * 1000;
        int populationSize = 1;
        int inputLayerSize = 5;
        int outputLayerSize = 14;
        double std = 1;
        double mean = 0;
        boolean graphics = false;
        double simulationTimeStep = 1.0;
        double simulationTimeLimit = Double.MAX_VALUE;
        double mapHeight = 640.0;
        double mapWidth = 640.0;
        double gapHeight = 40.0;
        double gapWidth = 120.0;
        int neighbourSize = 1;
        double decTemp = 0.925;
        double modifier = 10;

        List<Integer> hiddenLayerSizes = asList(10);

        List<Individual> startingIndividuals = new ArrayList<>();

        for (int counter = 0; counter < populationSize; counter++) {
            Individual randomIndividual =
                    new Individual(new FCFSNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize, std, mean));
            startingIndividuals.add(randomIndividual);
        }

        NeuralNetworkPlayer<SimulationState, Unit, Position> neuralNetworkPlayer = PlayerFactory.getSimulationNeuralNetworkPlayer(0);
        SimplePlayer<SimulationState, Unit, Position> simplePlayer = PlayerFactory.getSimulationSimplePlayer(1);
        List<SimulationEvaluator> simulationEvaluators = new ArrayList<>();

        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections = generateUnitSelections();

        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep,
                    simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, neuralNetworkPlayer, simplePlayer);
            fitnessEvaluator.setUnitSelection(unitSelection);
            simulationEvaluators.add(fitnessEvaluator);
        }

        Solver solver = new Solver(passLimit, searchTimeLimit, simulationEvaluators);

        List<Operator> operators = new ArrayList<>();
        operators.add(new SimulatedAnnealing(neighbourSize, solver,
                new NeuronMutation(1, new GaussianAdditionMutator(std, mean)), decTemp, modifier, populationSize));
        solver.setOperators(operators);

        solver.setIndividuals(startingIndividuals);
        Result result = solver.call();

        saveGraphToFile(result.getPopulationFitnessStatistics(), "graphs\\testNeuralWeb.png");
        saveFile("testNeuralWeb.json", toJson(result.getIndividual().getNeuralNetwork()));

        double totalFitness = 0;

        SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(false, simulationTimeStep,
                simulationTimeLimit, mapHeight, mapWidth,
                gapHeight, gapWidth, neuralNetworkPlayer, simplePlayer);
        neuralNetworkPlayer.setNeuralNetwork(result.getIndividual().getNeuralNetwork());
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> allUnitSelections = generateAllUnitSelections();
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : allUnitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            totalFitness += fitnessEvaluator.evaluate();
        }
        totalFitness /= allUnitSelections.size();

        System.out.println("Total fitness equals: " + totalFitness);
    }
}
