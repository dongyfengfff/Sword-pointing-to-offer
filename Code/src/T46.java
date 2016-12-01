/**
 * Author: zhangxin
 * Time: 2016/11/25 0025.
 * Desc:求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
 */
public class T46 {

    //不能用 for ,那么就用递归,但是递归如何判断最后的终止情况??
    public int Sum_Solution(int n) {
        int res = n;
        boolean flag = (n>0)&&((res+=Sum_Solution(n-1))>0);
        return res;
    }

    // n+n^2  /2,平方用math类求解, /2 用 >>1 表示;
    public int Sum_Solution1(int n) {
        int sum = (int) (Math.pow(n, 2) + n);
        return sum >> 1;
    }
}
