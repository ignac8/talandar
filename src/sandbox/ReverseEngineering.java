package sandbox;

import bwmcts.clustering.DynamicKMeans;
import bwmcts.combat.UctLogic;
import bwmcts.sparcraft.AnimationFrameData;
import bwmcts.sparcraft.Constants;
import bwmcts.sparcraft.EvaluationMethods;
import bwmcts.sparcraft.Game;
import bwmcts.sparcraft.GameState;
import bwmcts.sparcraft.Map;
import bwmcts.sparcraft.PlayerProperties;
import bwmcts.sparcraft.Players;
import bwmcts.sparcraft.Position;
import bwmcts.sparcraft.StateEvalScore;
import bwmcts.sparcraft.Unit;
import bwmcts.sparcraft.UnitProperties;
import bwmcts.sparcraft.WeaponProperties;
import bwmcts.sparcraft.players.Player;
import bwmcts.test.JNIBWAPI_LOAD;
import bwmcts.uct.UctConfig;
import bwmcts.uct.guctcd.ClusteringConfig;
import bwmcts.uct.guctcd.GUCTCD;
import bwmcts.uct.uctcd.UCTCD;
import jnibwapi.BWAPIEventListener;
import jnibwapi.JNIBWAPI;
import jnibwapi.types.UnitType;
import jnibwapi.types.UnitType.UnitTypes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class ReverseEngineering implements BWAPIEventListener {

    private static boolean graphics = false;

    JNIBWAPI bwapi;

    StringBuffer buf;

    public static void main(String[] args) throws Exception {
        System.out.println("Create TC instance");
        ReverseEngineering tc = new ReverseEngineering();
        //tc.bwapi=new JNIBWAPI(tc);
        //tc.bwapi.start();

        tc.bwapi = new JNIBWAPI_LOAD(tc);
        tc.bwapi.loadTypeData();

        AnimationFrameData.Init();
        PlayerProperties.Init();
        WeaponProperties.Init(tc.bwapi);
        UnitProperties.Init(tc.bwapi);

        graphics = true;

        Constants.Max_Units = 300;
        Constants.Max_Moves = Constants.Max_Units + Constants.Num_Directions + 1;

        Player p1 = new UctLogic(tc.bwapi, new GUCTCD(new UctConfig(0), new ClusteringConfig(1, 6, new DynamicKMeans(30.0))), 40);

        Player p2 = new UctLogic(tc.bwapi, new UCTCD(new UctConfig(1)), 40);

        tc.buf = new StringBuffer();
        System.out.println("Player0: " + p1.toString());
        System.out.println("Player1: " + p2.toString());
        tc.buf.append("Player0: ").append(p1.toString()).append("\r\n");
        tc.buf.append("Player1: ").append(p2.toString()).append("\r\n");

        tc.dragoonZTest(p1, p2, 100, new int[]{16, 8, 16, 32, 48, 64, 80, 96, 112, 128, 144});

        try {
            String player0 = p1.toString();
            if (player0.indexOf(" ") > 0) {
                player0 = player0.substring(0, player0.indexOf(" "));
            }
            String player1 = p2.toString();
            if (player1.indexOf(" ") > 0) {
                player1 = player1.substring(0, player1.indexOf(" "));
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
            Calendar cal = Calendar.getInstance();
            File f = new File(player0 + "_vs_" + player1 + "_" + dateFormat.format(cal.getTime()) + ".txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(f));
            out.write(tc.buf.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void dragoonZTest(Player p1, Player p2, int runs, int[] n) {


        for (Integer i : n) {
            try {
                System.out.println("--- units: " + i);
                buf.append("--- units: ").append(i).append("\r\n");
                float result = testDragoonZealotGames(p1, p2, i, runs);
                buf.append("DRAGOON ZEALOT TEST RESULT: ").append(result).append("\r\n");
                System.out.println("DRAGOON ZEALOT TEST RESULT: " + result);

                //System.out.println("Result=" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    float testDragoonZealotGames(Player p1, Player p2, int n, int games) throws Exception {

        HashMap<UnitType, Integer> unitsA = new HashMap<>();
        unitsA.put(UnitTypes.Protoss_Dragoon, n / 2);
        unitsA.put(UnitTypes.Protoss_Zealot, n / 2);


        HashMap<UnitType, Integer> unitsB = new HashMap<>();
        unitsB.put(UnitTypes.Protoss_Dragoon, n / 2);
        unitsB.put(UnitTypes.Protoss_Zealot, n / 2);


        Constants.Max_Units = n * 2;
        Constants.Max_Moves = Constants.Max_Units + Constants.Num_Directions + 1;

        System.out.println("Dragoons: " + n / 2 + "\tZealots: " + n / 2 + " on each side");
        buf.append("Dragoons: ").append(n / 2).append("\tZealots: ").append(n / 2).append(" on each side\r\n");
        List<Double> results = new ArrayList<>();
        int wins = 0;
        for (int i = 1; i <= games; i++) {
            double result = testGame(p1, p2, unitsA, unitsB);
            results.add(result);
            if (result > 0)
                wins++;

            //System.out.println("WHUUT " + result);

            if (0 == 0) {
                //System.out.println("Score average: " + average(results) + "\tDeviation: " + deviation(results));
                System.out.println("Win average: " + ((double) wins) / ((double) i));
                buf.append("Win average: ").append(((double) wins) / ((double) i)).append("\r\n");
            }
        }

        // Calc deviation and average
        System.out.println("--------------- Score average: " + average(results) + "\tDeviation: " + deviation(results));
        buf.append("--------------- Score average: ").append(average(results)).append("\tDeviation: ").append(deviation(results)).append("\r\n");
        System.out.println("--------------- Win average: " + ((double) wins) / ((double) games));
        buf.append("--------------- Win average: ").append(((double) wins) / ((double) games)).append("\r\n");
        return (float) wins / (float) games;

    }

    int testGame(Player p1, Player p2, HashMap<UnitType, Integer> unitsA, HashMap<UnitType, Integer> unitsB) throws Exception {

        GameState initialState = gameState(unitsA, unitsB);

        shufflePositions(initialState, 100);

        p1.setID(0);
        p2.setID(1);

        // enter a maximum move limit for the game to go on for
        int moveLimit = 20000;

        // contruct the game
        Game g = new Game(initialState, p1, p2, moveLimit, graphics, true);

        g.play();

        // you can access the resulting game state after g has been played via getState
        GameState finalState = g.getState();
        // you can now evaluate the state however you wish. let's use an LTD2 evaluation from the point of view of player one
        StateEvalScore score = finalState.eval(Players.Player_One.ordinal(), EvaluationMethods.LTD2);
        // StateEvalScore has two components, a numerical score and a number of Movement actions performed by each player
        // with this evaluation, positive val means win, negative means loss, 0 means tie
        return score._val;
    }

    private void shufflePositions(GameState state, int amount) {

        for (Unit unit : state.getAllUnits()[0]) {
            if (unit == null || unit.getPosition() == null)
                continue;
            int x = unit.getPosition().getX();
            int y = unit.getPosition().getY();
            int rX = (int) ((-amount) / 2 + Math.random() * amount);
            int rY = (int) ((-amount) / 2 + Math.random() * amount);
            int newX = x + rX;
            int newY = y + rY;

            if (newX > 30 && newX < state.getMap().getPixelWidth() - 30 &&
                    newY > 30 && newY < state.getMap().getPixelHeight() - 30) {
                unit.getPosition().setX(x + rX);
                unit.getPosition().setY(y + rY);
            }

        }

        for (Unit unit : state.getAllUnits()[1]) {
            if (unit == null || unit.getPosition() == null)
                continue;

            int x = unit.getPosition().getX();
            int y = unit.getPosition().getY();
            int rX = (int) ((-amount) / 2 + Math.random() * amount);
            int rY = (int) ((-amount) / 2 + Math.random() * amount);
            unit.getPosition().setX(x + rX);
            unit.getPosition().setY(y + rY);
        }

    }

    private GameState gameState(HashMap<UnitType, Integer> unitsA,
                                HashMap<UnitType, Integer> unitsB) throws Exception {

        // GameState only has a default constructor, you must add units to it manually
        GameState state = new GameState();
        state.setMap(new Map(25, 20));

        int startXA = 275;
        int startXB = 575;
        int space = 30;
        int startY = 30;
        int unitsPerLine = 16;

        for (UnitType type : unitsA.keySet()) {

            try {
                state.addUnit(bwapi.getUnitType(type.getID()), Players.Player_One.ordinal(), new Position(startXA, startY + space));
            } catch (Exception e) {
            }

            for (int i = 1; i < unitsA.get(type); i++) {
                int x = startXA - (i / unitsPerLine) * space;
                int y = startY + space * (i % unitsPerLine) + space;
                try {
                    state.addUnit(bwapi.getUnitType(type.getID()), Players.Player_One.ordinal(), new Position(x, y));
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }

            startXA -= space * 2;

        }

        for (UnitType type : unitsB.keySet()) {

            try {
                state.addUnit(bwapi.getUnitType(type.getID()), Players.Player_Two.ordinal(), new Position(startXB, startY + space));
            } catch (Exception e) {
            }

            for (int i = 1; i < unitsB.get(type); i++) {
                int x = startXB + (i / unitsPerLine) * space;
                int y = startY + space * (i % unitsPerLine) + space;
                try {
                    state.addUnit(bwapi.getUnitType(type.getID()), Players.Player_Two.ordinal(), new Position(x, y));
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }

            startXB += space * 2;

        }

        return state;
    }

    private double deviation(List<Double> times) {
        double average = average(times);
        double sum = 0;
        for (Double d : times) {
            sum += (d - average) * (d - average);
        }
        return Math.sqrt(sum / times.size());
    }

    private double average(List<Double> times) {
        double sum = 0;
        for (Double d : times) {
            sum += d;
        }
        return sum / ((double) times.size());
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
    public void playerLeft(int id) {
    }

    @Override
    public void nukeDetect(jnibwapi.Position p) {

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


    @Override
    public void connected() {

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
}