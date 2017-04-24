package utils;

public class RandomUtils extends org.apache.commons.lang3.RandomUtils {

    public static int nextRealInt(int start, int end) {
        return nextInt(0, end - start) + start;
    }

    public static double nextRealDouble(double start, double end) {
        return nextDouble(0, end - start) + start;
    }
}
