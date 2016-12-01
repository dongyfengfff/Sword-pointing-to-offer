import java.util.Stack;

/**
 * Author: zhangxin
 * Time: 2016/11/21 0021.
 * Desc: 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
 */
public class T21 {
    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();
    int min = Integer.MAX_VALUE;

    public void push(int node) {
        s1.push(node);
        if (node <= min) {
            s2.push(node);
            min = node;
        } else {
            //如果新添加的数比较大,那么再把较小的数push一遍
            s2.push(min);
        }
    }

    public void pop() {
        s1.pop();
        s2.pop();
    }

    public int top() {
        return s1.peek();
    }

    public int min() {
        return s2.peek();
    }

}
