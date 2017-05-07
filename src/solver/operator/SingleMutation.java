package solver.operator;

import solver.Individual;

import java.util.List;

public abstract class SingleMutation implements Operator {
    protected double chance;

    public SingleMutation(double chance) {
        this.chance = chance;
    }

    @Override
    public List<Individual> call(List<Individual> individuals) {
        for (Individual individual : individuals) {
            mutation(individual);
        }
        return individuals;
    }

    protected abstract void mutation(Individual individual);
}
