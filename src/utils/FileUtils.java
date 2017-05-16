package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import neuralnetwork.MyNeuralNetwork;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.neuron.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import solver.Result;

import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.jfree.chart.ChartUtilities.saveChartAsPNG;

public class FileUtils {

    private static final Gson GSON;

    static {
        RuntimeTypeAdapterFactory<NeuralNetwork> neuralNetworkRuntimeTypeAdapterFactory =
                RuntimeTypeAdapterFactory.of(NeuralNetwork.class)
                        .registerSubtype(MyNeuralNetwork.class);

        RuntimeTypeAdapterFactory<Neuron> neuronRuntimeTypeAdapterFactory =
                RuntimeTypeAdapterFactory.of(Neuron.class)
                        .registerSubtype(InputNeuron.class)
                        .registerSubtype(CalculableNeuron.class)
                        .registerSubtype(SigmoidNeuron.class)
                        .registerSubtype(StepNeuron.class)
                        .registerSubtype(FastSigmoidNeuron.class);

        GSON = new GsonBuilder()
                .registerTypeAdapterFactory(neuralNetworkRuntimeTypeAdapterFactory)
                .registerTypeAdapterFactory(neuronRuntimeTypeAdapterFactory)
                .create();
    }

    public static List<String> loadFile(String fileName) {
        try {
            List<String> fileContent = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
            reader.close();
            return fileContent;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveFile(String fileName, String fileContent) {
        List<String> jsonList = new ArrayList<>();
        jsonList.add(fileContent);
        saveFile(fileName, jsonList);
    }

    public static void saveFile(String fileName, List<String> fileContent) {
        try {
            File file = new File(fileName);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < fileContent.size(); i++) {
                String line = fileContent.get(i);
                writer.write(line);
                if (i < fileContent.size() - 1) {
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Gson getGson() {
        return GSON;
    }

    public static <T> String toJson(T t) {
        return GSON.toJson(t);
    }

    public static <T> T fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    public static void saveGraphToFile(List<Result> results, String filename) {
        XYSeries popMin = new XYSeries("Min");
        XYSeries popAvg = new XYSeries("Avg");
        XYSeries popMax = new XYSeries("Max");
        for (int counter = 0; counter < results.size(); counter++) {
            Result result = results.get(counter);
            popMin.add(counter, result.getMin());
            popAvg.add(counter, result.getAvg());
            popMax.add(counter, result.getMax());
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(popMin);
        dataset.addSeries(popAvg);
        dataset.addSeries(popMax);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Talandar",
                "Pokolenie",
                "Ocena",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        File file = new File("graphs\\" + filename + "." + new SimpleDateFormat("YYYY_MM_dd_HH_mm_ss").format(new Date()) + ".png");
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
