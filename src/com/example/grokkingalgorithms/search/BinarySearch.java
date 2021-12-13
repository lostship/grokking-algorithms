package com.example.grokkingalgorithms.search;

import java.util.stream.IntStream;

public class BinarySearch {
    /**
     * 对于一个长度为n的数组，二分查找最多需要floor(log<sup>n</sup>) + 1步，算法时间复杂度为O(log<sup>n</sup>)
     */
    public static int binarySearch(int[] arr, int item) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int i = (low + high) / 2;
            if (item == arr[i]) {
                return i;
            }
            if (item < arr[i]) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }

        return -1;
    }

    public static <T extends Comparable<? super T>> int binarySearch(T[] arr, T item) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int i = (low + high) / 2;
            int d = item.compareTo(arr[i]);

            if (d == 0) {
                return i;
            }
            if (d < 0) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = IntStream.rangeClosed(1, 1024).toArray();
        System.out.println(binarySearch(arr, 0));
        System.out.println(binarySearch(arr, 10));
    }
}
