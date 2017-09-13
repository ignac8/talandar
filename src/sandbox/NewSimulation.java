package sandbox;

import jnibwapi.types.UnitType;
import player.Player;
import player.SimplePlayer;
import simulation.Game;
import simulation.GameState;
import simulation.bridge.JNIBWAPI_LOAD;
import simulation.Position;
import simulation.Unit;

import java.util.ArrayList;
import java.util.List;

public class NewSimulation {
    public static void main(String... args) {
        JNIBWAPI_LOAD.getInstance();
        GameState gameState = new GameState(600, 600);
        gameState.putUnit(new Unit(0, UnitType.UnitTypes.Protoss_Zealot, new Position(100, 100)));
        gameState.putUnit(new Unit(1, UnitType.UnitTypes.Protoss_Zealot, new Position(500, 500)));
        List<Player> players = new ArrayList<>();
        players.add(new SimplePlayer(0));
        players.add(new SimplePlayer(1));
        Game game = new Game(gameState, players, true);
        game.play();
    }
}
