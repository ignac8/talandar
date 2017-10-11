package simulation.order;

import simulation.Position;
import simulation.SimulationState;
import simulation.Unit;

import static java.lang.Double.isFinite;

public class MoveOrder extends Order {
    private Position moveOrderPosition;

    public MoveOrder(Unit unitToOrder, Position moveOrderPosition) {
        super(unitToOrder);
        this.moveOrderPosition = moveOrderPosition;
    }

    @Override
    public void execute(SimulationState currentSimulationState, SimulationState futureSimulationState) {
        if (unitToOrder.getHitPoints() > 0) {
            Unit futureUnitToOrder = futureSimulationState.getUnits().get(unitToOrder.getUnitId());
            Position currentPosition = unitToOrder.getPosition();
            Position futurePosition = futureUnitToOrder.getPosition();
            double distance = moveOrderPosition.getDistance(currentPosition);
            double speed = unitToOrder.getUnitType().getTopSpeed() * currentSimulationState.getTimeStep();
            if (distance > 0 && isFinite(distance)) {
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
                if (futurePosition.getX() > currentSimulationState.getMaxX()) {
                    futurePosition.setX(currentSimulationState.getMaxX());
                }
                if (futurePosition.getY() < 0) {
                    futurePosition.setY(0);
                }
                if (futurePosition.getY() > currentSimulationState.getMaxY()) {
                    futurePosition.setY(currentSimulationState.getMaxY());
                }
                executed = true;
            }
        }
    }
}
