package extended.chapter_8_arrayandmatrix;

/**
 * Author: zhangxin
 * Time: 2017/1/25
 * Desc:需要排序的最短子数组的长度
 */
public class Problem_05_MinLengthForSort {
    public static int getMinLength(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        //从右向左遍历数组,找到一个最小值
        int min = arr[arr.length - 1]; //最小值初始为最右边的数
        int noMinIndex = -1;  //最小值的索引
        //最理想的状态是这样的:从右向左扫描,arr[i]都比min要小,也就是说min最后是最左边的数字;
        for (int i = arr.length - 2; i != -1; i--) {
            if (arr[i] > min) { //从右往左扫,还能找到比右边大的值,所以记录这个数的索引位置;
                noMinIndex = i;
            } else {
               // min = Math.min(min, arr[i]);
                min = arr[i];
            }
        }
        if (noMinIndex == -1) { //最理想的情况
            return 0;
        }


        int max = arr[0];
        int noMaxIndex = -1;
        for (int i = 1; i != arr.length; i++) {
            if (arr[i] < max) {
                noMaxIndex = i;
            } else {
                //max = Math.max(max, arr[i]);
                max = arr[i];
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
        System.out.println(getMinLength(arr));

    }
}
