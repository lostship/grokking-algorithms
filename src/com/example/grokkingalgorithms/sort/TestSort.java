package com.example.grokkingalgorithms.sort;

import java.util.function.Consumer;
import java.util.stream.IntStream;

import com.example.grokkingalgorithms.shuffle.Shuffle;
import com.example.grokkingalgorithms.sort.simple.BubbleSort;
import com.example.grokkingalgorithms.sort.simple.InsertionSort;
import com.example.grokkingalgorithms.sort.simple.SelectionSort;
import com.example.grokkingalgorithms.util.ArrayUtils;
import com.example.grokkingalgorithms.util.Tests;

public class TestSort {

    public static void main(String[] args) {
        int[] arr = IntStream.rangeClosed(1, 10000).toArray();
        Shuffle.knuthDurstenfeldShuffle(arr);

        testSort(BubbleSort::sort, arr);
        testSort(SelectionSort::sort, arr);
        testSort(InsertionSort::sort, arr);
        testSort(ShellSort::sort, arr);
        testSort(MergeSort::sort, arr);
        testSort(QuickSort::sort, arr);
        testSort(HeapSort::sort, arr);
    }

    private static void testSort(Consumer<int[]> sortFunc, int[] arr) {
        int[] copy = arr.clone();
        Tests.time(() -> sortFunc.accept(copy));

        if (!ArrayUtils.isSorted(copy)) {
            ArrayUtils.print(copy);
            throw new AssertionError();
        }
    }

}
