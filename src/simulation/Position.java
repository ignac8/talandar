package simulation;

import static java.lang.Math.sqrt;
import static utils.MathUtils.fastSquare;

public class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Position position) {
        return sqrt(fastSquare(x - position.x) + fastSquare(y - position.y));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Position copy() {
        return new Position(x, y);
    }
}
