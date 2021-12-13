package com.example.grokkingalgorithms.bitmap;

import java.util.BitSet;

public class FindAddend {
    public static void main(String[] args) {
        testFindAddend(new int[] { -3, -1, 0, 2, 4, 6, 1, 3, 5, 8 }, 5);
    }

    private static void testFindAddend(int[] numbers, int target) {
        BitSet bits = new BitSet();// nonnegative numbers
        BitSet _bits = new BitSet();// negative numbers

        for (int n : numbers) {
            if (n <= target && bits.get(0x01 << (target - n))) {
                System.out.println((target - n) + " + " + n);
            } else if (n > target && _bits.get(0x01 << (n - target))) {
                System.out.println((target - n) + " + " + n);
            }
            if (n >= 0) {
                bits.set(0x01 << n);
            } else {
                _bits.set(0x01 << -n);
            }
        }
    }
}
