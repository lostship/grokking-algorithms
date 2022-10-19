package com.example.grokkingalgorithms.util;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtils {

    public static int random(int min, int max) {
        return (int) (ThreadLocalRandom.current().nextDouble() * (max - min + 1) + min);
    }

    public static int randomDigits(int digits) {
        return (int) ((ThreadLocalRandom.current().nextDouble() * 9 + 1) * Math.pow(10, digits - 1));
    }

    public static int randomDigits(int minDigits, int maxDigits) {
        return (int) ((ThreadLocalRandom.current().nextDouble() * (Math.pow(10, maxDigits - minDigits + 1) - 1) + 1)
                * Math.pow(10, minDigits - 1));
    }

}
