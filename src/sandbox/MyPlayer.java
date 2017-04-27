package sandbox;


import bwmcts.sparcraft.AnimationFrameData;
import bwmcts.sparcraft.GameState;
import bwmcts.sparcraft.PlayerProperties;
import bwmcts.sparcraft.UnitAction;
import bwmcts.sparcraft.UnitProperties;
import bwmcts.sparcraft.WeaponProperties;
import bwmcts.sparcraft.players.Player;
import jnibwapi.JNIBWAPI;

import java.util.HashMap;
import java.util.List;

public class MyPlayer extends Player {

    public MyPlayer(int id, JNIBWAPI jnibwapi) {
        super(id);
        jnibwapi.loadTypeData();
        AnimationFrameData.Init();
        PlayerProperties.Init();
        WeaponProperties.Init(jnibwapi);
        UnitProperties.Init(jnibwapi);
    }

    @Override
    public void getMoves(GameState state, HashMap<Integer, List<UnitAction>> moves, List<UnitAction> moveVec) {
        state.getAllUnit();
    }
}
