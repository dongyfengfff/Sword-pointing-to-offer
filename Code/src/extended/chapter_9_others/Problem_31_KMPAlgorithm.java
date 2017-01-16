package extended.chapter_9_others;

/**
 * Author: zhangxin
 * Time: 2016/12/16 0016.
 * Desc:KMP算法;
 * 核心是next[]数组的计算,以及在匹配过程中如何使用next,感性上的理解是需要移动数组的,但在实际的使用中时只需要修改si与mi即可;
 */
public class Problem_31_KMPAlgorithm {
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0; //str中当前i的位置;其实真正的游标是si,你想匹配字符串的时候,主标是s掌控的;
        int mi = 0;//match中当前i的位置;
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

    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "ababa";
        /*str = "abxxxabwwab";
        match = "xab";*/
        System.out.println(getIndexOf(str, match));
        System.out.println(str.indexOf(match));
    }

}

/*
分析 match = "ababa"  => next[5]
next[i]的含义:match[0~i-1]组成的字符串,最长前缀(不包含最后一个字符)和最长后缀(不包含第一个字符)匹配上的长度;
初始化:
next[0] = -1;显然match[0~0-1]无意义,令其等于-1,代表第一个字符都匹配不上,match直接右移一位
next[1] = 0;显然match[0~1-1]就是match[0],只有一个字符,没有前缀也没有后缀;所以next[1]=0;
接下来开始匹配了,match[2]这个位置前面已经有两个字符了,可能前两个字符相等,那么next[2]=1,不相等,next[2]=0
可以发现的是,第一次匹配一定是match[i-1]和match[0]匹配;接下来如果再能匹配,因为match[i-1]和match[0]已匹配,match[i]和match[1]也能匹配了

ababa的next数组;
    0 1 2 3 4
    a b a b a
   -1 0 0 1 2

接下来拿ababcccc与ababe来匹配,ababe的next数组为{-1,0,0,1,2},和上面的ababa是一样的;
一开始是可以匹配的,当si = mi = 4 时,匹配不上了,这个时候的隐含条件就是m中的[0~mi-1]是都能匹配上的
这时候找next[mi],next[4]=2,说明mi之前的字符串中前两个和最后两个可以匹配上,所以下次比的时候,si = 4,mi = 2,从m[3]字符处开始匹配;
 */