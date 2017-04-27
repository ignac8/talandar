package sandbox;

import bwmcts.sparcraft.AnimationFrameData;
import bwmcts.sparcraft.Game;
import bwmcts.sparcraft.GameState;
import bwmcts.sparcraft.Map;
import bwmcts.sparcraft.PlayerProperties;
import bwmcts.sparcraft.UnitProperties;
import bwmcts.sparcraft.WeaponProperties;
import bwmcts.sparcraft.players.Player;
import bwmcts.test.JNIBWAPI_LOAD;
import jnibwapi.BWAPIEventListener;
import jnibwapi.JNIBWAPI;

public class Sandbox {

    public static void main(String... args) throws Exception {
        BWAPIEventListener bwapiEventListener = new MyBWAPIEventListener();
        JNIBWAPI jnibwapi = new JNIBWAPI_LOAD(bwapiEventListener);

        jnibwapi.loadTypeData();
        AnimationFrameData.Init();
        PlayerProperties.Init();
        WeaponProperties.Init(jnibwapi);
        UnitProperties.Init(jnibwapi);

        GameState initialState = new GameState();
        initialState.setMap(new Map(25, 25));

        Player player1 = new MyPlayer();
        Player player2 = new MyPlayer();
        Game game = new Game(initialState, player1, player2, 100000, true);
        game.play();
        System.out.println("Kurczak");

    }
}
