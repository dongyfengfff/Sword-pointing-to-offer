package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/16 0016.
 * Desc: 回文最少分割数
 * ACDCDCDAD => A CDCDC DAD
 * 动态规划?
 * 切分的方法:如果当前遍历到dp[i],假设在j位置:i <= j < len,有str[i...j]是回文串
 * dp[i]的值可能是:dp[j-1]+1,dp[j+1]代表剩下的str[j+1..len-1],1代表str[i...j]本身就是回文
 * 那么这样就需要让j在i...len-1的位置上进行枚举,找到最小的情况;即 dp[i] = Min{dp[j+1]+1(i<=j<len,且str[i...j]必须是回文)}
 * 那如果判定str[i..j]是回文呢?此时有需要一个布尔二维数组,p[][],其实只用到了一般三角的位置;p[i][j]表示str[i..j]是否回文
 * 如果p[i][j]为true:有以下三种情况:
 * str[i...j]:由一个字符组成;
 * str[i...j]:由两个相同的字符组成
 * str[i...j]:str[i+1...j-1]是true&&str[i]==str[j];
 */
public class Problem_21_PalindromeMinCut {

    public static int minCut(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        int len = chas.length;
        //动态规划数组; dp[i]的含义是子串str[i...len-1]至少需要几次才能切成回文子串,dp[0]就是最后结果;
        int[] dp = new int[len + 1];

        //初始化,最后dp[len]其实是在第一次i=len-1,j=i时,使用min()中来使用的;
        //TODO:可以从len-2开始,dp[len-1]直接设置成0就可以吧;
        dp[len] = -1;

        //
        boolean[][] p = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE; //初始化值为最大值;
            for (int j = i; j < len; j++) {
                //i和j两头相等&&(j-i<2||两头内部是回文);回文必须要求两头相等==>i和j,这是必须的;
                if (chas[i] == chas[j] && (j - i < 2 || p[i + 1][j - 1])) {
                    p[i][j] = true;
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }
        return dp[0];
    }
}
