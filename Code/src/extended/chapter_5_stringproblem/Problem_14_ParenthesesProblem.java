package extended.chapter_5_stringproblem;

import java.util.Stack;

/**
 * Author: zhangxin
 * Time: 2017/1/11 0011.
 * Desc:给定一个字符串,判断是不是整体有效的括号字符
 * 进阶:给定一个字符串,返回其最长括号子字符串
 */
public class Problem_14_ParenthesesProblem {
    //左老师是使用了一个计数的方法来模拟stack;
    public static boolean isValid(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push('(');
            } else if (str.charAt(i) == ')') {
                //可能会遇到先出现了一个),这样的话,pop出来的就是null;
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }

        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

    /*
    使用动态规划的思想

     */
    public static int maxLength(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        //构建动态规划数组;dp[i]代表的是在str[0...i]中,以str[i]结尾的最长的括号字符串的长度;
        //如果str[i] = '(',那么dp[i]肯定是0;不解释,直接跳过;
        //如果str[i] = ')',如果str[i-1]是'(',那么dp[i]=2;如果str[i-1]是')',首先拿到dp[i-1]=m,说明人家前面能组成一个,那你就要看一下str[i-1 -m]是不是'(',这样的话dp[i]=m+2;
        int[] dp = new int[chas.length];
        int pre = 0;
        int res = 0;
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chas[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    public static void main(String[] args) {
        String str1 = ")((())())";
        System.out.println(isValid(str1));
        System.out.println(maxLength(str1));

        String str2 = "(())(()(()))";
        System.out.println(isValid(str2));
        System.out.println(maxLength(str2));

        String str3 = "()(()()(";
        System.out.println(isValid(str3));
        System.out.println(maxLength(str3));

    }

}
