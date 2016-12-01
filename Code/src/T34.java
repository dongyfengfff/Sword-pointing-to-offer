import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhangxin
 * Time: 2016/11/18 0018.
 * Desc:把只包含因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */

/*
《参考程序员面试金典》伪代码如下
1）初始化array和队列：Q2 Q3 Q5
2）将1插入array
3）分别将1*2、1*3 、1*5插入Q2 Q3 Q5
4)令x为Q2 Q3 Q5中的最小值，将x添加至array尾部
5）若x存在于：
    Q2：将 x * 2、x * 3、x*5 分别放入Q2 Q3 Q5，从Q2中移除x
    Q3：将 x * 3、x*5 分别放入Q3 Q5，从Q3中移除x
    Q5：将 x * 5放入Q5，从Q5中移除x
6）重复步骤4~6，知道找到第k个元素
*/

public class T34 {
    public int GetUglyNumber_Solution(int index) {
        if (index < 1) {
            return 0;
        }
        int ugly = 1;
        int cur = 1, count = 1;
        Queue<Integer> q2 = new LinkedList<Integer>();
        Queue<Integer> q3 = new LinkedList<Integer>();
        Queue<Integer> q5 = new LinkedList<Integer>();

        q2.add(2);
        q3.add(3);
        q5.add(5);

        while (count < index) {
            ugly = Math.min(q2.peek(), Math.min(q3.peek(), q5.peek()));
            if (ugly == q2.peek()) {
                q2.add(ugly * 2);
                q3.add(ugly * 3);
                q5.add(ugly * 5);
                q2.poll();
            } else if (ugly == q3.peek()) {
                q3.add(ugly * 3);
                q5.add(ugly * 5);
                q3.poll();
            } else {
                q5.add(ugly * 5);
                q5.poll();
            }
            count++;
        }
        return ugly;
    }


}
