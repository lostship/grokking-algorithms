package com.example.grokkingalgorithms.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Combinations {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int[] rule = { 2, 2, 2, 2 };
        Set<String> results = group(arr, rule);

        for (String r : results) {
            System.out.println(r);
        }
        System.out.println(results.size());
    }

    public static List<int[]> cnm(int[] arr, int m) {
        List<int[]> resultList = new ArrayList<>();
        cnm(arr, arr.length, m, 0, 0, new int[m], resultList);
        return resultList;
    }

    private static void cnm(int[] arr, int n, int m, int startIndex, int deep, int[] temp, List<int[]> resultList) {
        if (deep == m) {
            resultList.add(Arrays.copyOf(temp, m));
            return;
        }

        for (int i = startIndex; i <= n - m + deep; i++) {
            temp[deep] = arr[i];
            cnm(arr, n, m, i + 1, deep + 1, temp, resultList);
        }
    }

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

        for (int[] g : cnm(arr, rule[deep])) {
            temp[deep] = g;
            group(diff(arr, g), rule, deep + 1, temp, results);
        }
    }

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
