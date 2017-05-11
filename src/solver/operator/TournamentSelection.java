package solver.operator;

import solver.Individual;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public final class TournamentSelection implements Operator {

    private int tournamentSize;

    public TournamentSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    @Override
    public List<Individual> call(List<Individual> individuals) {
        int numberOfIndividuals = individuals.size();
        List<Individual> newIndividuals = new ArrayList<>();
        for (int outerCounter = 0; outerCounter < numberOfIndividuals; outerCounter++) {
            Individual individualToAdd = individuals.get(nextInt(0, numberOfIndividuals));
            for (int innerCounter = 1; innerCounter < tournamentSize; innerCounter++) {
                Individual newIndividual = individuals.get(nextInt(0, numberOfIndividuals));
                if (newIndividual.getFitness() > individualToAdd.getFitness()) {
                    individualToAdd = newIndividual;
                }
            }
            Individual clonedIndividual = individualToAdd.copy();
            newIndividuals.add(clonedIndividual);
        }
        return newIndividuals;
    }
}
