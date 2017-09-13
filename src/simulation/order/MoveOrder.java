package simulation.order;

import simulation.GameState;
import simulation.Position;
import simulation.Unit;

public class MoveOrder extends Order {
    private Position moveOrderPosition;

    public MoveOrder(Unit unitToOrder, Position moveOrderPosition) {
        super(unitToOrder);
        this.moveOrderPosition = moveOrderPosition;
    }

    @Override
    public void execute(GameState currentGameState, GameState futureGameState) {
        if (unitToOrder.getHitPoints() > 0) {
            Unit futureUnitToOrder = futureGameState.getUnits().get(unitToOrder.getUnitId());
            Position currentPosition = unitToOrder.getPosition();
            Position futurePosition = futureUnitToOrder.getPosition();
            double distance = moveOrderPosition.getDistance(currentPosition);
            double speed = unitToOrder.getUnitType().getTopSpeed();
            if (speed < distance) {
                double factor = speed / distance;
                futurePosition.setX(currentPosition.getX() + (moveOrderPosition.getX() - currentPosition.getX()) * factor);
                futurePosition.setY(currentPosition.getY() + (moveOrderPosition.getY() - currentPosition.getY()) * factor);
            } else {
                futurePosition.setX(moveOrderPosition.getX());
                futurePosition.setY(moveOrderPosition.getY());
            }
            if (futurePosition.getX() < 0) {
                futurePosition.setX(0);
            }
            if (futurePosition.getX() > currentGameState.getMaxX()) {
                futurePosition.setX(currentGameState.getMaxX());
            }
            if (futurePosition.getY() < 0) {
                futurePosition.setY(0);
            }
            if (futurePosition.getY() > currentGameState.getMaxY()) {
                futurePosition.setY(currentGameState.getMaxY());
            }
            if (currentPosition.getX() != futurePosition.getY() || currentPosition.getY() != futurePosition.getY()) {
                executed = true;
            }

        }
    }
}
