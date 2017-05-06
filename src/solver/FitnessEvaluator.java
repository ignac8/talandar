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
import bwmcts.sparcraft.players.Player;
import bwmcts.test.JNIBWAPI_LOAD;
import jnibwapi.JNIBWAPI;
import jnibwapi.types.UnitType;
import neuralnetwork.NeuralNetwork;
import player.SimplePlayer;

public class FitnessEvaluator {

    private JNIBWAPI bwapi;
    private Player player1;
    private Player player2;

    public FitnessEvaluator() {
        bwapi = new JNIBWAPI_LOAD();
        bwapi.loadTypeData();

        AnimationFrameData.Init();
        PlayerProperties.Init();
        WeaponProperties.Init(bwapi);
        UnitProperties.Init(bwapi);

        player1 = new SimplePlayer(0, bwapi);
        player2 = new SimplePlayer(1, bwapi);
    }

    public double evaluate(NeuralNetwork neuralNetwork) {
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
            Game game = new Game(state, player1, player2, 100000, true);
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
