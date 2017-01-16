package extended.chapter_1_stackandqueue;

import java.util.Stack;

/**
 * Author: zhangxin
 * Time: 2016/12/27 0027.
 * Desc:使用栈来解决汉诺塔问题,修改下规则,left,mid,right,left和right上的环不能直接交换,必须经过mid
 * <p>
 * 这里提供两种思路解决:
 * 递归:
 * 非递归:
 */
public class Problem_06_HanoiStack {

    //假设已经实现了该功能,fun就有能力把n个柱子从left移动到right;什么时候停止?//还要用一个stack,不一定,递归里不需要
    static void func(int n, int left, int mid, int right) {
        if (n == 1) {
            System.out.println("remove from: " + left + " to " + right);
        }

        /*
        func(n, left, right, mid);  //(1)先将所有的柱子从left移动到mid.但是这一步想的美好,可实现起来就是死循环,n不减少啊;
        func(n - 1, mid, right, left);//(2)将mid上的n-1个柱子移动到left
        func(1, mid, left, right);//(3)将mid上的最后一个柱子移动到right
        */

        //上面的移动策略错误
        func(n - 1, left, mid, right);
        func(1, left, right, mid);
        func(n - 1, right, mid, left);
        func(1, mid, left, right);
    }

    /***
     * 使用递归解决问题
     * @param num 要移动的步数
     * @param left 以下的left/mid/right没有必要,可以直接定义为全局变量;
     * @param mid
     * @param right
     * @param from 从哪?
     * @param to  到哪?
     * @return 返回移动n所需要的步数
     */
    public static int process(int num, String left, String mid, String right,
                              String from, String to) {

        if (num == 1) {
            if (from.equals(mid) || to.equals(mid)) {
                //from/to有一个是在mid,那么无论你想怎么移动都是一步看可以
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            } else {
                //其它情况都是需要两步;
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }
        }

        if (from.equals(mid) || to.equals(mid)) {
            //from或者to有一个是mid
            String another = (from.equals(left) || to.equals(left)) ? right : left;
            int part1 = process(num - 1, left, mid, right, from, another);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + to);
            int part3 = process(num - 1, left, mid, right, another, to);
            return part1 + part2 + part3;
        } else {
            //from~to是跨mid的,也就是左右移动;eg:是将N从left移动到right
            int part1 = process(num - 1, left, mid, right, from, to); //(1)先将n-1,从left移动到right
            int part2 = 1;//(2)将最后一个n从left移动到mid
            System.out.println("Move " + num + " from " + from + " to " + mid);
            int part3 = process(num - 1, left, mid, right, to, from);//(3)再将n-1从right移动到left
            int part4 = 1;//(4)再将最后一个从mid移动到right
            System.out.println("Move " + num + " from " + mid + " to " + to);
            int part5 = process(num - 1, left, mid, right, from, to);//(5)最后将n-1从left再移动到right
            return part1 + part2 + part3 + part4 + part5;
        }
    }

//###########################################################################################
    //非递归,使用栈来模拟该过程;

    //定义枚举类型,[什么都不做,左到中,中到左,中到右,右到中],整个过程中只能出现这个四个动作;
    public static enum Action {
        No, LToM, MToL, MToR, RToM
    }

    //汉诺塔问题解决思路2,使用栈来模拟问题
    public static int hanoiProblem2(int num, String left, String mid, String right) {
        //一个柱子代表一个栈
        Stack<Integer> lS = new Stack<Integer>();
        Stack<Integer> mS = new Stack<Integer>();
        Stack<Integer> rS = new Stack<Integer>();

        //初始化三个栈,每个栈先压入整数最大值?防止出现空指针吧;这样三个最大数怎么也移动不了;
        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);

        //初始化左栈
        for (int i = num; i > 0; i--) {
            lS.push(i);
        }


        Action[] record = {Action.No};
        int step = 0;
        while (rS.size() != num + 1) {
            //其实下面四个顺序无所谓,因为每一轮循环,只有一个结果返回了1; [X]
            //不是随意的,最好是按照 左->中->右的顺序来吧;
            step += fStackTotStack(record, Action.MToL, Action.LToM, lS, mS, left, mid);
            step += fStackTotStack(record, Action.LToM, Action.MToL, mS, lS, mid, left);
            step += fStackTotStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
            step += fStackTotStack(record, Action.MToR, Action.RToM, rS, mS, right, mid);
        }
        return step;
    }

    /***
     * 一个动作能否执行的条件:1:不问反小压大的规则;2:不能逆向前一个动作;
     * 于是推出两个重要原则:
     * (1)游戏的第一个动作一定是L->M;
     * (2)四个动作中只有一个动作不违反小压大和互逆原则,其它三个均违反
     * @param record    保存的是上一次的移动方式
     * @param preNoAct 名字起的有歧义,好像是之前移动的动作,但是不是,而是本次要移动的动作的取反;--防止又移动回去,(其实这个参数可以取消的)
     * @param nowAct   现在要移动的动作
     * @param fStack   fromStack
     * @param tStack   toStack
     * @param from     from描述字符串
     * @param to       to描述字符串
     * @return
     */
    public static int fStackTotStack(Action[] record, Action preNoAct,
                                     Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack,
                                     String from, String to) {
        //note:原本是fStack.peek() < tStack.peek(),现在该为<=,这样在上面的while循环中,四个调用的顺序是随意的;
        if (record[0] != preNoAct && fStack.peek() <= tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        func(3, 1, 2, 3);
    }
}
