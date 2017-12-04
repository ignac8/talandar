package gui.action;

import fitnessevaluator.FitnessEvaluator;
import fitnessevaluator.SimulationEvaluator;
import gui.updater.Logger;
import jnibwapi.types.UnitType;
import neuralnetwork.FCFSNeuralNetwork;
import player.NeuralNetworkPlayer;
import player.Player;
import player.SimplePlayer;
import solver.Individual;
import solver.Result;
import solver.Solver;
import solver.operator.Operator;
import solver.operator.crossover.NeuronCrossover;
import solver.operator.crossover.crosser.SwapCrosser;
import solver.operator.mutation.BiasMutation;
import solver.operator.mutation.WeightMutation;
import solver.operator.mutation.mutator.GaussianAdditionMutator;
import solver.operator.selection.TournamentSelection;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.primitives.Ints.asList;
import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateAllUnitSelections;
import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateUnitSelections;

public class Learn implements Runnable {

    private Player firstPlayer = new NeuralNetworkPlayer(0);
    private Player secondPlayer = new SimplePlayer(1);
    private Logger logger = Logger.getInstance();
    private double simulationTimeStep = 1.0;
    private double simulationTimeLimit = 10000;
    private double mapHeight = 640.0;
    private double mapWidth = 640.0;
    private double gapHeight = 40.0;
    private double gapWidth = 120.0;
    private int inputLayerSize = 5;
    private int outputLayerSize = 15;

    private Result result;

    private boolean graphics;
    private int passLimit;
    private int searchTimeLimit;
    private int populationSize;
    private int tournamentSize;
    private double crossoverChance;
    private double weightMutationChance;
    private double biasMutationChance;
    private double initialStd;
    private double initialMean;
    private double weightStd;
    private double weightMean;
    private double biasStd;
    private double biasMean;
    private int numberOfUnitSelections;
    private int hiddenLayerSize;

    @Override
    public void run() {
        List<Integer> hiddenLayerSizes = asList(hiddenLayerSize);

        List<Individual> startingIndividuals = new ArrayList<>();
        for (int counter = 0; counter < populationSize; counter++) {
            Individual randomIndividual =
                    new Individual(new FCFSNeuralNetwork(inputLayerSize, hiddenLayerSizes,
                            outputLayerSize, initialStd, initialMean));
            startingIndividuals.add(randomIndividual);
        }

        List<FitnessEvaluator> fitnessEvaluators = new ArrayList<>();

        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections
                = generateUnitSelections(numberOfUnitSelections);

        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, simulationTimeStep,
                    simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, firstPlayer, secondPlayer);
            fitnessEvaluator.setUnitSelection(unitSelection);
            fitnessEvaluators.add(fitnessEvaluator);
        }

        Solver solver = new Solver(passLimit, searchTimeLimit, fitnessEvaluators);

        List<Operator> operators = new ArrayList<>();
        operators.add(new TournamentSelection(tournamentSize));
        operators.add(new NeuronCrossover(crossoverChance, new SwapCrosser()));
        operators.add(new WeightMutation(weightMutationChance, new GaussianAdditionMutator(weightStd, weightMean)));
        operators.add(new BiasMutation(biasMutationChance, new GaussianAdditionMutator(biasStd, biasMean)));

        solver.setOperators(operators);

        result = solver.solve(startingIndividuals);
        double totalFitness = 0;

        SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(false, simulationTimeStep,
                simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, firstPlayer, secondPlayer);
        NeuralNetworkPlayer neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayer());
        neuralNetworkPlayer.setNeuralNetwork(result.getNeuralNetwork());
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> allUnitSelections = generateAllUnitSelections();
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : allUnitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            totalFitness += fitnessEvaluator.evaluate();
        }
        totalFitness /= allUnitSelections.size();
        logger.log("Total fitness equals: " + totalFitness);
    }

    public void setGraphics(boolean graphics) {
        this.graphics = graphics;
    }

    public Result getResult() {
        return result;
    }

    public void setPassLimit(int passLimit) {
        this.passLimit = passLimit;
    }

    public void setSearchTimeLimit(int searchTimeLimit) {
        this.searchTimeLimit = searchTimeLimit;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public void setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    public void setCrossoverChance(double crossoverChance) {
        this.crossoverChance = crossoverChance;
    }

    public void setWeightMutationChance(double weightMutationChance) {
        this.weightMutationChance = weightMutationChance;
    }

    public void setBiasMutationChance(double biasMutationChance) {
        this.biasMutationChance = biasMutationChance;
    }

    public void setInitialStd(double initialStd) {
        this.initialStd = initialStd;
    }

    public void setInitialMean(double initialMean) {
        this.initialMean = initialMean;
    }

    public void setWeightStd(double weightStd) {
        this.weightStd = weightStd;
    }

    public void setWeightMean(double weightMean) {
        this.weightMean = weightMean;
    }

    public void setBiasStd(double biasStd) {
        this.biasStd = biasStd;
    }

    public void setBiasMean(double biasMean) {
        this.biasMean = biasMean;
    }

    public void setNumberOfUnitSelections(int numberOfUnitSelections) {
        this.numberOfUnitSelections = numberOfUnitSelections;
    }

    public void setHiddenLayerSize(int hiddenLayerSize) {
        this.hiddenLayerSize = hiddenLayerSize;
    }
}
