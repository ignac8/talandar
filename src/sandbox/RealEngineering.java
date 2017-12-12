package sandbox;

import fitnessevaluator.starcraft.StarcraftEvaluator;
import jnibwapi.JNIBWAPI;
import jnibwapi.Position;
import jnibwapi.Unit;
import neuralnetwork.ConstantNeuralNetwork;
import neuralnetwork.NeuralNetwork;
import player.NeuralNetworkPlayer;
import player.factory.PlayerFactory;

public class RealEngineering {

    public static void main(String... args) {
        StarcraftEvaluator starcraftEvaluator = StarcraftEvaluator.getInstance();
        NeuralNetworkPlayer<JNIBWAPI, Unit, Position> neuralNetworkStarcraftPlayer = PlayerFactory.getStarcraftNeuralNetworkPlayer(0);
        starcraftEvaluator.setPlayer(neuralNetworkStarcraftPlayer);
        //NeuralNetwork neuralNetwork = fromJson(loadFile("best-0.1,0.001,1000,3.json"), NeuralNetwork.class);
        NeuralNetwork neuralNetwork = new ConstantNeuralNetwork(5, 14, 7, 5);
        neuralNetworkStarcraftPlayer.setNeuralNetwork(neuralNetwork);
        for (int counter = 0; counter < 9 * 10; counter++) {
            double fitness = starcraftEvaluator.evaluate();
            System.out.println(counter);
            System.out.println(fitness);
        }
        System.exit(0);
    }
}