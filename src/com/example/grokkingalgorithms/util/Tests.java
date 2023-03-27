package com.example.grokkingalgorithms.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tests {

    public static void time(Runnable run) {
        long start = System.currentTimeMillis();
        run.run();
        long end = System.currentTimeMillis();
        System.out.printf("run time: %dms%n", end - start);
    }

    public static void test(int[] arr, int m) {
        long counter = 0;
        if (deep == m) {
            counter.increment();
            return;
        }

        for (int i = startIndex; i <= n - m + deep; i++) {
            count(n, m, i + 1, deep + 1, counter);
        }

        int n = arr.length;
        for (int i = 0; i < n - m; i++) {

        }
    }

    public static void main(String[] args) {
        long n1 = Combinations.count(200, 9);
        long n2 = Combinations.count(190, 9);
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(BigDecimal.valueOf(n2).divide(BigDecimal.valueOf(n1), 2, RoundingMode.HALF_UP));

        n1 = Combinations.count(200, 7);
        n2 = Combinations.count(190, 7);
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(BigDecimal.valueOf(n2).divide(BigDecimal.valueOf(n1), 2, RoundingMode.HALF_UP));

    }

}
