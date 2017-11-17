package solver;

import java.util.List;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class PopulationFitnessStatistic {
    private double min;
    private double avg;
    private double max;

    public PopulationFitnessStatistic(List<Individual> individuals) {
        min = MAX_VALUE;
        max = -1 * MAX_VALUE;
        double sum = 0;
        for (Individual individual : individuals) {
            double fitness = individual.getFitness();
            sum += fitness;
            min = min(min, fitness);
            max = max(max, fitness);
        }
        avg = sum / individuals.size();
    }

    public double getMin() {
        return min;
    }

    public double getAvg() {
        return avg;
    }

    public double getMax() {
        return max;
    }
}
