package util;

import java.util.Random;

public class RandomUtils extends org.apache.commons.lang3.RandomUtils {

    private static final Random RANDOM = new Random();

    public static int nextRealInt(int start, int end) {
        return nextInt(0, end - start) + start;
    }

    public static double nextRealDouble(double start, double end) {
        return nextDouble(0, end - start) + start;
    }

    public static double nextGaussian(double std, double mean) {
        return RANDOM.nextGaussian() * std + mean;
    }
}
