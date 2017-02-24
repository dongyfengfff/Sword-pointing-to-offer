import java.util.Stack;

/**
 * Author: zhangxin
 * Time: 2016/11/21 0021.
 * Desc:输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * <p>
 * <p>
 * 解题思路:先找到第一个偶数,然后将起始位置定在该处,然后找下一个奇数,
 */
public class T14 {
    public void reOrderArray(int[] array) {
        int len = array.length;
        Stack<Integer> s = new Stack<Integer>();

        int even = 0; //第一个偶数的位置;
        for (; even < len; even++) {
            if ((array[even] & 1) == 0) {
                //这是一个偶数
                s.push(array[even]);
                break;
            }
        }

        for (int i = even + 1; i < len; i++) {
            if ((array[i]&1)==1){  //奇数
                array[even] = array[i];
                even++;
            }else {
                s.push(array[i]);
            }
        }

        while (!s.isEmpty()){
            len --;
            array[len] = s.pop();
        }


    }
}
