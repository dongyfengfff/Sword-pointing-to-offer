package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2016/12/26 0026.
 * Desc:汉诺塔问题,熟练使用递归
 * 进阶1:给出一个数组,该数组中的值均在1~3之间,1:左,2:中,3:右;arr[i]=val表示:第i+1个圆盘的位置,在val柱子上;
 * 如果arr数组时最优移动移动轨迹的过程中出现的状态,那么该状态是最优移动轨迹的第几个状态??如果arr状态不是最优移动状态中的,返回-1;
 */
public class Problem_06_HanoiProblem {
    //########################################################################

    //汉诺塔问题,使用递归
    public static void hanoi(int n) {
        if (n > 0) {
            func(n, "left", "mid", "right");
        }
    }

    /*
    汉诺塔的递归;你让我用递归解决汉诺塔,我不会,我只会弄一个的情况,也就是终止情况,但是我假装我会,定义一个fun方法,注意:我这个方法是可以实现功能的
    怎么使用fun方法?首先进入一般情况,现在有n个柱子,我用fun()将n-1个柱子瞬间从from移动到to;
    接下来处理from上的最大的柱子,直接移动到to;
    此时我们已经处理完这个最大的环了,这个环的位置固定了,这时候我们就可以考虑忽略这个最大的环的存在,进而考虑如何将刚刚mid上的n-1个环移动到to
    这又是新的一轮递归;
    */
    public static void func(int n, String from, String mid, String to) {
        //终止条件
        if (n == 1) {
            System.out.println("move from " + from + " to " + to);
        } else {
            func(n - 1, from, to, mid); //步骤1:将上面的n-1从from移动到mid
            func(1, from, mid, to); //步骤2:将最下面的1从from移动到to,此时最大的环已经在to上了,位置就不变了,其实这时候就可以忽略这个环了,直接考虑n-1个环从mid到to的问题;
            func(n - 1, mid, from, to);//步骤3:将中间的n-1个从mid移动到to
        }
    }




    //########################################################################
    //最优移动轨迹:上一步已经用递归求出来了,那么最直接的方法,移动一遍,没移动一步对比一下数组,但是这样效率太慢了;
    public static int step1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return process(arr, arr.length - 1, 1, 2, 3);
    }
    //递归过程:
    public static int process(int[] arr, int i, int from, int mid, int to) {
        if (i == -1) {
            return 0;
        }
        if (arr[i] != from && arr[i] != to) {
            return -1;
        }
        if (arr[i] == from) {
            //最后一个圆环还在左柱上,说明上面的n-1个圆环从from往mid移动的过程还未完成;
            return process(arr, i - 1, from, to, mid);
        } else {
            //最后一个圆环已经在右柱上,接下来考虑上面的n-1个圆环从mid往to移动的过程;
            int rest = process(arr, i - 1, mid, from, to);
            if (rest == -1) {
                //中间过程出问题了,直接返回-1,上面递归的函数都是返回-1;
                return -1;
            }
            return (1 << i) + rest;
        }
    }



    //########################################################################
    //采用非递归
    public static int step2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int from = 1;
        int mid = 2;
        int to = 3;
        int i = arr.length - 1;
        int res = 0;
        int tmp = 0;
        while (i >= 0) {
            if (arr[i] != from && arr[i] != to) {
                return -1;
            }
            if (arr[i] == to) {
                res += 1 << i;
                tmp = from;
                from = mid;
            } else {
                tmp = to;
                to = mid;
            }
            mid = tmp;
            i--;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 3;
        hanoi(n);

        /*int[] arr = { 3, 3, 2, 1 };
        System.out.println(step1(arr));
        System.out.println(step2(arr));*/

    }
}
/*
*
* */