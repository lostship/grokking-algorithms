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

        // �Ӿ�����ÿ����p1�����·�����ֱ��p2����ʾ�ֱ���p1����������ַ�Ϊ��ʼ�ַ�����p2����������ַ�Ϊ�����ַ�����ȡ�������Ӵ�
        // ����������Ӵ���ȫ��ͬ������һ�������Ӵ�
        // ���һ�������Ӵ�һ����p1��Ϊ0�ĵ㿪ʼ����p2��Ϊ0�ĵ��������p2��ֵ������������Ӵ��ĳ���
        // Ҳ��˾����е����ֵ��������Ӵ�����
        // �����ֵ������p2�����Ϸ����ң�һֱ���߽����ֵΪ1�ĵ�p1��������Ӵ�
        Matrices.print(t, s1.toCharArray(), s2.toCharArray());

        String res = null;
        if (maxLen > 0) {
            res = s1.substring(resIndexInS1 - maxLen + 1, resIndexInS1 + 1);
        }
        return res;
    }
}
