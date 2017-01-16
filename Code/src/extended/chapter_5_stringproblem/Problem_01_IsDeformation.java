package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/2 0002.
 * Desc:判断两个字符串是否为变形词
 */
public class Problem_01_IsDeformation {

    public static boolean isDeformation(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < chas1.length; i++) {
            map[chas1[i]]++;
        }
        for (int i = 0; i < chas2.length; i++) {
            if (map[chas2[i]]-- == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean myIsDeformation(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        int[] hash = new int[255];
        for (int i = 0; i < str1.length(); i++) {
            hash[str1.charAt(i)]++;
        }

        for (int i = 0; i < str2.length(); i++) {
            if ((--hash[str2.charAt(i)]) < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String A = "abcabcabc";
        String B = "bcacbaacb";
        System.out.println(isDeformation(A, B));
        System.out.println(myIsDeformation(A, B));

    }

}
