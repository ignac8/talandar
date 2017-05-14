package solver.operator;

import neuralnetwork.NeuralNetwork;
import neuralnetwork.neuron.CalculableNeuron;
import solver.Individual;
import solver.operator.crosser.Crosser;

import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextBoolean;

public final class NeuronCrossover extends DoubleCrossover {

    public NeuronCrossover(double chance, Crosser crosser) {
        super(chance, crosser);
    }

    @SuppressWarnings("Duplicates")
    @Override
    protected void crossover(Individual firstIndividual, Individual secondIndividual) {
        NeuralNetwork firstNeuralNetwork = firstIndividual.getNeuralNetwork();
        NeuralNetwork secondNeuralNetwork = secondIndividual.getNeuralNetwork();
        List<CalculableNeuron> firstOutputLayer = firstNeuralNetwork.getOutputLayer();
        List<CalculableNeuron> secondOutputLayer = secondNeuralNetwork.getOutputLayer();
        List<List<CalculableNeuron>> firstHiddenLayers = firstNeuralNetwork.getHiddenLayers();
        List<List<CalculableNeuron>> secondHiddenLayers = secondNeuralNetwork.getHiddenLayers();

        for (int i = 0; i < firstOutputLayer.size(); i++) {
            if (nextBoolean()) {
                crosser.cross(firstOutputLayer.get(i), secondOutputLayer.get(i));
            }
        }

        for (int i = 0; i < firstHiddenLayers.size(); i++) {
            List<CalculableNeuron> firstHiddenLayer = firstHiddenLayers.get(i);
            List<CalculableNeuron> secondHiddenLayer = secondHiddenLayers.get(i);
            for (int j = 0; j < firstHiddenLayer.size(); j++) {
                if (nextBoolean()) {
                    crosser.cross(firstHiddenLayer.get(j), secondHiddenLayer.get(j));
                }
            }
        }
    }
}
