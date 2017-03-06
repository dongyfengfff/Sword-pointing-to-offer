import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhangxin
 * Time: 2016/11/25 0025.
 * Desc:圆圈中最后剩下的数字
 * 让小朋友们围成一个大圈,然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中.
 * 从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演
 * <p>
 * 注意:是从0开始计数的;
 */
public class T45 {
    //是用来一个队列来实现,效率较低
    public int LastRemaining_Solution(int n, int m) {
        if (n <= 0 || m <= 0) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        //初始化 queue
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (count == m - 1) {
                count = 0;
                System.out.println(i);
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
                System.out.println(out);
                count = 0;
                continue;
            } else {
                queue.add(out);
            }
            count++;
        }
        return out;
    }

    //使用递推公式法
    //
    public int LastRemaining_Solution2(int n, int m) {
        if (n == 0 || m == 0) {
            return -1;
        }

        // 表示只有一个人的时候,那么标号就是0了
        int res = 0;
        //下面的过程,每次遍历都是还原 i-1时结果的编号在i人时的编号,最终还原到i个人时候的编号,也就是所求的结果;
        for (int i = 2; i <= n; i++) {
            res = (res + m) % i;
            System.out.println(i + "->" + res);
        }
        return res;
    }

    public static void main(String[] args) {
        T45 t = new T45();
        //t.LastRemaining_Solution(10,7);
        //System.out.println("#################");
//        System.out.println(t.LastRemaining_Solution(10, 7));
        System.out.println(t.LastRemaining_Solution___(5,3));
    }

//NOTE:m>n的情况,没有考虑...其实还是m和n从0开始算比较好,这样取余的时候就不会遇到res=0的情况;最优解是上面一种方法;
    public int LastRemaining_Solution___(int n, int m) {
        if (n <= 0 || m <= 0) {
            return 0;
        }
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res = (res + m) % i;
            if(res==0){  //res正好取余==0,但是res是不可能为0的,所以res应该是这次队伍中的最后一个吧;
                res = i;
            }
            System.out.println(i+"-->"+res);
        }
        return res-1; //因为题目是按照从0开始的,这里计算是按照从1开始的,所以需要将结果-1;
    }
}

/*
就是经典约瑟夫环问题的裸题

我一开始一直没理解这个递推是怎么来的，后来终于理解了

假设问题是从n个人编号分别为0...n-1，取第k个，

则第k个人编号为k-1的淘汰，剩下的编号为  0,1,2,3...k-2,k,k+1,k+2...

此时因为从刚刚淘汰那个人的下一个开始数起，因此重新编号

把k号设置为0,则

k    0

k+1 1

...

0 n-k

1 n-k+1

假设已经求得了n-1个人情况下的最终胜利者保存在f[n-1]中，则毫无疑问，该胜利者还原到原来的真正编号即为 (f[n-1]+k)%n
（因为第二轮重新编号的时候，相当于把每个人的编号都减了k，因此重新+k即可恢复到原来编号）。由此，我们可以想象，当最终只剩下一个人的时候，该人即为胜利者，此时重新编号，因为只有一个人，所以此时f[1]=0

这样f[2]=(f[1]+k)%2,这样就可以求出最终胜利者在2个人的时候的情况下的编号，由递推公式f[n]=(f[n-1]+k)%n,可递推到最初编号序列中该胜利者的编号。

因此用这个方法，只需一遍On的扫描，即可求出最终答案

不过该题要求编号从1开始，只要把f[n]+1即可，同时，该题指定了第一个要删除的人必须为编号为m的人，其实也不难，求出f[n]之后，把原本编号为0的位置移到跟m只相距k的位置即可实现第一次删除的编号为m。所以最终 ans=
(f[n]+1+m-k);

当然因为m-k可能为负数，导致整个ans为负，这样其实最后+n即可解决。

*/