package com.example.grokkingalgorithms.bitmap;

import java.util.BitSet;

public class BloomFilter {

    // 为了减少冲突和误判，使用8个随机映射函数得到8个随机数，映射到bitmap的8个位置上。
    // 本例使用加法hash算法，使用8个素数作为seed。
    private static final int[] SEEDS = { 3, 5, 7, 11, 13, 31, 37, 61 };
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private final BitSet filter;
    private final int capacity;

    public BloomFilter(int capacity) {
        this.capacity = tableSizeFor(capacity);
        this.filter = new BitSet(this.capacity);
    }

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int capacity) {
        int n = -1 >>> Integer.numberOfLeadingZeros(capacity - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public void add(String value) {
        if (value == null) {
            return;
        }
        for (int s : SEEDS) {
            filter.set(hashCode(value, s));
        }
    }

    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        for (int s : SEEDS) {
            if (!filter.get(hashCode(value, s))) {
                return false;
            }
        }
        return true;
    }

    private int hashCode(String value, int seed) {
        int h = 0;
        int length = value.length();
        for (int i = 0; i < length; i++) {
            h = seed * h + value.charAt(i);
        }
        if (h > MAXIMUM_CAPACITY) {
            h = h % capacity;
        }
        return h;
    }

    public static void main(String[] args) {
        BloomFilter filter = new BloomFilter(1000);
        filter.add("hello");
        System.out.println(filter.contains("hello"));
        System.out.println(filter.contains("world"));
    }

}
