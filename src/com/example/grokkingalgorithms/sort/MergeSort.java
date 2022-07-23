package com.example.grokkingalgorithms.sort;

import java.util.stream.IntStream;

import com.example.grokkingalgorithms.shuffle.Shuffle;
import com.example.grokkingalgorithms.util.Arrays;
import com.example.grokkingalgorithms.util.Tests;

/**
 * 时间复杂度O(n * log<sup>n</sup>)
 */
public class MergeSort {

    public static int[] sort(int[] data) {
        int[] target = new int[data.length];
        sort(data, target, 0, data.length - 1, 0);
        return target;
    }

    /**
     * @param s data当前查找起始下标
     * @param t data当前查找结束下标
     * @param k target当前查找起始下标
     */
    private static void sort(int[] data, int[] target, int s, int t, int k) {
        if (s == t) {
            target[k] = data[s];
            return;
        }

        int[] temp = new int[t - s + 1];
        int m = (s + t) / 2;
        sort(data, temp, s, m, 0);
        sort(data, temp, m + 1, t, m - s + 1);
        merge(temp, target, s, m, t, k);
    }

    private static void merge(int[] temp, int[] target, int s, int m, int t, int k) {
        int i = s;
        int j = m + 1;

        while (i <= m && j <= t) {
            if (temp[i - s] <= temp[j - s]) {
                target[k++] = temp[(i++) - s];
            } else {
                target[k++] = temp[(j++) - s];
            }
        }

        while (i <= m) {
            target[k++] = temp[(i++) - s];
        }

        while (j <= t) {
            target[k++] = temp[(j++) - s];
        }
    }

    public static void main(String[] args) {
        int[] arr = IntStream.rangeClosed(1, 100).toArray();

        Tests.time(() -> {
            for (int i = 0; i < 100000; i++) {
                Shuffle.knuthDurstenfeldShuffle(arr);
                int[] sorted = sort(arr);
                if (!Arrays.isSorted(sorted)) {
                    Arrays.print(sorted);
                    throw new AssertionError();
                }
            }
        });
    }

}
