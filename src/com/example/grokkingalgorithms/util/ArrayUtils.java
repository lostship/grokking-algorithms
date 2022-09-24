package com.example.grokkingalgorithms.util;

import java.util.Arrays;

public class ArrayUtils {

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void print(byte[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

}
