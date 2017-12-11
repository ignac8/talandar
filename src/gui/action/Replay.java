package gui.action;

import fitnessevaluator.simulation.SimulationEvaluator;
import gui.updater.Logger;
import jnibwapi.types.UnitType;
import neuralnetwork.NeuralNetwork;
import player.simulation.NeuralNetworkSimulationPlayer;
import player.simulation.SimpleSimulationPlayer;
import util.Pair;

import java.util.List;

import static fitnessevaluator.simulation.unitselection.UnitSelectionGenerator.generateUnitSelections;

public class Replay implements Runnable {

    private double mapHeight = 640.0;
    private double mapWidth = 640.0;
    private double gapHeight = 40.0;
    private double gapWidth = 120.0;
    private NeuralNetworkSimulationPlayer neuralNetworkSimulationPlayer = new NeuralNetworkSimulationPlayer(0);
    private SimpleSimulationPlayer simpleSimulationPlayer = new SimpleSimulationPlayer(1);
    private NeuralNetwork neuralNetwork;
    private boolean graphics;
    private Logger logger = Logger.getInstance();
    private SimulationEvaluator simulationEvaluator = new SimulationEvaluator(graphics, 1, 10000,
            mapHeight, mapWidth, gapHeight, gapWidth, neuralNetworkSimulationPlayer, simpleSimulationPlayer);

    @Override
    public void run() {
        double totalFitness = 0;
        simulationEvaluator.setGraphics(graphics);
        neuralNetworkSimulationPlayer.setNeuralNetwork(neuralNetwork);
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
