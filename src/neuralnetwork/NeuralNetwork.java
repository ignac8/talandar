package neuralnetwork;

import neuralnetwork.neuron.CalculableNeuron;
import neuralnetwork.neuron.InputNeuron;

import java.util.ArrayList;
import java.util.List;

public abstract class NeuralNetwork {

    protected List<InputNeuron> inputLayer;
    protected List<CalculableNeuron> outputLayer;
    protected double fitness;

    protected NeuralNetwork() {
        inputLayer = new ArrayList<>();
        outputLayer = new ArrayList<>();
    }

    public List<InputNeuron> getInputLayer() {
        return inputLayer;
    }

    public List<CalculableNeuron> getOutputLayer() {
        return outputLayer;
    }

    public abstract void calculateOutput();

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
