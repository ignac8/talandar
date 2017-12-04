package neuralnetwork.neuron;

public final class LinearNeuron extends CalculableNeuron {

    public LinearNeuron() {
        super();
    }

    @Override
    protected double activation(double value) {
        return value;
    }

}