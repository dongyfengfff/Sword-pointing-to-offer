package LintCode.dp;

import java.util.Arrays;
import java.util.SortedMap;

/**
 * Author: zhangxin
 * Time: 2017/9/9 0009.
 * Desc:
 */
public class numDecodings {
    /*
 * @param s: a string,  encoded message
 * @return: an integer, the number of ways decoding
 */
    public int numDecodings(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.startsWith("0")){
            return 0;
        }
        char[] cs = s.toCharArray();
        int[] dp = new int[cs.length + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {

            int res = isA_Z(cs[i - 2], cs[i - 1]);
            if (res == 0) {
                dp[s.length()] = 0;
                break;
            } else if (res < 10) {
                dp[i] = dp[i - 1];
            } else if (res == 10 || res == 20) {
                dp[i] = dp[i - 2];
            } else if (res <= 26) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                if (res %10 == 0){
                    dp[s.length()] = 0;
                    break;
                }
                dp[i] = dp[i - 1];
            }
        }
        System.out.println(Arrays.toString(dp));

        return dp[s.length()];
    }

    private int isA_Z(char c1, char c2) {
        return (c1 - '0') * 10 + c2 - '0';
    }

    public static void main(String[] args) {
        System.out.println(new numDecodings().numDecodings("10"));
    }
}
