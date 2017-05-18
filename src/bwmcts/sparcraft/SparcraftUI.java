package bwmcts.sparcraft;

import bwmcts.sparcraft.players.Player;
import jnibwapi.types.UnitType;
import jnibwapi.types.UnitType.UnitTypes;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class SparcraftUI extends JComponent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static SparcraftUI instance;
    public int c = 6;
    int offSetX = 0;
    int offSetY = 0;
    HashMap<String, Image> images = new HashMap<String, Image>();
    Image background;
    String dirPath = "img\\";
    private GameState state;
    private Player p1;
    private Player p2;
    private JFrame frame;

    public SparcraftUI(boolean standalone) {
        if (standalone) {
            frame = new JFrame();
            frame.setSize(700, 700);
            frame.setTitle("Sparcraft in JAVA");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        for (UnitType u : UnitTypes.getAllUnitTypes()) {
            images.put(u.toString(), Toolkit.getDefaultToolkit().getImage(dirPath + "units\\" + u.getName().replaceAll(" ", "_") + ".png"));
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

            graphics.setColor(Color.blue);
            int k = 0;
            for (Unit a : state.getAllUnits()[0]) {
                if (a != null && a.isAlive()) {
                    Image i = images.get(a.getUnitType().toString());
                    Position p = a.currentPosition(state.getTime());
                    if (i != null) {
                        drawImageOnPosition(graphics, i, p);
                    } else {
                        graphics.drawOval(p.getX() - 2 + offSetX, p.getY() - 2 + offSetY, 4, 4);
                    }
                    if (a.previousAction != null && a.previousAction.moveType == UnitActionTypes.ATTACK) {
                        graphics.drawLine(p.getX() - 2 + offSetX, p.getY() - 2 + offSetY, a.previousAction.pos().getX() - 2 + offSetX, a.previousAction.pos().getY() - 2 + offSetY);
                    }
                    //drawUnitInformation(graphics, a, ++k, p);
                }

            }
            graphics.setColor(Color.red);
            for (Unit a : state.getAllUnits()[1]) {
                if (a != null && a.isAlive()) {
                    Image i = images.get(a.getUnitType().toString());
                    Position p = a.currentPosition(state.getTime());
                    if (i != null) {
                        drawImageOnPosition(graphics, i, p);
                    } else {
                        graphics.drawOval(p.getX() - 2 + offSetX, p.getY() - 2 + offSetY, 4, 4);
                    }
                    if (a.previousAction != null && a.previousAction.moveType == UnitActionTypes.ATTACK) {
                        graphics.drawLine(p.getX() - 2 + offSetX, p.getY() - 2 + offSetY, a.previousAction.pos().getX() - 2 + offSetX, a.previousAction.pos().getY() - 2 + offSetY);
                    }
                    //drawUnitInformation(graphics, a, ++k, p);
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

        } else {
            graphics.drawRect(1, 1, 200, 200);
        }
    }

    private void drawClusters(Graphics g, List<List<Unit>> clusters) {
        int clusterId = 0;
        if (clusters == null)
            return;
        for (List<Unit> units : clusters) {
            g.setColor(getColor(clusterId++));
            for (Unit a : units) {
                g.drawOval(a.currentPosition(state.getTime()).getX() - 12 + offSetX, a.currentPosition(state.getTime()).getY() - 12 + offSetY, 24, 24);
                g.drawOval(a.currentPosition(state.getTime()).getX() - 13 + offSetX, a.currentPosition(state.getTime()).getY() - 13 + offSetY, 26, 26);
                g.drawOval(a.currentPosition(state.getTime()).getX() - 14 + offSetX, a.currentPosition(state.getTime()).getY() - 14 + offSetY, 28, 28);
            }
        }
    }

    private void drawScaleForMap(Graphics g, int pixelWidth, int pixelHeight) {
        for (int i = 0; i < (int) pixelWidth + 1; i += 50) {
            if (i != 0 && i % 100 == 0) {
                g.drawString(String.valueOf(i), offSetX + i, offSetY);
            }
            g.drawLine(offSetX + i, offSetY, offSetX + i, offSetY - 10);
        }
        for (int i = 0; i < (int) pixelHeight + 1; i += 50) {
            if (i != 0 && i % 100 == 0) {
                g.drawString(String.valueOf(i), offSetX - 20, offSetY + i);
            }
            g.drawLine(offSetX - 10, offSetY + i, offSetX, offSetY + i);
        }
    }

    private void drawUnitInformation(Graphics g, Unit u, int i, Position p) {
        g.drawString(u.getId() + ":" + u.name() + " HP:" + u.getCurrentHP() + " A:" + u.getArmor() + " D:" + u.damageGround() + "/" + u.damageAir(), 3, i * 20);

        g.drawRect(p.getX() + offSetX - 15, p.getY() - 15 + offSetY, (int) (30 * u.currentHP / u.getMaxHP()), 1);

    }

    public void setGameState(GameState state) {
        this.state = state;
    }

    private void drawImageOnPosition(Graphics g, Image i, Position p) {
        int width = i.getWidth(this);
        int height = i.getHeight(this);

        g.drawImage(i, p.getX() + offSetX - (int) (width / 2), p.getY() + offSetY - (int) (height / 2), width, height, this);
    }

    private Color getColor(int i) {

        switch (i) {
            case 0:
                return Color.CYAN;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.WHITE;
            case 3:
                return Color.PINK;
            case 4:
                return Color.YELLOW;
            case 5:
                return Color.LIGHT_GRAY;
            case 6:
                return Color.RED;
            case 7:
                return Color.BLUE;
            case 8:
                return Color.ORANGE;
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
