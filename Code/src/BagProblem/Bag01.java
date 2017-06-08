package BagProblem;

import java.util.Scanner;

/**
 * Author: zhangxin
 * Time: 2017/3/30 0030.
 * Desc:01背包问题
 * 有n个物品，每个物品的重量为weight[i]，每个物品的价值为value[i]。
 * 现在有一个背包，它所能容纳的重量为total，问：当你面对这么多有价值的物品时，你的背包所能带走的最大价值是多少?
 */
public class Bag01 {
    public static void main(String[] args) {
      /*  f1();
        System.out.println();
        f2();*/
        f3();
    }

    static void f1() {
        //开始套路
        int n;  //一共有n件物品
        int total; //背包的总重量为total


        n = 5;
        total = 10;

        int[] weight = {4, 5, 6, 2, 2};
        int[] value = {6, 4, 5, 3, 6};
        //step1:创建动态规划数组,其中dp[i][j]表示:在前i件物品中选择若干件放在承重为 j 的背包中，可以取得的最大价值。
        int[][] dp = new int[n][total + 1];

        //step2:初始化第一行和第一列;

        //step2.1  初始化第一行,这里要注意的是,你的第一个物品是不是能放进去,如果放不进去...
        for (int i = weight[0]; i < total + 1; i++) {
            dp[0][i] = value[0];
        }


        //step2.1  初始化第一列,这里第一列为什么多出来一列,用的是total+1,在0的时候可以用作辅助手段;
        // 第一列初始化有严重的问题; 第一列不初始化了吧;


        //step3,开始动态规划了啊;考虑 dp[i][j]的取值只能有三种情况
        /*
         dp[i-1][j] :不放物品i
         dp[i][j-1] : 虽然背包质量+1,但是还是不足以放更多东西,那么还是和j-1的时候一样;
         dp[i-1][j- xxx]:这次我要放入物品i,那么之前没放物品i时候的最佳值再加上物品i的值
         三者取最大
        */

        for (int i = 1; i < n; i++) {  //从第一行开始了
            for (int j = 1; j < total + 1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (j >= weight[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j - weight[i]] + value[i], dp[i][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < total + 1; j++) {
//                System.out.print(dp[i][j]+"  ");
                System.out.printf("%2d  ", dp[i][j]);
            }
            System.out.println();
        }
    }


    static void f2() {
        //开始套路
        int n;  //一共有n件物品
        int total; //背包的总重量为total


        n = 5;
        total = 10;

        int[] weight = {4, 5, 6, 2, 2};
        int[] value = {6, 4, 5, 3, 6};
        //step1:创建动态规划数组,其中dp[i][j]表示:在前i件物品中选择若干件放在承重为 j 的背包中，可以取得的最大价值。
        int[][] dp = new int[n][total + 1];

        //step2:初始化第一行和第一列;

        //step2.1  初始化第一行,这里要注意的是,你的第一个物品是不是能放进去,如果放不进去...
        for (int i = weight[0]; i < total + 1; i++) {
            dp[0][i] = value[0];
        }


        //step2.1  初始化第一列,这里第一列为什么多出来一列,用的是total+1,在0的时候可以用作辅助手段;
        // 第一列初始化有严重的问题; 第一列不初始化了吧;


        //step3,开始动态规划了啊;考虑 dp[i][j]的取值只能有三种情况
        /*
         dp[i-1][j] :不放物品i
         dp[i][j-1] : 虽然背包质量+1,但是还是不足以放更多东西,那么还是和j-1的时候一样;
         dp[i-1][j- xxx]:这次我要放入物品i,那么之前没放物品i时候的最佳值再加上物品i的值
         三者取最大
        */

        for (int i = 1; i < n; i++) {  //从第一行开始了
            for (int j = 1; j < total + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= weight[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j - weight[i]] + value[i], dp[i][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < total + 1; j++) {
//                System.out.print(dp[i][j]+"  ");
                System.out.printf("%2d  ", dp[i][j]);
            }
            System.out.println();
        }
    }


    static void f3() {

        //开始套路
        int n;  //一共有n件物品
        int total; //背包的总重量为total

        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        for (int i = 0; i < count; i++) {

            n = in.nextInt();

            total = in.nextInt();

            int[] value = new int[n];
            int[] weight = new int[n];

            for (int j = 0; j < n; j++) {
                value[j] = in.nextInt();
            }

            for (int j = 0; j < n; j++) {
                weight[j] = in.nextInt();
            }

            f4(n, total, value, weight);
        }


    }

    private static void f4(int n, int total, int[] value, int[] weight) {
        //step1:创建动态规划数组,其中dp[i][j]表示:在前i件物品中选择若干件放在承重为 j 的背包中，可以取得的最大价值。
        int[][] dp = new int[n][total + 1];


        //step2:初始化第一行和第一列;
        //step2.1  初始化第一行,这里要注意的是,你的第一个物品是不是能放进去,如果放不进去...
        for (int i = weight[0]; i < total + 1; i++) {
            dp[0][i] = value[0];
        }


        //step2.2  初始化第一列,这里第一列为什么多出来一列,用的是total+1,在0的时候可以用作辅助手段;
        // 第一列初始化有严重的问题; 第一列不初始化了吧;


        //step3,开始动态规划了啊;考虑 dp[i][j]的取值只能有三种情况
        /*
         dp[i-1][j] :不放物品i
         dp[i][j-1] : 虽然背包质量+1,但是还是不足以放更多东西,那么还是和j-1的时候一样;
         dp[i-1][j- xxx]:这次我要放入物品i,那么之前没放物品i时候的最佳值再加上物品i的值
         三者取最大
        */

        for (int i = 1; i < n; i++) {  //从第一行开始了
            for (int j = 1; j < total + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= weight[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j - weight[i]] + value[i], dp[i][j]);
                }
            }
        }

        System.out.println(dp[n - 1][total]);
    }


    //不用二维数组,改用一维的;
    private static void f5(int n, int total, int[] value, int[] weight) {
        //step1:创建动态规划数组,其中dp[i][j]表示:在前i件物品中选择若干件放在承重为 j 的背包中，可以取得的最大价值。
        int[] dp = new int[total + 1];


        //step2:初始化第一行和第一列;
        //step2.1  初始化第一行,这里要注意的是,你的第一个物品是不是能放进去,如果放不进去...
        for (int i = weight[0]; i < total + 1; i++) {
            dp[i] = value[0];
        }


        //step2.2  初始化第一列,这里第一列为什么多出来一列,用的是total+1,在0的时候可以用作辅助手段;
        // 第一列初始化有严重的问题; 第一列不初始化了吧;


        //step3,开始动态规划了啊;考虑 dp[i][j]的取值只能有三种情况
        /*
         dp[i-1][j] :不放物品i
         dp[i][j-1] : 虽然背包质量+1,但是还是不足以放更多东西,那么还是和j-1的时候一样;
         dp[i-1][j- xxx]:这次我要放入物品i,那么之前没放物品i时候的最佳值再加上物品i的值
         三者取最大
        */

        for (int i = 1; i < n; i++) {  //从第一行开始了
            for (int j = total; j > 0; j--) {
                if (j >= weight[i]) {
                    dp[j] = Math.max(dp[j - weight[i]] + value[i], dp[j]);
                }
            }
        }

        System.out.println(dp[total]);
    }


}
