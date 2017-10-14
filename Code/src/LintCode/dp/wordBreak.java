package LintCode.dp;

import java.util.Set;

/**
 * Author: zhangxin
 * Time: 2017/9/9 0009.
 * Desc:给出一个字符串s和一个词典，判断字符串s是否可以被空格切分成一个或多个出现在字典中的单词。
 */
public class wordBreak {
    /**
     * @param s:    A string s
     * @param dict: A dictionary of words dict
     */
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null && dict == null) return true;
        if (s == null || dict == null) return false;
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        //整体思路就是遍历s字符串
        for (int i = 0; i < s.length(); i++) {
            //拿到字典中的每个子串，判断是否在s中；使用subString；
            for (String str : dict) {
//核心在if的判断中：
//dp[i] = true,可以保证这是一个起点，前面一个单词匹配了；
//                str.length()<s.length(),保证不越界；
                if (dp[i] && i + str.length() <= s.length() && s.substring(i, i + str.length()).equals(str)) {
                    dp[i + str.length()] = true;
                }
            }
        }
        return dp[s.length()];
    }
}
