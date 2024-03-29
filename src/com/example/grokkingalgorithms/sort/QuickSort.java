package com.example.grokkingalgorithms.sort;

import java.util.stream.IntStream;

import com.example.grokkingalgorithms.shuffle.Shuffle;
import com.example.grokkingalgorithms.util.ArrayUtils;
import com.example.grokkingalgorithms.util.MathUtils;
import com.example.grokkingalgorithms.util.Tests;

/**
 * 平均时间复杂度O(n * log<sup>n</sup>)
 * 最坏时间复杂度O(n<sup>2</sup>)
 * 最好时间复杂度O(n * log<sup>n</sup>)
 * 辅助空间O(log<sup>n</sup>)
 * 不稳定
 * 
 * 使用分治的基本算法思想（divide and conquer, D&C）
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
                ArrayUtils.swap(arr, i, j);
            }
        }

        if (arr[i] > k) {
            ArrayUtils.swap(arr, i, end);
        } else if (arr[i] < k) {
            i = end;
        }

        sort(arr, start, i - 1);
        sort(arr, i + 1, end);
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
