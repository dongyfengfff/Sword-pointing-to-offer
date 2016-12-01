/**
 * Author: zhangxin
 * Time: 2016/11/22 0022.
 * Desc:输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 假设输入的数组的任意两个数字都互不相同。
 */
public class T24 {
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length==0){
            return false;
        }
        return isBST(sequence, 0, sequence.length - 1);
    }

    boolean isBST(int[] arr, int start, int end) {
        if (start >= end) {
            return true;
        }
        int root = arr[end];
        int index;
        for (index = start; index < end; index++) {
            if (arr[index] > root) {
                break;
            }
        }
        for (int i = index; i < end; i++) {
            if (arr[i] < root) {
                return false;
            }
        }

        return isBST(arr, start, index - 1) && isBST(arr, index, end - 1);
    }

    public static void main(String[] args) {
        int[] a = {5, 7, 9, 11, 10, 8};
        int[] b = {5, 7};
        System.out.println(new T24().VerifySquenceOfBST(b));
    }
}
