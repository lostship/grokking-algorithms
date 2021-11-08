package com.example.grokkingalgorithms.recursive;

public class TestRecursive {
    /**
     * 递归计算阶乘
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * 尾递归、尾递归优化
     * <p>
     * 当递归调用是整个函数体中最后执行的语句，且它的返回值不属于表达式的一部分时，这个递归调用就是尾递归。
     * <p>
     * 正常情况下，递归调用次数越多，调用栈就会占用越多的内存空间。
     * <p>
     * 尾递归函数的特点是在回归过程中不用做任何操作。当编译器检测到一个函数调用是尾递归的时候，会生成优化的代码：
     * 因为调用返回时栈帧中并没有其他事情可做，因此也就没有必要保存栈帧了，
     * 可以覆盖当前的栈帧，而不是在其上压入一个新创建的栈帧，这样所使用的栈空间就大大减少了。
     * 这就是尾递归优化。
     * <p>
     * 并非所有编程语言都支持尾递归优化，java目前还不支持尾递归优化。
     */
    public static int tailRecursive(int n, int res) {
        if (n == 1) {
            return res;
        }
        return tailRecursive(n - 1, n * res);
    }
}
