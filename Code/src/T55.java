/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:字符流中第一个不重复的数字
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，
 * 第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 */
public class T55 {
    int[] hash = new int[256];
    StringBuffer s = new StringBuffer();

    public void Insert(char ch) {
        s.append(ch);
        hash[ch]++;
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        for (int i = 0; i < s.length(); i++) {
            if (hash[s.charAt(i)] == 1) {
                return s.charAt(i);
            }
        }
        return '#';
    }
}
