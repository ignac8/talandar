package sandbox;

import com.google.common.primitives.Ints;
import fitnessevaluator.FitnessEvaluator;
import fitnessevaluator.SimulationEvaluator;
import jnibwapi.types.UnitType;
import neuralnetwork.FCFSNeuralNetwork;
import neuralnetwork.NeuralNetwork;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;
import solver.Individual;
import solver.Result;
import solver.Solver;
import solver.operator.Operator;
import solver.operator.crossover.NeuronCrossover;
import solver.operator.crossover.crosser.SwapCrosser;
import solver.operator.mutation.NeuronMutation;
import solver.operator.mutation.mutator.GaussianMutator;
import solver.operator.selection.TournamentSelection;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateAllUnitSelections;
import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateUnitSelections;
import static util.FileUtils.saveFile;
import static util.FileUtils.saveGraphToFile;
import static util.FileUtils.toJson;

public class ForwardEngineering {

    public static void main(String... args) {

        int passLimit = 100;
        int searchTimeLimit = Integer.MAX_VALUE;
        int populationSize = 100;
        int inputLayerSize = 5;
        int outputLayerSize = 14;
        int tournamentSize = 2;
        double crossoverChance = 0.4;
        double mutationChance = 0.001;
        double std = 1;
        double mean = 0;
        boolean graphics = false;
        double simulationTimeStep = 1.0;
        double simulationTimeLimit = Double.MAX_VALUE;
        double mapHeight = 640.0;
        double mapWidth = 640.0;
        double gapHeight = 40.0;
        double gapWidth = 120.0;

        List<Integer> hiddenLayerSizes = Ints.asList(10);

        List<Individual> startingIndividuals = new ArrayList<>();

        for (int counter = 0; counter < populationSize; counter++) {
            Individual randomIndividual =
                    new Individual(
                            new FCFSNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize, std, mean));
            startingIndividuals.add(randomIndividual);
        }

        NeuralNetworkPlayer neuralNetworkPlayer = new NeuralNetworkPlayer(0);
        SimplePlayer simplePlayer = new SimplePlayer(1);
        List<FitnessEvaluator> fitnessEvaluators = new ArrayList<>();

        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections
                = generateUnitSelections();

        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep,
                    simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, neuralNetworkPlayer, simplePlayer);
            fitnessEvaluator.setUnitSelection(unitSelection);
            fitnessEvaluators.add(fitnessEvaluator);
        }

        Solver solver = new Solver(passLimit, searchTimeLimit, fitnessEvaluators);

        List<Operator> operators = new ArrayList<>();
        operators.add(new TournamentSelection(tournamentSize));
        operators.add(new NeuronCrossover(crossoverChance, new SwapCrosser()));
        operators.add(new NeuronMutation(mutationChance, new GaussianMutator(std, mean)));

        solver.setOperators(operators);

        solver.setIndividuals(startingIndividuals);
        Result result = solver.call();

        saveGraphToFile(result.getPopulationFitnessStatistics(), "graphs\\testNeuralWeb.png");
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
