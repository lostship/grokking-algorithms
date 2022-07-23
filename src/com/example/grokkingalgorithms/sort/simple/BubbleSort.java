package com.example.grokkingalgorithms.sort.simple;

import java.util.stream.IntStream;

import com.example.grokkingalgorithms.shuffle.Shuffle;
import com.example.grokkingalgorithms.util.Arrays;
import com.example.grokkingalgorithms.util.Tests;

/**
 * 时间复杂度O(n<sup>2</sup>)
 */
public class BubbleSort {

    public static void sort(int[] arr) {
        boolean sorted = false;

        for (int i = 0; i < arr.length - 1 && !sorted; i++) {
            sorted = true;

            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    Arrays.swap(arr, j, j + 1);

                    if (sorted) {
                        sorted = false;
                    }
                }
            }
        }
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
