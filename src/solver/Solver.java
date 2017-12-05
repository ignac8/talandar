package solver;

import fitnessevaluator.FitnessEvaluator;
import player.NeuralNetworkPlayer;
import solver.operator.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static java.lang.System.currentTimeMillis;

public class Solver implements Callable<Result> {

    private List<Operator> operators;
    private int passLimit;
    private long timeStart;
    private long timeLimit;
    private Individual bestIndividual;
    private List<PopulationFitnessStatistic> populationFitnessStatistics;
    private List<FitnessEvaluator> fitnessEvaluators;
    private int passCount;
    private List<Individual> individuals;

    public Solver(int passLimit, long timeLimit, List<FitnessEvaluator> fitnessEvaluators) {
        this.populationFitnessStatistics = new ArrayList<>();
        this.timeStart = currentTimeMillis();
        this.passLimit = passLimit;
        this.timeLimit = timeLimit;
        this.fitnessEvaluators = fitnessEvaluators;
    }

    public Result call() {
        this.passCount = 0;
        evaluate(individuals);
        while (!done()) {
            for (Operator operator : operators) {
                individuals = operator.call(individuals);
            }
            evaluate(individuals);
            populationFitnessStatistics.add(new PopulationFitnessStatistic(individuals));
        }
        return new Result(bestIndividual, populationFitnessStatistics);
    }

    public void evaluate(List<Individual> individuals) {
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
    }

    private boolean done() {
        return currentTimeMillis() - timeStart > timeLimit || passCount > passLimit;
    }

    public void setOperators(List<Operator> operators) {
        this.operators = operators;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }
}
