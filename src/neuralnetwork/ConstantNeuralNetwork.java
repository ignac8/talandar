package neuralnetwork;

import neuralnetwork.neuron.ConstantCalculableNeuron;
import neuralnetwork.neuron.ConstantNeuron;

public final class ConstantNeuralNetwork extends NeuralNetwork {

    public ConstantNeuralNetwork(int inputLayerSize, int outputLayerSize, int index, double value) {
        super();
        for (int counter = 0; counter < inputLayerSize; counter++) {
            inputLayer.add(new ConstantNeuron());
        }
        for (int counter = 0; counter < outputLayerSize; counter++) {
            outputLayer.add(new ConstantCalculableNeuron());
        }
        outputLayer.get(index).setValue(value);
    }

    @Override
    public NeuralNetwork copy() {
        int index = -1;
        double value = 0;
        for (int counter = 0; counter < outputLayer.size() && index == -1; counter++) {
            value = outputLayer.get(counter).getValue();
            if (value != 0) {
                index = counter;
            }
        }
        return new ConstantNeuralNetwork(inputLayer.size(), outputLayer.size(), index, value);
    }
}