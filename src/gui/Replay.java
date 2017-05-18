package gui;

import solver.FitnessEvaluator;
import solver.Individual;

import java.util.concurrent.Callable;

import static utils.FileUtils.fromJson;
import static utils.FileUtils.loadFile;

public class Replay implements Callable<Object> {

    @Override
    public Object call() throws Exception {
        String fileName = "testNeuralWeb.json";
        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(true);
        String json = loadFile(fileName).get(0);
        Individual bestOne = fromJson(json, Individual.class);
        fitnessEvaluator.evaluate(bestOne);
        return null;
    }
}
