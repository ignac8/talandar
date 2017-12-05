package solver;

import java.util.List;

public class Result {
    private Individual individual;
    private List<PopulationFitnessStatistic> populationFitnessStatistics;

    public Result(Individual individual, List<PopulationFitnessStatistic> populationFitnessStatistics) {
        this.individual = individual;
        this.populationFitnessStatistics = populationFitnessStatistics;
    }

    public Individual getIndividual() {
        return individual;
    }

    public List<PopulationFitnessStatistic> getPopulationFitnessStatistics() {
        return populationFitnessStatistics;
    }
}
