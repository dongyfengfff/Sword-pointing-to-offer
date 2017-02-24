import java.util.Stack;

/**
 * Author: zhangxin
 * Time: 2017/2/20 0020.
 * Desc:用两个栈实现队列;分别完成在队列尾插入和在队列头部删除的功能;
 * 分析:栈:后进先出;       队列:先进先出;
 */
public class T07 {
    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();

    //插入到队列的尾部
    public void push(int i) {
        s1.push(i);
    }


    //从队列的头部删除
    public int pop() {
        if (s1.isEmpty()&&s2.isEmpty()){
            throw new RuntimeException("Queue is empty!");
        }
        if (s2.isEmpty()){
           while (!s1.isEmpty()){
               s2.push(s1.pop());
           }
        }

        return s2.pop();
    }

    public static void main(String[] args) {

    }
}
