package com.example.grokkingalgorithms.divideandconquer;

/**
 * 分治思想（D&C）求正整数最大公约数
 * 
 * 工作原理：
 * 1、找出尽可能简单的基线条件（base case）；
 * 2、确定如何缩小问题的规模（不断将问题分解），直到符合基线条件。
 */
public class GreatestCommonDivisor {
    /**
     * 求两个正整数的最大公约数
     * 
     * <p>
     * 命题1：设正整数a，b (a>b)的最大公约数为n，则a-b一定能被n整除 ((a-b)/n = a/n - b/n = 0)
     * 
     * <p>
     * 命题2：设正整数a，b的最大公约数为n，则a+b一定能被n整除 ((a+b)/n = a/n + b/n = 0)
     * 
     * <p>
     * 命题3：设正整数a，b (a>b)的最大公约数为n，则a+b与b的最大公约数一定为n
     * 证：
     * 设a+b与b的最大公约数为n'
     * 假设n'>n，由命题1可知，(a+b)-b=a一定能被n'整除，所以a，b都能被n'整除，与a，b的最大公约数为n矛盾，所以n'一定不大于n
     * 由命题2可知，a+b一定能被n整除，所以n'=n
     * 
     * <p>
     * （扩展）命题4：设正整数a，b (a>b)的最大公约数为n，则a-b与b的最大公约数一定为n
     * 证：
     * 设a-b与b的最大公约数为n'
     * 假设n'>n，由命题2可知，(a-b)+b=a一定能被n'整除，所以a，b都能被n'整除，与a，b的最大公约数为n矛盾，所以n'一定不大于n
     * 由命题1可知，a-b一定能被n整除，所以n'=n
     * 
     * <p>
     * 由命题3可知，求解两个正整数的最大公约数n = f(a,b) (a>b)，可以使用分治思想，分解为求解更小规模的问题：f(a-b,b)
     * 基线条件为函数的两个参数值相等，为减少运算步骤也可以优化为一个参数可以被另一个参数整除
     * 
     * <p>
     * 转换成几何问题就是：在一个长a，宽b的矩形中，均匀分割成尽可能大的正方形，求正方形边长n。(a,b,n ∈ N+)
     */
    public static int greatestCommonDivisor(int a, int b) {
        if (a % b == 0) {
            return b;
        } else if (b % a == 0) {
            return a;
        }

        return greatestCommonDivisor(Math.abs(a - b), Math.min(a, b));
    }

    public static void main(String[] args) {
        System.out.println(greatestCommonDivisor(168, 64));
        System.out.println(greatestCommonDivisor(31, 5));
    }
}
