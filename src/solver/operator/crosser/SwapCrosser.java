package solver.operator.crosser;

import neuralnetwork.Connection;
import neuralnetwork.neuron.CalculableNeuron;

import java.util.List;

public class SwapCrosser implements Crosser {

    public void cross(CalculableNeuron firstNeuron, CalculableNeuron secondNeuron) {
        double avgBias = (firstNeuron.getBias() + secondNeuron.getBias()) / 2;
        firstNeuron.setBias(avgBias);
        secondNeuron.setBias(avgBias);
        List<Connection> firstConnections = firstNeuron.getConnections();
        List<Connection> secondConnections = secondNeuron.getConnections();
        for (int i = 0; i < firstConnections.size(); i++) {
            Connection firstConnection = firstConnections.get(i);
            Connection secondConnection = secondConnections.get(i);
            double avgWeight = (firstConnection.getWeight() + secondConnection.getWeight()) / 2;
            firstConnection.setWeight(avgWeight);
            secondConnection.setWeight(avgWeight);
        }
    }
}