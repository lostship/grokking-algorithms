package com.example.grokkingalgorithms.util;

public class Matrices {
    public static void print(int[][] body, char[] leftHead, char[] topHead) {
        for (int i = -1; i < leftHead.length; i++) {
            for (int j = -1; j < topHead.length; j++) {
                if (i >= 0 && j >= 0) {
                    System.out.printf("%2s", body[i][j]);
                } else if (i == -1 && j == -1) {
                    System.out.print(" ");
                } else if (i == -1) {
                    System.out.printf("%2s", topHead[j]);
                } else if (j == -1) {
                    System.out.print(leftHead[i]);
                }
            }
            System.out.println();
        }
    }
}
