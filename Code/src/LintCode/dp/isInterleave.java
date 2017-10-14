package LintCode.dp;

/**
 * Author: zhangxin
 * Time: 2017/9/8 0008.
 * Desc:
 */
public class isInterleave {

    public boolean isInterleave(String s1, String s2, String s3) {
        // write your code here
        if (s1.length() == 0 && s2.length() == 0 && s3.length() == 0) {
            return true;
        }

        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return isInterleaveCore(s1, 0, s2, 0, s3, 0);
    }

    private boolean isInterleaveCore(String s1, int index1, String s2, int index2, String s3, int index3) {
        if (index3 == s3.length()) {
            return true;
        }
        char c3 = s3.charAt(index3);
        if (index1 < s1.length() && index2 < s2.length() && c3 == s1.charAt(index1) && c3 == s2.charAt(index2)) {
            return isInterleaveCore(s1, index1 + 1, s2, index2, s3, index3 + 1) ||
                    isInterleaveCore(s1, index1, s2, index2 + 1, s3, index3 + 1);
        }

        if (index1 < s1.length() && c3 == s1.charAt(index1)) {
            return isInterleaveCore(s1, index1 + 1, s2, index2, s3, index3 + 1);
        }

        if (index2 < s2.length() && c3 == s2.charAt(index2)) {
            return isInterleaveCore(s1, index1, s2, index2 + 1, s3, index3 + 1);
        }

        return false;
    }

    public static void main(String[] args) {
        new isInterleave().isInterleave("a", "", "a");
    }
}
