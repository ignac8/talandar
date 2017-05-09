package solver;

import neuralnetwork.NeuralNetwork;

public class Individual {

    private double fitness;
    private NeuralNetwork neuralNetwork;

    public Individual(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public Individual copy() {
        NeuralNetwork copiedNeuralNetwork = neuralNetwork.copy();
        return new Individual(copiedNeuralNetwork);
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }
}