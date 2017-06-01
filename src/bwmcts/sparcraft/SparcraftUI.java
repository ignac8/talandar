package bwmcts.sparcraft;

import bwmcts.sparcraft.players.Player;
import jnibwapi.types.UnitType;
import jnibwapi.types.UnitType.UnitTypes;
import player.NeuralNetworkPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SparcraftUI extends JComponent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static SparcraftUI instance;
    private int offSetX = 0;
    private int offSetY = 0;
    private HashMap<String, Image> images = new HashMap<>();
    private Image background;
    private String dirPath = "img\\";
    private GameState state;
    private Player p1;
    private Player p2;
    private JFrame frame;

    private SparcraftUI(boolean standalone) {
        if (standalone) {
            frame = new JFrame();
            frame.setSize(640, 640);
            frame.setTitle("Sparcraft in JAVA");
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.getContentPane().add(this);
            frame.setVisible(true);
        }
    }

    public static synchronized SparcraftUI getUI(boolean standalone) {
        if (instance == null) {
            instance = new SparcraftUI(standalone);
        }

        return instance;
    }

    public static synchronized SparcraftUI getUI(GameState state, Player p1, Player p2, boolean standalone) {
        if (instance == null) {
            instance = new SparcraftUI(standalone);
        }
        instance.setGameState(state);
        instance.setP1(p1);
        instance.setP2(p2);

        return instance;
    }

    public synchronized static void closeWindow() {
        if (instance != null) {
            instance.frame.dispose();
        }
    }

    private void loadImages() {
        for (UnitType unitType : UnitTypes.getAllUnitTypes()) {
            images.put(unitType.toString(), Toolkit.getDefaultToolkit().getImage(dirPath + "units\\" + unitType.getName().replaceAll(" ", "_") + ".png"));
        }
        int i = (int) (Math.random() * 10 % 4);
        background = Toolkit.getDefaultToolkit().getImage(dirPath + "ground\\ground" + (i > 0 ? i : "") + ".png");
    }

    public void paint(Graphics graphics) {
        if (state != null) {

            if (images.isEmpty()) {
                loadImages();
            }

            Map map = state.getMap();
            if (map != null) {
                if (background != null) {
                    graphics.drawImage(background, offSetX, offSetY, map.getPixelWidth() * 2, map.getPixelHeight() * 2, this);
                } else {
                    graphics.drawRect(offSetX, offSetY, map.getPixelWidth() * 2, map.getPixelHeight() * 2);
                }
                //drawScaleForMap(graphics, map.getPixelWidth() * 2, map.getPixelHeight() * 2);
            }

            if (p1 instanceof NeuralNetworkPlayer) {
                ConcurrentHashMap<Unit, Integer> maxIndexes = ((NeuralNetworkPlayer) p1).getMaxIndexes();
                drawChosenActions(graphics, maxIndexes);
            }

            if (p2 instanceof NeuralNetworkPlayer) {
                ConcurrentHashMap<Unit, Integer> maxIndexes = ((NeuralNetworkPlayer) p2).getMaxIndexes();
                drawChosenActions(graphics, maxIndexes);
            }

            graphics.setColor(Color.blue);
            int counter = 0;
            for (Unit unit : state.getAllUnits()[0]) {
                if (unit != null && unit.isAlive()) {
                    Image image = images.get(unit.getUnitType().toString());
                    Position position = unit.currentPosition(state.getCurrentTime());
                    if (image != null) {
                        drawImageOnPosition(graphics, image, position);
                    } else {
                        graphics.drawOval(position.getX() - 2 + offSetX, position.getY() - 2 + offSetY, 4, 4);
                    }
                    if (unit.previousAction != null && unit.previousAction.moveType == UnitActionTypes.ATTACK) {
                        graphics.drawLine(position.getX() - 2 + offSetX, position.getY() - 2 + offSetY, unit.previousAction.pos().getX() - 2 + offSetX, unit.previousAction.pos().getY() - 2 + offSetY);
                    }
                    //drawUnitInformation(graphics, unit, ++counter, position);
                    drawUnitHP(graphics, unit, position);
                }

            }
            graphics.setColor(Color.red);
            for (Unit unit : state.getAllUnits()[1]) {
                if (unit != null && unit.isAlive()) {
                    Image image = images.get(unit.getUnitType().toString());
                    Position position = unit.currentPosition(state.getCurrentTime());
                    if (image != null) {
                        drawImageOnPosition(graphics, image, position);
                    } else {
                        graphics.drawOval(position.getX() - 2 + offSetX, position.getY() - 2 + offSetY, 4, 4);
                    }
                    if (unit.previousAction != null && unit.previousAction.moveType == UnitActionTypes.ATTACK) {
                        graphics.drawLine(position.getX() - 2 + offSetX, position.getY() - 2 + offSetY, unit.previousAction.pos().getX() - 2 + offSetX, unit.previousAction.pos().getY() - 2 + offSetY);
                    }
                    //drawUnitInformation(graphics, unit, ++counter, position);
                    drawUnitHP(graphics, unit, position);
                }

            }



            /*
            if (p1 instanceof UctLogic) {
                List<List<Unit>> clustersP1 = ((UctLogic) p1).getClusters();
                if (clustersP1 != null)
                    drawClusters(graphics, clustersP1);
            }
            if (p2 instanceof UctLogic) {
                List<List<Unit>> clustersP2 = ((UctLogic) p2).getClusters();
                if (clustersP2 != null)
                    drawClusters(graphics, clustersP2);
            }
            */

        }
    }

    private void drawChosenActions(Graphics graphics, ConcurrentHashMap<Unit, Integer> maxIndexes) {
        for (Entry<Unit, Integer> entry : maxIndexes.entrySet()) {
            Unit unit = entry.getKey();
            int maxIndex = entry.getValue();
            if (entry.getKey().isAlive()) {
                graphics.setColor(getColor(maxIndex));

                int innerRadius = 12;
                int outerRadius = 14;

                for (int counter = innerRadius; counter <= outerRadius; counter++) {
                    graphics.drawOval(
                            unit.currentPosition(state.getCurrentTime()).getX() - counter,
                            unit.currentPosition(state.getCurrentTime()).getY() - counter,
                            counter * 2,
                            counter * 2);
                }
            }
        }
    }

    private void drawClusters(Graphics graphics, List<List<Unit>> clusters) {
        int clusterId = 0;
        if (clusters == null)
            return;
        for (List<Unit> units : clusters) {
            graphics.setColor(getColor(clusterId++));
            for (Unit a : units) {
                graphics.drawOval(a.currentPosition(state.getCurrentTime()).getX() - 12 + offSetX, a.currentPosition(state.getCurrentTime()).getY() - 12 + offSetY, 24, 24);
                graphics.drawOval(a.currentPosition(state.getCurrentTime()).getX() - 13 + offSetX, a.currentPosition(state.getCurrentTime()).getY() - 13 + offSetY, 26, 26);
                graphics.drawOval(a.currentPosition(state.getCurrentTime()).getX() - 14 + offSetX, a.currentPosition(state.getCurrentTime()).getY() - 14 + offSetY, 28, 28);
            }
        }
    }

    private void drawScaleForMap(Graphics g, int pixelWidth, int pixelHeight) {
        for (int i = 0; i < pixelWidth + 1; i += 50) {
            if (i != 0 && i % 100 == 0) {
                g.drawString(String.valueOf(i), offSetX + i, offSetY);
            }
            g.drawLine(offSetX + i, offSetY, offSetX + i, offSetY - 10);
        }
        for (int i = 0; i < pixelHeight + 1; i += 50) {
            if (i != 0 && i % 100 == 0) {
                g.drawString(String.valueOf(i), offSetX - 20, offSetY + i);
            }
            g.drawLine(offSetX - 10, offSetY + i, offSetX, offSetY + i);
        }
    }

    private void drawUnitInformation(Graphics g, Unit u, int i, Position p) {
        g.drawString(u.getId() + ":" + u.name() + " HP:" + u.getCurrentHP() + " A:" + u.getArmor() + " D:" + u.damageGround() + "/" + u.damageAir(), 3, i * 20);

        g.drawRect(p.getX() + offSetX - 15, p.getY() - 15 + offSetY, 30 * u.currentHP / u.getMaxHP(), 1);

    }

    private void drawUnitName(Graphics g, Unit u, int i, Position p) {
        g.drawString(u.getId() + ":" + u.name() + " HP:" + u.getCurrentHP() + " A:" + u.getArmor() + " D:" + u.damageGround() + "/" + u.damageAir(), 3, i * 20);
    }

    private void drawUnitHP(Graphics g, Unit u, Position p) {
        g.drawRect(p.getX() + offSetX - 15, p.getY() - 15 + offSetY, 30 * u.currentHP / u.getMaxHP(), 1);
    }

    public void setGameState(GameState state) {
        this.state = state;
    }

    private void drawImageOnPosition(Graphics g, Image i, Position p) {
        int width = i.getWidth(this);
        int height = i.getHeight(this);

        g.drawImage(i, p.getX() + offSetX - width / 2, p.getY() + offSetY - height / 2, width, height, this);
    }

    private Color getColor(int i) {

        switch (i) {
            case 0:
                return Color.RED;
            case 1:
                return Color.YELLOW;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.CYAN;
            case 4:
                return Color.BLUE;
            case 5:
                return Color.MAGENTA;
            case 6:
                return Color.ORANGE;
            case 7:
                return Color.PINK;
            case 8:
                return Color.WHITE;
            case 9:
                return Color.LIGHT_GRAY;
            case 10:
                return Color.GRAY;
            case 11:
                return Color.DARK_GRAY;
            default:
                return Color.BLACK;
        }
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }
}
