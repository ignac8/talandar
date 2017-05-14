package solver.operator;

import neuralnetwork.Connection;
import neuralnetwork.neuron.CalculableNeuron;
import solver.Individual;
import solver.operator.crosser.Crosser;

import java.util.List;

import static java.util.Collections.shuffle;
import static org.apache.commons.lang3.RandomUtils.nextDouble;

public abstract class DoubleCrossover implements Operator {

    protected double chance;
    protected Crosser crosser;

    public DoubleCrossover(double chance, Crosser crosser) {
        this.chance = chance;
        this.crosser = crosser;
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

}
