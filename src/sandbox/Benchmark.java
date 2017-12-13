package sandbox;

import fitnessevaluator.simulation.SimulationEvaluator;
import jnibwapi.types.UnitType;
import neuralnetwork.NeuralNetwork;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;
import player.factory.PlayerFactory;
import simulation.Position;
import simulation.SimulationState;
import simulation.Unit;
import util.Pair;

import java.util.List;

import static fitnessevaluator.simulation.unitselection.UnitSelectionGenerator.generateUnitSelections;
import static jnibwapi.Map.TILE_SIZE;
import static util.FileUtils.fromJson;
import static util.FileUtils.loadFile;

public class Benchmark {

    public static void main(String... args) {

        String fileName = "best-0.1,0.001,1000,3.json";
        String json = loadFile(fileName);
        NeuralNetwork bestOne = fromJson(json, NeuralNetwork.class);
        boolean graphics = false;
        double simulationTimeStep = 1.0;
        double simulationTimeLimit = 10000000;
        int mapHeight = TILE_SIZE * 20;
        int mapWidth = TILE_SIZE * 20;
        int gapHeight = 40;
        int gapWidth = 120;
        NeuralNetworkPlayer<SimulationState, Unit, Position> neuralNetworkPlayer = PlayerFactory.getSimulationNeuralNetworkPlayer(0);
        SimplePlayer<SimulationState, Unit, Position> simplePlayer = PlayerFactory.getSimulationSimplePlayer(1);

        double totalFitness = 0;
        SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep, simulationTimeLimit, mapHeight, mapWidth,
                gapHeight, gapWidth, neuralNetworkPlayer, simplePlayer);
        neuralNetworkPlayer.setNeuralNetwork(bestOne);
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> allUnitSelections = generateUnitSelections();
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : allUnitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            totalFitness += fitnessEvaluator.evaluate();
        }
        totalFitness /= allUnitSelections.size();

        System.out.println("Total fitness equals: " + totalFitness);
    }
}