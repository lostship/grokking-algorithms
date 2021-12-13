package com.example.grokkingalgorithms.dynamic;

import com.example.grokkingalgorithms.util.Matrices;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String s1 = "fish";
        String s2 = "fosh";
        int length = search(s1, s2);
        System.out.println("longest common subsequence length: " + length);
    }

    public static int search(String s1, String s2) {
        int maxLen = 0;
        int[][] t = new int[s1.length()][s2.length()];

        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                int l = 0;
                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        l = 1;
                    } else {
                        l = t[i - 1][j - 1] + 1;
                    }
                } else if (i - 1 >= 0 && j - 1 >= 0) {
                    l = Math.max(t[i - 1][j], t[j - 1][i]);
                } else if (i - 1 >= 0) {
                    l = t[i - 1][j];
                } else if (j - 1 >= 0) {
                    l = t[i][j - 1];
                } else {
                    l = 0;
                }

                t[i][j] = l;

                if (maxLen < l) {
                    maxLen = l;
                }
            }
        }

        // 从矩阵中每个点p，表示从起始字符开始，分别以p横纵坐标的字符为结束字符，截取的两个子串
        // p的值为这两个子串中，包含的最大公共子序列长度
        // 也因此矩阵中的最大值就是最大子序列长度
        Matrices.print(t, s1.toCharArray(), s2.toCharArray());

        return maxLen;
    }
}
