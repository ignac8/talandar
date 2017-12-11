package sandbox;

import fitnessevaluator.starcraft.StarcraftEvaluator;
import jnibwapi.JNIBWAPI;
import jnibwapi.Position;
import jnibwapi.Unit;
import neuralnetwork.NeuralNetwork;
import player.NeuralNetworkPlayer;
import player.factory.PlayerFactory;

import static util.FileUtils.fromJson;
import static util.FileUtils.loadFile;

public class RealEngineering {

    public static void main(String... args) {
        StarcraftEvaluator starcraftEvaluator = StarcraftEvaluator.getInstance();
        NeuralNetworkPlayer<JNIBWAPI, Unit, Position> neuralNetworkStarcraftPlayer = PlayerFactory.getStarcraftNeuralNetworkPlayer(0);
        starcraftEvaluator.setNeuralNetworkPlayer(neuralNetworkStarcraftPlayer);
        NeuralNetwork neuralNetwork = fromJson(loadFile("best-0.4,0.001,100,9.json"), NeuralNetwork.class);
        neuralNetworkStarcraftPlayer.setNeuralNetwork(neuralNetwork);
        double fitness = starcraftEvaluator.evaluate();
        System.out.println(fitness);
    }
}