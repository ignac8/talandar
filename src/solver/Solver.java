package solver;

import fitnessevaluator.FitnessEvaluator;
import player.NeuralNetworkPlayer;
import solver.operator.Operator;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;
import static utils.FileUtils.saveFile;
import static utils.FileUtils.saveGraphToFile;
import static utils.FileUtils.toJson;

public class Solver {

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
        this.passCount = 0;
        evaluate();
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

    public Individual solve() {
        while (!done()) {
            for (Operator operator : operators) {
                individuals = operator.call(individuals);
            }
            evaluate();
        }
        return bestIndividual;
    }

    private boolean done() {
        return currentTimeMillis() - timeStart > timeLimit || passCount > passLimit;
    }

    public void graph(String fileName) {
        saveGraphToFile(results, fileName);
    }

    public void save(String fileName) {
        String json = toJson(bestIndividual.getNeuralNetwork());
        saveFile(fileName, json);
    }
}
