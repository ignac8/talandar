package solver.fitnessevaluator;

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
import player.MyPlayer;

import java.util.List;

import static java.lang.Math.pow;
import static jnibwapi.Map.TILE_SIZE;

public class JarcraftEvaluator implements FitnessEvaluator {

    private static JNIBWAPI bwapi;

    static {
        bwapi = new JNIBWAPI_LOAD();
        bwapi.loadTypeData();
        AnimationFrameData.Init();
        PlayerProperties.Init();
        WeaponProperties.Init(bwapi);
        UnitProperties.Init(bwapi);
    }

    private boolean graphics;
    private int limit;
    private int runCount;
    private int mapHeight;
    private int mapWidth;
    private int gapHeight;
    private int gapWidth;
    private MyPlayer firstPlayer;
    private MyPlayer secondPlayer;
    private List<List<UnitType>> firstPlayerUnits;
    private List<List<UnitType>> secondPlayerUnits;


    public JarcraftEvaluator(boolean graphics, int limit, int mapHeight, int mapWidth, int gapHeight, int gapWidth,
                             MyPlayer firstPlayer, MyPlayer secondPlayer,
                             List<List<UnitType>> firstPlayerUnits, List<List<UnitType>> secondPlayerUnits) {
        this.graphics = graphics;
        this.limit = limit;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.gapHeight = gapHeight;
        this.gapWidth = gapWidth;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.firstPlayerUnits = firstPlayerUnits;
        this.secondPlayerUnits = secondPlayerUnits;
        runCount = 0;
    }

    public double evaluate() {
        GameState finalState = playGame();
        double fitness = rateGame(finalState);
        runCount++;
        return fitness;
    }

    private GameState putUnits(GameState state, boolean secondPlayer, List<List<UnitType>> unitTypes) {
        for (int i = 1; i <= unitTypes.size(); i++) {
            List<UnitType> unitTypesColumn = unitTypes.get(i - 1);
            for (int j = 0; j < unitTypesColumn.size(); j++) {
                UnitType unitType = unitTypesColumn.get(j);
                unitType = bwapi.getUnitType(unitType.getID());
                if (secondPlayer) {
                    Position position = new Position(gapWidth * i,
                            (mapHeight - gapHeight * unitTypesColumn.size() / 2) / 2 + gapHeight * j);
                    state.addUnit(unitType, 0, position);
                } else {
                    Position position = new Position(mapWidth - gapWidth * i,
                            (mapHeight - gapHeight * unitTypesColumn.size() / 2) / 2 + gapHeight * j);
                    state.addUnit(unitType, 1, position);
                }
            }
        }
        return state;
    }

    private GameState playGame() {
        try {
            GameState state = new GameState();
            state.setMap(new Map(mapWidth / TILE_SIZE, mapHeight / TILE_SIZE));
            state = putUnits(state, false, firstPlayerUnits);
            state = putUnits(state, true, secondPlayerUnits);
            Game game = new Game(state, firstPlayer, secondPlayer, limit, graphics, true);
            game.play();
            return game.getState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private double rateGame(GameState finalState) {
        double playerFitness = 0;
        double playerMaxFitness = 0;
        double enemyFitness = 0;
        double enemyMaxFitness = 0;

        Unit[][] allUnits = finalState.getAllUnits();

        for (int playerId = 0; playerId < allUnits.length; playerId++) {
            Unit[] playerUnits = allUnits[playerId];
            for (Unit unit : playerUnits) {
                if (unit != null) {
                    double unitWorth = unitWorthBasic(unit);
                    double adjustedUnitWorth = unitWorthModifier(unit) * unitWorth;
                    switch (playerId) {
                        case 0:
                            playerFitness += adjustedUnitWorth;
                            playerMaxFitness += unitWorth;
                            break;
                        case 1:
                            enemyFitness += adjustedUnitWorth;
                            enemyMaxFitness += unitWorth;
                            break;
                    }
                }
            }
        }
        return playerFitness / playerMaxFitness - enemyFitness / enemyMaxFitness;
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

    public int getRunCount() {
        return runCount;
    }

    public MyPlayer getFirstPlayer() {
        return firstPlayer;
    }

    public MyPlayer getSecondPlayer() {
        return secondPlayer;
    }
}
