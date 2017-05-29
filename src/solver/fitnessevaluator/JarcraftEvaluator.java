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
import solver.fitnessevaluator.unitselection.UnitSelection;

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
    private int mapHeight;
    private int mapWidth;
    private int gapHeight;
    private int gapWidth;
    private MyPlayer firstPlayer;
    private MyPlayer secondPlayer;
    private UnitSelection unitSelection;

    public JarcraftEvaluator(boolean graphics, int limit, int mapHeight, int mapWidth, int gapHeight, int gapWidth,
                             MyPlayer firstPlayer, MyPlayer secondPlayer, UnitSelection unitSelection) {
        this.graphics = graphics;
        this.limit = limit;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.gapHeight = gapHeight;
        this.gapWidth = gapWidth;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.unitSelection = unitSelection;
    }

    public double evaluate() {
        GameState finalState = playGame();
        double fitness = rateGame(finalState);
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
                            mapHeight / 2 - gapHeight * (unitTypesColumn.size() - 1) / 2 + gapHeight * j);
                    state.addUnit(unitType, 0, position);
                } else {
                    Position position = new Position(mapWidth - gapWidth * i,
                            mapHeight / 2 - gapHeight * (unitTypesColumn.size() - 1) / 2 + gapHeight * j);
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
            state = putUnits(state, false, unitSelection.getFirstPlayerUnitSelection());
            state = putUnits(state, true, unitSelection.getSecondPlayerUnitSelection());
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

    public MyPlayer getFirstPlayer() {
        return firstPlayer;
    }

    public MyPlayer getSecondPlayer() {
        return secondPlayer;
    }
}
