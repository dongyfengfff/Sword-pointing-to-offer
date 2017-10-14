package LintCode.dp;

/**
 * Author: zhangxin
 * Time: 2017/9/8 0008.
 * Desc:
 */
public class longestCommonSubsequence {
    /*
 * @param A: A string
 * @param B: A string
 * @return: The length of longest common subsequence of A and B
 */
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        if (A == null || B == null) {
            return 0;
        }

        int len1 = A.length();
        int len2 = B.length();
        if (len1 * len2 == 0) {
            return 0;
        }

        int[][] dp = new int[len1][len2];

        char a = A.charAt(0);
        char b = B.charAt(0);

        int index1 = A.indexOf(b);
        if (index1 > -1) {
            for (int i = index1; i < len1; i++) {
                dp[i][0] = 1;
            }
        }

        int index2 = B.indexOf(a);
        if (index2 > -1) {
            for (int i = index2; i < len2; i++) {
                dp[0][i] = 1;
            }
        }

        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (A.charAt(i) == B.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }

       /* for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }*/
        return dp[len1 - 1][len2 - 1];
    }

    public static void main(String[] args) {
        new longestCommonSubsequence().longestCommonSubsequence("bedaacbade", "dccaeedbeb");
    }
}
