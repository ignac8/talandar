package solver.operator.crosser;

import neuralnetwork.Connection;
import neuralnetwork.neuron.CalculableNeuron;

import java.util.List;

public class AverageCrosser implements Crosser {

    public void cross(CalculableNeuron firstNeuron, CalculableNeuron secondNeuron) {
        double temp = firstNeuron.getBias();
        firstNeuron.setBias(secondNeuron.getBias());
        secondNeuron.setBias(temp);
        List<Connection> firstConnections = firstNeuron.getConnections();
        List<Connection> secondConnections = secondNeuron.getConnections();
        for (int i = 0; i < firstConnections.size(); i++) {
            Connection firstConnection = firstConnections.get(i);
            Connection secondConnection = secondConnections.get(i);
            temp = firstConnection.getWeight();
            firstConnection.setWeight(secondConnection.getWeight());
            secondConnection.setWeight(temp);
        }
    }

}
