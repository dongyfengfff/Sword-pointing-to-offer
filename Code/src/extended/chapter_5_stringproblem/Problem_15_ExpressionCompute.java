package extended.chapter_5_stringproblem;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Author: zhangxin
 * Time: 2017/1/12 0012.
 * Desc:str = "48*((70-65)-43)+8*1" 返回-1816
 * 注意对 '-'的判断;
 * 可以认为给出的公式是正确的,不用对合法性继续校验
 * 计算结果不会溢出
 * TODO:计算公式的模板方法,需要背过!!!
 */
public class Problem_15_ExpressionCompute {

    public static int getValue(String str) {
        return value(str.toCharArray(), 0)[0];
    }


    /*
    用递归来解决字符串公式的问题;

     */
    public static int[] value(char[] str, int i) {
        LinkedList<String> que = new LinkedList<String>();  //双端队列,先进先出;
        int pre = 0;
        int[] bra = null;
        while (i < str.length && str[i] != ')') {

            if (str[i] >= '0' && str[i] <= '9') {
                //遇到了数字的情况;
                // TODO:处理数时的技巧
                pre = pre * 10 + str[i++] - '0'; //最好写成 str[i] ;处理完之后在i++
            } else if (str[i] != '(') {
                //只要不是数字,不是'(',也就是遇到 +-*/)这五种了;
                addNum(que, pre);
                que.addLast(String.valueOf(str[i++]));
                pre = 0;
            } else {
                //是 '(' 的情况; 遇到(,把下一部分作为一个新的表达式来计算了;
                bra = value(str, i + 1);
                pre = bra[0]; //得到的结果保存在bra中,那么()中
                i = bra[1] + 1;
            }
        }
        //遇到str末端,其实更可能是遇到了 ')',那么接下来计算()中的结果,然后在返回一个int数组,第一个元素是()的结果,第二个元素是')'的索引
        addNum(que, pre);
        return new int[] { getNum(que), i };
    }



    /***
     * 遇到操作符了,数字计算结束了,要添加到que中;这里当前que中最后肯定是操作符,如果是 *或/就直接计算了,在保存值,如果是 +或-就直接保存值;
     * @param que 双端队列;
     * @param num 一个新的数;
     */
    public static void addNum(LinkedList<String> que, int num) {
        if (!que.isEmpty()) {
            int cur = 0;
            String top = que.pollLast(); //把队列最后一个操作符拿出来;
            if (top.equals("+") || top.equals("-")) {
                que.addLast(top);//如果操作符是 +或-,再次放到队列的最后;
            } else {
                //操作符是: * 或者 / ,再拿出一个数字出来;
                cur = Integer.valueOf(que.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        que.addLast(String.valueOf(num));
    }

    //计算que中表达式的结果,其实*和/都已经计算过了,剩下的就是+和-了,直接按顺序计算即可;
    //NOTE:这个计算方法也很巧妙;
    public static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }









//####################################################################################
    //NOTE:千万不要用这种方法来解析,坑!!!
    static void getRes(String str) {
        char pre = '('; //伪造一个pre,只是为了判断str的首字符是'('的情况
        char cur;
        int num = 0;
        Stack<Integer> s1 = new Stack<Integer>(); //用来盛放数字;
        Stack<Character> s2 = new Stack<Character>(); //用来盛放符号;
        int n1, n2;
        char op;
        int index = 0;
        while (index < str.length()) {
            cur = str.charAt(index);
            if (cur >= '0' && cur <= '9') {
                num = num * 10 + cur;
            } else if (cur == '(') {
                //TODO:入栈吧,好像不存也没事;
                s2.push('(');
            } else if (cur == ')') {
                //遇到')',该计算了
                n2 = s1.pop();
                n1 = s1.pop();
                op = s2.pop();
                s2.pop(); //把 '(' pop出来;
                s1.push(calculator(n1, n2, op));
            } else if (cur == '*' || cur == '/') {

            } else if (cur == '+') {
                if (!s2.isEmpty() && (s2.peek() == '*' || s2.peek() == '/')) {
                    n2 = s1.pop();
                    n1 = s1.pop();
                    op = s2.pop();
                    s2.pop();
                    s1.push(calculator(n1, n2, op));
                }
            } else if (cur == '-') {
                //遇到-特殊对待,倒是是减号还是负号
                if (pre=='('){

                }else {

                }
            }
            pre = cur;
            index++;
        }
    }

    static int calculator(int n1, int n2, char op) {
        int res = 0;
        switch (op) {
            case '+':
                res = n1 + n2;
                break;
            case '-':
                res = n1 - n2;
                break;
            case '*':
                res = n1 * n2;
                break;
            case '/':
                res = n1 / n2;
                break;
            default:
                break;
        }
        return res;
    }



    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp));

        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp));

        exp = "10-5*3";
        System.out.println(getValue(exp));

        exp = "-3*4";
        System.out.println(getValue(exp));

        exp = "(-3)*4";
        System.out.println(getValue(exp));

        exp = "3+1*4";
        System.out.println(getValue(exp));

    }
}
