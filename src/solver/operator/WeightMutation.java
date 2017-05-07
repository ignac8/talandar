package solver.operator;

import neuralnetwork.Connection;
import neuralnetwork.NeuralNetwork;
import solver.Individual;

import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextDouble;

public final class WeightMutation extends SingleMutation {

    public WeightMutation(double chance, Mutator mutator) {
        super(chance, mutator);
    }

    @Override
    protected void mutation(Individual individual) {
        NeuralNetwork neuralNetwork = individual.getNeuralNetwork();
        List<List<List<Connection>>> connectionsListList = neuralNetwork.getConnectionsListList();
        for (List<List<Connection>> ConnectionsList : connectionsListList) {
            for (List<Connection> Connections : ConnectionsList) {
                for (Connection connection : Connections) {
                    if (nextDouble() < chance) {
                        connection.setWeight(mutator.mutate(connection.getWeight()));
                    }
                }
            }
        }

    }

}
