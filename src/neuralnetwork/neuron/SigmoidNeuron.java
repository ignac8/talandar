package neuralnetwork.neuron;

import org.apache.commons.math3.analysis.function.Sigmoid;

public class SigmoidNeuron extends CalculableNeuron {

    private static final Sigmoid sigmoid = new Sigmoid();

    public SigmoidNeuron() {
        super();
    }

    @Override
    protected double activation(double value) {
        return sigmoid.value(value);
    }


}
