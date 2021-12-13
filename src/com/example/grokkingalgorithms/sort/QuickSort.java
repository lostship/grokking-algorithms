package com.example.grokkingalgorithms.sort;

import java.util.stream.IntStream;

import com.example.grokkingalgorithms.shuffle.Shuffle;
import com.example.grokkingalgorithms.util.Arrays;
import com.example.grokkingalgorithms.util.Tests;

/**
 * 使用分治的基本算法思想（divide and conquer, D&C），时间复杂度O(n * log<sup>n</sup>)
 */
public class QuickSort {
    public static void sort(final int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(final int[] arr, final int start, final int end) {
        if (start >= end) {
            return;
        }

        final int k = arr[end];
        int i = start;
        int j = end - 1;

        while (i < j) {
            while (i < j && arr[i] <= k) {
                i++;
            }
            while (i < j && arr[j] >= k) {
                j--;
            }
            if (i != j) {
                Arrays.swap(arr, i, j);
            }
        }

        if (arr[i] > k) {
            Arrays.swap(arr, i, end);
        } else if (arr[i] < k) {
            i = end;
        }

        sort(arr, start, i - 1);
        sort(arr, i + 1, end);
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
