package extended.chapter_9_others;

/**
 * Author: zhangxin
 * Time: 2017/3/23 0023.
 * Desc:两个等长的升序数组,快速找到中上位数;要求时间复杂度:O(logN),空间复杂度:O(1)
 * 奇数情况:3+3=>找3;如果a3>b3,那么接下来找的数组为a[1..3]和b[3..5]
 * 偶数情况:4+4=>找4;如果a4>b4,那么接下来找的数组为a[1..4]和b[5..8]
 * <p>
 * 思路:分别对两个数组进行二分查找,找中位数,根据arr1[mid]和arr2[mid]的值来判断;同时还要根据每次数组的长度是否为奇偶来进行不同的判断;
 */
public class Problem_26_FindUpMedian {
    public static int getUpMedian(int[] arr1, int[] arr2) {
        //对输入的两个数组进行合法性验证;
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            throw new RuntimeException("Your arr is invalid!");
        }
        int start1 = 0;
        int end1 = arr1.length - 1;
        int start2 = 0;
        int end2 = arr2.length - 1;

        int mid1 = 0;
        int mid2 = 0;

        int offset = 0;     //表明下面要处理的数组中元素个数是奇数还是偶数;

        while (start1 < end1) {

            mid1 = (start1 + end1) / 2;
            mid2 = (start2 + end2) / 2;

            // end - start + 1 代表数组元素的个数长度;
            // 元素个数为奇数，offset为0，
            // 元素个数为偶数，offset为1。
            offset = ((end1 - start1 + 1) & 1) ^ 1;

            if (arr1[mid1] > arr2[mid2]) {
                end1 = mid1;
                start2 = mid2 + offset;
            } else if (arr1[mid1] < arr2[mid2]) {
                start1 = mid1 + offset;
                end2 = mid2;
            } else {
                //最理想的情况;
                return arr1[mid1];
            }
        }
        return Math.min(arr1[start1], arr2[start2]);
    }
}
