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
    private List<Double> hiddenTemps;

    public SimulatedAnnealing(int neighbourSize, Solver solver, SingleMutation singleMutation, double decTemp, double modifier, int populationSize) {
        super(neighbourSize, solver, singleMutation);
        this.decTemp = decTemp;
        currTemps = new ArrayList<>();
        maxFitnesses = new ArrayList<>();
        hiddenTemps = new ArrayList<>();
        Double temp = 2 * modifier;
        for (int counter = 0; counter < populationSize; counter++) {
            hiddenTemps.add(temp);
            currTemps.add(temp);
            maxFitnesses.add(-Double.MAX_VALUE);
        }
    }

    @Override
    protected List<Individual> choose(List<List<Individual>> listOfNeighbours, List<Individual> previousIndividuals) {
        List<Individual> result = new ArrayList<>();
        for (int outerCounter = 0; outerCounter < listOfNeighbours.size(); outerCounter++) {
            Double currTemp = currTemps.get(outerCounter);
            Double maxFitness = maxFitnesses.get(outerCounter);
            Double hiddenTemp = hiddenTemps.get(outerCounter);
            List<Individual> neighbours = listOfNeighbours.get(outerCounter);
            Individual previousIndividual = previousIndividuals.get(outerCounter);
            shuffle(neighbours);
            Individual chosenIndividual = previousIndividual;
            for (int counter = 0; counter < neighbours.size() && currTemp > 0; counter++) {
                Individual proposedIndividual = neighbours.get(counter);
                double prob = exp((chosenIndividual.getFitness() - proposedIndividual.getFitness()) / currTemp);
                if (proposedIndividual.getFitness() > chosenIndividual.getFitness() || nextDouble(0, 1) < prob) {
                    chosenIndividual = proposedIndividual;
                }
            }
            double currentFitness = chosenIndividual.getFitness();
            if (maxFitness == 0 || currentFitness > maxFitness) {
                maxFitness = currentFitness;
            }
            hiddenTemp *= decTemp;
            currTemp = hiddenTemp * (1 + (maxFitness - currentFitness) / currentFitness);
            if (currTemp < 0) currTemp = 0.0;
            result.add(chosenIndividual);
            currTemps.set(outerCounter, currTemp);
            maxFitnesses.set(outerCounter, maxFitness);
            hiddenTemps.set(outerCounter, hiddenTemp);
        }
        return result;
    }
}