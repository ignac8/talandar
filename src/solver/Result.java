package solver;

import neuralnetwork.NeuralNetwork;

import java.util.List;

public class Result {
    private NeuralNetwork neuralNetwork;
    private List<PopulationFitnessStatistic> populationFitnessStatistics;

    public Result(NeuralNetwork neuralNetwork, List<PopulationFitnessStatistic> populationFitnessStatistics) {
        this.neuralNetwork = neuralNetwork;
        this.populationFitnessStatistics = populationFitnessStatistics;
    }

    public NeuralNetwork getNeuralNetwork() {

        return neuralNetwork;
    }

    public List<PopulationFitnessStatistic> getPopulationFitnessStatistics() {
        return populationFitnessStatistics;
    }
}
