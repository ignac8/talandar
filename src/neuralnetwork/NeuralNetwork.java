package neuralnetwork;

import neuralnetwork.neuron.CalculableNeuron;
import neuralnetwork.neuron.ConstantNeuron;
import neuralnetwork.neuron.Neuron;

import java.util.ArrayList;
import java.util.List;

public abstract class NeuralNetwork {

    protected List<ConstantNeuron> inputLayer;
    protected List<CalculableNeuron> outputLayer;
    protected List<List<CalculableNeuron>> hiddenLayers;
    protected List<List<List<Connection>>> connectionsListList;

    protected NeuralNetwork() {
        inputLayer = new ArrayList<>();
        outputLayer = new ArrayList<>();
        hiddenLayers = new ArrayList<>();
        connectionsListList = new ArrayList<>();
    }

    public List<ConstantNeuron> getInputLayer() {
        return inputLayer;
    }

    public List<CalculableNeuron> getOutputLayer() {
        return outputLayer;
    }

    public List<List<CalculableNeuron>> getHiddenLayers() {
        return hiddenLayers;
    }

    public List<List<List<Connection>>> getConnectionsListList() {
        return connectionsListList;
    }

    public void calculateOutput() {
        for (List<CalculableNeuron> hiddenLayer : hiddenLayers) {
            for (Neuron neuron : hiddenLayer) {
                neuron.calculate();
            }
        }
        for (Neuron neuron : outputLayer) {
            neuron.calculate();
        }
    }

    public abstract NeuralNetwork copy();


}
