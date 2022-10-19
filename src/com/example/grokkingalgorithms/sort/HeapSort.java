package com.example.grokkingalgorithms.sort;

import java.util.stream.IntStream;

import com.example.grokkingalgorithms.shuffle.Shuffle;
import com.example.grokkingalgorithms.util.ArrayUtils;
import com.example.grokkingalgorithms.util.MathUtils;
import com.example.grokkingalgorithms.util.Tests;

/**
 * 平均时间复杂度O(n * log<sup>n</sup>)
 * 最坏时间复杂度O(n * log<sup>n</sup>)
 * 最好时间复杂度O(n * log<sup>n</sup>)
 * 辅助空间O(1)
 * 不稳定
 */
public class HeapSort {

    public static void sort(int[] data) {
        int n = data.length;
        if (n <= 1) {
            return;
        }

        // 建立初始最大堆
        // 从1开始编号的完全二叉树，编号i的结点的子结点是2i和2i+1，所以当i > n/2时没有子节点
        // 编程时因为从0开始，所以编号i的结点的子结点是2i+1和2i+2，所以当2i+1 > n-1，即i > n/2-1时没有子节点
        // 因此从i = n/2-1开始建立初始最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapAdjust(data, i, n - 1);
        }

        // 不断输出根节点最大值，并调整新堆
        for (int i = n - 1; i >= 0; i--) {
            ArrayUtils.swap(data, i, 0);
            heapAdjust(data, 0, i - 1);
        }
    }

    private static void heapAdjust(int[] data, int s, int e) {
        for (int i = 2 * s + 1; i <= e; i = 2 * s + 1) {
            if (i < e && data[i + 1] > data[i]) {
                i++;
            }

            if (data[s] >= data[i]) {
                return;
            }

            ArrayUtils.swap(data, s, i);
            s = i;
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
