package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2016/12/23 0023.
 * Desc:最小的钱币数
 * 给定一个数组arr,该数组中的各个数代表钱币的面值,每个面值的数量无限多
 * 给定一个aim,用arr中的钱币凑齐aim数,是的凑出的钱币数量最少
 * <p>
 * 套路:
 * 1. 构造dp数组
 * 2. 初始化dp数组的第一行和第一列
 * 3. 空间压缩
 * <p>
 * 进阶:如果所标示的钱币的数量只有一个,如何凑出最小钱币数量(这里可能的一种情况是arr[]数组中包含相同的值,这样某种钱币的数量就不是1了)
 */
public class Problem_03_CoinsMin {

    /*
    不用考虑顺序,不用对arr进行排序
    从上向下进行遍历,遇到最小的那个会被一直顺位移动下来;
     */
    public static int minCoins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        int n = arr.length;
        int max = Integer.MAX_VALUE;

        //(1)构造动态数组 (2)此时的数组其实已经初始化为全0了;
        int[][] dp = new int[n][aim + 1];  //n行,aim+1列;此时的列的索引就是当前凑的货币总额;

        //(2)初始化第一行;
        for (int j = 1; j <= aim; j++) {  //此时j就是当前行的钱币凑成的总额;注意是从1开始的,也就是说第0列的值为0;
            dp[0][j] = max;//先赋值为max;
            //第一个判定条件是保证第二个判定条件数组下标不越界
            //只是初始化第一行,将arr[0]的倍数列初始化响应的倍数;
            if (j - arr[0] >= 0 && dp[0][j - arr[0]] != max) {
                dp[0][j] = dp[0][j - arr[0]] + 1;
            }
        }

        int left = 0;
        for (int i = 1; i < n; i++) {//最外层按列遍历
            for (int j = 1; j <= aim; j++) {//内层按行遍历
                left = max;
                //还是进行 行的初始化,只初始化倍数列,但是倍数并不放在数组中,而是放在left中;
                if (j - arr[i] >= 0 && dp[i][j - arr[i]] != max) {
                    left = dp[i][j - arr[i]] + 1;
                }
                //这一步,比较left和上一行的大小才进行真正的数组初始化;
                //NOTE:是在这一句话屏蔽的数组不用排序的情况;
                dp[i][j] = Math.min(left, dp[i - 1][j]);
            }
        }
        return dp[n - 1][aim] != max ? dp[n - 1][aim] : -1;
    }

    //接下来进行空间压缩,生成长度为aim+1的动态规划一维数组,按行更新,为什么不按列更新,是因为从上一部未压缩的规划来看,总体上还是按照行来更新的

    public static int minCoins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        int n = arr.length;
        int max = Integer.MAX_VALUE;

        int[] dp = new int[aim + 1]; //生成动态规划数组,按照行来向下进行压缩

        //先来一波初始化;
        for (int j = 1; j <= aim; j++) {
            dp[j] = max;
            if (j - arr[0] >= 0 && dp[j - arr[0]] != max) {
                dp[j] = dp[j - arr[0]] + 1;
            }
        }
        int left = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {
                left = max;
                if (j - arr[i] >= 0 && dp[j - arr[i]] != max) {
                    left = dp[j - arr[i]] + 1;
                }
                dp[j] = Math.min(left, dp[j]);
            }
        }
        return dp[aim] != max ? dp[aim] : -1;
    }

    //进阶问题的经典动态规划算法
    public static int minCoins3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[][] dp = new int[n][aim + 1]; //创建dp数组

        //和之前一样,初始化数组的第一行
        for (int j = 1; j <= aim; j++) {
            dp[0][j] = max;
        }
        //只给恰好等于的元素置为1;
        if (arr[0] <= aim) {
            dp[0][arr[0]] = 1;
        }

        int leftup = 0; // 左上角某个位置的值

        for (int i = 1; i < n; i++) { //最外层从上到下遍历
            for (int j = 1; j <= aim; j++) { //最内层从左到右遍历
                leftup = max;
                // 注意和之前的判定条件进行对比:
                // if (j - arr[i] >= 0 && dp[i][j - arr[i]] != max) //之前是只要 a[i][j-cur]上有值就赋值
                if (j - arr[i] >= 0 && dp[i - 1][j - arr[i]] != max) { //现在是上一行的...有值才开始赋值=>保证只使用一次;
                    leftup = dp[i - 1][j - arr[i]] + 1;
                }
                dp[i][j] = Math.min(leftup, dp[i - 1][j]);
            }
        }
        return dp[n - 1][aim] != max ? dp[n - 1][aim] : -1;
    }

    public static int minCoins4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[] dp = new int[aim + 1];
        for (int j = 1; j <= aim; j++) {
            dp[j] = max;
        }
        if (arr[0] <= aim) {
            dp[arr[0]] = 1;
        }
        int leftup = 0; // 左上角某个位置的值
        for (int i = 1; i < n; i++) {
            for (int j = aim; j > 0; j--) {
                leftup = max;
                // if (j - arr[i] >= 0 && dp[i][j - arr[i]] != max) //之前是只要 a[i][j-cur]上有值就赋值
                if (j - arr[i] >= 0 && dp[j - arr[i]] != max) {
                    leftup = dp[j - arr[i]] + 1;
                }
                dp[j] = Math.min(leftup, dp[j]);
            }
        }
        return dp[aim] != max ? dp[aim] : -1;
    }

    public static void main(String[] args) {
        /*int[] arr1 = { 100, 20, 5, 10, 2, 50, 1 };
        int aim1 = 17019;
        System.out.println(minCoins1(arr1, aim1));
        System.out.println(minCoins2(arr1, aim1));

        int[] arr2 = { 10, 100, 2, 5, 5, 5, 10, 1, 1, 1, 2, 100 };
        int aim2 = 223;
        System.out.println(minCoins3(arr2, aim2));
        System.out.println(minCoins4(arr2, aim2));*/

        int[] arr = {2, 3, 5};
        int aim = 20;
        System.out.println(minCoins1(arr, aim));
        System.out.println(f1(arr, aim));
    }


    //最简洁的写法;
    static int f1(int[] arr, int aim) {

        //创建数组
        int[] dp = new int[aim + 1];

        //初始化第一行
        int max = Integer.MAX_VALUE;
        for (int i = 1; i < aim + 1; i++) {
            dp[i] = max;
            if (i >= arr[0] && dp[i - arr[0]] != max) {
                dp[i] = dp[i - arr[0]] + 1;
            }
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = arr[i]; j < aim + 1; j++) {
                if (dp[j - arr[i]] != max) {
                    dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
                }
            }
        }
        return dp[aim] != max ? dp[aim] : -1;
    }
}

