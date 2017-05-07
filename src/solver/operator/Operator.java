package solver.operator;

import solver.Individual;

import java.util.List;

public interface Operator {

    List<Individual> call(List<Individual> individuals);
}
