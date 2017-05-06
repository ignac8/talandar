package solver.operator;

import neuralnetwork.NeuralNetwork;

import java.util.List;

public abstract class Operator {

    public abstract List<NeuralNetwork> call(List<NeuralNetwork> neuralNetworks);
}
