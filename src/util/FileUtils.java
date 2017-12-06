package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import neuralnetwork.FCFSNeuralNetwork;
import neuralnetwork.NeuralNetwork;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import solver.PopulationFitnessStatistic;
import util.serializer.FCFSNeuralNetworkSerializer;
import util.serializer.NeuralNetworkSerializer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.*;
import static org.jfree.chart.ChartUtilities.saveChartAsPNG;

public class FileUtils {

    private static final Gson GSON;

    static {
        GSON = new GsonBuilder()
                .registerTypeAdapter(NeuralNetwork.class, new NeuralNetworkSerializer())
                .registerTypeAdapter(FCFSNeuralNetwork.class, new FCFSNeuralNetworkSerializer())
                .create();
    }

    public static String loadFile(String filePath) {
        try {
            BufferedReader reader = newBufferedReader(Paths.get(filePath));
            String fileContent = reader.readLine();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveFile(String filePath, String fileContent) {
        try {
            Path path = Paths.get(filePath);
            createDirectories(path.getParent());
            if (notExists(path)) {
                createFile(path);
            }
            BufferedWriter writer = newBufferedWriter(path);
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
