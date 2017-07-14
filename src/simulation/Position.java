package simulation;

import static java.lang.Math.sqrt;
import static utils.MathUtils.fastSquare;

public class Position {
    private double x;
    private double y;

    public double getDistance(Position position) {
        return sqrt(fastSquare(x - position.x) + fastSquare(y - position.y));
    }

}
