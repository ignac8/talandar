package neuralnetwork.neuron;

import static java.lang.Math.tanh;

public final class TanhNeuron extends CalculableNeuron {

    public TanhNeuron() {
        super();
    }

    @Override
    protected double activation(double value) {
        return tanh(value);
    }
}