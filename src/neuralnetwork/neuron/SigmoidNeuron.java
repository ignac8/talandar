package neuralnetwork.neuron;

import org.apache.commons.math3.analysis.function.Sigmoid;

public final class SigmoidNeuron extends CalculableNeuron {

    private static final Sigmoid SIGMOID = new Sigmoid();

    public SigmoidNeuron() {
        super();
    }

    @Override
    protected double activation(double value) {
        return SIGMOID.value(value);
    }


}
