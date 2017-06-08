package extended.chapter_9_others;

/**
 * Author: zhangxin
 * Time: 2017/3/23 0023.
 * Desc: 将一个整数转换为中文表达式
 */
public class Problem_21_ChineseExpression {
    //将个位数转换为中文;
    public static String num1To9(int num) {
        if (num < 1 || num > 9) {
            return "";
        }
        String[] names = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};
        return names[num - 1];
    }

    //将两位数转换为中文,有bai位的话一般是:一十九 ;没有百位的话,一般是:十九
    public static String num1To99(int num, boolean hasBai) {
        if (num < 1 || num > 99) {
            return "";
        }

        if (num < 10) {
            return num1To9(num);
        }

        //能走到这一步,说明是两位数;
        int shi = num / 10;
        if (shi == 1 && hasBai) {
            return "十" + num1To9(shi);
        } else {
            return num1To9(shi) + "十" + num1To9(num % 10);
        }
    }


    public static String num1To999(int num) {
        if (num<1||num>999){
            return "";
        }

        if (num<100){
            return num1To99(num, false);
        }

        String res = num1To9(num/100)+"百";

        int rest = num%100;
        if (rest==0){
            return res;
        }else if (rest>10){
            res += num1To99(rest,true);
        }else {
            //302这种情况
            res += "零" + num1To9(rest);
        }

        return res;
    }

    public static String num1To9999(int num) {
        if (num < 1 || num > 9999) {
            return "";
        }
        if (num < 1000) {
            return num1To999(num);
        }
        String res = num1To9(num / 1000) + "千";
        int rest = num % 1000;
        if (rest == 0) {
            return res;
        } else if (rest >= 100) {
            res += num1To999(rest);
        } else {
            res += "零" + num1To99(rest, false);
        }
        return res;
    }


    public static String num1To99999999(int num) {
        if (num < 1 || num > 99999999) {
            return "";
        }
        int wan = num / 10000;
        int rest = num % 10000;
        if (wan == 0) {
            return num1To9999(rest);
        }
        String res = num1To9999(wan) + "万";
        if (rest == 0) {
            return res;
        } else {
            if (rest < 1000) {
                return res + "零" + num1To999(rest);
            } else {
                return res + num1To9999(rest);
            }
        }
    }

    //最终的方法:
    public static String getNumChiExp(int num) {
        if (num == 0) {
            return "零";
        }
        String res = num < 0 ? "负" : "";
        int yi = Math.abs(num / 100000000);
        int rest = Math.abs((num % 100000000));
        if (yi == 0) {
            return res + num1To99999999(rest);
        }
        res += num1To9999(yi) + "亿";
        if (rest == 0) {
            return res;
        } else {
            if (rest < 10000000) {
                return res + "零" + num1To99999999(rest);
            } else {
                return res + num1To99999999(rest);
            }
        }
    }


}
