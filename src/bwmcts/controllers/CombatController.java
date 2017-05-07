package bwmcts.controllers;

import bwmcts.clustering.UPGMA;
import bwmcts.combat.ICombatLogic;
import bwmcts.combat.UctLogic;
import bwmcts.uct.UctConfig;
import bwmcts.uct.guctcd.ClusteringConfig;
import bwmcts.uct.guctcd.GUCTCD;
import bwmcts.uct.iuctcd.IUCTCD;
import jnibwapi.BWAPIEventListener;
import jnibwapi.JNIBWAPI;
import jnibwapi.Position;
import jnibwapi.Unit;
import jnibwapi.util.BWColor;

public class CombatController implements BWAPIEventListener {

    int i = 0;
    private JNIBWAPI bwapi;
    private ICombatLogic combatLogic;

    public CombatController() {
        bwapi = new JNIBWAPI(this, false, true);

        //combatLogic = new NoOverKillAttackValueLogic();
        //combatLogic = new AttackValueLogic();
        //combatLogic = new NoOverKillAttackValueLogic();
        //combatLogic = new AttackClosestLogic();
        //combatLogic = new KiterLogic();

        bwapi.start();
    }

    public static void main(String[] args) {
        new CombatController();
    }

    public void connected() {
        bwapi.loadTypeData();

    }

    @Override
    public void matchStart() {

    }

    @Override
    public void matchFrame() {

    }

    @Override
    public void matchEnd(boolean winner) {

    }

    // Method called at the beginning of the game.
    public void gameStarted() {
        System.out.println("Game Started!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        bwapi.printText(bwapi.getMyUnits().size() + " units.");

        // allow me to manually control units during the game
        bwapi.enableUserInput();

        // set game speed to 30 (0 is the fastest. Tournament speed is 20)
        // You can also change the game speed from within the game by "/speed X" command.
        bwapi.setGameSpeed(30);
        bwapi.drawTargets(true);
        //bwapi.drawIDs(true);
        //bwapi.drawHealth(true);
        // analyze the map
        bwapi.loadMapData();
        bwapi.enablePerfectInformation();

        //combatLogic =new GuctcdLogic(bwapi,new GUCTCD(1.6,20,bwapi.getEnemies().get(0).getID(),bwapi.getSelf().getID(),500,false));
        //combatLogic
        GUCTCD guctcdA = new GUCTCD(new UctConfig(0),
                new ClusteringConfig(1, 6, new UPGMA()));
        combatLogic = new UctLogic(bwapi, new IUCTCD(new UctConfig(0)), 40);
        //combatLogic = new UctcdLogic(bwapi,new IUCTCD(1.6,20,bwapi.getEnemies().get(0).getID(),bwapi.getSelf().getID(),500,false), 30);
        //combatLogic =new GPortfolioGreedyLogic(bwapi,2,2,30,6);
        //combatLogic = new PortfolioGreedyLogic(bwapi,1,1,20 );
        //combatLogic =new IuctcdLogic(bwapi,new IUCTCD(1.6,20,bwapi.getEnemies().get(0).getID(),bwapi.getSelf().getID(),5000,false));

        // ============== YOUR CODE GOES HERE =======================

        // This is called at the beginning of the game. You can
        // initialize some data structures (or do something similar)
        // if needed. For example, you should maintain a memory of seen
        // enemy buildings.

        // ==========================================================
    }

    public void act() {

        // ============== YOUR CODE GOES HERE =======================
        //System.out.println("Act called");
        //if ((i++)%2==1){
        int time = 40;    // 40 ms
        System.out.println("CC Called at " + bwapi.getFrameCount());
        combatLogic.act(bwapi, time);
        //}
        //System.out.println("CC Returning at " + bwapi.getFrameCount());
        //System.out.println("Act done");
    }

    // Method called on every frame (approximately 30x every second).
    public void gameUpdate() {

        // Draw debug information on screen
        // drawDebugInfo();

        // Call the act() method every X frames
        int x = 1;
        if (bwapi.getFrameCount() % x == 0)
            act();

    }

    // Some additional event-related methods.
    public void gameEnded() {
    }

    public void matchEnded(boolean winner) {
    }

    public void nukeDetect(int x, int y) {
    }

    public void nukeDetect() {
    }

    public void playerLeft(int id) {
    }

    @Override
    public void nukeDetect(Position p) {

    }

    public void unitCreate(int unitID) {
    }

    public void unitDestroy(int unitID) {
    }

    public void unitDiscover(int unitID) {
    }

    public void unitEvade(int unitID) {
    }

    public void unitHide(int unitID) {
    }

    public void unitMorph(int unitID) {
    }

    @Override
    public void unitRenegade(int unitID) {

    }

    @Override
    public void saveGame(String gameName) {

    }

    @Override
    public void unitComplete(int unitID) {

    }

    @Override
    public void playerDropped(int playerID) {

    }

    public void unitShow(int unitID) {

    }

    public void keyPressed(int keyCode) {
    }

    @Override
    public void sendText(String text) {

    }

    @Override
    public void receiveText(String text) {

    }

    // Returns the id of a unit of a given type, that is closest to a pixel position (x,y), or -1 if we
    // don't have a unit of this type
    public int getNearestUnit(int unitTypeID, int x, int y) {
        int nearestID = -1;
        double nearestDist = 9999999;
        for (Unit unit : bwapi.getMyUnits()) {
            if ((unit.getTypeID() != unitTypeID) || (!unit.isCompleted())) continue;
            double dist = Math.sqrt(Math.pow(unit.getX() - x, 2) + Math.pow(unit.getY() - y, 2));
            if (nearestID == -1 || dist < nearestDist) {
                nearestID = unit.getID();
                nearestDist = dist;
            }
        }
        return nearestID;
    }

    // Draws debug information on the screen.
    // Reimplement this function however you want.
    public void drawDebugInfo() {

        // Draw our home position.
        //bwapi.drawText(new Point(5,0), "Our home position: "+String.valueOf(homePositionX)+","+String.valueOf(homePositionY), true);

        // Draw circles over workers (blue if they're gathering minerals, green if gas, Yellow if they're constructing).
        for (Unit u : bwapi.getMyUnits()) {
            if (u.isGatheringMinerals()) bwapi.drawCircle(u.getX(), u.getY(), 12, BWColor.Blue.getID(), false, false);
            else if (u.isGatheringGas()) bwapi.drawCircle(u.getX(), u.getY(), 12, BWColor.Green.getID(), false, false);
        }

    }


}
