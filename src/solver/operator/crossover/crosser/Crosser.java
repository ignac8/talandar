package solver.operator.crossover.crosser;

import neuralnetwork.neuron.CalculableNeuron;

public interface Crosser {

    void cross(CalculableNeuron neuron1, CalculableNeuron neuron2);
}
