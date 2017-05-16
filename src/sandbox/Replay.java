package sandbox;

import solver.FitnessEvaluator;
import solver.Individual;

import static utils.FileUtils.fromJson;
import static utils.FileUtils.loadFile;

public class Replay {

    public static void main(String... args) {

        String fileName = "testNeuralWeb.json";
        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(true);
        String json = loadFile(fileName).get(0);
        Individual bestOne = fromJson(json, Individual.class);

        while (true) {
            fitnessEvaluator.evaluate(bestOne);
        }

    }
}
