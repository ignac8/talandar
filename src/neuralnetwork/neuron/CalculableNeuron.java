package neuralnetwork.neuron;

import neuralnetwork.Connection;

import java.util.ArrayList;
import java.util.List;

public abstract class CalculableNeuron extends Neuron {

    protected List<Connection> connections;
    protected double bias;

    public CalculableNeuron() {
        connections = new ArrayList<>();
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void calculate() {
        value = bias;
        for (Connection connection : connections) {
            value += connection.getNeuron().getValue() * connection.getWeight();
        }
        value = activation(value);
    }

    protected abstract double activation(double value);

}
