package sandbox;

import fitnessevaluator.SimulationEvaluator;
import jnibwapi.types.UnitType;
import neuralnetwork.FCFSNeuralNetwork;
import neuralnetwork.NeuralNetwork;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;
import util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.primitives.Ints.asList;
import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateAllUnitSelections;
import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateMirrorUnitSelections;
import static java.util.Collections.shuffle;
import static org.jfree.chart.ChartUtilities.saveChartAsPNG;

public class UnitSelectionsTesting {

    public static void main(String... args) {

        int inputLayerSize = 5;
        int outputLayerSize = 15;
        double std = 1000;
        double mean = 0;
        boolean graphics = false;
        double simulationTimeStep = 1.0;
        double simulationTimeLimit = Double.MAX_VALUE;
        double mapHeight = 640.0;
        double mapWidth = 640.0;
        double gapHeight = 40.0;
        double gapWidth = 120.0;

        List<Integer> hiddenLayerSizes = asList(10);

        NeuralNetworkPlayer neuralNetworkPlayer = new NeuralNetworkPlayer(0);
        NeuralNetwork neuralNetwork = new FCFSNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize, std, mean);
        neuralNetworkPlayer.setNeuralNetwork(neuralNetwork);

        SimplePlayer simplePlayer = new SimplePlayer(1);

        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> allUnitSelections = generateAllUnitSelections();
        shuffle(allUnitSelections);
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> mirrorUnitSelections = generateMirrorUnitSelections(allUnitSelections);

        SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep,
                simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, neuralNetworkPlayer, simplePlayer);

        XYSeries pop = new XYSeries("Pop");

        List<Double> allFitness = new ArrayList<>();
        List<Double> mirrorFitness = new ArrayList<>();

        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : allUnitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            allFitness.add(fitnessEvaluator.evaluate());
        }
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : mirrorUnitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            mirrorFitness.add(fitnessEvaluator.evaluate());
        }

        for (int i = 1; i <= allUnitSelections.size(); i++) {
            double fitness = 0;
            for (int j = 0; j < i; j++) {
                fitness += allFitness.get(j);
                fitness += mirrorFitness.get(j);
            }
            fitness /= i * 2;
            pop.add(i, fitness);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(pop);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Talandar",
                "Number of unit selections",
                "Fitness",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        File file = new File("graph.png");
        try {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            saveChartAsPNG(file, chart, 1200, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
