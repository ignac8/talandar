package sandbox;

import fitnessevaluator.FitnessEvaluator;
import fitnessevaluator.JarcraftEvaluator;
import fitnessevaluator.unitselection.UnitSelection;
import neuralnetwork.FCSNeuralNetwork;
import player.JarcraftPlayer;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;
import solver.Individual;
import solver.Solver;
import solver.operator.BiasMutation;
import solver.operator.NeuronCrossover;
import solver.operator.Operator;
import solver.operator.TournamentSelection;
import solver.operator.WeightMutation;
import solver.operator.crosser.AverageCrosser;
import solver.operator.mutator.GaussianMutator;

import java.util.ArrayList;
import java.util.List;

import static fitnessevaluator.unitselection.JarcraftTestCaseGenerator.generateAllTestCases;
import static fitnessevaluator.unitselection.JarcraftTestCaseGenerator.generateRandomTestCases;
import static jnibwapi.Map.TILE_SIZE;

public class ForwardEngineering {

    public static void main(String... args) {
        String fileName = "testNeuralWeb.json";
        int passLimit = Integer.MAX_VALUE;
        int timeLimit = 1 * 60 * 1000;
        int populationSize = 100;
        int inputLayerSize = 5;
        int outputLayerSize = 8;
        int tournamentSize = 2;
        double crossoverChance = 0.85;
        double weightMutationChance = 0.5;
        double biasMutationChance = 0.5;
        double initialStd = 10;
        double initialMean = 0;
        double weightStd = 1;
        double weightMean = 0;
        double biasStd = 1;
        double biasMean = 0;
        boolean graphics = false;
        int limit = 10000;
        int mapHeight = TILE_SIZE * 20;
        int mapWidth = TILE_SIZE * 20;
        int gapHeight = 40;
        int gapWidth = 120;
        int numberOfTestCases = 25;

        int[] hiddenLayerSizes = {10};

        List<Individual> startingIndividuals = new ArrayList<>();

        for (int counter = 0; counter < populationSize; counter++) {
            Individual randomIndividual =
                    new Individual(new FCSNeuralNetwork(inputLayerSize, hiddenLayerSizes,
                            outputLayerSize, initialStd, initialMean));
            startingIndividuals.add(randomIndividual);
        }

        List<Operator> operators = new ArrayList<>();
        operators.add(new TournamentSelection(tournamentSize));
        operators.add(new NeuronCrossover(crossoverChance, new AverageCrosser()));
        operators.add(new WeightMutation(weightMutationChance, new GaussianMutator(weightStd, weightMean)));
        operators.add(new BiasMutation(biasMutationChance, new GaussianMutator(biasStd, biasMean)));

        JarcraftPlayer firstPlayer = new NeuralNetworkPlayer(0);
        JarcraftPlayer secondPlayer = new SimplePlayer(1);
        List<FitnessEvaluator> fitnessEvaluators = new ArrayList<>();

        for (UnitSelection unitSelection : generateRandomTestCases(numberOfTestCases)) {
            FitnessEvaluator fitnessEvaluator = new JarcraftEvaluator(graphics, limit, mapHeight, mapWidth,
                    gapHeight, gapWidth, firstPlayer, secondPlayer, unitSelection);
            fitnessEvaluators.add(fitnessEvaluator);
        }

        Solver solver = new Solver(operators, passLimit, timeLimit, fileName, startingIndividuals, fitnessEvaluators);

        Individual bestOne = solver.solve();
        solver 

        double totalFitness = 0;

        JarcraftEvaluator fitnessEvaluator = new JarcraftEvaluator(graphics, limit, mapHeight, mapWidth,
                gapHeight, gapWidth, firstPlayer, secondPlayer, null);
        NeuralNetworkPlayer neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayer());
        neuralNetworkPlayer.setNeuralNetwork(bestOne.getNeuralNetwork());
        List<UnitSelection> allTestCases = generateAllTestCases();
        for (UnitSelection unitSelection : allTestCases) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            totalFitness += fitnessEvaluator.evaluate();
        }
        totalFitness /= allTestCases.size();

        System.out.println("Total fitness equals: " + totalFitness);
    }
}
