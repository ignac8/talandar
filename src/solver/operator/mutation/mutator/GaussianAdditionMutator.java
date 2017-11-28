package solver.operator.mutation.mutator;

import static util.RandomUtils.nextGaussian;

public class GaussianAdditionMutator implements Mutator {

    private double std;
    private double mean;

    public GaussianAdditionMutator(double std, double mean) {
        this.std = std;
        this.mean = mean;
    }

    @Override
    public double mutate(double value) {
        return value + nextGaussian(std, mean);
    }
}
