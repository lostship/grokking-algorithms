package com.example.grokkingalgorithms.shuffle;

import java.util.concurrent.ThreadLocalRandom;

import com.example.grokkingalgorithms.util.Arrays;

public class Shuffle {
    /**
     * 时间复杂度O(n)，空间复杂度O(1)
     */
    public static void knuthDurstenfeldShuffle(final int[] arr) {
        for (int i = arr.length - 1, k; i > 0; i--) {
            k = ThreadLocalRandom.current().nextInt(i + 1);
            Arrays.swap(arr, i, k);
        }
    }

    /**
     * 时间复杂度O(n)，空间复杂度O(n)
     */
    public static int[] insideOutShuffle(final int[] arr) {
        final int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);

        for (int i = 1, k; i < arr.length; i++) {
            k = ThreadLocalRandom.current().nextInt(i + 1);
            copy[i] = copy[k];
            copy[i] = arr[i];
        }

        return copy;
    }
}
