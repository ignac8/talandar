package solver.operator;

import static utils.RandomUtils.nextGaussian;

public class GaussianMutator implements Mutator {

    private double std;
    private double mean;

    public GaussianMutator(double std, double mean) {
        this.std = std;
        this.mean = mean;
    }

    @Override
    public double mutate(double value) {
        return value + nextGaussian(std, mean);
    }
}
