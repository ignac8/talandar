package sandbox;

import neuralnetwork.FCSNeuralNetwork;
import player.JarcraftPlayer;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;
import solver.Individual;
import solver.Solver;
import solver.fitnessevaluator.FitnessEvaluator;
import solver.fitnessevaluator.JarcraftEvaluator;
import solver.fitnessevaluator.unitselection.UnitSelection;
import solver.operator.BiasMutation;
import solver.operator.NeuronCrossover;
import solver.operator.Operator;
import solver.operator.TournamentSelection;
import solver.operator.WeightMutation;
import solver.operator.crosser.AverageCrosser;
import solver.operator.mutator.GaussianMutator;

import java.util.ArrayList;
import java.util.List;

import static jnibwapi.Map.TILE_SIZE;
import static solver.fitnessevaluator.unitselection.JarcraftTestCaseGenerator.generateRandomTestCases;

public class ForwardEngineering {

    public static void main(String... args) {
        String fileName = "testNeuralWeb.json";
        int passLimit = 10000;
        int timeLimit = 25 * 60 * 100000;
        int populationSize = 10000;
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
        double biasStd = 100;
        double biasMean = 0;
        boolean graphics = false;
        int limit = 10000;
        int mapHeight = TILE_SIZE * 20;
        int mapWidth = TILE_SIZE * 20;
        int gapHeight = 40;
        int gapWidth = 120;

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

        for (UnitSelection unitSelection : generateRandomTestCases(5)) {
            FitnessEvaluator fitnessEvaluator = new JarcraftEvaluator(graphics, limit, mapHeight, mapWidth,
                    gapHeight, gapWidth, firstPlayer, secondPlayer, unitSelection);
            fitnessEvaluators.add(fitnessEvaluator);
        }

        Solver solver = new Solver(operators, passLimit, timeLimit, fileName, startingIndividuals, fitnessEvaluators);

        solver.solve();
    }
}
