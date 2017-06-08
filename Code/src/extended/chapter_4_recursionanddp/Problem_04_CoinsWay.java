package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2016/12/23 0023.
 * Desc:上一题是求最少钱币的数量,现在是让求有几种可以换算的方法(每种钱币数量无限)
 * 之前我们所有的动态规划目标都是一个:求最优解,现在让求的是所有的解的个数
 */
public class Problem_04_CoinsWay {

    //(1)穷举,没有任何技术含量
    public static int coins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    //暴力枚举的具体执行过程,使用了递归;
    public static int process1(int[] arr, int index, int aim) {
        int res = 0;
        //递归的终止条件
        if (index == arr.length) {
            //如果此时index就是arr.length了,说明已经到头了,那么如果此时aim==0,说明之前的递归是有效的,算作一次,不等于0,说明之前递归无效;
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += process1(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }
//#######################################################################################################

    //(2)记忆搜索,将之前暴力枚举的过程存储下来(数组),避免重复计算
    public static int coins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        //我本来是想用一个HasnMap的,这里却用了一个二维数组,太大了
        int[][] map = new int[arr.length + 1][aim + 1];
        return process2(arr, 0, aim, map);
    }

    public static int process2(int[] arr, int index, int aim, int[][] map) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for (int i = 0; arr[index] * i <= aim; i++) {
                mapValue = map[index + 1][aim - arr[index] * i];
                if (mapValue != 0) {
                    res += mapValue == -1 ? 0 : mapValue;
                } else {
                    res += process2(arr, index + 1, aim - arr[index] * i, map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1 : res;
        return res;
    }


//#######################################################################################################

    //(3)基本的动态规划算法;arr中当然是没有重复数字,因为每种钱币数量无限,在出现重复钱币没有意义
    public static int coins3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        //构造dp,dp[i][j]代表的是:使用arr[0~i]的钱币,组成钱数位j的所有组合情况数量;那么最后的接口就是dp[N-1][aim],最右下角;
        int[][] dp = new int[arr.length][aim + 1];

        //初始化第一列,全是1,代表钱币是0,方法数都是1;
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        //初始化第一行,表示只能用arr[0]这种钱币,那么可以组成的钱币数是 arr[0]的整数倍,所以只初始化整数倍的,数值均为1,因为只是用arr[0]
        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }

        int num = 0;
        for (int i = 1; i < arr.length; i++) {//最外层从上往下扫描
            for (int j = 1; j <= aim; j++) { //最内层从左往右扫描
                num = 0;
                for (int k = 0; j - arr[i] * k >= 0; k++) {
                    num += dp[i - 1][j - arr[i] * k];
                }
                dp[i][j] = num;
            }
        }
        //返回的是右下角最后一个元素;
        return dp[arr.length - 1][aim];
    }

    //加速的动态规划,此时只是效率增加,但是动态规划使用的辅助数组并没有减少
    public static int coins4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp[arr.length - 1][aim];
    }

    public static int coins5(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[] dp = new int[aim + 1];
        for (int j = 0; arr[0] * j <= aim; j++) {
            dp[arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
            }
        }
        return dp[aim];
    }

    public static int f1(int[] arr, int aim) {

        int[] dp = new int[aim + 1];

        //初始化第一行
        for (int i = 0; i < aim + 1; i += arr[0]) {
            dp[i] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = arr[i]; j < aim+1; j++) {
                dp[j] += dp[j - arr[i]];
            }
        }

        return dp[aim];
    }


    public static void main(String[] args) {
        int[] coins = {2, 3, 5};// { 10, 5, 1, 25 };
        int aim = 15;//2000;

        int i = f1(coins, aim);
        int j = coins5(coins, aim);
        System.out.println(i);
        System.out.println(j);
        /*long start = 0;
        long end = 0;
        System.out.println("===========暴力递归的方法===========");
        start = System.currentTimeMillis();
        System.out.println(coins1(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        aim = 20000;

        System.out.println("===========记忆搜索的方法===========");
        start = System.currentTimeMillis();
        System.out.println(coins2(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");*/

//        System.out.println("=====动态规划O(N*(aim^2))的方法=====");
//        start = System.currentTimeMillis();
//        System.out.println(coins3(coins, aim));
//        end = System.currentTimeMillis();
//        System.out.println("cost time : " + (end - start) + "(ms)");

       /* System.out.println("=======动态规划O(N*aim)的方法=======");
        start = System.currentTimeMillis();
        System.out.println(coins4(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        System.out.println("====动态规划O(N*aim)的方法+空间压缩===");
        start = System.currentTimeMillis();
        System.out.println(coins5(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");*/

    }
}
