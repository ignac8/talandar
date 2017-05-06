package sandbox;

import solver.FitnessEvaluator;

public class ForwardEngineering {
    public static void main(String... args) {
        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator();
        double fitness = fitnessEvaluator.evaluate(null);
        System.out.println(fitness);
    }
}
