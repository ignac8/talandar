package solver;

import neuralnetwork.MyNeuralNetwork;
import solver.operator.Operator;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static java.lang.System.currentTimeMillis;
import static utils.FileUtils.saveGraphToFile;
import static utils.FileUtils.toJson;

public class Solver implements Callable<Individual> {

    private List<Individual> individuals;
    private List<Operator> operators;
    private int passCounter;
    private int passLimit;
    private long timeStart;
    private long timeLimit;
    private Individual bestIndividual;
    private List<Result> results;
    private String fileName;
    private FitnessEvaluator fitnessEvaluator;

    public Solver(int populationSize, List<Operator> operators, int passLimit, long timeLimit, String fileName,
                  int inputLayerSize, List<Integer> hiddenLayerSizes, int outputLayerSize, double std, double bias,
                  FitnessEvaluator fitnessEvaluator) {
        individuals = new ArrayList<>(populationSize);
        results = new ArrayList<>();
        this.operators = operators;
        passCounter = 0;
        timeStart = currentTimeMillis();
        this.passLimit = passLimit;
        this.timeLimit = timeLimit;
        this.fileName = fileName;
        this.fitnessEvaluator = fitnessEvaluator;

        for (int counter = 0; counter < populationSize; counter++) {
            Individual randomIndividual =
                    new Individual(new MyNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize, std, bias));
            individuals.add(randomIndividual);
        }
        evaluate();
    }

    public Individual call() {
        while (!done()) {
            for (Operator operator : operators) {
                individuals = operator.call(individuals);
            }
            evaluate();
        }
        graph();
        save();
        return bestIndividual;
    }


    private void evaluate() {
        for (Individual individual : individuals) {
            individual.setFitness(fitnessEvaluator.evaluate(individual));
            if (bestIndividual == null || individual.getFitness() < bestIndividual.getFitness()) {
                bestIndividual = individual.copy();
            }
        }
        results.add(new Result(individuals));
    }

    private boolean done() {
        return currentTimeMillis() - timeStart > timeLimit || passCounter++ > passLimit;
    }

    private void graph() {
        saveGraphToFile(results, fileName);
    }

    private void save() {
        String json = toJson(bestIndividual);
        List<String> jsonList = new ArrayList<>();
        jsonList.add(json);
        FileUtils.saveFile(fileName, jsonList);
    }
}
