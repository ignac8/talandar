package solver.operator.mutation;

import solver.Individual;
import solver.operator.Operator;
import solver.operator.mutation.mutator.Mutator;

import java.util.List;

public abstract class SingleMutation implements Operator {
    protected double chance;
    protected Mutator mutator;

    public SingleMutation(double chance, Mutator mutator) {
        this.chance = chance;
        this.mutator = mutator;
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
