package player.starcraft;

import jnibwapi.JNIBWAPI;

public abstract class StarcraftPlayer {

    protected jnibwapi.Player jnibwapiPlayer;

    public abstract void giveOrders(JNIBWAPI jnibwapi);
}
