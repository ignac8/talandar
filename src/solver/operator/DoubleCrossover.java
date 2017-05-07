package solver.operator;

import solver.Individual;

import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextDouble;

public abstract class DoubleCrossover implements Operator {
    private double chance;

    public DoubleCrossover(double chance) {
        this.chance = chance;
    }

    @Override
    public List<Individual> call(List<Individual> individuals) {
        for (int counter = 0; counter < individuals.size() - 1; counter += 2) {
            Individual firstIndividual = individuals.get(counter);
            Individual secondIndividual = individuals.get(counter + 1);
            if (nextDouble(0, 1) < chance) {
                crossover(firstIndividual, secondIndividual);
            }
        }
        return individuals;
    }

    protected abstract void crossover(Individual firstIndividual, Individual secondIndividual);
}
