package sandbox;

import neuralnetwork.MyNeuralNetwork;
import solver.FitnessEvaluator;
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

public class ForwardEngineering {

    public static void main(String... args) {

        String fileName = "testNeuralWeb.json";
        int passLimit = 25 * 1000;
        int timeLimit = 10 * 60 * 1000;
        int populationSize = 1000;
        int inputLayerSize = 5;
        int outputLayerSize = 4;
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

        List<Integer> hiddenLayerSizes = new ArrayList<>();
        hiddenLayerSizes.add(20);

        List<Individual> startingIndividuals = new ArrayList<>();

        for (int counter = 0; counter < populationSize; counter++) {
            Individual randomIndividual =
                    new Individual(new MyNeuralNetwork(inputLayerSize, hiddenLayerSizes,
                            outputLayerSize, initialStd, initialMean));
            startingIndividuals.add(randomIndividual);
        }

        List<Operator> operators = new ArrayList<>();
        operators.add(new TournamentSelection(tournamentSize));
        operators.add(new NeuronCrossover(crossoverChance, new AverageCrosser()));
        operators.add(new WeightMutation(weightMutationChance, new GaussianMutator(weightStd, weightMean)));
        operators.add(new BiasMutation(biasMutationChance, new GaussianMutator(biasStd, biasMean)));

        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(false);

        Solver solver = new Solver(operators, passLimit, timeLimit, fileName, startingIndividuals, fitnessEvaluator);

        solver.call();


    }
}
