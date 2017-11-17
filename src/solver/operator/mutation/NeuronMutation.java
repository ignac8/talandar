package solver.operator.mutation;

import neuralnetwork.Connection;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.neuron.CalculableNeuron;
import solver.Individual;
import solver.operator.mutation.mutator.Mutator;

import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextDouble;

public final class NeuronMutation extends SingleMutation {

    public NeuronMutation(double chance, Mutator mutator) {
        super(chance, mutator);
    }

    @Override
    protected void mutation(Individual individual) {
        NeuralNetwork neuralNetwork = individual.getNeuralNetwork();
        List<CalculableNeuron> outputLayer = neuralNetwork.getOutputLayer();
        List<List<CalculableNeuron>> hiddenLayers = neuralNetwork.getHiddenLayers();
        if (nextDouble(0, 1) < chance) {
            for (CalculableNeuron neuron : outputLayer) {
                neuron.setBias(mutator.mutate(neuron.getBias()));
                for (Connection connection : neuron.getConnections()) {
                    connection.setWeight(mutator.mutate(neuron.getBias()));
                }
            }
        }
        for (List<CalculableNeuron> hiddenLayer : hiddenLayers) {
            if (nextDouble(0, 1) < chance) {
                for (CalculableNeuron neuron : hiddenLayer) {
                    neuron.setBias(mutator.mutate(neuron.getBias()));
                    for (Connection connection : neuron.getConnections()) {
                        connection.setWeight(mutator.mutate(neuron.getBias()));
                    }
                }
            }
        }
    }
}