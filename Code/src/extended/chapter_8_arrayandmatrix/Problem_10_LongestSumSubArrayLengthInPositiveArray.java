package extended.chapter_8_arrayandmatrix;

/**
 * Author: zhangxin
 * Time: 2017/1/26
 * Desc:未排序的正数数组中累加和为给定值k的最长子数组的长度
 * 使用left和right两个指针,不断向右移动即可;
 */
public class Problem_10_LongestSumSubArrayLengthInPositiveArray {
    public static int getMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];  //sum代表arr[left...right]的和
        int len = 0;      //代表累加和为k的子数组的最大长度;
        while (right < arr.length) {
            if (sum == k) {
                len = Math.max(len, right - left + 1);
                //sum -= arr[left++];
                sum = sum - arr[left];
                left++;
            } else if (sum < k) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                //sum>k,,没有right--的一说,只能left++;
               // sum -= arr[left++];
                sum = sum - arr[left];
                left++;
            }
        }
        return len;
    }

    public static int[] generatePositiveArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 10) + 1;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 20;
        int k = 15;
        int[] arr = generatePositiveArray(len);
        printArray(arr);
        System.out.println(getMaxLength(arr, k));
    }
}
