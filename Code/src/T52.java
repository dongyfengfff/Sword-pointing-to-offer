/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:重建乘积数组
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1]
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 *
 * 本来最好的方案是求出A中所有数字的乘积,然后在B中除去对应的数字,现在既然不让用除法,那么只能看B的结构,算两次了;
 */
public class T52 {
    public int[] multiply(int[] A) {
        int[] a1 = new int[A.length];
        int[] a2 = new int[A.length];
        a1[0] = 1;
        a2[A.length - 1] = 1;
        for (int i = 1; i < A.length; i++) {
            a1[i] = a1[i - 1] * A[i - 1];
        }
        for (int i = A.length - 2; i >= 0; i--) {
            a2[i] = a2[i+1]*A[i+1];
        }

        for (int i = 0; i < A.length; i++) {
            a1[i] *= a2[i];
        }
        return a1;
    }

    //比方法1少使用了一次循环;
    public int[] multiply2(int[] A) {
        if (A == null || A.length == 0) {
            return new int[0];
        }

        int len = A.length;

        if (len == 1) {
            return A;
        }

        int[] B = new int[len];

        int sum = 1;
        B[0] = 1;
        for (int i = 1; i<len; i++) {
            sum = sum*A[i-1];
            B[i] = sum;
        }

        sum = 1;
        for (int i = len-2; i>=0; i--) {
            sum = sum * A[i+1];
            B[i] = B[i] * sum;
        }


        return B;
    }
}
