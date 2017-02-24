import java.util.Stack;

/**
 * Author: zhangxin
 * Time: 2016/11/21 0021.
 * Desc:输入两个整数序列，第一个序列表示栈的压入顺序，
 * 请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4，5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */
public class T22 {
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        int lenA = pushA.length;
        int lenB = popA.length;
        if (lenA != lenB) {
            return false;
        }
        int indexA = 0, indexB = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (; indexA < lenA; indexA++) {
            if (pushA[indexA] != popA[indexB]) {  //重点考察的是不相等的情况;
                if (!stack.isEmpty()) {
                    if (stack.peek() == popA[indexB]) {
                        stack.pop();
                        indexB++;
                    }
                }
                //NOTE:关键问题出在这里,即使i<>j不相等,但是i-1和j相等,依然算匹配到了,首先把i-1,pop出来,但还是要把i对应的值push进去
                stack.push(pushA[indexA]);
            } else { //值相等,什么也不做,让i++,j++;
                indexB++;
            }
        }

        for (; indexB < lenB; indexB++) {
            if (popA[indexB] != stack.pop()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {4, 5, 3, 2, 1};
        System.out.println(new T22().IsPopOrder(a, b));
    }


    public boolean IsPopOrder1(int[] pushA, int[] popA) {
        int lenA = pushA.length;
        int lenB = popA.length;
        if (lenA != lenB) {
            return false;
        }

        int i = 0, j = 0;
        Stack<Integer> s = new Stack<Integer>();
        for (; i < lenA; i++) {
            //先考虑相等的情况,那么什么也不做;
            if (pushA[i] == popA[j]) {
                j++;
            } else { //现在的前提是:当前的 A[i] 和 B[j] 的数不相等;
                if ((!s.isEmpty()) && (s.peek()==popA[j])) {
                    s.pop();
                    j++;
                    i--;
                } else {
                    return false;
                }
            }
        }

        while (!s.isEmpty()&&j<lenB){
            if (s.pop()!=popA[j]){
                return false;
            }
            j++;
        }
        return true;
    }
}
