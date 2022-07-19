package com.example.grokkingalgorithms.codec;

import java.util.Arrays;

public class HammingCode {

    public static void main(String[] args) {
        // 低到高位
        int[] bits = { 1, 0, 0, 1, 0, 1, 1, 0 };
        System.out.println("海明码偶校验（低到高位）: " + Arrays.toString(hammingEven(bits)));
        System.out.println("海明码奇校验（低到高位）: " + Arrays.toString(hammingOdd(bits)));
    }

    public static int[] hammingOdd(int[] bits) {
        return hamming(bits, 1);
    }

    public static int[] hammingEven(int[] bits) {
        return hamming(bits, 0);
    }

    private static int getK(int[] bits) {
        int n = bits.length;
        int k = 0;
        while ((0x01 << k) - 1 < n + k) {
            k++;
        }
        return k;
    }

    private static int calcP(int n1, int n2) {
        return (n1 + n2) & 0x01;
    }

    private static int[] hamming(int[] bits, final int initP) {
        int k = getK(bits);
        System.out.println("k: " + k);

        int n = bits.length;
        int hlen = n + k;
        int[] result = new int[hlen];
        int pi = 1;

        for (int i = 0; i < hlen; i++) {
            int t = i + 1;

            if (t == (0x01 << (pi - 1))) {
                pi++;
                result[i] = initP;
                continue;
            }

            int d = bits[i - pi + 1];
            result[i] = d;

            int dpi = 1;
            while (t > 0) {
                if ((t & 0x01) == 1) {
                    int p = result[dpi - 1];
                    result[dpi - 1] = calcP(p, d);
                }
                dpi <<= 1;
                t >>= 1;
            }
        }

        return result;
    }

}
