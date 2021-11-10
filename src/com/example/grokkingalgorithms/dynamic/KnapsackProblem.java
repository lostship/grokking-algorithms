package com.example.grokkingalgorithms.dynamic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 动态规划（Dynamic Programming，DP）
 * 
 * 仅当每个子问题都是独立的，即不依赖于其他子问题时（子问题的worth不会因为是否选择了其他子问题而发生变化），动态规划才管用
 * 
 * 每个子问题都只能要么不拿，要么拿全部，动态规划不能处理对某个子问题拿一部分的情况（比如取一半的water）
 * 而贪心算法能够设计合适的贪心策略处理该情况
 */
public class KnapsackProblem {
    private enum Item {
        WATER("water", 3, 1, 10),
        BOOK("book", 1, 1, 3),
        FOOD("food", 2, 2, 9),
        JACKET("jacket", 2, 2, 5),
        CAMERA("camera", 1, 2, 6);

        private final String name;
        private final int weight;
        private final int volume;
        private final int worth;

        Item(String name, int weight, int volume, int worth) {
            this.name = name;
            this.weight = weight;
            this.volume = volume;
            this.worth = worth;
        }
    }

    private static final Item[] ITEMS = Item.values();
    private static final Map<Integer, Map<Integer, Map<Integer, Result>>> CACHE = new HashMap<>();
    private static final Result EMPTY = new Result(-1, 0, 0, 0, false);

    public static void main(String[] args) {
        final int MAX_WEIGHT = 6;
        final int MAX_VOLUME = 4;

        Result res = choose(ITEMS.length - 1, MAX_WEIGHT, MAX_VOLUME);
        System.out.println("max weight: " + MAX_WEIGHT);
        System.out.println("max volume: " + MAX_VOLUME);
        System.out.println("max worth: " + res.worth);
        System.out.println("choose:");
        while (res != null) {
            if (res.contained) {
                System.out.printf("  %-6s %d %d %2d%n",
                        ITEMS[res.k].name,
                        ITEMS[res.k].weight,
                        ITEMS[res.k].volume,
                        ITEMS[res.k].worth);
            }
            res = getCache(res.nextK(), res.nextW(), res.nextV());
        }
    }

    private static Result getCache(int k, int w, int v) {
        return Optional.ofNullable(CACHE.get(k))
                .map(map -> map.get(w))
                .map(map -> map.get(v))
                .orElse(null);
    }

    private static void putCache(int k, int w, int v, Result result) {
        CACHE.computeIfAbsent(k, key -> new HashMap<Integer, Map<Integer, Result>>())
                .computeIfAbsent(w, key -> new HashMap<Integer, Result>())
                .put(v, result);
    }

    /**
     * @param k 当前物品索引
     * @param w 当前剩余容量
     */
    private static Result choose(int k, int w, int v) {
        // base case
        if (k < 0) {
            return EMPTY;
        }

        Result res = getCache(k, w, v);
        if (res != null) {
            return res;
        }

        int noKWorth = choose(k - 1, w, v).worth;
        if (w < ITEMS[k].weight || v < ITEMS[k].volume) {
            res = new Result(k, w, v, noKWorth, false);
        } else {
            int hasKWorth = ITEMS[k].worth + choose(k - 1, w - ITEMS[k].weight, v - ITEMS[k].volume).worth;
            if (hasKWorth > noKWorth) {
                res = new Result(k, w, v, hasKWorth, true);
            } else {
                res = new Result(k, w, v, noKWorth, false);
            }
        }

        putCache(k, w, v, res);
        return res;
    }

    private static class Result {
        private final int k; // 当前物品索引
        private final int w; // 当最大重量为w时
        private final int v; // 当最大体积为v时
        private final int worth; // 在索引小于等于k的物品中，当最大重量为w、最大体积为v时，可选物品最大总价值
        private final boolean contained; // 要达到worth，是否包含当前物品

        public Result(int k, int w, int v, int worth, boolean contained) {
            this.k = k;
            this.w = w;
            this.v = v;
            this.worth = worth;
            this.contained = contained;
        }

        public Integer nextK() {
            return k - 1;
        }

        public Integer nextW() {
            if (contained) {
                return w - ITEMS[k].weight;
            } else {
                return w;
            }
        }

        public Integer nextV() {
            if (contained) {
                return v - ITEMS[k].volume;
            } else {
                return v;
            }
        }
    }
}
