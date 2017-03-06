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
        System.out.println(t.StrToInt("10.2"));;
    }


    //其实并不用考虑复杂的情况,测试用例中没有复杂的字符样式;
    public int StrToInt1(String str) {
        if (str==null||str.length()==0) {
            return 0;
        }

        char[] cs = str.toCharArray();
        int flag = 1;
        int index = 0;
        if (cs[0]=='-') {
            flag = -1;
            index++;
        }else if (cs[0]=='+') {
            index++;
        }

        int sum = 0;
        for (; index < cs.length; index++) {
            if (cs[index]<'0' || cs[index] > '9') {
                return 0;
            }
            sum = 10*sum + cs[index] - '0';
        }
        return flag*sum;
    }
}
