/**
 * Author: zhangxin
 * Time: 2016/11/18 0018.
 * Desc: 统计一个数字在排序数组中出现的次数。
 * 重点在于改造二分查找,找到数组中第一个数;
 */
public class T38 {
    public int GetNumberOfK1(int[] array, int k) {
        if (array.length<1){
            return 0;
        }
        int start = 0, end = array.length - 1;
        if (k < array[0] || k > array[end]) {
            return 0;
        }

        int mid = 0;
        while (start < end - 1) {
            mid = (start + end) / 2;
            if (k > array[mid]) {
                start = mid;
            } else {
                // k <= arr[mid]
                end = mid;
            }
        }
        int s = array[start] == k ? start : end;
        int count = 0;
        for (int i = s; i < array.length; i++) {
            if (array[i] == k) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }


    int getFirstK(int[] array, int k) {
        int start = 0, end = array.length - 1;
        int mid = 0;
        while (start < end - 1) {
            mid = (start + end) / 2;
            if (k > array[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (array[start] == k) {
            return start;
        } else if (array[end] == k) {
            return end;
        }
        return -1;
    }


    int getLastK(int[] array, int k, int start) {
        int end = array.length - 1;
        int mid = 0;
        while (start < end - 1) {
            mid = (start + end) / 2;
            if (k >= array[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return array[end] == k ? end : start;
    }

    public int GetNumberOfK(int[] array, int k) {
        int len = array.length - 1;
        if (k < array[0] || k > array[len]) {
            return 0;
        }

        int start = getFirstK(array, k);
        if (start < 0) {
            return 0;
        }
        int end = getLastK(array, k, start);
        System.out.println(start + "<===>" + end);
        return end - start + 1;

    }

    public static void main(String[] args) {
        int[] a = {2, 2, 4, 4, 4, 4, 4};
        //System.out.println(new T38().GetNumberOfK(a, 3));
        T38 t = new T38();
       /* System.out.println(t.getFirstK(a,4));
        System.out.println(t.getLastK(a,4));*/
        int[] b = {1, 2, 3, 3, 3, 3, 4, 5};
        System.out.println(t.GetNumberOfK(b, 3));
    }
}
