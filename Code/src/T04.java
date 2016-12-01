/**
 * Author: zhangxin
 * Time: 2016/11/14 0014.
 * Desc:请实现一个函数，将一个字符串中的空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class T04 {
    public String replaceSpace(StringBuffer str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                count++;
            }
        }

        char[] cs = new char[str.length() + count * 2];
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                cs[j++] = '%';
                cs[j++] = '2';
                cs[j++] = '0';
            } else {
                cs[j++] = c;
            }
        }
        return new String(cs);
    }

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("11 22 33 ");
        System.out.println(new T04().replaceSpace(sb));
    }
}
