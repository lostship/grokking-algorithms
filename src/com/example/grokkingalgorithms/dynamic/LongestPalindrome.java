package com.example.grokkingalgorithms.dynamic;

import com.example.grokkingalgorithms.util.Matrices;
import com.example.grokkingalgorithms.util.Strings;

public class LongestPalindrome {

    public static void main(String[] args) {
        String s = "gfbaabcfg";
        // solution1(s);
        // solution2(s);
        solution3(s);
    }

    public static String solution1(String s) {
        int len = s.length();
        int i = 0;
        int j = 0;
        String max = "";

        while (i < len) {
            j = len - 1;

            while (j >= i + max.length()) {
                int t = 0;

                boolean isTrue = true;
                while (i + t <= j - t) {
                    if (s.charAt(i + t) != s.charAt(j - t)) {
                        isTrue = false;
                        break;
                    }
                    t++;
                }

                if (isTrue) {
                    if (j - i + 1 > max.length()) {
                        max = s.substring(i, j + 1);
                    }
                }

                j--;
            }

            i++;
        }

        return max;
    }

    public static void solution2(String s) {
        int len = s.length();
        int[][] t = new int[len][len];
        int maxLen = 0;
        int maxIndex = 0;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                char cleft = s.charAt(len - i - 1);
                char ctop = s.charAt(j);
                int l = 0;

                if (cleft == ctop) {
                    if (i == 0 || j == 0) {
                        l = 1;
                    } else {
                        l = t[i - 1][j - 1] + 1;
                    }
                    t[i][j] = l;
                }

                // 矩阵header按照字符串正序排列，左侧按照字符串倒序排列
                // i是横坐标，j是纵坐标，左上角为原点
                // 左下到右上对角线的点坐标满足：i = len - j - 1;
                // 在该对角线左上方的点坐标满足：x < len - j - 1;
                // 在该对角线右下方的点坐标满足：x > len - j - 1;
                // 回文字必定以某一个字符为中心点，或者以某两个字符之间为中心点
                // 左下到右上的对角线就是字符串中相同位置的字符坐标形成的对角线
                // 跨越了左下到右上对角线的，连续的左上到右下的斜线包含的坐标对应字符
                // 就表示以字符串中某个字符，或者某两个字符之间为中心点，组成的回文
                if (l > maxLen
                        && i >= len - j - 1
                        && i - l <= len - (j - l) - 1) {
                    maxLen = l;
                    maxIndex = j;
                }
            }
        }

        Matrices.print(t, Strings.reverse(s).toCharArray(), s.toCharArray());
        String longestPalindrome = s.substring(maxIndex - maxLen + 1, maxIndex + 1);
        System.out.printf("longest palindrome: \"%s\" (length: %d)", longestPalindrome, maxLen);
    }

    public static String solution3(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        String res = s.substring(begin, begin + maxLen);

        Matrices.print(dp, s.toCharArray(), s.toCharArray());
        System.out.println(res);
        return res;
    }

}
