package BagProblem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author: zhangxin
 * Time: 2017/3/30 0030.
 * Desc:多重背包问题
 * 有n种物品，每种物品有amount[i]个，每个物品的重量为weight[i]，每个物品的价值为value[i]。
 * 现在有一个背包，它所能容纳的重量为total，问：当你面对这么多有价值的物品时，你的背包所能带走的最大价值是多少？
 * <p>
 * 和前面的多重背包一样,只是在考虑第i个背包的情况下加一个计数的功能,不能超过i的数量;
 */
public class BagMore {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (in.hasNext()) {
            list.add(in.nextInt());
        }

        for (int i = 0; i < list.size(); i = i + 2) {
            System.out.println(list.get(i) + list.get(i + 1));
        }
    }


    void func1(int n, int total, int[] weight, int[] value, int[] amout) {

       /* for i=[0,n)
    *//*    将count数组清零        *//*
        for(j=weight[i]; j<=total; j++)
            if    count[j-weight[i]]<amout[i]
        tab[j] = max(tab[j-weight[i]]+value[i],tab[j]);
        if    tab[j]=tab[j-weight[i]]+value[i]    //    决定放入i是较优解
        count[i] = count[j-weight[i]] + 1
        else    if    tab[j]=0        //    防止装第1个物品和装其他物品的情况
        tab[j] = tab[j-1],count[j] = count[j-1]
        else    count[j] = count[j-1]
    }*/

        //这里count[i]表示放入物品i是最优解的情况下,使用了物品i的数量...;
        //count[i]=count[j-weight[i]]+1 ;  j代表的是当前的总重量吧
        // 放入第i个物品的操作是基于count[j-weight[i]]放入的，所以当count[i-weight[i]]>=amount[i]时，就要阻止放入即便放入第i个物品是较优值。
        int[] count = new int[n];

        //直接就上压缩的;
        int[] tab = new int[total + 1];
        for (int i = 0; i < n; i++) {
            for (int j = weight[i]; j < total + 1; j++) {
                //此时要考虑的是在当前j下,要不要物品i?
                if(count[j-weight[i]]<amout[i]){ //还可以用i;
                    tab[j] = Math.max(tab[j-weight[i]]+value[i],tab[j]);
                }
            }
        }
    }
}
