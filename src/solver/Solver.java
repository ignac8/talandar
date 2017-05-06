package solver;

import neuralnetwork.MyNeuralNetwork;
import neuralnetwork.NeuralNetwork;
import solver.operator.Operator;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static java.lang.System.currentTimeMillis;

import static utils.FileUtils.saveGraphToFile;

import static utils.FileUtils.toJson;

public class Solver implements Callable<NeuralNetwork> {

    private List<NeuralNetwork> neuralNetworks;
    private List<Operator> operators;
    private int passCounter;
    private int passLimit;
    private long timeStart;
    private long timeLimit;
    private NeuralNetwork bestNeuralNetwork;
    private List<Result> results;
    private String fileName;
    private FitnessEvaluator fitnessEvaluator;

    public Solver(int populationSize, List<Operator> operators, int passLimit, long timeLimit, String fileName,
                  int inputLayerSize, List<Integer> hiddenLayerSizes, int outputLayerSize, double std, double bias,
                  FitnessEvaluator fitnessEvaluator) {
        neuralNetworks = new ArrayList<>(populationSize);
        results = new ArrayList<>();
        this.operators = operators;
        passCounter = 0;
        timeStart = currentTimeMillis();
        this.passLimit = passLimit;
        this.timeLimit = timeLimit;
        this.fileName = fileName;
        this.fitnessEvaluator = fitnessEvaluator;

        for (int counter = 0; counter < populationSize; counter++) {
            NeuralNetwork randomNeuralNetwork =
                    new MyNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize, std, bias);
            neuralNetworks.add(randomNeuralNetwork);
        }
        evaluate();
    }

    public NeuralNetwork call() {
        while (!done()) {
            for (Operator operator : operators) {
                    neuralNetworks = operator.call(neuralNetworks);
            }
            evaluate();
        }
        graph();
        save();
        return bestNeuralNetwork;
    }


    private void evaluate() {
        for (NeuralNetwork neuralNetwork : neuralNetworks) {
            neuralNetwork.setFitness(fitnessEvaluator.evaluate(neuralNetwork));
            if (bestNeuralNetwork == null || neuralNetwork.getFitness() < bestNeuralNetwork.getFitness()) {
                bestNeuralNetwork = new MyNeuralNetwork((MyNeuralNetwork) neuralNetwork);
            }
        }
        results.add(new Result(neuralNetworks));
    }

    private boolean done() {
        return currentTimeMillis() - timeStart > timeLimit || passCounter++ > passLimit;
    }

    private void graph() {
        saveGraphToFile(results, fileName);
    }

    private void save() {
        String json = toJson(bestNeuralNetwork);
        List<String> jsonList = new ArrayList<>();
        jsonList.add(json);
        FileUtils.saveFile(fileName, jsonList);
    }
}
