package com.example.grokkingalgorithms.backtracking;

import java.util.Arrays;

import com.example.grokkingalgorithms.util.ArrayUtils;

public class NQueen {

    public static void main(String[] args) {
        int n = 4;
        nQueen(n);
        nQueen2(n);
    }

    public static void nQueen(int n) {
        nQueen(n, 0, new int[n]);
    }

    public static void nQueen(int n, int i, int[] columns) {
        if (i == n) {
            ArrayUtils.print(columns);
            return;
        }

        while (columns[i] < n) {
            if (place(columns, i)) {
                nQueen(n, i + 1, columns);
            }
            columns[i]++;
        }

        columns[i] = 0;
    }

    public static void nQueen2(int n) {
        int[] columns = new int[n];
        Arrays.fill(columns, -1);
        int i = 0;

        while (true) {
            columns[i]++;

            if (columns[i] == n) {
                if (i == 0) {
                    return;
                }

                columns[i] = -1;
                i--;
                continue;
            }

            if (place(columns, i)) {
                i++;
            }

            if (i == n) {
                ArrayUtils.print(columns);
                i--;
            }
        }
    }

    /**
     * NQueen问题的限界函数可以根据问题描述直接定义，即任意两个皇后不在相同直线或斜线上
     */
    private static boolean place(int[] columns, int index) {
        for (int i = 0; i < index; i++) {
            if (columns[i] == columns[index]
                    || Math.abs(columns[index] - columns[i]) == Math.abs(index - i)) {
                return false;
            }
        }
        return true;
    }

}
