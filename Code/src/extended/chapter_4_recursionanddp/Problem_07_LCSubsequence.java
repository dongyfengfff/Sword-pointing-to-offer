package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2016/12/28 0028.
 * Desc:最长公共子序列问题
 * 之前已经说明过子序列和子数组的区别
 * eg:s1="A1BC2D3EFGH45I6JK7LMN",s2="12OPQ3RST4U5V6W7XYZ",那么最长公共子序列就是1234567
 * 动态规划建表过程:
 * s1长度为M,s2长度为N,建立一个M*N的数组dp,M行,N列
 * dp[i][j]的含义是:s1[0~i]和s2[0~j]的最长公共子串的长度;
 * 初始化第一行和第一列(过程类似),这里以初始化第一行为例,第一列表示的是s1的首字母和s2[0~j]的匹配长度
 * 不用说,最大就是1了,最多就是匹配上s1的首字母,遍历s2,遍历到j匹配上了,dp[0][j]=1,那么j后面的数组也全都设置为1;
 * 对于其他 i>0,j>0的位置,有三种可能的取值
 * 1:dp[i][j-1]
 * 2:dp[i-1][j]
 * 3:dp[i-1][j-1]+1
 * dp[i][j]选取以上三种可能值最大的情况;
 * 接下来通过dp[i][j]中的值来去得公共子序列,方法如下:
 */
public class Problem_07_LCSubsequence {
    public static String lcse(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1, chs2);
        int m = chs1.length - 1;
        int n = chs2.length - 1;
        char[] res = new char[dp[m][n]];
        int index = res.length - 1;
        while (index >= 0) {
            if (n > 0 && (dp[m][n] == dp[m][n - 1])) {
                n--;
            } else if (m > 0 && (dp[m][n] == dp[m - 1][n])) {
                m--;
            } else{
                res[index--] = chs1[m];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    public static int[][] getdp(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        for (int j = 1; j < str2.length; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String str1 = "A1BC2D3EFGH45I6JK7LMN";
        String str2 = "12OPQ3RST4U5V6W7XYZ";
        System.out.println(lcse(str1, str2));
        System.out.println(myLcse(str1, str2));

    }

    //#############我的实现方法#############################################
    public static String myLcse(String str1, String str2) {
        int M = str1.length();
        int N = str2.length();
        if (M * N == 0) {
            return "";
        }
        //(1)构造动态规划数组
        int[][] dp = new int[M][N];

        //(2)初始化数组的第一行和第一列
        boolean flag = false;
        for (int i = 0; i < M; i++) {
            if (flag) {
                dp[i][0] = 1;
                continue;
            }
            if (str1.charAt(i) == str2.charAt(0)) {
                dp[i][0] = 1;
                flag = true;
            }

        }

        flag = false;
        for (int i = 0; i < N; i++) {
            if (flag) {
                dp[0][i] = 1;
                continue;
            }
            if (str2.charAt(i) == str1.charAt(0)) {
                dp[0][i] = 1;
                flag = true;
            }
        }

        //对dp中的普通值赋值;
        int f1, f2, f3;
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                f1 = dp[i - 1][j];
                f2 = dp[i][j - 1];
                f3 = dp[i - 1][j - 1];
                if (str1.charAt(i) == str2.charAt(j)) {
                    f3++;
                }
                dp[i][j] = Math.max(Math.max(f1, f2), f3);
            }
        }

        char[] res = new char[dp[M - 1][N - 1]];
        int index = dp[M - 1][N - 1] - 1;
        System.out.println(index);

        //(4)根据dp数组来寻找字符串,就直接在这个方法里写吧;
        int i = M - 1;
        int j = N - 1;
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i - 1][j]) {
                i--;
            } else if (dp[i][j] == dp[i][j - 1]) {
                j--;
            } else if (dp[i][j] == dp[i - 1][j - 1] + 1) {
                res[index--] = str1.charAt(i);
                i--;
                j--;
            }

        }
        if (index == 0) {
            res[0] = i == 0 ? str1.charAt(0) : str2.charAt(0);
        }
        return String.valueOf(res);
    }

}
