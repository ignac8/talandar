package sandbox;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import fitnessevaluator.FitnessEvaluator;
import fitnessevaluator.SimulationEvaluator;
import jnibwapi.types.UnitType;
import neuralnetwork.FCFSNeuralNetwork;
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

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateUnitSelections;
import static util.FileUtils.saveFile;
import static util.FileUtils.saveGraphToFile;
import static util.FileUtils.toJson;

public class Research {

    public static void main(String... args) throws ExecutionException, InterruptedException, IOException {

        int passLimit = 10000;
        int searchTimeLimit = Integer.MAX_VALUE;
        int inputLayerSize = 5;
        int outputLayerSize = 15;
        int tournamentSize = 2;
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

        List<Double> crossoverChances = Doubles.asList(0.1, 0.4, 0.7);
        List<Double> mutationChances = Doubles.asList(0.001, 0.01, 0.1);
        List<Integer> populationSizes = Ints.asList(10, 100, 1000);
        int numberOfThreads = 8;
        int numberOfRetries = 10;
        int numberOfTasks = crossoverChances.size() * mutationChances.size() * populationSizes.size() * numberOfRetries;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Vector<Future<Result>> futures = new Vector<>(numberOfTasks);

        BufferedWriter writer = Files.newBufferedWriter(Paths.get("results.json"));

        for (double crossoverChance : crossoverChances) {
            for (double mutationChance : mutationChances) {
                for (int populationSize : populationSizes) {
                    for (int numberOfRetry = 0; numberOfRetry < numberOfRetries; numberOfRetry++) {
                        List<Individual> startingIndividuals = new ArrayList<>();

                        for (int counter = 0; counter < populationSize; counter++) {
                            Individual randomIndividual = new Individual(
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

                        Future<Result> future = executorService.submit(solver);
                        futures.add(future);
                    }
                }
            }
        }

        int threadCounter = 0;
        for (double crossoverChance : crossoverChances) {
            for (double mutationChance : mutationChances) {
                for (int populationSize : populationSizes) {
                    for (int numberOfRetry = 0; numberOfRetry < numberOfRetries; numberOfRetry++) {
                        Result result = futures.get(threadCounter).get();
                        String name = String.join(",",
                                Double.toString(crossoverChance),
                                Double.toString(mutationChance),
                                Integer.toString(populationSize),
                                Integer.toString(numberOfRetry));
                        saveGraphToFile(result.getPopulationFitnessStatistics(), "graphs\\" + name + ".png");
                        saveFile("neuralNetworks\\" + name + ".json", toJson(result.getIndividual().getNeuralNetwork()));
                        double totalFitness = result.getIndividual().getFitness();
                        String textResult = String.join(",", Integer.toString(threadCounter), name, Double.toString(totalFitness));
                        System.out.println(textResult);
                        writer.write(textResult);
                        writer.newLine();
                        writer.flush();
                        threadCounter++;
                    }
                }
            }
        }

        writer.close();
        executorService.shutdown();
    }
}
