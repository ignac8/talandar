package solver.operator.crossover;

import neuralnetwork.NeuralNetwork;
import neuralnetwork.neuron.CalculableNeuron;
import solver.Individual;
import solver.operator.crossover.crosser.Crosser;

import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextBoolean;

public final class LayerCrossover extends DoubleCrossover {

    public LayerCrossover(double chance, Crosser crosser) {
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

        if (nextBoolean()) {
            for (int i = 0; i < firstOutputLayer.size(); i++) {
                crosser.cross(firstOutputLayer.get(i), secondOutputLayer.get(i));
            }
        }

        for (int i = 0; i < firstHiddenLayers.size(); i++) {
            if (nextBoolean()) {
                List<CalculableNeuron> firstHiddenLayer = firstHiddenLayers.get(i);
                List<CalculableNeuron> secondHiddenLayer = secondHiddenLayers.get(i);
                for (int j = 0; j < firstHiddenLayer.size(); j++) {
                    crosser.cross(firstHiddenLayer.get(j), secondHiddenLayer.get(j));
                }
            }
        }
    }
}

