package extended.chapter_9_others;

/**
 * Author: zhangxin
 * Time: 2017/4/26 0026.
 * Desc:
 * 两个排序数组中找到第k小的数;
 * 要求:时间复杂度O(log(min(M,N))),空间复杂度O(1)
 * 思路:和上一题思路类似,只不过上一个题目是两个数组长度相等,找中位数;这个题目是数组长度不相等,找k位数;
 */

import java.util.Arrays;

public class Problem_27_FindKthMinNumber {

    public static int findKthNum(int[] arr1, int[] arr2, int kth) {
        if (arr1 == null || arr2 == null) {
            throw new RuntimeException("Your arr is invalid!");
        }

        /*
        情况一:k不符合情况,直接报错
         */
        if (kth < 1 || kth > arr1.length + arr2.length) {
            throw new RuntimeException("K is invalid!");
        }

        //重新定义两个数组,一个用来保存long的一个用来保存short的;
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;

        /*
        情况二:
        k<=s,那么取两个数组的前k位,这就类似于上个题的方法了
        */
        if (kth <= s) {
            return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
        }

        /*
        情况三:
        k>l(大前提)
        在细分为三种情况:
        NOTE:注意:这个找特殊点的时候都是和另一个数组的最后一个数比较!!!
        (1)理想情况,k在shorts中,直接返回;
        (2)理想情况,k在longs中,直接返回;
        (3)都不是上述两种情况,那么取二者剩下的区间,此时这两个区间长度肯定是相同的都是s+l-k的长度,取其中位数;
         */
        if (kth > l) {
            //k-l-1比longs的最后一位还大,注意k是比l大的啊,那么k肯定是shorts[kth - l - 1]
            if (shorts[kth - l - 1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            //和上一个情况类似;
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }

            return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
        }


        /*
        情况四:
        核心在于找longs中的两个节点; 这种情况下short中的每个数据都有可能是k,但是longs中的不一定,
        s<k<=l
        节点在于7,和17,其实17也有可能是的,只要long[17]<short[0],但是我们整个方法中都是和数组的最后一个数进行比较,所以只能考虑7的情况;而17进入中位数情况考虑;
         */
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }

        /*
        情况五:
         */
        return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
    }

    //额,就是上一题的算法;
    public static int getUpMedian(int[] a1, int s1, int e1, int[] a2, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;
        while (s1 < e1) {
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            offset = ((e1 - s1 + 1) & 1) ^ 1;
            if (a1[mid1] > a2[mid2]) {
                e1 = mid1;
                s2 = mid2 + offset;
            } else if (a1[mid1] < a2[mid2]) {
                s1 = mid1 + offset;
                e2 = mid2;
            } else {
                return a1[mid1];
            }
        }
        return Math.min(a1[s1], a2[s2]);
    }

    // For test, this method is inefficient but absolutely right,居然把两个数组排序了,⊙﹏⊙b汗
    public static int[] getSortedAllArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new RuntimeException("Your arr is invalid!");
        }
        int[] arrAll = new int[arr1.length + arr2.length];
        int index = 0;
        for (int i = 0; i != arr1.length; i++) {
            arrAll[index++] = arr1[i];
        }
        for (int i = 0; i != arr2.length; i++) {
            arrAll[index++] = arr2[i];
        }
        Arrays.sort(arrAll);
        return arrAll;
    }

    //用来做测试的,不管;
    public static int[] generateSortedArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != len; i++) {
            res[i] = (int) (Math.random() * (maxValue + 1));
        }
        Arrays.sort(res);
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len1 = 10;
        int len2 = 23;
        int maxValue1 = 20;
        int maxValue2 = 100;
        int[] arr1 = generateSortedArray(len1, maxValue1);
        int[] arr2 = generateSortedArray(len2, maxValue2);
        printArray(arr1);
        printArray(arr2);
        int[] sortedAll = getSortedAllArray(arr1, arr2);
        printArray(sortedAll);
        int kth = 17;
        System.out.println(findKthNum(arr1, arr2, kth));
        System.out.println(sortedAll[kth - 1]);

    }

}

