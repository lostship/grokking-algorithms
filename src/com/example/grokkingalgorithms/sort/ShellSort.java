package com.example.grokkingalgorithms.sort;

import java.util.stream.IntStream;

import com.example.grokkingalgorithms.shuffle.Shuffle;
import com.example.grokkingalgorithms.util.Arrays;
import com.example.grokkingalgorithms.util.Tests;

/**
 * 时间复杂度O(n * log<sup>n</sup>)
 */
public class ShellSort {

    public static void sort(int[] data) {
        sort(data, data.length / 2);
    }

    private static void sort(int[] data, int d) {
        if (d < 1) {
            return;
        }

        for (int i = d; i < data.length; i++) {
            int t = data[i];
            int j = i;
            while (j >= d && t < data[j - d]) {
                data[j] = data[j - d];
                j -= d;
            }
            data[j] = t;
        }

        sort(data, d / 2);
    }

    public static void main(String[] args) {
        int[] arr = IntStream.rangeClosed(1, 100).toArray();

        Tests.time(() -> {
            for (int i = 0; i < 100000; i++) {
                Shuffle.knuthDurstenfeldShuffle(arr);
                sort(arr);
                if (!Arrays.isSorted(arr)) {
                    Arrays.print(arr);
                    throw new AssertionError();
                }
            }
        });
    }

}
