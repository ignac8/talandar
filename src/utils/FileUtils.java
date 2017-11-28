package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import neuralnetwork.FCFSNeuralNetwork;
import neuralnetwork.FCSNeuralNetwork;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.neuron.CalculableNeuron;
import neuralnetwork.neuron.FastSigmoidNeuron;
import neuralnetwork.neuron.InputNeuron;
import neuralnetwork.neuron.Neuron;
import neuralnetwork.neuron.SigmoidNeuron;
import neuralnetwork.neuron.StepNeuron;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import solver.PopulationFitnessStatistic;
import utils.serializer.FCFSNeuralNetworkSerializer;
import utils.serializer.FCSNeuralNetworkSerializer;
import utils.serializer.NeuralNetworkSerializer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import static org.jfree.chart.ChartUtilities.saveChartAsPNG;

public class FileUtils {

    private static final Gson GSON;

    static {
        GSON = new GsonBuilder()
                .registerTypeAdapter(NeuralNetwork.class, new NeuralNetworkSerializer())
                .registerTypeAdapter(FCSNeuralNetwork.class, new FCSNeuralNetworkSerializer())
                .registerTypeAdapter(FCFSNeuralNetwork.class, new FCFSNeuralNetworkSerializer())
                .create();
    }

    public static String loadFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
            String fileContent = reader.readLine();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveFile(String filePath, String fileContent) {
        try {
            File file = new File(filePath);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(fileContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> String toJson(T t) {
        return GSON.toJson(t);
    }

    public static <T> T fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    public static void saveGraphToFile(List<PopulationFitnessStatistic> populationFitnessStatistics, String fileName) {
        XYSeries popMin = new XYSeries("Min");
        XYSeries popAvg = new XYSeries("Avg");
        XYSeries popMax = new XYSeries("Max");
        for (int counter = 0; counter < populationFitnessStatistics.size(); counter++) {
            PopulationFitnessStatistic populationFitnessStatistic = populationFitnessStatistics.get(counter);
            popMin.add(counter, populationFitnessStatistic.getMin());
            popAvg.add(counter, populationFitnessStatistic.getAvg());
            popMax.add(counter, populationFitnessStatistic.getMax());
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(popMin);
        dataset.addSeries(popAvg);
        dataset.addSeries(popMax);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Talandar",
                "Generation",
                "Fitness",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        File file = new File(fileName);
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
