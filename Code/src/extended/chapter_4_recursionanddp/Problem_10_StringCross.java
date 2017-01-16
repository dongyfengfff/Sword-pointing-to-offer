package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2016/12/29 0029.
 * Desc:字符串的交错组成,给定三个字符串s1,s2,aim,判定aim中是否仅包含s1,和s2中的所有字符,并且相对顺序一样
 */
public class Problem_10_StringCross {
    //我的思路,不使用动态规划的方法;
    public static boolean isCross0(String str1, String str2, String aim) {

        if (str1 == null || str2 == null || aim == null) {
            return false;
        }

        int len = aim.length();
        int len1 = str1.length();
        int len2 = str2.length();
        if (len != len1 + len2) {
            return false;
        }

        int index = 0, index1 = 0, index2 = 0;
        while (index < len) {
            if (index1 < len1 && aim.charAt(index) == str1.charAt(index1)) {
                index1++;
            } else if (index2 < len2 && aim.charAt(index) == str2.charAt(index2)) {
                index2++;
            } else {
                return false;
            }
            index++;
        }
        return true;
    }

    public static boolean isCross1(String str1, String str2, String aim) {
        if (str1 == null || str2 == null || aim == null) {
            return false;
        }
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        char[] chaim = aim.toCharArray();
        if (chaim.length != ch1.length + ch2.length) {
            return false;
        }

        //生成数组 m = s1.length+1,  n = s2.length+1; 为什么要+1?给每个单个字符串一种可能;
        //dp[i][j]的含义是:aim[0~i+j-1]是否能被s1[0~i]和s2[0~j]组成?
        boolean[][] dp = new boolean[ch1.length + 1][ch2.length + 1];
        dp[0][0] = true;

        //初始化第一列
        for (int i = 1; i <= ch1.length; i++) {
            if (ch1[i - 1] != chaim[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }

        //初始化第一行;
        for (int j = 1; j <= ch2.length; j++) {
            if (ch2[j - 1] != chaim[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }


        for (int i = 1; i <= ch1.length; i++) {
            for (int j = 1; j <= ch2.length; j++) {
                if ((ch1[i - 1] == chaim[i + j - 1] && dp[i - 1][j])
                        || (ch2[j - 1] == chaim[i + j - 1] && dp[i][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[ch1.length][ch2.length];
    }

    public static boolean isCross2(String str1, String str2, String aim) {
        if (str1 == null || str2 == null || aim == null) {
            return false;
        }

        int len = aim.length();
        int len1 = str1.length();
        int len2 = str2.length();
        if (len != len1 + len2) {
            return false;
        }

        boolean[] dp = new boolean[len2 + 1];
        dp[0] = true;

        //初始化dp数组
        for (int i = 1; i < len2 + 1; i++) {
            if (aim.charAt(i - 1) == str2.charAt(i - 1)) {
                dp[i] = true;
            } else {
                break;
            }
        }


        for (int i = 1; i <= len1; i++) {
            boolean pre = dp[0];
            if (pre && str1.charAt(i-1) == aim.charAt(i-1)) {
                dp[0] = true;
            }
            for (int j = 1; j <= len2; j++) {
                if (dp[j - 1] && aim.charAt(i + j - 1) == str2.charAt(j-1)
                        || dp[j] && aim.charAt(i + j - 1) == str1.charAt(i-1)) {
                    dp[j] = true;
                }
            }
        }
        return dp[len2];
    }

    public static void main(String[] args) {
        String str1 = "1234";
        String str2 = "abcd";
        String aim = "1a23bcd4";
        System.out.println(isCross1(str1, str2, aim));
        System.out.println(isCross2(str1, str2, aim));
        System.out.println(isCross0(str1, str2, aim));
    }
}
