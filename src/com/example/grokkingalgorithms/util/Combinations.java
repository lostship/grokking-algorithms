package com.example.grokkingalgorithms.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Combinations {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int[] rule = { 2, 2, 2, 2 };
        Set<String> results = group(arr, rule);

        for (String r : results) {
            System.out.println(r);
        }
        System.out.println(results.size());
    }

    /**
     * 从给定元素数量n的集合中选出m个元素，不能重复选择同一个元素，计算所有组合方式数量
     * 
     * @param n 给定集合元素数量
     * @param m 选出几个元素
     * @return 所有组合方式数量
     */
    public static long count(int n, int m) {
        if (m > n - m) {
            m = n - m;
        }
        BigDecimal n1 = BigDecimal.valueOf(1);
        BigDecimal m1 = BigDecimal.valueOf(1);
        while (m >= 1) {
            n1 = n1.multiply(BigDecimal.valueOf(n--));
            m1 = m1.multiply(BigDecimal.valueOf(m--));
        }
        return n1.divide(m1).longValue();
    }

    /**
     * 从给定元素集合arr中选出m个元素，不能重复选择同一个元素，找出所有组合方式
     * 
     * @param arr 给定元素集合
     * @param m   选出几个元素
     * @return 所有组合方式
     */
    public static List<int[]> list(int[] arr, int m) {
        List<int[]> resultList = new ArrayList<>();
        list(arr, arr.length, m, 0, 0, new int[m], resultList);
        return resultList;
    }

    private static void list(int[] arr, int n, int m, int startIndex, int deep, int[] temp, List<int[]> resultList) {
        if (deep == m) {
            resultList.add(Arrays.copyOf(temp, m));
            return;
        }

        for (int i = startIndex; i <= n - m + deep; i++) {
            temp[deep] = arr[i];
            list(arr, n, m, i + 1, deep + 1, temp, resultList);
        }
    }

    /**
     * 将给定元素集合arr，按照分堆数量规则rule，分成几个子集合，各子集合间无交叉，所有子集合的并集是arr，找出所有分堆方式
     * 
     * @param arr  给定元素集合
     * @param rule 分堆规则
     * @return 所有分堆方式
     */
    public static Set<String> group(int[] arr, int[] rule) {
        Set<String> results = new HashSet<>();
        int[][] temp = new int[rule.length][];
        group(arr, rule, 0, temp, results);
        return results;
    }

    private static void group(int[] arr, int[] rule, int deep, int[][] temp, Set<String> results) {
        if (deep == rule.length) {
            int[][] t = Arrays.copyOf(temp, deep);
            Arrays.sort(t, Comparator.comparingInt(v -> v[0]));
            StringBuilder sb = new StringBuilder();
            for (int[] g : t) {
                sb.append(Arrays.toString(g));
            }
            results.add(sb.toString());
            return;
        }

        for (int[] g : list(arr, rule[deep])) {
            temp[deep] = g;
            group(diff(arr, g), rule, deep + 1, temp, results);
        }
    }

    /**
     * 返回差集arr1-arr2
     */
    public static int[] diff(int[] arr1, int[] arr2) {
        int[] temp = new int[arr1.length];
        int size = 0;
        for (int i : arr1) {
            if (!contains(arr2, i)) {
                temp[size++] = i;
            }
        }

        int[] res = new int[size];
        System.arraycopy(temp, 0, res, 0, size);
        return res;
    }

    /**
     * 返回集合arr中是否包含元素值为i的元素
     */
    public static boolean contains(int[] arr, int i) {
        for (int j : arr) {
            if (i == j) {
                return true;
            }
        }
        return false;
    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

}
