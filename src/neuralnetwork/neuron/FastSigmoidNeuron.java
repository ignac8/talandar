package neuralnetwork.neuron;

import static java.lang.Math.abs;

public final class FastSigmoidNeuron extends CalculableNeuron {

    public FastSigmoidNeuron() {
        super();
    }

    @Override
    protected double activation(double value) {
        return 1 / (1 + abs(value));
    }

}