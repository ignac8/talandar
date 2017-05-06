package solver;

import neuralnetwork.NeuralNetwork;

public class Individual {

    private NeuralNetwork neuralNetwork;
    private double fitness;

    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }

    public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public double getFitness() {
        return fitness;
    }

    public void calculateFitness(FitnessEvaluator fitnessEvaluator)
    {
        fitness = fitnessEvaluator.evaluate(neuralNetwork);
    }


}

