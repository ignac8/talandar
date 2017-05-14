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

import static bwmcts.sparcraft.SparcraftUI.closeWindow;

public class ForwardEngineering {

    public static void main(String... args) {

        String fileName = "asd";
        int passLimit = 100000;
        int timeLimit = 15 * 1000;
        int populationSize = 10;
        int inputLayerSize = 2;
        int outputLayerSize = 3;
        int tournamentSize = 1;
        double crossoverChance = 0.85;
        double weightMutationChance = 0.05;
        double biasMutationChance = 0.05;
        double initialStd = 10;
        double initialMean = 0;
        double weightStd = 1;
        double weightMean = 0;
        double biasStd = 1;
        double biasMean = 0;

        List<Integer> hiddenLayerSizes = new ArrayList<>();
        hiddenLayerSizes.add(3);

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

        Individual bestOne = solver.call();

//        fitnessEvaluator.setGraphics(true);
//        fitnessEvaluator.evaluate(bestOne);
//        fitnessEvaluator.evaluate(bestOne);
//        fitnessEvaluator.evaluate(bestOne);
//        fitnessEvaluator.evaluate(bestOne);
//        fitnessEvaluator.evaluate(bestOne);
//        fitnessEvaluator.evaluate(bestOne);
//        fitnessEvaluator.evaluate(bestOne);

        closeWindow();
    }
}
