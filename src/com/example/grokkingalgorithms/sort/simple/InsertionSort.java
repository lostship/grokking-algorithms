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
 * 
 * 但是前边的部分已经是有序的，每轮都是将数据插入到前边有序的序列中，
 * 也就是前边的顺序对后续每轮比较都有帮助，
 * 不需要像冒泡和选择排序那样每轮都比较满n-i-1次，
 * 所以平均比较次数优于冒泡和选择排序。
 */
public class InsertionSort {

    public static void sort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int t = data[i];
            int j = i;
            while (j >= 1 && t < data[j - 1]) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = t;
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
