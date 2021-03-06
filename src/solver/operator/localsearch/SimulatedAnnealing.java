package solver.operator.localsearch;

import solver.Individual;
import solver.Solver;
import solver.operator.mutation.SingleMutation;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.exp;
import static java.util.Collections.shuffle;
import static org.apache.commons.lang3.RandomUtils.nextDouble;

public class SimulatedAnnealing extends LocalSearch {

    private List<Double> currTemps;
    private double decTemp;
    private List<Double> maxFitnesses;

    public SimulatedAnnealing(int neighbourSize, Solver solver, SingleMutation singleMutation, double decTemp, double modifier, int populationSize) {
        super(neighbourSize, solver, singleMutation);
        this.decTemp = decTemp;
        currTemps = new ArrayList<>();
        maxFitnesses = new ArrayList<>();
        for (int counter = 0; counter < populationSize; counter++) {
            currTemps.add(modifier);
            maxFitnesses.add(-Double.MAX_VALUE);
        }
    }

    @Override
    protected List<Individual> choose(List<List<Individual>> listOfNeighbours, List<Individual> previousIndividuals) {
        List<Individual> result = new ArrayList<>();
        for (int outerCounter = 0; outerCounter < listOfNeighbours.size(); outerCounter++) {
            Double currTemp = currTemps.get(outerCounter);
            Double maxFitness = maxFitnesses.get(outerCounter);
            List<Individual> neighbours = listOfNeighbours.get(outerCounter);
            Individual previousIndividual = previousIndividuals.get(outerCounter);
            shuffle(neighbours);
            Individual chosenIndividual = previousIndividual;
            for (int innerCounter = 0; innerCounter < neighbours.size() && currTemp > 0; innerCounter++) {
                Individual proposedIndividual = neighbours.get(innerCounter);
                double prob = exp((proposedIndividual.getFitness() - chosenIndividual.getFitness()) / currTemp);
                if (proposedIndividual.getFitness() > chosenIndividual.getFitness() || nextDouble(0, 1) < prob) {
                    chosenIndividual = proposedIndividual;
                }
            }
            double currentFitness = chosenIndividual.getFitness();
            if (maxFitness == 0 || currentFitness > maxFitness) {
                maxFitness = currentFitness;
            }
            currTemp *= decTemp;
            if (currTemp < 0) currTemp = 0.0;
            result.add(chosenIndividual);
            currTemps.set(outerCounter, currTemp);
            maxFitnesses.set(outerCounter, maxFitness);
        }
        return result;
    }
}