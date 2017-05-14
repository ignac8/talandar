package solver;

import bwmcts.sparcraft.AnimationFrameData;
import bwmcts.sparcraft.Game;
import bwmcts.sparcraft.GameState;
import bwmcts.sparcraft.Map;
import bwmcts.sparcraft.PlayerProperties;
import bwmcts.sparcraft.Position;
import bwmcts.sparcraft.Unit;
import bwmcts.sparcraft.UnitProperties;
import bwmcts.sparcraft.WeaponProperties;
import bwmcts.test.JNIBWAPI_LOAD;
import jnibwapi.JNIBWAPI;
import jnibwapi.types.UnitType;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;

import static java.lang.Math.pow;

public class FitnessEvaluator {

    private JNIBWAPI bwapi;
    private NeuralNetworkPlayer neuralNetworkPlayer;
    private SimplePlayer simplePlayer;
    private boolean graphics;
    private UnitType zealotType;
    private UnitType dragoonType;

    public FitnessEvaluator(boolean graphics) {
        this.graphics = graphics;
        bwapi = new JNIBWAPI_LOAD();
        bwapi.loadTypeData();

        AnimationFrameData.Init();
        PlayerProperties.Init();
        WeaponProperties.Init(bwapi);
        UnitProperties.Init(bwapi);

        zealotType = UnitType.UnitTypes.Protoss_Zealot;
        dragoonType = UnitType.UnitTypes.Protoss_Dragoon;

        neuralNetworkPlayer = new NeuralNetworkPlayer(0, bwapi);
        simplePlayer = new SimplePlayer(1, bwapi);
    }

    public double evaluate(Individual individual) {
        neuralNetworkPlayer.setNeuralNetwork(individual.getNeuralNetwork());
        GameState finalState = playGame();
        double fitness = rateGame(finalState);
        return fitness;
    }

    private GameState playGame() {
        try {
            GameState state = new GameState();
            for (int i = 0; i < 12; i++) {
                state.addUnit(bwapi.getUnitType(dragoonType.getID()), 0,
                        new Position(40, 155 + i * 30));
            }
            for (int i = 0; i < 12; i++) {
                state.addUnit(bwapi.getUnitType(zealotType.getID()), 0,
                        new Position(120, 155 + i * 30));
            }
            for (int i = 0; i < 12; i++) {
                state.addUnit(bwapi.getUnitType(zealotType.getID()), 1,
                        new Position(640 - 120, 155 + i * 30));
            }
            for (int i = 0; i < 12; i++) {
                state.addUnit(bwapi.getUnitType(dragoonType.getID()), 1,
                        new Position(640 - 60, 155 + i * 30));
            }
            state.setMap(new Map(20, 20));
            Game game = new Game(state, neuralNetworkPlayer, simplePlayer, 100000, graphics);
            game.play();
            return game.getState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private double rateGame(GameState finalState) {
        double fitness = 0;
        double maxFitness = 0;

        Unit[][] allUnits = finalState.getAllUnits();

        for (int playerId = 0; playerId < allUnits.length; playerId++) {
            Unit[] playerUnits = allUnits[playerId];
            for (int i = 0; i < playerUnits.length; i++) {
                Unit unit = playerUnits[i];
                if (unit != null) {
                    double unitWorth = unitWorthBasic(unit);
                    double adjustedUnitWorth = unitWorthModifier(unit) * unitWorth;
                    switch (playerId) {
                        case 0:
                            fitness += adjustedUnitWorth;
                            maxFitness += unitWorth;
                            break;
                        case 1:
                            fitness -= adjustedUnitWorth;
                            break;
                    }
                }
            }
        }
        return fitness / maxFitness;
    }

    private double unitWorthModifier(Unit unit) {
        return pow((unit.getCurrentHP() / unit.getMaxHP()), 0.75);
    }

    private double unitWorthBasic(Unit unit) {
        return unit.getMineralPrice() + 2 * unit.getGasPrice();
    }

    public void setGraphics(boolean graphics) {
        this.graphics = graphics;
    }
}
