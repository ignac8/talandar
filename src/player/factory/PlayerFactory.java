package player.factory;

import jnibwapi.JNIBWAPI;
import player.NeuralNetworkPlayer;
import player.SimplePlayer;
import player.executor.SimulationExecutor;
import player.executor.StarcraftExecutor;
import simulation.SimulationState;

public class PlayerFactory {

    public static SimplePlayer<SimulationState, simulation.Unit, simulation.Position> getSimulationSimplePlayer(int playerId) {
        return new SimplePlayer<>(new SimulationExecutor(), playerId);
    }

    public static NeuralNetworkPlayer<SimulationState, simulation.Unit, simulation.Position> getSimulationNeuralNetworkPlayer(int playerId) {
        return new NeuralNetworkPlayer<>(new SimulationExecutor(), playerId);
    }

    public static SimplePlayer<JNIBWAPI, jnibwapi.Unit, jnibwapi.Position> getStarcraftSimplePlayer(int playerId) {
        return new SimplePlayer<>(new StarcraftExecutor(), playerId);
    }

    public static NeuralNetworkPlayer<JNIBWAPI, jnibwapi.Unit, jnibwapi.Position> getStarcraftNeuralNetworkPlayer(int playerId) {
        return new NeuralNetworkPlayer<>(new StarcraftExecutor(), playerId);
    }
}
