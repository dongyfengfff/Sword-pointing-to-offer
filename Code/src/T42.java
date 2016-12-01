/**
 * Author: zhangxin
 * Time: 2016/11/24 0024.
 * Desc:
 * 1. 翻转单词顺序,hello world => world hello
 * 1. 左旋字符串, (abcde,2) => cdeab
 */
public class T42 {

    public String ReverseSentence(String str) {
        if (str.length() == 0) {
            return "";
        }
        char[] array = (str + " ").toCharArray();  //手动加个空格,防止后面数组溢出;
        //如果传进来的字符串本来末尾就是空格怎么??
        int start = 0, end = 0;
        while (end < str.length()) {
            for (; start < str.length(); start++) {
                if (array[start] != ' ') {
                    break;
                }
            }
            //此时得到的 start 就是第一个单词的头

            if (start >= str.length()) {
                break;
            }

            end = start;
            for (; end < str.length(); end++) {
                if (array[end] == ' ') {
                    break;
                }
            }
            // 此时得到的end 就是单词后的第一个空格;
            reverse(array, start, end - 1);
            start = end;
        }
        reverse(array, 0, str.length() - 1);
        str = new String(array, 0, str.length());
        return str;
    }

    void reverse(char[] array, int start, int end) {
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }

    void swap(char[] array, int start, int end) {
        char c = array[start];
        array[start] = array[end];
        array[end] = c;

    }


    public static void main(String[] args) {
        T42 t = new T42();
        //System.out.println(t.ReverseSentence(" "));
        System.out.println(t.LeftRotateString("abcdef",3));
    }

    public String LeftRotateString(String str, int n) {
        if (n <= 0 || n > str.length()) {
            return str;
        }
        char[] array = str.toCharArray();
        reverse(array,0,n-1);
        reverse(array,n,str.length()-1);
        reverse(array,0,str.length()-1);
        return new String(array);
    }
}
