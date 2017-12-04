package sandbox;

import fitnessevaluator.FitnessEvaluator;
import fitnessevaluator.SimulationEvaluator;
import fitnessevaluator.unitselection.UnitSelectionGenerator;
import jnibwapi.types.UnitType;
import neuralnetwork.FCFSNeuralNetwork;
import neuralnetwork.NHLNeuralNetwork;
import neuralnetwork.NeuralNetwork;
import player.NeuralNetworkPlayer;
import player.Player;
import player.SimplePlayer;
import solver.Individual;
import solver.Result;
import solver.Solver;
import solver.operator.Operator;
import solver.operator.crossover.NeuronCrossover;
import solver.operator.crossover.crosser.AverageCrosser;
import solver.operator.mutation.NeuronMutation;
import solver.operator.mutation.mutator.GaussianMutator;
import solver.operator.selection.TournamentSelection;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.primitives.Ints.asList;
import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateAllUnitSelections;
import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateUnitSelections;
import static util.FileUtils.saveFile;
import static util.FileUtils.saveGraphToFile;
import static util.FileUtils.toJson;

public class ForwardEngineering {

    public static void main(String... args) {

        int passLimit = 10000;
        int searchTimeLimit = Integer.MAX_VALUE;
        int populationSize = 1000;
        int inputLayerSize = 5;
        int outputLayerSize = 15;
        int tournamentSize = 2;
        double crossoverChance = 0;
        double mutationChance = 0.01;
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

        List<Individual> startingIndividuals = new ArrayList<>();

        for (int counter = 0; counter < populationSize; counter++) {
            Individual randomIndividual =
                    new Individual(
                            new FCFSNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize, std, mean));
                            //new NHLNeuralNetwork(inputLayerSize, outputLayerSize, std, mean));
            startingIndividuals.add(randomIndividual);
        }

        Player firstPlayer = new NeuralNetworkPlayer(0);
        Player secondPlayer = new SimplePlayer(1);
        List<FitnessEvaluator> fitnessEvaluators = new ArrayList<>();

        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections
                = generateUnitSelections();

        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep,
                    simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, firstPlayer, secondPlayer);
            fitnessEvaluator.setUnitSelection(unitSelection);
            fitnessEvaluators.add(fitnessEvaluator);
        }

        Solver solver = new Solver(passLimit, searchTimeLimit, fitnessEvaluators);

        List<Operator> operators = new ArrayList<>();
        operators.add(new TournamentSelection(tournamentSize));
        operators.add(new NeuronCrossover(crossoverChance, new AverageCrosser()));
        operators.add(new NeuronMutation(mutationChance, new GaussianMutator(std, mean)));

        solver.setOperators(operators);

        Result result = solver.solve(startingIndividuals);

        saveGraphToFile(result.getPopulationFitnessStatistics(), "graphs\\testNeuralWeb.png");
        saveFile("testNeuralWeb.json", toJson(result.getNeuralNetwork()));

        double totalFitness = 0;
        SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(false, simulationTimeStep,
                simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, firstPlayer, secondPlayer);
        NeuralNetworkPlayer neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayer());
        NeuralNetwork neuralNetwork = result.getNeuralNetwork();
        neuralNetworkPlayer.setNeuralNetwork(neuralNetwork);
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> allUnitSelections = generateAllUnitSelections();
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : allUnitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            totalFitness += fitnessEvaluator.evaluate();
        }
        totalFitness /= allUnitSelections.size();
        System.out.println("Total fitness equals: " + totalFitness);

        totalFitness = 0;
        fitnessEvaluator = new SimulationEvaluator(false, simulationTimeStep,
                simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, firstPlayer, secondPlayer);
        neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayer());
        neuralNetwork = result.getNeuralNetwork();
        neuralNetworkPlayer.setNeuralNetwork(neuralNetwork);
        unitSelections = generateUnitSelections();
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            totalFitness += fitnessEvaluator.evaluate();
        }
        totalFitness /= allUnitSelections.size();

        System.out.println("Total fitness equals: " + totalFitness);
    }
}
