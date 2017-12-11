package sandbox;

import fitnessevaluator.simulation.SimulationEvaluator;
import jnibwapi.types.UnitType;
import neuralnetwork.FCFSNeuralNetwork;
import neuralnetwork.NeuralNetwork;
import player.simulation.NeuralNetworkSimulationPlayer;
import player.simulation.SimpleSimulationPlayer;
import solver.Individual;
import solver.Result;
import solver.Solver;
import solver.operator.Operator;
import solver.operator.crossover.NeuronCrossover;
import solver.operator.crossover.crosser.SwapCrosser;
import solver.operator.localsearch.SimulatedAnnealing;
import solver.operator.mutation.NeuronMutation;
import solver.operator.mutation.mutator.GaussianAdditionMutator;
import solver.operator.mutation.mutator.GaussianMutator;
import solver.operator.selection.TournamentSelection;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.primitives.Ints.asList;
import static fitnessevaluator.simulation.unitselection.UnitSelectionGenerator.generateAllUnitSelections;
import static fitnessevaluator.simulation.unitselection.UnitSelectionGenerator.generateUnitSelections;
import static util.FileUtils.saveFile;
import static util.FileUtils.saveGraphToFile;
import static util.FileUtils.toJson;

public class Hybrid {
    public static void main(String... args) {
        int passLimit = 10000;
        int searchTimeLimit = 600 * 1 * 1000;
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
        int tournamentSize = 2;
        double crossoverChance = 0.1;
        double mutationChance = 0.01;
        List<Integer> hiddenLayerSizes = asList(10);
        int populationSize = 100;

        List<Individual> startingIndividuals = new ArrayList<>();

        Individual randomIndividual =
                new Individual(new FCFSNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize, std, mean));
        startingIndividuals.add(randomIndividual);

        NeuralNetworkSimulationPlayer neuralNetworkPlayer = new NeuralNetworkSimulationPlayer(0);
        SimpleSimulationPlayer simplePlayer = new SimpleSimulationPlayer(1);
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
                new NeuronMutation(1, new GaussianAdditionMutator(std, mean)), decTemp, modifier, 1));
        solver.setOperators(operators);

        solver.setIndividuals(startingIndividuals);
        Result result = solver.call();

        saveGraphToFile(result.getPopulationFitnessStatistics(), "graphs\\sa.png");
        saveFile("testNeuralWeb.json", toJson(result.getIndividual().getNeuralNetwork()));

        List<Individual> clonedIndividuals = new ArrayList<>();
        for (int counter = 0; counter < populationSize; counter++) {
            Individual clonedIndividual =
                    new Individual(result.getIndividual().getNeuralNetwork().copy());
            clonedIndividuals.add(clonedIndividual);
        }

        operators = new ArrayList<>();
        operators.add(new TournamentSelection(tournamentSize));
        operators.add(new NeuronCrossover(crossoverChance, new SwapCrosser()));
        operators.add(new NeuronMutation(mutationChance, new GaussianMutator(std, mean)));
        solver.setOperators(operators);

        solver.setIndividuals(clonedIndividuals);
        result = solver.call();
        saveGraphToFile(result.getPopulationFitnessStatistics(), "graphs\\sa.png");
        saveFile("testNeuralWeb.json", toJson(result.getIndividual().getNeuralNetwork()));

        double totalFitness;
        SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(false, simulationTimeStep,
                simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, neuralNetworkPlayer, simplePlayer);
        NeuralNetwork neuralNetwork = result.getIndividual().getNeuralNetwork();
        neuralNetworkPlayer.setNeuralNetwork(neuralNetwork);

        totalFitness = 0;
        unitSelections = generateAllUnitSelections();
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            totalFitness += fitnessEvaluator.evaluate();
        }
        totalFitness /= unitSelections.size();
        System.out.println("Total fitness equals: " + totalFitness);

        totalFitness = 0;
        unitSelections = generateUnitSelections();
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            totalFitness += fitnessEvaluator.evaluate();
        }
        totalFitness /= unitSelections.size();
        System.out.println("Total fitness equals: " + totalFitness);
    }
}
