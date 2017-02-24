package extended.chapter_8_arrayandmatrix;

import java.util.HashMap;

/**
 * Author: zhangxin
 * Time: 2017/1/26
 * Desc:未排序的数组中累加和为k的最长子数组系列问题;与前一题不同的是,这次数组没有强调是正数;
 * 进阶1:arr中所有子数组中正数和负数个数相等的最长子数组的长度;
 * 进阶2:arr中只有0和1,求arr中所有子数组中0和1个数相等的最长子数组的长度
 */
public class Problem_11_LongestSumSubArrayLength {

    /*
    s(i)代表子数组arr[0...i]所有元素的累加和.那么arr[j,i] 的累加和为: s[i] -s[j-1],这是解决本题的核心
    同时需要一个hashMap作为辅助
    */
    public static int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();  //key为从左向右累加过程中出现的sun值,value表示sum最早出现的位置;
        map.put(0, -1); // important,原因是在某一处sum直接等于k了,那么为了和下面统一,所以将其索引设置为-1;
        int sum = 0; //当前的计算值,把sum想象成一个大值,比k大的值,比较好理解,因为sum是不断向右累加的;
        int len = 0; //当前的长度
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {  //如果之前出现过sum-k的值,拿到其索引,现在的累加和为sum,那么说明现在正好凑齐了k;
                len = Math.max(i - map.get(sum - k), len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }
    //############################################################
/*
在理解了上述问题的解法之后,进阶问题的解决方法如出一辙;
进阶1:先将数组中所有正数置为0,负数置为-1,令k=0
进阶2:将先数组中所有的0置为-1,令k=0即可
 */

}
