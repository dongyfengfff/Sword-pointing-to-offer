package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/14 0014.
 * Desc:找到被指定的新类型的字符串
 * 长度是1或者2;
 * 单个小写字母:
 * 大写+小写:
 * 大写+大写:
 *
 * eaCCBi=>e a CC Bi
 *
 * 给定k表示str中新类型的第k个位置,返回k位置的新类型,对k的理解有误差;
 *
 *
 *
 */
public class Problem_19_FindNewTypeChar {
    public static String pointNewchar(String s, int k) {
        if (s == null || s.equals("") || k < 0 || k >= s.length()) {
            return "";
        }
        char[] chas = s.toCharArray();
        int uNum = 0;
        //统计k-1开始向左大写字符的个数 uNum,但是遇到小写字母要立即停止
        for (int i = k - 1; i >= 0; i--) {
            if (!isUpper(chas[i])) {
                break;
            }
            uNum++;
        }

        //uNum是奇数:返回 str[k-1,k]
        if ((uNum & 1) == 1) {
            return s.substring(k - 1, k + 1);
        }

        //uNum是偶数

        // chas[k]是大写:返回str[k,k+1]
        if (isUpper(chas[k])) {
            return s.substring(k, k + 2);
        }

        //chas[k]是小写,返回str[k]
        return String.valueOf(chas[k]);
    }

    public static boolean isUpper(char ch) {
        return !(ch < 'A' || ch > 'Z');
    }

    public static void main(String[] args) {
        String str = "aaABCDEcBCg";
       /* System.out.println(pointNewchar(str, 7));
        System.out.println(pointNewchar(str, 4));
        System.out.println(pointNewchar(str, 10));
*/
        System.out.println(pointNewchar(str, 0));
        System.out.println(pointNewchar(str, 1));
        System.out.println(pointNewchar(str, 2));
        System.out.println(pointNewchar(str, 3));
        System.out.println(pointNewchar(str, 4));
        System.out.println(pointNewchar(str, 5));
        System.out.println(pointNewchar(str, 6));
        System.out.println(pointNewchar(str, 7));

    }
}
