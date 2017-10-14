package LintCode.dp;

import java.rmi.MarshalException;

/**
 * Author: zhangxin
 * Time: 2017/9/9 0009.
 * Desc:
 * 假设你是一个专业的窃贼，准备沿着一条街打劫房屋。
 * 每个房子都存放着特定金额的钱。你面临的唯一约束条件是：
 * 相邻的房子装着相互联系的防盗系统，且 当相邻的两个房子同一天被打劫时，该系统会自动报警。
 * 给定一个非负整数列表，表示每个房子中存放的钱， 算一算，如果今晚去打劫，你最多可以得到多少钱
 * 在不触动报警装置的情况下。
 */
public class houseRobber {
    /*
 * @param A: An array of non-negative integers
 * @return: The maximum amount of money you can rob tonight
 */
    public long houseRobber(int[] A) {
        // write your code here
        if (A== null || A.length==0){
            return 0;
        }
//       dp[i] 表示打劫第i家时，最多的金额；
        long[] dp = new long[A.length];
        dp[0] = A[0];
        if (A.length < 2) {
            return dp[0];
        }
        dp[1] = Math.max(A[0], A[1]);


        for (int i = 2; i < dp.length; i++) {
//            前者最大
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + A[i]);
        }
        return dp[A.length - 1];
    }

    public static void main(String[] args) {
        int[] a = {3, 8, 4};
        System.out.println(new houseRobber().houseRobber(a));
    }
}
