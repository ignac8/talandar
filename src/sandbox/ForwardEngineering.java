package sandbox;

import neuralnetwork.MyNeuralNetwork;
import solver.FitnessEvaluator;
import solver.Individual;

import java.util.ArrayList;
import java.util.List;

public class ForwardEngineering {
    private static int populationSize;
    private static int inputLayerSize;
    private static List<Integer> hiddenLayerSizes;
    private static int outputLayerSize;
    private static double std;
    private static double bias;
    private static List<Individual> startingIndividuals;

    public static void main(String... args) {
        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(false);
        double fitness = fitnessEvaluator.evaluate(null);
        System.out.println(fitness);


        startingIndividuals = new ArrayList<>();

        for (int counter = 0; counter < populationSize; counter++) {
            Individual randomIndividual =
                    new Individual(new MyNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize, std, bias));
            startingIndividuals.add(randomIndividual);
        }
    }
}
