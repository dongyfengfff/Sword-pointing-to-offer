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

NOTE:
这个题目再写一遍好像也没什么技巧,记住吧;
为什么要执行步骤(5),拿队列q5来举例子,这个队列中只能存放 ugly * 5 的数字,不能是随便一个n都*5吧,都则不满足ugly的特性了;
那么既然要 *5,我们用谁 *5呢?自然是上次取到的ugly,上次去到的ugly是最小的;
而对于q3,同样保存的是ugly*3,但是有一个问题是:如果上次的ugly是5,那q3要不要把 ugly*5 放进去?
不能放进去,因为ugly是5的话,对于q3来说并不一定是最小的,因为添加到q3中的是: 5*3,但是之前ugly是3的时候,已经将3*5添加到q5中去了
为了避免重复元素,所以...
*/

public class T34 {
    public int GetUglyNumber_Solution(int index) {
        if (index < 1) {
            return 0;
        }
        int ugly = 1;
        int count = 1;
        Queue<Integer> q2 = new LinkedList<Integer>();
        Queue<Integer> q3 = new LinkedList<Integer>();
        Queue<Integer> q5 = new LinkedList<Integer>();

        q2.add(2);
        q3.add(3);
        q5.add(5);

        while (count < index) {
            //先取到当前三个队列最前面的最小值,作为此次的ugly
            ugly = Math.min(q2.peek(), Math.min(q3.peek(), q5.peek()));
            //找到ugly之后还要判断是在那个队列中
            //如果是在q2中,那么
            if (ugly == q2.peek()) {
                q2.offer(ugly * 2);
                q3.offer(ugly * 3);
                q5.offer(ugly * 5);
                q2.poll();
            } else if (ugly == q3.peek()) {
                q3.offer(ugly * 3);
                q5.offer(ugly * 5);
                q3.poll();
            } else {
                q5.offer(ugly * 5);
                q5.poll();
            }
            count++;
        }
        return ugly;
    }

    public int GetUglyNumber_Solution2(int index) {
        if (index < 0) {
            return 0;
        }

        int[] arr = new int[index];
        arr[0] = 1;
        int nextIndex = 1;

        //下列三个index的含义是，N分别乘以indexN，所得到的值一定是比min大的；
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;

        while (nextIndex < index) {
            int min = Math.min(2 * arr[index2], Math.min(3 * arr[index3], 5 * arr[index5]));
            arr[nextIndex] = min;

            while (2 * arr[index2] <= min) {
                index2++;
            }

            while (3 * arr[index3] <= min) {
                index3++;
            }

            while (5 * arr[index5] <= min) {
                index5++;
            }

            nextIndex++;
        }

        return arr[index - 1];
    }
}
