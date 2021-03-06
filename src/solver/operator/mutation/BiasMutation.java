package solver.operator.mutation;

import neuralnetwork.NeuralNetwork;
import neuralnetwork.neuron.CalculableNeuron;
import solver.Individual;
import solver.operator.mutation.mutator.Mutator;

import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextDouble;

public final class BiasMutation extends SingleMutation {

    public BiasMutation(double chance, Mutator mutator) {
        super(chance, mutator);
    }

    @Override
    protected void mutation(Individual individual) {
        NeuralNetwork neuralNetwork = individual.getNeuralNetwork();
        List<CalculableNeuron> outputLayer = neuralNetwork.getOutputLayer();
        List<List<CalculableNeuron>> hiddenLayers = neuralNetwork.getHiddenLayers();
        for (CalculableNeuron neuron : outputLayer) {
            if (nextDouble(0, 1) < chance) {
                neuron.setBias(mutator.mutate(neuron.getBias()));
            }
        }
        for (List<CalculableNeuron> hiddenLayer : hiddenLayers) {
            for (CalculableNeuron neuron : hiddenLayer) {
                if (nextDouble(0, 1) < chance) {
                    neuron.setBias(mutator.mutate(neuron.getBias()));
                }
            }
        }
    }
}
