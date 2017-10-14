package LintCode.dp;

/**
 * Author: zhangxin
 * Time: 2017/9/9 0009.
 * Desc:给出两个单词word1和word2，计算出将word1 转换为word2的最少操作次数。
 * <p>
 * 你总共三种操作方法：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 */
public class minDistance {
    /**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }


        // write your code here
        int len1 = word1.length();
        int len2 = word2.length();

        if (len1 == 0) {
            return len2;
        }

        if (len2 == 0) {
            return len1;
        }


        //dp[i][j]代表将word1的前i个字符转为word2的前j个字符的代价
        int[][] dp = new int[len1][len2];
        //初始化第一行，第一列
        if (word1.charAt(0) == word2.charAt(0)) {
            dp[0][0] = 0;
        } else {
            dp[0][0] = 1;
        }

        //初始化行
        for (int i = 1; i < len2; i++) {
            if (word1.charAt(0) == word2.charAt(i)) {
                dp[0][i] = i;
            } else {
                dp[0][i] = dp[0][i - 1] + 1;
            }

        }

        //初始化列
        for (int i = 1; i < len1; i++) {
            if (word2.charAt(0) == word1.charAt(i)) {
                dp[i][0] = i;
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
            }
        }

        // dp[i][j] 的选择
        // 如果 i 和 j 相等，res = dp[i-1][j-1]
        // dp[i-1][j] + 1  dp[i][j-1] + 1
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;  // 替换
                }

                int res = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                dp[i][j] = Math.min(dp[i][j], res);
            }
        }

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[len1 - 1][len2 - 1];
    }

    public static void main(String[] args) {
        new minDistance().minDistance("park", "spake");
    }
}
