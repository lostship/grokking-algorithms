package com.example.grokkingalgorithms.dynamic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DropEggProblem {
    /**
     * 求解扔鸡蛋问题，最佳策略下，至少需要几步（即最佳策略下的最坏情况）
     * 策略即在持有一定数量鸡蛋时选择从几楼开始扔
     * 最坏情况即该策略下最多需要多少步
     * 最佳策略即所有策略中最坏情况下步骤最少的策略
     */
    public static void main(String[] args) {
        int floors = 100;
        int eggs = 2;
        int atLeastNeedSteps = f(floors, eggs);
        System.out.println("floors: " + floors);
        System.out.println("eggs: " + eggs);
        System.out.println("best drop floor: " + get(floors, eggs).bestDropFloor);
        System.out.println("best drop steps: " + atLeastNeedSteps);
    }

    private static final Map<Integer, Map<Integer, Strategy>> cache = new HashMap<>();

    private static void cache(int floors, int eggs, int bestDropFloor, int bestDropSteps) {
        cache.computeIfAbsent(floors, f -> new HashMap<>())
                .put(eggs, new Strategy(bestDropFloor, bestDropSteps));
    }

    private static Strategy get(int floors, int eggs) {
        return Optional.ofNullable(cache.get(floors))
                .map(map -> map.get(eggs))
                .orElse(null);
    }

    private static class Strategy {
        private final int bestDropFloor;
        private final int bestDropSteps;

        public Strategy(int bestDropFloor, int bestDropSteps) {
            this.bestDropFloor = bestDropFloor;
            this.bestDropSteps = bestDropSteps;
        }
    }

    private static int f(int floors, int eggs) {
        if (floors <= 0 || eggs <= 0) {
            return 0;
        }
        if (eggs == 1) {
            return floors;
        }

        Strategy strategy = get(floors, eggs);
        if (strategy != null) {
            return strategy.bestDropSteps;
        }

        int bestDropFloor = 0;
        int bestDropSteps = floors;
        for (int dropFloor = 1; dropFloor < floors; dropFloor++) {
            int dropSteps = Math.max(f(floors - dropFloor, eggs), f(dropFloor - 1, eggs - 1));
            if (dropSteps < bestDropSteps) {
                bestDropFloor = dropFloor;
                bestDropSteps = dropSteps;
            }
        }

        bestDropSteps += 1;
        cache(floors, eggs, bestDropFloor, bestDropSteps);
        return bestDropSteps;
    }
}
