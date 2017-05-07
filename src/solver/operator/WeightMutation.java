package solver.operator;

import neuralnetwork.Connection;
import neuralnetwork.MyNeuralNetwork;
import solver.Individual;
import utils.RandomUtils;

import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextDouble;

public abstract class WeightMutation extends SingleMutation {

    public WeightMutation(double chance) {
        super(chance);
    }

    @Override
    protected void mutation(Individual individual) {
        MyNeuralNetwork myNeuralNetwork = (MyNeuralNetwork) individual.getNeuralNetwork();
        List<List<List<Connection>>> connectionsListList = myNeuralNetwork.getConnectionsListList();
        for (int i = 0; i < connectionsListList.size(); i++) {
            for (int j = 0; j < connectionsListList.get(i).size(); j++) {
                for (int k = 0; k < connectionsListList.get(i).get(j).size(); k++) {
                    if(nextDouble() < chance) {
                        Connection connection = connectionsListList.get(i).get(j).get(k);
                        connection.setWeight(mutateWeight(connection.getWeight()));
                    }
                }
            }
        }

    }

    protected abstract double mutateWeight(double weight);
}
