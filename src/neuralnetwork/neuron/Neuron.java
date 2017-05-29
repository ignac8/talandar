package neuralnetwork.neuron;

public abstract class Neuron {

    transient protected double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public abstract void calculate();
}
