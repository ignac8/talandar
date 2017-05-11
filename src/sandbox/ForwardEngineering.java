package sandbox;

import neuralnetwork.MyNeuralNetwork;
import solver.FitnessEvaluator;
import solver.Individual;
import solver.Solver;
import solver.operator.*;

import java.util.ArrayList;
import java.util.List;

import static bwmcts.sparcraft.SparcraftUI.closeWindow;

public class ForwardEngineering {
    private static int populationSize;
    private static int inputLayerSize;
    private static List<Integer> hiddenLayerSizes;
    private static int outputLayerSize;
    private static double std;
    private static double mean;
    private static List<Individual> startingIndividuals;
    private static FitnessEvaluator fitnessEvaluator;

    public static void main(String... args) {

        populationSize = 100;
        inputLayerSize = 15;
        hiddenLayerSizes = new ArrayList<>();
        hiddenLayerSizes.add(5);
        hiddenLayerSizes.add(5);
        hiddenLayerSizes.add(5);
        outputLayerSize = 3;
        std = 100;
        mean = 0;

        startingIndividuals = new ArrayList<>();

        for (int counter = 0; counter < populationSize; counter++) {
            Individual randomIndividual =
                    new Individual(new MyNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize, std, mean));
            startingIndividuals.add(randomIndividual);
        }

        List<Operator> operators = new ArrayList<>();
        operators.add(new TournamentSelection(5));
        operators.add(new NeuronCrossover(0.85));
        operators.add(new WeightMutation(0.005, new GaussianMutator(std, mean)));
        operators.add(new BiasMutation(0.005, new GaussianMutator(std, mean)));

        fitnessEvaluator = new FitnessEvaluator(false);

        Solver solver = new Solver(operators, 1000000, 15 * 1000, "asd", startingIndividuals, fitnessEvaluator);

        Individual bestOne = solver.call();

        fitnessEvaluator.setGraphics(true);
        fitnessEvaluator.evaluate(bestOne);
        fitnessEvaluator.evaluate(bestOne);
        fitnessEvaluator.evaluate(bestOne);
        fitnessEvaluator.evaluate(bestOne);
        fitnessEvaluator.evaluate(bestOne);
        fitnessEvaluator.evaluate(bestOne);
        fitnessEvaluator.evaluate(bestOne);

        closeWindow();
    }
}
