package com.example.grokkingalgorithms.recursive;

public class TestRecursive {
    /**
     * �ݹ����׳�
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * β�ݹ顢β�ݹ��Ż�
     * <p>
     * ���ݹ���������������������ִ�е���䣬�����ķ���ֵ�����ڱ��ʽ��һ����ʱ������ݹ���þ���β�ݹ顣
     * <p>
     * ��������£��ݹ���ô���Խ�࣬����ջ�ͻ�ռ��Խ����ڴ�ռ䡣
     * <p>
     * β�ݹ麯�����ص����ڻع�����в������κβ���������������⵽һ������������β�ݹ��ʱ�򣬻������Ż��Ĵ��룺
     * ��Ϊ���÷���ʱջ֡�в�û������������������Ҳ��û�б�Ҫ����ջ֡�ˣ�
     * ���Ը��ǵ�ǰ��ջ֡��������������ѹ��һ���´�����ջ֡��������ʹ�õ�ջ�ռ�ʹ������ˡ�
     * �����β�ݹ��Ż���
     * <p>
     * �������б�����Զ�֧��β�ݹ��Ż���javaĿǰ����֧��β�ݹ��Ż���
     */
    public static int tailRecursive(int n, int res) {
        if (n == 1) {
            return res;
        }
        return tailRecursive(n - 1, n * res);
    }
}
