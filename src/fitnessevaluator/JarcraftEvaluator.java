package fitnessevaluator;

import bwmcts.sparcraft.Game;
import bwmcts.sparcraft.GameState;
import bwmcts.sparcraft.Map;
import bwmcts.sparcraft.Position;
import bwmcts.sparcraft.Unit;
import bwmcts.test.JNIBWAPI_LOAD;
import fitnessevaluator.unitselection.UnitSelection;
import jnibwapi.types.UnitType;
import player.JarcraftPlayer;

import java.util.List;

import static java.lang.Math.pow;
import static jnibwapi.Map.TILE_SIZE;

public class JarcraftEvaluator implements FitnessEvaluator {

    static {
        JNIBWAPI_LOAD.initialize();
    }

    private boolean graphics;
    private int limit;
    private int mapHeight;
    private int mapWidth;
    private int gapHeight;
    private int gapWidth;
    private JarcraftPlayer firstPlayer;
    private UnitSelection unitSelection;
    private JarcraftPlayer secondPlayer;

    public JarcraftEvaluator(boolean graphics, int limit, int mapHeight, int mapWidth, int gapHeight, int gapWidth,
                             JarcraftPlayer firstPlayer, JarcraftPlayer secondPlayer, UnitSelection unitSelection) {
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
                if (!secondPlayer) {
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

    public JarcraftPlayer getFirstPlayer() {
        return firstPlayer;
    }

    public JarcraftPlayer getSecondPlayer() {
        return secondPlayer;
    }

    public void setUnitSelection(UnitSelection unitSelection) {
        this.unitSelection = unitSelection;
    }
}
