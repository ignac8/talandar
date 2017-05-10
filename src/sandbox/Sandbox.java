package sandbox;

import solver.FitnessEvaluator;

public class Sandbox {
    public static void main(String... args) {
        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(true);
        fitnessEvaluator.playGame();
    }
}
