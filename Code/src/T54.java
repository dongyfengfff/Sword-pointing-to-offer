/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class T54 {
    String s = "0123456789";

    public boolean isNumeric(char[] str) {
        boolean b1 = false;
        boolean b2 = false;

        if (str.length == 0) {
            return false;
        }

        int start = 0, end = str.length - 1;
        if (str[start] == '+' || str[start] == '-') {
            start++;
            if (start == end) {
                return false;
            }
        }

        for (int i = start; i <= end; i++) {
            if (str[i]=='e'||str[i]=='E'){
                if (i==start){
                    return false;
                }
                return isSimpleNumeric(str,i+1,end);
            }
            if (!isANumeric(str[i])){
                return false;
            }
        }
        return true;
    }

    boolean isSimpleNumeric(char[] str, int start, int end) {
        if (start == end) {
            return false;
        }

        //首先检查第一个是不是 + 或 -
        if (str[start] == '+' || str[start] == '-') {
            start++;
            if (start == end) {
                return false;
            }
        }

        for (int i = start; i <= end; i++) {
            if (!isANumeric(str[i])) {
                return false;
            }
        }


        return true;
    }

    boolean isANumeric(char c) {
        return s.indexOf(c) >= 0;
    }

    public static void main(String[] args) {
        T54 t = new T54();
        //System.out.println(t.isSimpleNumeric("+2344e5".toCharArray(), 0, 6));
        //System.out.println(t.isNumeric());
    }
}
