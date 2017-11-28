package solver.operator.mutation.mutator;

import static util.RandomUtils.nextGaussian;

public class GaussianMutator implements Mutator {

    private double std;
    private double mean;

    public GaussianMutator(double std, double mean) {
        this.std = std;
        this.mean = mean;
    }

    @Override
    public double mutate(double value) {
        return nextGaussian(std, mean);
    }
}
