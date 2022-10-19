package com.example.grokkingalgorithms.sort;

import java.util.stream.IntStream;

import com.example.grokkingalgorithms.shuffle.Shuffle;
import com.example.grokkingalgorithms.util.ArrayUtils;
import com.example.grokkingalgorithms.util.MathUtils;
import com.example.grokkingalgorithms.util.Tests;

/**
 * 平均时间复杂度O(n<sup>1.3</sup>)
 * 辅助空间O(1)
 * 不稳定
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
        Tests.time(() -> {
            for (int i = 0; i < 100000; i++) {
                int[] arr = IntStream.rangeClosed(1, MathUtils.random(1, 100)).toArray();
                Shuffle.knuthDurstenfeldShuffle(arr);
                sort(arr);
                if (!ArrayUtils.isSorted(arr)) {
                    ArrayUtils.print(arr);
                    throw new AssertionError();
                }
            }
        });
    }

}
