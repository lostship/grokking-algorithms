package com.example.grokkingalgorithms.util;

public class Tests {
    public static void time(Runnable run) {
        long start = System.currentTimeMillis();
        run.run();
        long end = System.currentTimeMillis();
        System.out.printf("run time: %dms%n", end - start);
    }
}
