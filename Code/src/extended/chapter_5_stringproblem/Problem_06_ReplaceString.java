package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/3 0003.
 * Desc:将给定字符串str中的from替换成to,如果是连续多个from连接的,只要替换成一个to即可
 * 思路1:像本章中的问题3那样,拷贝字符;
 * 思路2:使用KMP算法寻找匹配的字符串;
 * 思路3:正则表达式...
 * TODO: 2017/1/3 0003  未完成
 */
public class Problem_06_ReplaceString {
    public static void main(String[] args) {
        String str = "abc1abcabc1234abcabcabc5678";
        String from = "abc";
        String to = "XXXXX";
        System.out.println(replace(str, from, to));

        str = "abc";
        from = "123";
        to = "X";
        System.out.println(replace(str, from, to));

    }

    //思路1:

    //#######################################################
    //思路2:改造KMP,找到字符后替换成to
    public static String replace(String str, String from, String to) {
        if (str == null || from == null || str.equals("") || from.equals("")) {
            return str;
        }

        char[] chas = str.toCharArray();
        int index = 0;
        while ((index = getIndexOf(index, str, from)) != -1) {
            for (int i = index; i < index + from.length(); i++) {
                chas[i] = '0';
            }
            index += from.length();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] != '0') {
                sb.append(chas[i]);
            } else if (i == 0 || chas[i - 1] != '0') {
                sb.append(to);
            }
        }

        return sb.toString();
    }


    public static int getIndexOf(int index, String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0; //str中当前i的位置;其实真正的游标是si,你想匹配字符串的时候,主标是s掌控的;
        int mi = 0;//match中当前i的位置;
        si = index;
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                //当前字符能匹配上,si,mi都前移一位;
                si++;
                mi++;
            } else if (next[mi] == -1) {
                //匹配不上,mi=0,第一个字符都匹配不上,si前移一位;
                si++;
            } else {
                //前面还是有部分能匹配上的,mi=next[mi];si不变;
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    /***
     * 获取nextArr数组
     * nextArr[0] = -1;因为如果第一个字符都匹配不上,那么match整体后移1位;
     * nextArr[1] = 0;因为第一个字符没有前缀也没有后缀,肯定是0
     * @param ms match字符串对应的字符数组
     * @return
     */
    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2; //当前位置;
        int cn = 0; //前缀开始匹配的位置;
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                //遇到某个字符匹配不上了,那么直接下次不能再用之前的了,而是将cn置0
                // next先不设置,cn==0后,再去进入循环,从头匹配;也许能匹配的上;
                //cn = next[cn]; 不看这一句,删掉;
                cn = 0;
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }
}
