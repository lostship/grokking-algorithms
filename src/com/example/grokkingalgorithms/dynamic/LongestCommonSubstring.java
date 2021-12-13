package com.example.grokkingalgorithms.dynamic;

import com.example.grokkingalgorithms.util.Matrices;

public class LongestCommonSubstring {
    public static void main(String[] args) {
        String s1 = "fish";
        String s2 = "hish";
        String substring = search(s1, s2);
        System.out.printf("longest common substring: \"%s\" (length: %d)%n", substring, substring.length());
    }

    public static String search(String s1, String s2) {
        int maxLen = 0;
        int resIndexInS1 = -1;
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
                }
                t[i][j] = l;

                if (maxLen < l) {
                    maxLen = l;
                    resIndexInS1 = i;
                }
            }
        }

        // 从矩阵中每个点p1向右下方延伸直到p2，表示分别以p1横纵坐标的字符为起始字符，以p2横纵坐标的字符为结束字符，截取的两个子串
        // 如果这两个子串完全相同，则是一个公共子串
        // 因此一个公共子串一定以p1不为0的点开始，到p2不为0的点结束，而p2的值就是这个公共子串的长度
        // 也因此矩阵中的最大值就是最大子串长度
        // 从最大值点坐标p2向左上方查找，一直到边界或者值为1的点p1，就是最长子串
        Matrices.print(t, s1.toCharArray(), s2.toCharArray());

        String res = null;
        if (maxLen > 0) {
            res = s1.substring(resIndexInS1 - maxLen + 1, resIndexInS1 + 1);
        }
        return res;
    }
}
