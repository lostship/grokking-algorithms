package com.example.grokkingalgorithms.string;

import java.util.Arrays;

public class KMP {

    public static void main(String[] args) {
        String s = "abacbcabababbcbc";
        String p = "abababb";
        int index = indexKMP(s, p);

        System.out.println("index: " + index);
        System.out.println(s.indexOf(p) == index);
    }

    public static int indexKMP(String source, String pattern) {
        char[] s = source.toCharArray();
        char[] p = pattern.toCharArray();

        int[] next = getNext(p);
        System.out.println("next[]: " + Arrays.toString(next));

        int i = 0;
        int j = -1;
        int slen = s.length;
        int plen = p.length;

        while (i < slen && j < plen) {
            if (j == -1) {
                i++;
                j = 0;
            } else if (p[j] == s[i]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j == plen) {
            return i - j;
        }

        return -1;
    }

    /**
     * 设主串s长度n，模式串p长度m，当模式串中的p[j]与主串中的s[i]不等时，
     * 因其前边的j个字符"p[0]...p[j-1]"已经获得了匹配，
     * 所以若存在0<k<j，使模式串中的"p[0]...p[k-1]" == "p[j-k]...p[j-1]"，
     * 这时就可以直接用si与pk相比较，从而使i无需回退。
     * 
     * 依据模式串的next函数值实现字串的滑动。若令next[j]=k，则next[j]表示当模式串中的p[j]与主串中相应字符不等时，
     * 令模式串的p[k]与主串的相应字符进行比较。
     * next函数的定义如下：
     * 
     * <code>
     *           -1，当j=0时
     * next[j] = max{k | 0<k<j 且 "p[0]...p[k-1]" == "p[j-k]...p[j-1]"}
     *           0，其他情况
     * </code>
     */
    private static int[] getNext(char[] p) {
        int plen = p.length;
        int[] next = new int[plen];
        next[0] = -1;
        int i = 0;
        int j = -1;

        while (i < plen - 1) {
            if (j == -1) {
                i++;
                j = 0;
                next[i] = 0;
            } else if (p[i] == p[j]) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }

        return next;
    }

}
