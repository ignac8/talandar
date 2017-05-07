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

public class FitnessEvaluator {

    private JNIBWAPI bwapi;
    private NeuralNetworkPlayer neuralNetworkPlayer;
    private SimplePlayer simplePlayer;

    public FitnessEvaluator() {
        bwapi = new JNIBWAPI_LOAD();
        bwapi.loadTypeData();

        AnimationFrameData.Init();
        PlayerProperties.Init();
        WeaponProperties.Init(bwapi);
        UnitProperties.Init(bwapi);

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
            UnitType type = UnitType.UnitTypes.Terran_Marine;
            state.addUnit(bwapi.getUnitType(type.getID()), 0, new Position(20, 20));
            state.addUnit(bwapi.getUnitType(type.getID()), 1, new Position(40, 40));
            state.addUnit(bwapi.getUnitType(type.getID()), 0, new Position(60, 60));
            state.addUnit(bwapi.getUnitType(type.getID()), 1, new Position(80, 80));
            state.setMap(new Map(20, 20));
            Game game = new Game(state, simplePlayer, neuralNetworkPlayer, 100000, true);
            game.play();
            return game.getState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private double rateGame(GameState finalState) {
        double fitness = 0;

        Unit[][] allUnits = finalState.getAllUnits();

        for (int playerId = 0; playerId < allUnits.length; playerId++) {
            Unit[] playerUnits = allUnits[playerId];
            for (int i = 0; i < playerUnits.length; i++) {
                Unit unit = playerUnits[i];
                if (unit != null) {
                    switch (playerId) {
                        case 0:
                            fitness += unit.getCurrentHP();
                            break;
                        case 1:
                            fitness -= unit.getCurrentHP();
                            break;
                    }
                }
            }
        }
        return fitness;
    }
}
