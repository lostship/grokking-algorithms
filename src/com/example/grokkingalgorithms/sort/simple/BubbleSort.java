package com.example.grokkingalgorithms.sort.simple;

import java.util.stream.IntStream;

import com.example.grokkingalgorithms.shuffle.Shuffle;
import com.example.grokkingalgorithms.util.ArrayUtils;
import com.example.grokkingalgorithms.util.MathUtils;
import com.example.grokkingalgorithms.util.Tests;

/**
 * 平均时间复杂度O(n<sup>2</sup>)
 * 最坏时间复杂度O(n<sup>2</sup>)
 * 最好时间复杂度O(n)
 * 辅助空间O(1)
 * 稳定
 */
public class BubbleSort {

    public static void sort(int[] arr) {
        boolean sorted = false;

        for (int i = 0; i < arr.length - 1 && !sorted; i++) {
            sorted = true;

            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    ArrayUtils.swap(arr, j, j + 1);

                    if (sorted) {
                        sorted = false;
                    }
                }
            }
        }
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
