package neuralnetwork;

import neuralnetwork.neuron.Neuron;

public class Connection {

    private Neuron neuron;
    private double weight;

    public Connection(Neuron neuron) {
        this.neuron = neuron;
    }

    public Neuron getNeuron() {
        return neuron;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
