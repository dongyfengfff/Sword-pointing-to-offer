package extended.chapter_8_arrayandmatrix;

/**
 * Author: zhangxin
 * Time: 2017/1/23
 * Desc: 找无序数组中最小的k个数;
 * 时间复杂度:O(N*Log k)
 * 进阶:O(N),TODO:进阶方法较为复杂,暂时不考虑
 */
public class Problem_04_FindMinKNums {
    //###############时间复杂度:O(N*Log k),堆排序###############
    public static int[] getMinKNumsByHeap(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        //先创建一个大小为k的数组,作为堆
        int[] kHeap = new int[k];
        //将arr中前k个数作为初始的值,初始化一个大小为k的对,放在kHeap中;
        for (int i = 0; i != k; i++) {
            heapInsert(kHeap, arr[i], i);
        }

        for (int i = k; i != arr.length; i++) {
            if (arr[i] < kHeap[0]) {
                kHeap[0] = arr[i];
                heapify(kHeap, 0, k);
            }
        }
        return kHeap;
    }

    //建堆
    public static void heapInsert(int[] arr, int value, int index) {
        arr[index] = value;
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (arr[parent] < arr[index]) {
                swap(arr, parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    //调整堆
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        while (left < heapSize) {
            if (arr[left] > arr[index]) {
                largest = left;
            }
            if (right < heapSize && arr[right] > arr[largest]) {
                largest = right;
            }
            if (largest != index) {
                swap(arr, largest, index);
            } else {
                break;
            }
            index = largest;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }

    //###############  ###############












    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
