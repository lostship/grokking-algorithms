package com.example.grokkingalgorithms.backtracking;

import com.example.grokkingalgorithms.util.Arrays;

/**
 * 回溯法在包含问题的所有解的解空间树中，按照深度优先策略，从根节点出发搜索解空间树。
 * 回溯法在用来求问题的所有解时要回溯到根，且根节点的所有子树都已被搜索遍才结束；
 * 而用来求问题的任意解时，只要搜索到问题的一个解就可以结束。
 * 适用于解一些组合数较大的问题。
 */
public class BackTrackingKnapsack {

    public static void main(String[] args) {
        int[] weights = { 1, 11, 21, 23, 33, 43, 45, 55 };
        int[] values = { 11, 21, 31, 33, 43, 53, 55, 65 };
        int W = 110;

        sort(weights, values);
        Arrays.print(weights);
        Arrays.print(values);

        int[] result = backTrackingKnapsack(weights, values, W);

        System.out.println("最佳选择：");
        Arrays.print(result);
    }

    public static int[] backTrackingKnapsack(int[] weights, int[] values, int W) {
        int n = weights.length;
        int i = 0;
        int currentWeight = 0;
        int currentValue = 0;
        int[] temp = new int[n];
        int[] result = new int[n];
        int maxValue = 0;

        while (true) {
            if (i < n) {
                if (currentWeight + weights[i] <= W) {
                    currentWeight += weights[i];
                    currentValue += values[i];
                    temp[i] = 1;
                } else {
                    temp[i] = 0;
                }
            }

            if (i == n - 1) {
                if (currentValue > maxValue) {
                    maxValue = currentValue;
                    System.arraycopy(temp, 0, result, 0, n);
                    Arrays.print(result);
                    System.out.println(maxValue);
                }

                // 此处回溯操作已整合到限界函数判断后的回溯操作中
                // while (--i >= 0) {
                // if (Y[i] == 1) {
                // Y[i] = 0;
                // currentWeight -= weights[i];
                // currentValue -= values[i];
                // break;
                // }
                // }
                //
                // if (i < 0) {
                // return;
                // }
            }

            while (bound(weights, values, W, n, i, currentWeight, currentValue) <= maxValue) {
                while (i >= 0) {
                    if (temp[i] == 1) {
                        temp[i] = 0;
                        currentWeight -= weights[i];
                        currentValue -= values[i];
                        break;
                    }
                    i--;
                }

                if (i < 0) {
                    return result;
                }
            }

            i++;
        }
    }

    /**
     * 回溯法的限界函数
     * 
     * 问题的解空间往往很大，设计限界函数，在搜索过程中对某些节点进行剪枝，
     * 尽可能多地避免搜索不可能产生最优解的活结点，减少搜索空间，提高算法效率。
     */
    private static double bound(int[] weights, int[] values, int W, int n, int currentIndex, int currentWeight,
            int currentValue) {
        double bound = currentValue;
        for (int i = currentIndex + 1; i < n; i++) {
            if (currentWeight + weights[i] <= W) {
                currentWeight += weights[i];
                bound += values[i];
            } else {
                bound += (W - currentWeight) * (1.0 * values[i] / weights[i]);
                break;
            }
        }

        return bound;
    }

    /**
     * 在限界函数中将0-1背包问题松弛为背包问题，以计算当前活结点所有解空间能够得到的最大价值，
     * 如果该最大价值不超过当前以得到的最优解的价值，则可以对该活结点进行剪枝。
     * 为此需要将物品按照单位重量价值由高到低排序，供本例中限界函数使用的贪心策略使用。
     */
    private static void sort(int[] weights, int[] values) {
        int n = weights.length;
        double[] vws = new double[n];
        for (int i = 0; i < n; i++) {
            vws[i] = 1.0 * values[i] / weights[i];
        }

        for (int i = 1; i < n; i++) {
            int j = i;
            double vw = vws[j];
            int w = weights[j];
            int v = values[j];
            while (j >= 1 && (vw > vws[j - 1] || vw == vws[j - 1] && w < weights[j - 1])) {
                vws[j] = vws[j - 1];
                weights[j] = weights[j - 1];
                values[j] = values[j - 1];
                j--;
            }
            vws[j] = vw;
            weights[j] = w;
            values[j] = v;
        }
    }

}
