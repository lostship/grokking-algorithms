package com.example.grokkingalgorithms.dynamic;

public class MaxSubSum {

    public static void main(String[] args) {
        int[] data = { -2, 11, -4, 13, -5, -2 };
        int maxSubSum = maxSubSum(data);
        System.out.println(maxSubSum);
    }

    public static int maxSubSum(int[] data) {
        return maxSubSum(data, 0, data.length - 1, false);
    }

    private static int maxSubSum(int[] data, int sum, int k, boolean hasSelected) {
        if (k < 0) {
            return sum;
        }

        int selectThis = maxSubSum(data, sum + data[k], k - 1, true);

        int notSelectThis = hasSelected ? sum : maxSubSum(data, sum, k - 1, false);

        return Math.max(selectThis, notSelectThis);
    }

}
