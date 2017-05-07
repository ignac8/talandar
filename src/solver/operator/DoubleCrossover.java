package solver.operator;

import neuralnetwork.Connection;
import neuralnetwork.neuron.CalculableNeuron;
import solver.Individual;

import java.util.List;

import static java.util.Collections.shuffle;
import static org.apache.commons.lang3.RandomUtils.nextDouble;

public abstract class DoubleCrossover implements Operator {

    protected double chance;

    public DoubleCrossover(double chance) {
        this.chance = chance;
    }

    @Override
    public List<Individual> call(List<Individual> individuals) {
        shuffle(individuals);
        for (int counter = 0; counter < individuals.size() - 1; counter += 2) {
            if (nextDouble() < chance) {
                Individual firstIndividual = individuals.get(counter);
                Individual secondIndividual = individuals.get(counter + 1);
                crossover(firstIndividual, secondIndividual);
            }
        }
        return individuals;
    }

    protected abstract void crossover(Individual firstIndividual, Individual secondIndividual);

    protected void swapNeurons(CalculableNeuron firstNeuron, CalculableNeuron secondNeuron) {
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
