package sandbox;

import fitnessevaluator.FitnessEvaluator;
import fitnessevaluator.SimulationEvaluator;
import fitnessevaluator.unitselection.UnitSelectionGenerator;
import jnibwapi.types.UnitType;
import neuralnetwork.FCSNeuralNetwork;
import player.simulation.NeuralNetworkPlayer;
import player.simulation.Player;
import player.simulation.SimplePlayer;
import solver.Individual;
import solver.Solver;
import solver.operator.BiasMutation;
import solver.operator.NeuronCrossover;
import solver.operator.Operator;
import solver.operator.TournamentSelection;
import solver.operator.WeightMutation;
import solver.operator.crosser.AverageCrosser;
import solver.operator.mutator.GaussianMutator;
import utils.Pair;

import java.util.ArrayList;
import java.util.List;

import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateAllUnitSelections;
import static fitnessevaluator.unitselection.UnitSelectionGenerator.generateRandomUnitSelections;
import static jnibwapi.Map.TILE_SIZE;

public class NewForwardEngineering {

    public static void main(String... args) {
        String fileName = "testNeuralWeb.json";
        int passLimit = Integer.MAX_VALUE;
        int timeLimit = 1 * 10 * 1000;
        int populationSize = 100;
        int inputLayerSize = 5;
        int outputLayerSize = 8;
        int tournamentSize = 2;
        double crossoverChance = 0;
        double weightMutationChance = 1;
        double biasMutationChance = 1;
        double initialStd = 10;
        double initialMean = 0;
        double weightStd = 10;
        double weightMean = 0;
        double biasStd = 10;
        double biasMean = 0;
        boolean graphics = false;
        int limit = 10000;
        int mapHeight = TILE_SIZE * 20;
        int mapWidth = TILE_SIZE * 20;
        int gapHeight = 40;
        int gapWidth = 120;
        int numberOfUnitSelections = 10;

        int[] hiddenLayerSizes = {10};

        List<Individual> startingIndividuals = new ArrayList<>();

        for (int counter = 0; counter < populationSize; counter++) {
            Individual randomIndividual =
                    new Individual(new FCSNeuralNetwork(inputLayerSize, hiddenLayerSizes,
                            outputLayerSize, initialStd, initialMean));
            startingIndividuals.add(randomIndividual);
        }

        List<Operator> operators = new ArrayList<>();
        operators.add(new TournamentSelection(tournamentSize));
        operators.add(new NeuronCrossover(crossoverChance, new AverageCrosser()));
        operators.add(new WeightMutation(weightMutationChance, new GaussianMutator(weightStd, weightMean)));
        operators.add(new BiasMutation(biasMutationChance, new GaussianMutator(biasStd, biasMean)));

        Player firstPlayer = new NeuralNetworkPlayer(0);
        Player secondPlayer = new SimplePlayer(1);
        List<FitnessEvaluator> fitnessEvaluators = new ArrayList<>();

        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> unitSelections
                = generateRandomUnitSelections(numberOfUnitSelections);

        unitSelections.addAll(UnitSelectionGenerator.generateMirrorUnitSelections(unitSelections));

        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : unitSelections) {
            FitnessEvaluator fitnessEvaluator = new SimulationEvaluator(graphics, limit, mapHeight, mapWidth,
                    gapHeight, gapWidth, firstPlayer, secondPlayer, unitSelection);
            fitnessEvaluators.add(fitnessEvaluator);
        }

        Solver solver = new Solver(operators, passLimit, timeLimit, fileName, startingIndividuals, fitnessEvaluators);

        Individual bestOne = solver.solve();
        solver.graph("graphs\\testNeuralWeb.png");
        solver.save("testNeuralWeb.json");

        double totalFitness = 0;

        SimulationEvaluator fitnessEvaluator = new SimulationEvaluator(false, limit, mapHeight, mapWidth,
                gapHeight, gapWidth, firstPlayer, secondPlayer, null);
        NeuralNetworkPlayer neuralNetworkPlayer = (NeuralNetworkPlayer) (fitnessEvaluator.getFirstPlayer());
        neuralNetworkPlayer.setNeuralNetwork(bestOne.getNeuralNetwork());
        List<Pair<List<List<UnitType>>, List<List<UnitType>>>> allUnitSelections = generateAllUnitSelections();
        for (Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection : allUnitSelections) {
            fitnessEvaluator.setUnitSelection(unitSelection);
            totalFitness += fitnessEvaluator.evaluate();
        }
        totalFitness /= allUnitSelections.size();

        System.out.println("Total fitness equals: " + totalFitness);
    }
}
