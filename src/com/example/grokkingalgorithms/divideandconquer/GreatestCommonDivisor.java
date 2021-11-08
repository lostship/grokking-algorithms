package com.example.grokkingalgorithms.divideandconquer;

/**
 * ����˼�루D&C�������������Լ��
 * 
 * ����ԭ��
 * 1���ҳ������ܼ򵥵Ļ���������base case����
 * 2��ȷ�������С����Ĺ�ģ�����Ͻ�����ֽ⣩��ֱ�����ϻ���������
 */
public class GreatestCommonDivisor {
    /**
     * �����������������Լ��
     * 
     * <p>
     * ����1����������a��b (a>b)�����Լ��Ϊn����a-bһ���ܱ�n���� ((a-b)/n = a/n - b/n = 0)
     * 
     * <p>
     * ����2����������a��b�����Լ��Ϊn����a+bһ���ܱ�n���� ((a+b)/n = a/n + b/n = 0)
     * 
     * <p>
     * ����3����������a��b (a>b)�����Լ��Ϊn����a+b��b�����Լ��һ��Ϊn
     * ֤��
     * ��a+b��b�����Լ��Ϊn'
     * ����n'>n��������1��֪��(a+b)-b=aһ���ܱ�n'����������a��b���ܱ�n'��������a��b�����Լ��Ϊnì�ܣ�����n'һ��������n
     * ������2��֪��a+bһ���ܱ�n����������n'=n
     * 
     * <p>
     * ����չ������4����������a��b (a>b)�����Լ��Ϊn����a-b��b�����Լ��һ��Ϊn
     * ֤��
     * ��a-b��b�����Լ��Ϊn'
     * ����n'>n��������2��֪��(a-b)+b=aһ���ܱ�n'����������a��b���ܱ�n'��������a��b�����Լ��Ϊnì�ܣ�����n'һ��������n
     * ������1��֪��a-bһ���ܱ�n����������n'=n
     * 
     * <p>
     * ������3��֪��������������������Լ��n = f(a,b) (a>b)������ʹ�÷���˼�룬�ֽ�Ϊ����С��ģ�����⣺f(a-b,b)
     * ��������Ϊ��������������ֵ��ȣ�Ϊ�������㲽��Ҳ�����Ż�Ϊһ���������Ա���һ����������
     * 
     * <p>
     * ת���ɼ���������ǣ���һ����a����b�ľ����У����ȷָ�ɾ����ܴ�������Σ��������α߳�n��(a,b,n �� N+)
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
