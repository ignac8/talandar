package solver;

import fitnessevaluator.FitnessEvaluator;
import player.NeuralNetworkPlayer;
import solver.operator.Operator;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class Solver {

    private List<Individual> individuals;
    private List<Operator> operators;
    private int passLimit;
    private long timeStart;
    private long timeLimit;
    private Individual bestIndividual;
    private List<PopulationFitnessStatistic> populationFitnessStatistics;
    private List<FitnessEvaluator> fitnessEvaluators;
    private int passCount;

    public Solver(List<Operator> operators, int passLimit, long timeLimit,
                  List<Individual> startingIndividuals, List<FitnessEvaluator> fitnessEvaluators) {
        this.individuals = startingIndividuals;
        this.populationFitnessStatistics = new ArrayList<>();
        this.operators = operators;
        this.timeStart = currentTimeMillis();
        this.passLimit = passLimit;
        this.timeLimit = timeLimit;
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
        populationFitnessStatistics.add(new PopulationFitnessStatistic(individuals));
    }

    public Result solve() {
        while (!done()) {
            for (Operator operator : operators) {
                individuals = operator.call(individuals);
            }
            evaluate();
        }
        return new Result(bestIndividual.getNeuralNetwork(), populationFitnessStatistics);
    }

    private boolean done() {
        return currentTimeMillis() - timeStart > timeLimit || passCount > passLimit;
    }

}
