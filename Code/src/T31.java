/**
 * Author: zhangxin
 * Time: 2016/11/23 0023.
 * Desc: 连续子数组最大和;
 */
public class T31 {
    public int FindGreatestSumOfSubArray(int[] array) {
        int max = Integer.MIN_VALUE, sum = 0;
        int len = array.length;

        for (int i = 0; i < len; i++) {
            max = Math.max(sum += array[i], max);
            if (sum < 0) {
                sum = 0;
            }

        }
        return max;
    }

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, product = 1;
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            max = Math.max(product *= nums[i], max);
            if (nums[i] == 0) product = 1;
        }

        product = 1;
        for (int i = len - 1; i >= 0; i--) {
            max = Math.max(product *= nums[i], max);
            if (nums[i] == 0) product = 1;
        }

        return max;
    }
}
