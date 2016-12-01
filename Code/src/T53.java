/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:正则表达式匹配
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class T53 {
    //1. 注意是哪 str 和 pattern 比较,不要搞反了
    public boolean match1(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        int index1 = 0;
        int index2 = 0;

        return matchCore1(str, index1, pattern, index2);
    }

    private boolean matchCore1(char[] str, int index1, char[] patter, int index2) {
        //都匹配到了末尾
        if (index1 >= str.length && index2 >= patter.length) {
            return true;
        } else if (index1 != str.length && index2 == patter.length) {
            //patter 先到了结尾,匹配失败,可能会出现这种情况:  str = ""; patter = ".*"
            return false;
        }

        // 遇到了 a* 类型的问题;要么匹配,要么patter跳过两个字节;
        if (index2 + 1 < patter.length && patter[index2 + 1] == '*') {
            // 正好 str[index1] = patter[index2] 或者 patter[index2] = '.'
            if ((index1 < str.length && str[index1] == patter[index2])
                    || (index1 < str.length && patter[index2] == '.')) {
                return matchCore1(str, index1, patter, index2 + 2)     //当做什么也没匹配;
                        || matchCore1(str, index1 + 1, patter, index2 + 2) //匹配了一个字符,就跳过了
                        || matchCore1(str, index1 + 1, patter, index2 );// 匹配了一个,继续匹配
            } else {
                // a*不能匹配,patter跳过两个字节
                return matchCore1(str, index1, patter, index2 + 2);
            }
        }

        // 没有遇到 a* 的形式,老老实实匹配吧
        if (index1 < str.length && str[index1] == patter[index2]
                || index1 < str.length && patter[index2] == '.') {
            return matchCore1(str, index1 + 1, patter, index2 + 1);
        }

        return false;
    }

    //################################################################################
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        int strIndex = 0;
        int patternIndex = 0;
        return matchCore(str, strIndex, pattern, patternIndex);
    }

    public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
        //有效性检验：str到尾，pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        //pattern先到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }
        //模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])
                    || (pattern[patternIndex] == '.' && strIndex != str.length)) {
                return matchCore(str, strIndex, pattern, patternIndex + 2)//模式后移2，视为x*匹配0个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2)//视为模式匹配1个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex);//*匹配1个，再匹配str中的下一个
            } else {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
        }
        //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
        if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])
                || strIndex != str.length && (pattern[patternIndex] == '.')) {
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        }
        return false;
    }

    public static void main(String[] args) {
        T53 t = new T53();
        // "",".*"  "","."
        System.out.println(t.match1("aa".toCharArray(), "a*".toCharArray()));
    }
}
