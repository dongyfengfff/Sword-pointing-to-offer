import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhangxin
 * Time: 2016/11/25 0025.
 * Desc:圆圈中最后剩下的数字
 * 让小朋友们围成一个大圈,然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中.
 * 从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演
 */
public class T45 {
    public int LastRemaining_Solution(int n, int m) {
        if (n<=0||m<=0){
            return 0;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        //初始化 queue
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (count == m - 1) {
                count = 0;
                continue;
            } else {
                queue.add(i);
            }
            count++;
        }
        int out = 0;
        while (!queue.isEmpty()) {
            out = queue.poll();
            if (count == m - 1) {
                count = 0;
                continue;
            } else {
                queue.add(out);
            }
            count++;
        }
        return out;
    }

    public static void main(String[] args) {
        T45 t = new T45();
        System.out.println(t.LastRemaining_Solution(5,3));
    }
}
