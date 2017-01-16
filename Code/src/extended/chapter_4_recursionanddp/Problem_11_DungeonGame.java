package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2016/12/30 0030.
 * Desc:给定一个二维数组,数组中每个元素都是可正可负,从左上角走到右下角,只能向下或者向右走,每走到一格,自身的值加上当前格的值,自身的值必须>=1
 * 问从左上到右下,初始值最少为多少才能到右下;
 */
public class Problem_11_DungeonGame {

    public static int minHP1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 1;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row--][col--];
        dp[row][col] = m[row][col] > 0 ? 1 : -m[row][col] + 1;

        //这里只初始化了向左走的情况?
        for (int j = col - 1; j >= 0; j--) {
            dp[row][j] = Math.max(dp[row][j + 1] - m[row][j], 1);
        }

        int right = 0;
        int down = 0;

        //最外层还是行遍历,从下往上;
        for (int i = row - 1; i >= 0; i--) {
            dp[i][col] = Math.max(dp[i + 1][col] - m[i][col], 1);  //首先初始化当前行的最后一列的位置;
            for (int j = col - 1; j >= 0; j--) { //内层是列遍历, 从右向左
                right = Math.max(dp[i][j + 1] - m[i][j], 1);
                down = Math.max(dp[i + 1][j] - m[i][j], 1);
                dp[i][j] = Math.min(right, down);
            }
        }
        return dp[0][0];
    }


    public static int myMinHP1(int[][] data) {

        if (data == null || data.length == 0 || data[0] == null || data[0].length == 0) {
            return 1;
        }

        int m = data.length;  //几行;
        int n = data[0].length;  //几列;

        //(1)创建动态规划数组;dp[i][j]的含义是如果要走上i,j这一点,起码应该具备的点数;
        int[][] dp = new int[m][n];

        //(2)初始化,如何确保每一格的数都>=1?
        dp[m - 1][n - 1] = data[m - 1][n - 1] > 0 ? 1 : -data[m - 1][n - 1] + 1;

        //初始化组后一行:
        //我们现在是倒着往前走,所以要走到dp[m - 1][i + 1]的值是已知的,现在要求dp[m - 1][i]的值
        //我们不是要求最小值吗,当然是每一步越小越好,
        // 对于dp[m - 1][i]要么是 1,表明在i位置上是一个加血的过程,1是最小的
        //要么是一个大于1的值,不管i位置是加血还是失血,起码i+1位置上要求的最小值已经定了;
        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] = Math.max(dp[m - 1][i + 1] - data[m - 1][i], 1);
        }


        //初始化最后一列:
        for (int i = m - 2; i >= 0; i--) {
            //dp[n - 1][i] = Math.max(dp[n - 1][i + 1] - data[n - 1][i], 1);
            dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - data[i][n - 1], 1);
        }

        //开始规划:
        int right = 0;
        int down = 0;
        for (int i = m - 2; i >= 0; i--) {  //从下往上
            for (int j = n - 2; j >= 0; j--) {  //从右往左
                //源码是这样写的
                /*right = Math.max(dp[i][j + 1] - data[i][j], 1);
                down = Math.max(dp[i + 1][j] - data[i][j], 1);
                dp[i][j] = Math.min(right,down);*/
                //调整一下逻辑,先找到右边或者下边最小的部分,这就选定了方向,然后再和data[i][j]比较,再和1比较;
                dp[i][j] = Math.max(1, Math.min(dp[i][j + 1], dp[i + 1][j]) - data[i][j]);
            }
        }
        return dp[0][0];
    }

    //压缩的方法
    public static int myMinHP2(int[][] data) {
        if (data == null || data.length == 0 || data[0] == null || data[0].length == 0) {
            return 1;
        }


        int row = data.length;
        int column = data[0].length;

        int[] dp = new int[column];
        dp[column - 1] = data[row - 1][column - 1] > 0 ? 1 : 1 - data[row - 1][column - 1];

        for (int i = column - 2; i >= 0; i--) {
            dp[i] = Math.max(1, dp[i + 1] - data[row - 1][i]);
        }


        for (int i = row - 2; i >= 0; i--) {
            dp[column - 1] = Math.max(1, dp[column - 1] - data[i][column - 1]);
            for (int j = column - 2; j >= 0; j--) {
                dp[j] = Math.max(1, Math.min(dp[j], dp[j + 1]) - data[i][j]);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        int[][] map = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}};
        System.out.println(minHP1(map));
        System.out.println(myMinHP1(map));
        System.out.println(myMinHP2(map));

    }
}

/*
根据题目给定的条件,得到的动态规划的空间是:
7   5   2
6   11  5
1   1   6
 */
