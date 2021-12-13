package com.example.grokkingalgorithms.bitmap;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumber {
    public static void main(String[] args) {
        byte[] bits = getNums(33, 6);
        for (int i = 0; i < bits.length; i++) {
            System.out.printf("%8s: ", Integer.toBinaryString(0xFF & bits[i]));
            for (int j = 0; j < 8; j++) {
                if ((bits[i] & (0x01 << j)) != 0) {
                    System.out.print(i * 8 + j + 1 + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 在[1,m]的整数中，抽取n个数
     */
    private static byte[] getNums(int m, int n) {
        byte[] bits = new byte[m / 8 + (m % 8 == 0 ? 0 : 1)];
        int counter = 0;
        while (counter < n) {
            int i = ThreadLocalRandom.current().nextInt(m);
            if ((bits[i / 8] & (0x01 << (i % 8))) == 0) {
                bits[i / 8] |= (0x01 << (i % 8));
                counter++;
            }
        }
        return bits;
    }
}
