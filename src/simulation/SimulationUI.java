package simulation;

import jnibwapi.types.UnitType;
import simulation.order.AttackOrder;
import simulation.order.Order;
import simulation.player.Player;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import static java.awt.Toolkit.getDefaultToolkit;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static jnibwapi.types.UnitType.UnitTypes.getAllUnitTypes;

public class SimulationUI extends JComponent {

    private static final SimulationUI INSTANCE = new SimulationUI();
    private Map<UnitType, Image> images = new HashMap<>();
    private Image background;
    private String dirPath = "img\\";
    private GameState gameState;
    private List<Player> players;

    public static synchronized SimulationUI getInstance() {
        return INSTANCE;
    }

    private SimulationUI() {
        JFrame frame = new JFrame();
        frame.setSize(640, 640);
        frame.setTitle("Sparcraft in JAVA");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setVisible(true);

    }

    private void loadImages() {
        for (UnitType unitType : getAllUnitTypes()) {
            String filePath = dirPath + "units\\" + unitType.getName().replaceAll(" ", "_") + ".png";
            images.put(unitType, getDefaultToolkit().getImage(filePath));
        }
        int i = (int) (Math.random() * 10 % 4);
        background = getDefaultToolkit().getImage(dirPath + "ground\\ground" + (i > 0 ? i : "") + ".png");
    }

    public void paint(Graphics graphics) {
        if (gameState != null) {
            if (images.isEmpty()) {
                loadImages();
            }
            if (background != null) {
                graphics.drawImage(background, (int) gameState.getMaxX(), (int) gameState.getMaxY(), this);
            } else {
                graphics.drawRect(0, 0, (int) gameState.getMaxX(), (int) gameState.getMaxY());
            }

//            if (p1 instanceof NeuralNetworkPlayer) {
//                ConcurrentHashMap<Unit, Integer> maxIndexes = ((NeuralNetworkPlayer) p1).getCurrentMaxIndexes();
//                drawChosenActions(graphics, maxIndexes);
//            }
//
//            if (p2 instanceof NeuralNetworkPlayer) {
//                ConcurrentHashMap<Unit, Integer> maxIndexes = ((NeuralNetworkPlayer) p2).getCurrentMaxIndexes();
//                drawChosenActions(graphics, maxIndexes);
//            }

            for (Unit unit : gameState.getUnits().values()) {
                if (unit.getHitPoints() > 0) {
                    graphics.setColor(getColor(unit.getPlayerId()));
                    Image image = images.get(unit.getUnitType());
                    Position position = unit.getPosition();
                    if (image != null) {
                        int width = image.getWidth(this);
                        int height = image.getHeight(this);
                        graphics.drawImage(image, (int) position.getX() - width / 2, (int) position.getY() - height / 2, width, height, this);
                    } else {
                        graphics.drawOval((int) position.getX() - 2, (int) position.getY() - 2, 4, 4);
                    }
                    Order order = unit.getOrder();
                    if (order != null && order instanceof AttackOrder) {
                        AttackOrder attackOrder = (AttackOrder) order;
                        Position attackedUnitPosition = attackOrder.getUnitToAttack().getPosition();
                        graphics.drawLine((int) position.getX() - 2, (int) position.getY() - 2,
                                (int) attackedUnitPosition.getX() - 2, (int) attackedUnitPosition.getY() - 2);
                    }
                    drawUnitHP(graphics, unit, position);
                }

            }
        }
    }

    private void drawChosenActions(Graphics graphics, ConcurrentHashMap<Unit, Integer> maxIndexes) {
        for (Entry<Unit, Integer> entry : maxIndexes.entrySet()) {
            Unit unit = entry.getKey();
            int maxIndex = entry.getValue();
            if (entry.getKey().getHitPoints() > 0) {
                graphics.setColor(getColor(maxIndex));

                int innerRadius = 12;
                int outerRadius = 14;

                for (int counter = innerRadius; counter <= outerRadius; counter++) {
                    graphics.drawOval(
                            (int) unit.getPosition().getX() - counter,
                            (int) unit.getPosition().getY() - counter,
                            counter * 2,
                            counter * 2);
                }
            }
        }
    }

    private void drawUnitHP(Graphics graphics, Unit unit, Position position) {
        UnitType unitType = unit.getUnitType();
        double currentDurability = unit.getHitPoints() + unit.getShields();
        int maxDurability = unitType.getMaxHitPoints() + unitType.getMaxShields();
        graphics.drawRect((int) position.getX() - 15, (int) position.getY() - 15, (int) (30 * currentDurability / maxDurability), 1);
    }

    public void setGameState(GameState state) {
        this.gameState = state;
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

}
