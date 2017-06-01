package gui;

import solver.Individual;

import java.util.concurrent.Callable;

import static utils.FileUtils.fromJson;
import static utils.FileUtils.loadFile;

public class Replay implements Callable<Object> {

    @Override
    public Object call() throws Exception {
        String fileName = "testNeuralWeb.json";
        //JarcraftEvaluator fitnessEvaluator = new JarcraftEvaluator(firstPlayer, limit, secondPlayer, firstPlayerUnits, secondPlayerUnits, true, mapHeight, mapWidth, gapHeight, gapWidth);
        String json = loadFile(fileName);
        Individual bestOne = fromJson(json, Individual.class);
        //NeuralNetworkPlayer neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayerUnitSelection());
        //neuralNetworkPlayer.setNeuralNetwork(bestOne.getNeuralNetwork());
        //fitnessEvaluator.solve(bestOne);
        return null;
    }
}
