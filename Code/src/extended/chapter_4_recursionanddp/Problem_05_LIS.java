package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2016/12/26 0026.
 * Desc:最长递增子序列(注意区分子序列和子数组,子序列可以是不连续的,但在原数组的相对位置不变)
 *
 */
public class Problem_05_LIS {

    //############################################################
    //第一种,比较简单的思路,但是时间复杂度为O(n^2)
    public static int[] lis1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getdp1(arr);
        return generateLIS(arr, dp);
    }

    public static int[] getdp1(int[] arr) {
        //创建辅助数组,长度和arr相等,dp[i]表示的是arr数组中以arr[i]结尾的情况下,arr[0...i]中最长子序列的长度
        int[] dp = new int[arr.length];
        //接下来初始化吧
        for (int i = 0; i < arr.length; i++) {
            //先将dp[i]初始化为1,以为arr[i]很可能是当前数组中最大的值,以其结尾递增序列只有它自己,所以初始化为1
            //其实生成的这个dp[]数组,包含每一步决策的信息;
            dp[i] = 1;
            //接下来考虑这样一个情况,以arr[i]结尾,那么arr[0~i-1]中所有比arr[i]小的数都可以作为倒数第二个数,选哪个呢
            //当然是选arr[j]对应的dp[j]最大的哪一个
            //但是总感觉这个效率有点低啊;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    public static int[] generateLIS(int[] arr, int[] dp) {
        int len = 0;
        int index = 0;
        //先找到dp[]中最大的值,代表长度最大的,并返回其对应的index值;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }
        int[] lis = new int[len];
        lis[--len] = arr[index];
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                lis[--len] = arr[i];
                index = i;
            }
        }
        return lis;
    }



    //############################################################
    //加速的方法,这里使用两个数组,一个dp数组和之前的含义一样,另一个end数组,表示的,这样在向前找的时候,根据end数组直接找到;
    public static int[] lis2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getdp2(arr);
        return generateLIS(arr, dp);
    }

    /*
    找dp[i]的一个更快速的一个方法,
     */
    public static int[] getdp2(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length]; //ends是一个辅助数组,用来更快速的找到dp[i]
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0;  //这里end[0~right]表示的是有效区,对于有效区中的j,end[j]=c表示:所有长度为j+1的最小结尾是c
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            //二分查找
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            dp[i] = l + 1;
        }
        return dp;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 2, 1, 5, 3, 6, 4, 8, 9, 7 };
        printArray(arr);
        printArray(lis1(arr));
        printArray(lis2(arr));

    }
}
