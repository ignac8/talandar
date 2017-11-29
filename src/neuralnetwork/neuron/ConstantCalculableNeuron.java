package neuralnetwork.neuron;

public class ConstantCalculableNeuron extends CalculableNeuron {

    @Override
    public void calculate() {

    }

    @Override
    protected double activation(double value) {
        return value;
    }
}
