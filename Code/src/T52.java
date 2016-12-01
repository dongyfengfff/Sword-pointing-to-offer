/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:重建乘机数组
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1]
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
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
}
