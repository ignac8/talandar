package sandbox;

import bwmcts.combat.UctLogic;
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
import bwmcts.uct.UctConfig;
import bwmcts.uct.uctcd.UCTCD;
import jnibwapi.JNIBWAPI;
import jnibwapi.types.UnitType;

public class Sandbox {

    public static void main(String... args) throws Exception {

        MyBWAPIEventListener bwapiEventListener = new MyBWAPIEventListener();
        JNIBWAPI bwapi = new JNIBWAPI_LOAD(bwapiEventListener);

        bwapi.loadTypeData();

        AnimationFrameData.Init();
        PlayerProperties.Init();
        WeaponProperties.Init(bwapi);
        UnitProperties.Init(bwapi);

        Player player1 = new MyPlayer(1, bwapi);
        Player player2 = new MyPlayer(2, bwapi);

        GameState state = new GameState();
        state.setMap(new Map(25, 25));
        Unit unit1 = new Unit(UnitType.UnitTypes.Terran_Marine, 0, new Position(1, 1));
        Unit unit2 = new Unit(UnitType.UnitTypes.Terran_Marine, 1, new Position(2, 2));
        state.addUnit(unit1);
        state.addUnit(unit2);



        Game game = new Game(state, player1, player2, 100000, true);
        game.play();
        System.out.println("Kurczak");

    }
}
