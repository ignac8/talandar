package solver;

import player.NeuralNetworkPlayer;
import solver.fitnessevaluator.FitnessEvaluator;
import solver.operator.Operator;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static java.lang.System.currentTimeMillis;
import static utils.FileUtils.saveFile;
import static utils.FileUtils.saveGraphToFile;
import static utils.FileUtils.toJson;

public class Solver implements Callable<Individual> {

    private List<Individual> individuals;
    private List<Operator> operators;
    private int passLimit;
    private long timeStart;
    private long timeLimit;
    private Individual bestIndividual;
    private List<Result> results;
    private String fileName;
    private List<FitnessEvaluator> fitnessEvaluators;
    private int passCount;

    public Solver(List<Operator> operators, int passLimit, long timeLimit, String fileName,
                  List<Individual> startingIndividuals, List<FitnessEvaluator> fitnessEvaluators) {
        this.individuals = startingIndividuals;
        this.results = new ArrayList<>();
        this.operators = operators;
        this.timeStart = currentTimeMillis();
        this.passLimit = passLimit;
        this.timeLimit = timeLimit;
        this.fileName = fileName;
        this.fitnessEvaluators = fitnessEvaluators;
        passCount = 0;
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
            double fitness = 0;
            for (FitnessEvaluator fitnessEvaluator : fitnessEvaluators) {
                NeuralNetworkPlayer neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayer());
                neuralNetworkPlayer.setNeuralNetwork(individual.getNeuralNetwork());
                fitness += fitnessEvaluator.evaluate();
            }
            fitness /= fitnessEvaluators.size();
            individual.setFitness(fitness);
            if (bestIndividual == null || individual.getFitness() > bestIndividual.getFitness()) {
                bestIndividual = individual.copy();
            }
            passCount++;
        }
        results.add(new Result(individuals));
    }

    private boolean done() {
        return currentTimeMillis() - timeStart > timeLimit || passCount > passLimit;
    }

    private void graph() {
        saveGraphToFile(results, fileName);
    }

    private void save() {
        String json = toJson(bestIndividual.getNeuralNetwork());
        saveFile(fileName, json);
    }
}
