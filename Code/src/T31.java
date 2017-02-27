/**
 * Author: zhangxin
 * Time: 2016/11/23 0023.
 * Desc: 连续子数组最大和;
 *
 * 解题思路:首先肯定要for遍历整个数组,设置一个sum值,但是这个sum值并不是一直累加下去,需要在sum<0的时候重新将sum置为0;
 * 并且需要设置一个max用来保存每一步的最大值,使用Math.max(sum,max);不断更新max,最后max就是最大值;
 *
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

    //没用上..............
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


/*
解题思路:初始化max值为integer最小值,然后对array进行遍历,sum += array[i];如果此时的sum已经<0,那么就要将sum清0;
每一步都需要保存一个最大值,保存每一步的最大值;
 */
    public int FindGreatestSumOfSubArray2(int[] array) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i< array.length; i++) {
            sum += array[i];
            max = Math.max(max,sum);
            if (sum<0) {
                sum = 0;
            }
        }
        return max;
    }
}
