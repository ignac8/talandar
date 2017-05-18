package jnibwapi;

/**
 * Interface for BWAPI callback methods;
 * <p>
 * For BWAPI specific events see: http://code.google.com/p/bwapi/wiki/AIModule
 */
public interface BWAPIEventListener {

    /**
     * connection to BWAPI established
     */
    void connected();

    /**
     * game has just started, game settings can be turned on here
     */
    void matchStart();

    /**
     * perform AI logic here
     */
    void matchFrame();

    /**
     * game has just terminated
     */
    void matchEnd(boolean winner);

    /**
     * keyPressed from within StarCraft
     */
    void keyPressed(int keyCode);

    // BWAPI callbacks
    void sendText(String text);

    void receiveText(String text);

    void playerLeft(int playerID);

    void nukeDetect(Position p);

    void nukeDetect();

    void unitDiscover(int unitID);

    void unitEvade(int unitID);

    void unitShow(int unitID);

    void unitHide(int unitID);

    void unitCreate(int unitID);

    void unitDestroy(int unitID);

    void unitMorph(int unitID);

    void unitRenegade(int unitID);

    void saveGame(String gameName);

    void unitComplete(int unitID);

    void playerDropped(int playerID);
}
