/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc: 把字符串转为整数
 */
public class T49 {
    public int StrToInt(String str) {

        try {
            int i = Integer.parseInt(str);
            return i;
        } catch (NumberFormatException e) {
           return 0;
        }
    }

    public static void main(String[] args) {
        T49 t = new T49();
        System.out.println(t.StrToInt("10e2"));;
    }
}
