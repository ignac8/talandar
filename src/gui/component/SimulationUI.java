package gui.component;

import jnibwapi.types.UnitType;
import simulation.Position;
import simulation.SimulationState;
import simulation.Unit;
import simulation.order.AttackOrder;
import simulation.order.Order;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.awt.Toolkit.getDefaultToolkit;
import static jnibwapi.types.UnitType.UnitTypes.getAllUnitTypes;

public class SimulationUI extends JComponent {

    private static final SimulationUI INSTANCE = new SimulationUI();
    private Map<UnitType, Image> images = new LinkedHashMap<>();
    private Image background;
    private SimulationState simulationState;

    private SimulationUI() {
    }

    public static synchronized SimulationUI getInstance() {
        return INSTANCE;
    }

    public void paint(Graphics graphics) {
        if (simulationState != null) {
            if (images.isEmpty()) {
                loadImages();
            }
            drawBackground(graphics);
            for (Unit unit : simulationState.getUnits().values()) {
                if (unit.getHitPoints() > 0) {
                    drawChosenAction(graphics, unit);
                    graphics.setColor(getColor(unit.getPlayerId()));
                    drawUnit(graphics, unit);
                    drawAttack(graphics, unit);
                    drawUnitHP(graphics, unit);
                }
            }
        }
    }

    private void loadImages() {
        for (UnitType unitType : getAllUnitTypes()) {
            String filePath = "img\\" + "units\\" + unitType.getName().replaceAll(" ", "_") + ".png";
            images.put(unitType, getDefaultToolkit().getImage(filePath));
        }
        int i = (int) (Math.random() * 10 % 4);
        String backgroundFilePath = "img\\" + "ground\\ground" + (i > 0 ? i : "") + ".png";
        background = getDefaultToolkit().getImage(backgroundFilePath);
    }

    private void drawBackground(Graphics graphics) {
        if (background != null) {
            graphics.drawImage(background, 0, 0,
                    (int) simulationState.getMaxX(), (int) simulationState.getMaxY(), this);
        } else {
            graphics.drawRect(0, 0, (int) simulationState.getMaxX(), (int) simulationState.getMaxY());
        }
    }

    private void drawChosenAction(Graphics graphics, Unit unit) {
        int outputId = unit.getOutputId();
        if (outputId != -1 && unit.getHitPoints() > 0) {
            graphics.setColor(getColor(outputId));

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

    private Color getColor(int i) {
        switch (i) {
            case 0:
                return Color.RED;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.CYAN;
            case 4:
                return Color.YELLOW;
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
            case 12:
                return Color.RED;
            case 13:
                return Color.YELLOW;
            case 14:
                return Color.GREEN;
            case 15:
                return Color.CYAN;
            case 16:
                return Color.BLUE;
            case 17:
                return Color.MAGENTA;
            case 18:
                return Color.ORANGE;
            case 19:
                return Color.PINK;
            case 20:
                return Color.WHITE;
            case 21:
                return Color.LIGHT_GRAY;
            case 22:
                return Color.GRAY;
            case 23:
                return Color.DARK_GRAY;
            default:
                return Color.BLACK;
        }
    }

    private void drawUnit(Graphics graphics, Unit unit) {
        Position position = unit.getPosition();
        Image image = images.get(unit.getUnitType());
        if (image != null) {
            int width = image.getWidth(this);
            int height = image.getHeight(this);
            graphics.drawImage(image, (int) position.getX() - width / 2, (int) position.getY() - height / 2, width, height, this);
        } else {
            graphics.drawOval((int) position.getX() - 4 / 2, (int) position.getY() - 4 / 2, 4, 4);
        }
    }

    private void drawAttack(Graphics graphics, Unit unit) {
        Position position = unit.getPosition();
        Order order = unit.getOrder();
        if (order != null && order instanceof AttackOrder && order.isExecuted()) {
            AttackOrder attackOrder = (AttackOrder) order;
            Position attackedUnitPosition = attackOrder.getUnitToAttack().getPosition();
            graphics.drawLine((int) position.getX() - 2, (int) position.getY() - 2,
                    (int) attackedUnitPosition.getX() - 2, (int) attackedUnitPosition.getY() - 2);
        }
    }

    private void drawUnitHP(Graphics graphics, Unit unit) {
        Position position = unit.getPosition();
        UnitType unitType = unit.getUnitType();
        double currentDurability = unit.getHitPoints() + unit.getShields();
        int maxDurability = unitType.getMaxHitPoints() + unitType.getMaxShields();
        graphics.drawRect((int) position.getX() - 15, (int) position.getY() - 15, (int) (30 * currentDurability / maxDurability), 1);
    }

    public void setSimulationState(SimulationState state) {
        this.simulationState = state;
    }

}
