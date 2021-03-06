package sandbox;

import jnibwapi.types.UnitType;
import player.Player;
import player.factory.PlayerFactory;
import simulation.Position;
import simulation.Simulation;
import simulation.SimulationState;
import simulation.Unit;

import java.util.ArrayList;
import java.util.List;

public class NewSimulation {
    public static void main(String... args) {
        SimulationState simulationState = new SimulationState(600.0, 600.0, 1.0);
        simulationState.putUnit(new Unit(0, UnitType.UnitTypes.Protoss_Zealot, new Position(100, 100)));
        simulationState.putUnit(new Unit(1, UnitType.UnitTypes.Protoss_Zealot, new Position(500, 500)));
        List<Player<SimulationState, Unit, Position>> players = new ArrayList<>();
        players.add(PlayerFactory.getSimulationSimplePlayer(0));
        players.add(PlayerFactory.getSimulationSimplePlayer(1));
        Simulation simulation = new Simulation(simulationState, players, true, 1, Double.MAX_VALUE);
        simulation.play();
    }
}