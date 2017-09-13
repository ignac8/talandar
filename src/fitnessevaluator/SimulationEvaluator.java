package fitnessevaluator;

import jnibwapi.types.UnitType;
import player.simulation.Player;
import simulation.Game;
import simulation.GameState;
import simulation.JNIBWAPI_LOAD;
import simulation.Position;
import simulation.Unit;
import utils.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.lang.Math.pow;

public class SimulationEvaluator implements FitnessEvaluator<Player> {

    static {
        JNIBWAPI_LOAD.initialize();
    }

    private boolean graphics;
    private int limit;
    private int mapHeight;
    private int mapWidth;
    private int gapHeight;
    private int gapWidth;
    private Player firstPlayer;
    private Player secondPlayer;
    private Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection;

    public SimulationEvaluator(boolean graphics, int limit, int mapHeight, int mapWidth, int gapHeight, int gapWidth,
                               Player firstPlayer, Player secondPlayer, Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection) {
        this.graphics = graphics;
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
                    state.putUnit(new Unit(0, unitType, position));
                } else {
                    Position position = new Position(mapWidth - gapWidth * i,
                            mapHeight / 2 - gapHeight * (unitTypesColumn.size() - 1) / 2 + gapHeight * j);
                    state.putUnit(new Unit(1, unitType, position));
                }
            }
        }
        return state;
    }

    private GameState playGame() {
        try {
            GameState state = new GameState(mapWidth, mapHeight);
            state = putUnits(state, false, unitSelection.getLeft());
            state = putUnits(state, true, unitSelection.getRight());
            List<Player> playerList = Arrays.asList(firstPlayer, secondPlayer);
            Game game = new Game(state, playerList, graphics);
            return game.play();
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

        Map<Integer, Unit> units = finalState.getUnits();

        for (Unit unit : units.values()) {
            double unitWorth = unitWorthBasic(unit);
            double adjustedUnitWorth = unitWorthModifier(unit) * unitWorth;
            switch (unit.getPlayerId()) {
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
        double result = playerFitness / playerMaxFitness - enemyFitness / enemyMaxFitness;
        return result;
    }

    private double unitWorthModifier(Unit unit) {
        double currentDurability = unit.getHitPoints() + unit.getShields();
        int maxDurability = unit.getUnitType().getMaxHitPoints() + unit.getUnitType().getMaxShields();
        return pow((currentDurability / maxDurability), 0.75);
    }

    private double unitWorthBasic(Unit unit) {
        return unit.getUnitType().getMineralPrice() + 2 * unit.getUnitType().getGasPrice();
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setUnitSelection(Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection) {
        this.unitSelection = unitSelection;
    }
}
