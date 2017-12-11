package sandbox;

import fitnessevaluator.starcraft.StarcraftEvaluator;
import neuralnetwork.NeuralNetwork;
import player.starcraft.NeuralNetworkStarcraftPlayer;

import static util.FileUtils.fromJson;
import static util.FileUtils.loadFile;

public class RealEngineering {

    public static void main(String... args) {
        StarcraftEvaluator starcraftEvaluator = StarcraftEvaluator.getInstance();
        NeuralNetworkStarcraftPlayer neuralNetworkStarcraftPlayer = new NeuralNetworkStarcraftPlayer();
        starcraftEvaluator.setStarcraftPlayer(neuralNetworkStarcraftPlayer);
        NeuralNetwork neuralNetwork = fromJson(loadFile("best-0.4,0.001,100,9.json"), NeuralNetwork.class);
        neuralNetworkStarcraftPlayer.setNeuralNetwork(neuralNetwork);
        double fitness = starcraftEvaluator.evaluate();
        System.out.println(fitness);
    }
}