package fitnessevaluator.simulation;

import fitnessevaluator.FitnessEvaluator;
import jnibwapi.types.UnitType;
import player.Player;
import simulation.Position;
import simulation.Simulation;
import simulation.SimulationState;
import simulation.Unit;
import simulation.bridge.JNIBWAPI_LOAD;
import util.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SimulationEvaluator extends FitnessEvaluator<Unit> {

    static {
        JNIBWAPI_LOAD.loadIfNecessary();
    }

    private boolean graphics;
    private double timeStep;
    private double timeLimit;
    private double mapHeight;
    private double mapWidth;
    private double gapHeight;
    private double gapWidth;
    private Player<SimulationState, Unit, Position> firstPlayer;
    private Player<SimulationState, Unit, Position> secondPlayer;
    private Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection;

    public SimulationEvaluator(boolean graphics, double timeStep, double timeLimit, double mapHeight, double mapWidth,
                               double gapHeight, double gapWidth, Player<SimulationState, Unit, Position> firstPlayer, Player<SimulationState, Unit, Position> secondPlayer) {
        this.graphics = graphics;
        this.timeStep = timeStep;
        this.timeLimit = timeLimit;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.gapHeight = gapHeight;
        this.gapWidth = gapWidth;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    protected Collection<Unit> playGame() {
        try {
            SimulationState state = new SimulationState(mapWidth, mapHeight);
            state = putUnits(state, false, unitSelection.getLeft());
            state = putUnits(state, true, unitSelection.getRight());
            List<Player<SimulationState, Unit, Position>> playerList = Arrays.asList(firstPlayer, secondPlayer);
            Simulation simulation = new Simulation(state, playerList, graphics, timeStep, timeLimit);
            SimulationState simulationState = simulation.play();
            return simulationState.getUnits().values();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected double getMineralPrice(Unit unit) {
        return unit.getUnitType().getMineralPrice();
    }

    @Override
    protected double getGasPrice(Unit unit) {
        return unit.getUnitType().getGasPrice();
    }

    @Override
    protected double getHitPoints(Unit unit) {
        return unit.getHitPoints();
    }

    @Override
    protected double getShields(Unit unit) {
        return unit.getShields();
    }

    @Override
    protected double getMaxHitPoints(Unit unit) {
        return unit.getUnitType().getMaxHitPoints();
    }

    @Override
    protected double getMaxShields(Unit unit) {
        return unit.getUnitType().getMaxShields();
    }

    @Override
    protected int getPlayerId(Unit unit) {
        return unit.getPlayerId();
    }

    private SimulationState putUnits(SimulationState state, boolean secondPlayer, List<List<UnitType>> unitTypes) {
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

    public Player<SimulationState, Unit, Position> getFirstPlayer() {
        return firstPlayer;
    }

    public Player<SimulationState, Unit, Position> getSecondPlayer() {
        return secondPlayer;
    }

    public void setUnitSelection(Pair<List<List<UnitType>>, List<List<UnitType>>> unitSelection) {
        this.unitSelection = unitSelection;
    }

    public void setGraphics(boolean graphics) {
        this.graphics = graphics;
    }
}
