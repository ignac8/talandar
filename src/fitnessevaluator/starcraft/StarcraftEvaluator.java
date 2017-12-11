package fitnessevaluator.starcraft;

import fitnessevaluator.FitnessEvaluator;
import jnibwapi.BWAPIEventListener;
import jnibwapi.JNIBWAPI;
import jnibwapi.Position;
import jnibwapi.Unit;
import player.NeuralNetworkPlayer;

import java.util.Collection;

public class StarcraftEvaluator extends FitnessEvaluator<Unit> implements BWAPIEventListener, Runnable {

    private static StarcraftEvaluator instance;
    private JNIBWAPI jnibwapi;
    private boolean gameInProgress = false;
    private NeuralNetworkPlayer<JNIBWAPI, Unit, Position> neuralNetworkPlayer;

    private StarcraftEvaluator() {
        jnibwapi = new JNIBWAPI(this, false, true);
    }

    public static StarcraftEvaluator getInstance() {
        if (instance == null) {
            instance = new StarcraftEvaluator();
            (new Thread(instance)).start();
        }
        return instance;
    }

    public void setNeuralNetworkPlayer(NeuralNetworkPlayer<JNIBWAPI, Unit, Position> neuralNetworkPlayer) {
        this.neuralNetworkPlayer = neuralNetworkPlayer;
    }

    @Override
    protected Collection<Unit> playGame() {
        while (!isGameInProgress()) {

        }
        while (isGameInProgress()) {

        }
        return jnibwapi.getAllUnits();
    }

    private synchronized boolean isGameInProgress() {
        return gameInProgress;
    }

    private synchronized void setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
    }

    protected double getMineralPrice(Unit unit) {
        return unit.getType().getMineralPrice();
    }

    protected double getGasPrice(Unit unit) {
        return unit.getType().getGasPrice();
    }

    protected double getHitPoints(Unit unit) {
        return unit.getHitPoints();
    }

    protected double getShields(Unit unit) {
        return unit.getShields();
    }

    protected double getMaxHitPoints(Unit unit) {
        return unit.getType().getMaxHitPoints();
    }

    protected double getMaxShields(Unit unit) {
        return unit.getType().getMaxShields();
    }

    protected int getPlayerId(Unit unit) {
        return unit.getPlayer().getID();
    }

    @Override
    public void run() {
        jnibwapi.start();
    }

    @Override
    public void connected() {

    }

    @Override
    public void matchStart() {
        setGameInProgress(true);
        jnibwapi.enablePerfectInformation();
        //jnibwapi.enableUserInput();
        jnibwapi.sendText("war aint what it used to be");
        jnibwapi.sendText("black sheep wall");
    }

    @Override
    public void matchFrame() {
        if (!isGameInProgress()) {
            matchStart();
        } else {
            neuralNetworkPlayer.giveOrders(jnibwapi);
        }
    }

    @Override
    public void matchEnd(boolean winner) {
        setGameInProgress(false);
    }

    @Override
    public void keyPressed(int keyCode) {

    }

    @Override
    public void sendText(String text) {

    }

    @Override
    public void receiveText(String text) {

    }

    @Override
    public void playerLeft(int playerID) {

    }

    @Override
    public void nukeDetect(Position p) {

    }

    @Override
    public void nukeDetect() {

    }

    @Override
    public void unitDiscover(int unitID) {

    }

    @Override
    public void unitEvade(int unitID) {

    }

    @Override
    public void unitShow(int unitID) {

    }

    @Override
    public void unitHide(int unitID) {

    }

    @Override
    public void unitCreate(int unitID) {

    }

    @Override
    public void unitDestroy(int unitID) {

    }

    @Override
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
}