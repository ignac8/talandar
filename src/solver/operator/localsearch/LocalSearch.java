package solver.operator.localsearch;

import solver.Individual;
import solver.Solver;
import solver.operator.Operator;
import solver.operator.mutation.SingleMutation;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.shuffle;
import static java.util.Collections.sort;

public class LocalSearch implements Operator {

    private int size;
    private Solver solver;
    private SingleMutation singleMutation;

    public LocalSearch(int neighbourSize, Solver solver, SingleMutation singleMutation) {
        this.size = neighbourSize;
        this.solver = solver;
        this.singleMutation = singleMutation;
    }

    @Override
    public List<Individual> call(List<Individual> individuals) {
        List<List<Individual>> listOfNeighbours = new ArrayList<>();
        for (int counter = 0; counter < individuals.size(); counter++) {
            Individual individual = individuals.get(counter);
            List<Individual> neighbours = generateRandomNeighbours(individual, size);
            solver.evaluate(neighbours);
            listOfNeighbours.add(neighbours);
        }
        individuals = choose(listOfNeighbours, individuals);
        return individuals;
    }

    private List<Individual> generateRandomNeighbours(Individual individual, int size) {
        List<Individual> randomNeighbours = new ArrayList<>();
        for (int counter = 0; counter < size; counter++) {
            Individual randomNeighbour = individual.copy();
            randomNeighbours.add(randomNeighbour);
        }
        singleMutation.call(randomNeighbours);
        return randomNeighbours;
    }

    protected List<Individual> choose(List<List<Individual>> listOfNeighbours, List<Individual> previousIndividuals) {
        List<Individual> result = new ArrayList<>();
        for (int counter = 0; counter < listOfNeighbours.size(); counter++) {
            List<Individual> neighbours = listOfNeighbours.get(counter);
            Individual previousIndividual = previousIndividuals.get(counter);
            shuffle(neighbours);
            sort(neighbours);
            Individual bestNeighbour = neighbours.get(0);
            Individual chosenIndividual = bestNeighbour.getFitness() > previousIndividual.getFitness() ? bestNeighbour : previousIndividual;
            result.add(chosenIndividual);
        }
        return result;
    }
}