package LintCode.dp;

/**
 * Author: zhangxin
 * Time: 2017/9/9 0009.
 * Desc:找出一个序列中乘积最大的子数组（至少包含一个数）。
 */
public class maxProduct {
    /*
 * @param nums: An array of integers
 * @return: An integer
 */
    public int maxProduct(int[] nums) {
        // write your code here
        int max = Integer.MIN_VALUE;

        int len = nums.length;
        int[] dp = new int[len];
        // dp[i][j] 表示以i开头，以j结尾区间的最大值；空间有浪费，其实只用到了一半；
        for (int i = 0; i < len; i++) {
            dp[i] = nums[i];
            max = Math.max(max, dp[i]);
            for (int j = i + 1; j < len; j++) {
                dp[j] = dp[j - 1] * nums[j];
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }
}
