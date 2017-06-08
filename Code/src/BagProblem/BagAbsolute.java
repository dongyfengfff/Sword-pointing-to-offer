package BagProblem;

/**
 * Author: zhangxin
 * Time: 2017/3/30 0030.
 * Desc:完全背包问题
 * 有n种物品，每种物品有无限个，每个物品的重量为weight[i]，每个物品的价值为value[i]。
 * 现在有一个背包，它所能容纳的重量为total，问：当你面对这么多有价值的物品时，你的背包所能带走的最大价值是多少？
 */
public class BagAbsolute {
    public static void main(String[] args) {

    }


    static void f0() {
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

        //step2.1  初始化第一行
        for (int i = weight[0]; i < total + 1; i++) {
            dp[0][i] = i / weight[0] * value[0];
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
                    dp[i][j] = Math.max(dp[i][j - weight[i]] + value[i], dp[i][j]);
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

    static void f1() {
        //开始套路
        int n;  //一共有n件物品
        int total; //背包的总重量为total

        n = 5;
        total = 10;

        int[] w = {4, 5, 6, 2, 2};
        int[] v = {6, 4, 5, 3, 6};

        int[][] dp = new int[n][total + 1];

        //初始化第一行
        for (int i = w[0]; i < total + 1; i++) {
            dp[0][i] = dp[0][i - w[0]] + v[0];
        }

        //第一列就不初始化了吧


        for (int i = 1; i < n; i++) {
            for (int j = 0; j < total + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= w[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - w[i] + v[i]]);
                }
            }
        }
        System.out.println(dp[n - 1][total]);
    }


    void f2() {
        //开始套路
        int n;  //一共有n件物品
        int total; //背包的总重量为total

        n = 5;
        total = 10;

        int[] w = {4, 5, 6, 2, 2};
        int[] v = {6, 4, 5, 3, 6};

        int[] dp = new int[total + 1];

        //初始化第一行
        for (int i = w[0]; i < total + 1; i++) {
            dp[i] = dp[i - w[0]] + v[0];
        }


        for (int i = 1; i < n; i++) {
            for (int j = 0; j < total + 1; j++) {
                if (j >= w[i]) {
                    dp[j] = Math.max(dp[j], dp[j - w[i] + v[i]]);
                }
            }
        }
        System.out.println(dp[total]);
    }


    void f3() {
        //开始套路
        int n;  //一共有n件物品
        int total; //背包的总重量为total

        n = 5;
        total = 10;

        int[] w = {4, 5, 6, 2, 2};
        int[] v = {6, 4, 5, 3, 6};

        int[] dp = new int[total + 1];


        for (int i = 0; i < n; i++) {
            for (int j = w[i]; j < total + 1; j++) {
                dp[j] = Math.max(dp[j], dp[j - w[i] + v[i]]);
            }
        }
        System.out.println(dp[total]);
    }

}
