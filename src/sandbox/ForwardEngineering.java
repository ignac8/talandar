package sandbox;

import jnibwapi.types.UnitType;
import neuralnetwork.FCSNeuralNetwork;
import player.MyPlayer;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;
import solver.Individual;
import solver.Solver;
import solver.fitnessevaluator.FitnessEvaluator;
import solver.fitnessevaluator.JarcraftEvaluator;
import solver.operator.BiasMutation;
import solver.operator.NeuronCrossover;
import solver.operator.Operator;
import solver.operator.TournamentSelection;
import solver.operator.WeightMutation;
import solver.operator.crosser.AverageCrosser;
import solver.operator.mutator.GaussianMutator;

import java.util.ArrayList;
import java.util.List;

import static jnibwapi.Map.TILE_SIZE;

public class ForwardEngineering {

    public static void main(String... args) {
        String fileName = "testNeuralWeb.json";
        int passLimit = 25 * 1000;
        int timeLimit = 10 * 60 * 1000;
        int populationSize = 1000;
        int inputLayerSize = 5;
        int outputLayerSize = 4;
        int tournamentSize = 2;
        double crossoverChance = 0.85;
        double weightMutationChance = 0.5;
        double biasMutationChance = 0.5;
        double initialStd = 10;
        double initialMean = 0;
        double weightStd = 1;
        double weightMean = 0;
        double biasStd = 1;
        double biasMean = 0;

        List<Integer> hiddenLayerSizes = new ArrayList<>();
        hiddenLayerSizes.add(20);

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

        boolean graphics = false;
        int limit = Integer.MAX_VALUE;
        int mapHeight = TILE_SIZE * 20;
        int mapWidth = TILE_SIZE * 20;
        int gapHeight = 40;
        int gapWidth = 120;
        MyPlayer firstPlayer = new NeuralNetworkPlayer(0, true);
        MyPlayer secondPlayer = new SimplePlayer(1);
        List<List<UnitType>> firstPlayerUnits = new ArrayList<>();
        List<List<UnitType>> secondPlayerUnits = new ArrayList<>();
        List<UnitType> unitTypesColumn;

        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Dragoon);
        }
        firstPlayerUnits.add(unitTypesColumn);

        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Zealot);
        }
        firstPlayerUnits.add(unitTypesColumn);

        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Dragoon);
        }
        secondPlayerUnits.add(unitTypesColumn);

        unitTypesColumn = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            unitTypesColumn.add(UnitType.UnitTypes.Protoss_Zealot);
        }
        secondPlayerUnits.add(unitTypesColumn);

        FitnessEvaluator fitnessEvaluator = new JarcraftEvaluator(graphics, limit, mapHeight, mapWidth,
                gapHeight, gapWidth, firstPlayer, secondPlayer, firstPlayerUnits, secondPlayerUnits);

        List<FitnessEvaluator> fitnessEvaluators = new ArrayList<>();
        fitnessEvaluators.add(fitnessEvaluator);

        Solver solver = new Solver(operators, passLimit, timeLimit, fileName, startingIndividuals, fitnessEvaluators);

        solver.call();
    }
}
