package neuralnetwork.neuron;

import org.apache.commons.math3.analysis.function.StepFunction;

public final class StepNeuron extends CalculableNeuron {

    private static final StepFunction STEP_FUNCTION = new StepFunction(new double[]{-1, 0}, new double[]{0, 1});

    public StepNeuron() {
        super();
    }

    @Override
    protected double activation(double value) {
        return STEP_FUNCTION.value(value);
    }


}
