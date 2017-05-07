package solver.operator;

import neuralnetwork.NeuralNetwork;
import neuralnetwork.neuron.CalculableNeuron;
import solver.Individual;

import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextDouble;

public abstract class BiasMutation extends SingleMutation {

    public BiasMutation(double chance) {
        super(chance);
    }

    @Override
    protected void mutation(Individual individual) {
        NeuralNetwork neuralNetwork = individual.getNeuralNetwork();
        List<CalculableNeuron> outputLayer = neuralNetwork.getOutputLayer();
        List<List<CalculableNeuron>> hiddenLayers = neuralNetwork.getHiddenLayers();
        for (int i = 0; i < outputLayer.size(); i++) {
            if (nextDouble() < chance) {
                CalculableNeuron neuron = outputLayer.get(i);
                neuron.setBias(mutateBias(neuron.getBias()));
            }
        }
        for (int i = 0; i < hiddenLayers.size(); i++) {
            for (int j = 0; j < hiddenLayers.get(i).size(); j++) {
                if (nextDouble() < chance) {
                    CalculableNeuron neuron = hiddenLayers.get(i).get(j);
                    neuron.setBias(mutateBias(neuron.getBias()));
                }
            }
        }
    }

    protected abstract double mutateBias(double bias);
}
