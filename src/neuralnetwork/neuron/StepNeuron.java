package neuralnetwork.neuron;

import org.apache.commons.math3.analysis.function.StepFunction;

public class StepNeuron extends CalculableNeuron {

    private static final StepFunction stepFunction = new StepFunction(new double[]{-1, 0}, new double[]{0, 1});

    public StepNeuron() {
        super();
    }

    @Override
    protected double activation(double value) {
        return stepFunction.value(value);
    }


}
