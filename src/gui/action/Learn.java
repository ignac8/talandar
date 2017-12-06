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
import solver.operator.mutation.NeuronMutation;
import solver.operator.mutation.mutator.GaussianMutator;
import solver.operator.selection.TournamentSelection;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.primitives.Ints.asList;
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
    private int outputLayerSize = 14;

    private Result result;

    private boolean graphics;
    private int passLimit;
    private int searchTimeLimit;
    private int populationSize;
    private int tournamentSize;
    private double crossoverChance;
    private double mutationChance;
    private double std;
    private double mean;
    private int hiddenLayerSize;

    @Override
    public void run() {
        List<Integer> hiddenLayerSizes = asList(hiddenLayerSize);

        List<Individual> startingIndividuals = new ArrayList<>();
        for (int counter = 0; counter < populationSize; counter++) {
            Individual randomIndividual =
                    new Individual(new FCFSNeuralNetwork(inputLayerSize, hiddenLayerSizes,
                            outputLayerSize, std, mean));
            startingIndividuals.add(randomIndividual);
        }

        List<FitnessEvaluator> fitnessEvaluators = new ArrayList<>();

        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections = generateUnitSelections();

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
        operators.add(new NeuronMutation(mutationChance, new GaussianMutator(std, mean)));

        solver.setOperators(operators);

        solver.setIndividuals(startingIndividuals);
        result = solver.call();
        double totalFitness = 0;

        SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(false, simulationTimeStep,
                simulationTimeLimit, mapHeight, mapWidth, gapHeight, gapWidth, firstPlayer, secondPlayer);
        NeuralNetworkPlayer neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayer());
        neuralNetworkPlayer.setNeuralNetwork(result.getIndividual().getNeuralNetwork());
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            totalFitness += fitnessEvaluator.evaluate();
        }
        totalFitness /= unitSelections.size();
        logger.log("Total fitness equals: " + totalFitness);
    }

    public double getSimulationTimeStep() {
        return simulationTimeStep;
    }

    public void setSimulationTimeStep(double simulationTimeStep) {
        this.simulationTimeStep = simulationTimeStep;
    }

    public double getSimulationTimeLimit() {
        return simulationTimeLimit;
    }

    public void setSimulationTimeLimit(double simulationTimeLimit) {
        this.simulationTimeLimit = simulationTimeLimit;
    }

    public double getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(double mapHeight) {
        this.mapHeight = mapHeight;
    }

    public double getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(double mapWidth) {
        this.mapWidth = mapWidth;
    }

    public double getGapHeight() {
        return gapHeight;
    }

    public void setGapHeight(double gapHeight) {
        this.gapHeight = gapHeight;
    }

    public double getGapWidth() {
        return gapWidth;
    }

    public void setGapWidth(double gapWidth) {
        this.gapWidth = gapWidth;
    }

    public int getInputLayerSize() {
        return inputLayerSize;
    }

    public void setInputLayerSize(int inputLayerSize) {
        this.inputLayerSize = inputLayerSize;
    }

    public int getOutputLayerSize() {
        return outputLayerSize;
    }

    public void setOutputLayerSize(int outputLayerSize) {
        this.outputLayerSize = outputLayerSize;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public boolean isGraphics() {
        return graphics;
    }

    public void setGraphics(boolean graphics) {
        this.graphics = graphics;
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

    public void setMutationChance(double mutationChance) {
        this.mutationChance = mutationChance;
    }

    public void setStd(double std) {
        this.std = std;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public void setHiddenLayerSize(int hiddenLayerSize) {
        this.hiddenLayerSize = hiddenLayerSize;
    }
}
