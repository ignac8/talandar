package sandbox;

import neuralnetwork.MyNeuralNetwork;
import solver.FitnessEvaluator;
import solver.Individual;
import utils.FileUtils;

import static bwmcts.sparcraft.SparcraftUI.closeWindow;
import static utils.FileUtils.fromJson;
import static utils.FileUtils.loadFile;

public class Replay {

    public static void main(String... args) {

        String fileName = "testNeuralWeb.json";
        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(true);
        String json = loadFile(fileName).get(0);
        Individual bestOne = fromJson(json, Individual.class);
        fitnessEvaluator.evaluate(bestOne);
        closeWindow();
    }
}
