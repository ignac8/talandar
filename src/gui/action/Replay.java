package gui.action;

import fitnessevaluator.SimulationEvaluator;
import gui.updater.Logger;
import jnibwapi.types.UnitType;
import neuralnetwork.NeuralNetwork;
import player.NeuralNetworkPlayer;
import player.Player;
import player.SimplePlayer;
import util.Pair;

import java.util.List;

import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateUnitSelections;

public class Replay implements Runnable {

    private double mapHeight = 640.0;
    private double mapWidth = 640.0;
    private double gapHeight = 40.0;
    private double gapWidth = 120.0;
    private Player firstPlayer = new NeuralNetworkPlayer(0);
    private Player secondPlayer = new SimplePlayer(1);
    private NeuralNetwork neuralNetwork;
    private boolean graphics;
    private Logger logger = Logger.getInstance();
    private SimulationEvaluator simulationEvaluator = new SimulationEvaluator(graphics, 1, 10000,
            mapHeight, mapWidth, gapHeight, gapWidth, firstPlayer, secondPlayer);

    @Override
    public void run() {
        double totalFitness = 0;
        simulationEvaluator.setGraphics(graphics);
        ((NeuralNetworkPlayer) firstPlayer).setNeuralNetwork(neuralNetwork);
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections = generateUnitSelections();
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            simulationEvaluator.setUnitSelection(unitSelection);
            totalFitness += simulationEvaluator.evaluate();
        }
        totalFitness /= unitSelections.size();
        logger.log("Total fitness equals: " + totalFitness);
    }

    public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public void setGraphics(boolean graphics) {
        this.graphics = graphics;
    }

}
