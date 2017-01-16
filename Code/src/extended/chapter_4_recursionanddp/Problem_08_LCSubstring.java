package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2016/12/28 0028.
 * Desc:最长公共子串问题
 * 进阶要求:时间复杂度O(m*n),空间复杂度O(1)
 */
public class Problem_08_LCSubstring {

    public static String lcst1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1, chs2);
        int end = 0;
        int max = 0;
        for (int i = 0; i < chs1.length; i++) {
            for (int j = 0; j < chs2.length; j++) {
                if (dp[i][j] > max) {
                    end = i;
                    max = dp[i][j];
                }
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    public static int[][] getdp(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        for (int i = 0; i < str1.length; i++) {
            if (str1[i] == str2[0]) {
                dp[i][0] = 1;
            }
        }
        for (int j = 1; j < str2.length; j++) {
            if (str1[0] == str2[j]) {
                dp[0][j] = 1;
            }
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp;
    }

    public static String lcst2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int row = 0; // 斜线开始位置的行
        int col = chs2.length - 1; // 斜线开始位置的列
        int max = 0; // 记录最大长度
        int end = 0; // 最大长度更新时，记录子串的结尾位置
        while (row < chs1.length) {
            int i = row;
            int j = col;
            int len = 0;
            // 从(i,j)开始向右下方遍历
            while (i < chs1.length && j < chs2.length) {
                if (chs1[i] != chs2[j]) {
                    len = 0;
                } else {
                    len++;
                }
                // 记录最大值，以及结束字符的位置
                if (len > max) {
                    end = i;
                    max = len;
                }
                i++;
                j++;
            }
            if (col > 0) { // 斜线开始位置的列先向左移动
                col--;
            } else { // 列移动到最左之后，行向下移动
                row++;
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    public static void main(String[] args) {
        String str1 = "ABC1234567DEFG";
        String str2 = "HIJKL1234567MNOP";
//        System.out.println(lcst1(str1, str2));
//        System.out.println(lcst2(str1, str2));
        System.out.println(fun1(str1, str2));

    }

    //##########################我的方法###########################
    public static String fun1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return "";
        }

        int m = s1.length();
        int n = s2.length();

        //(1)
        int[][] dp = new int[m][n];

        //(2)
        if (s1.charAt(0) == s2.charAt(0)) {
            dp[0][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            if (s1.charAt(i) == s2.charAt(0)) {
                dp[i][0] = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            if (s2.charAt(i) == s1.charAt(0)) {
                dp[0][i] = 1;
            }
        }

        //(3)
        int max = 0;
        int indexI = 0, indexJ = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    if (max < dp[i][j]) {
                        max = dp[i][j];
                        indexI = i;
                        indexJ = j;
                    }
                }
            }
        }
        //return s1.substring(indexI + 1 - max, indexI + 1);  //或者返回这个也可以,indexI要对应s1,indexJ要对应s2;
        return s2.substring(indexJ + 1 - max, indexJ + 1);
    }

    //加速的方案:不设置dp数组,但是逻辑还和之前一样,将dp的每一斜行都遍历一次,这样时间复杂度还是 O(m*n)
    public static String fun2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return "";
        }


        return "";
    }
}


