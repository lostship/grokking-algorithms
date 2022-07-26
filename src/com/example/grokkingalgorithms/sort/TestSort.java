package com.example.grokkingalgorithms.sort;

import java.util.function.Consumer;
import java.util.stream.IntStream;

import com.example.grokkingalgorithms.shuffle.Shuffle;
import com.example.grokkingalgorithms.sort.simple.BubbleSort;
import com.example.grokkingalgorithms.sort.simple.InsertionSort;
import com.example.grokkingalgorithms.sort.simple.SelectionSort;
import com.example.grokkingalgorithms.util.Arrays;
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
    }

    private static void testSort(Consumer<int[]> sortFunc, int[] arr) {
        int[] copy = Arrays.copy(arr);
        Tests.time(() -> sortFunc.accept(copy));

        if (!Arrays.isSorted(copy)) {
            Arrays.print(copy);
            throw new AssertionError();
        }
    }

}
