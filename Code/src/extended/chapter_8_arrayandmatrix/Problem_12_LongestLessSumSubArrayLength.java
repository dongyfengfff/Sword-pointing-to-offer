package extended.chapter_8_arrayandmatrix;

import java.util.HashMap;

/**
 * Author: zhangxin
 * Time: 2017/1/27
 * Desc:未排序数组中累加和小于或等于给定值k的最长子数组长度
 * <p>
 * 思路:一次求出以数组每个位置结尾的,累加和<=k的最长子数组长度,其实和前一题类似,上一题是等于,这一题,稍微修改一下;
 */
public class Problem_12_LongestLessSumSubArrayLength {
    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1]; //辅助数组,表示arr数组从左到右累加和最大值,第一个数为0,h[0]=0;
        int sum = 0;
        h[0] = sum;
        //初始化辅助数组;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);//h[i+1]表示的是:arr[0~i]的最大sum值;所以h[]是递增的;
        }

        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    //使用二分查找方法,找到在arr中第一个等于num的索引;
    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                //arr[mid] < num
                low = mid + 1;
            }
        }
        return res;
    }

}
