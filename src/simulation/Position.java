package simulation;

import static java.lang.Math.pow;

public class Position {
    private double x;
    private double y;

    public double getDistance(Position position) {
        return pow(x - position.x, 2) + pow(y - position.y, 2);
    }

}
