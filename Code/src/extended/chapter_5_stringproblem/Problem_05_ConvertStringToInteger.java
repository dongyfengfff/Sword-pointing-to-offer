package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/3 0003.
 * Desc:将整数字符串转换为整数
 */
public class Problem_05_ConvertStringToInteger {
    /*
    思路:整数的最小值的绝对值比整数的最大值大1;
    所以在转换过程中一律以负数的形式出现,然后根据posi决定返回什么(也就是说负数的返回比正数的范围大1)
    思路巧妙啊
     */
    public static int convert(String str) {
        if (str == null || str.equals("")) {
            return 0; // can not convert
        }
        char[] chas = str.toCharArray();
        if (!isValid(chas)) {
            return 0; // can not convert
        }
        boolean posi = chas[0] == '-' ? false : true;
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        int res = 0;
        int cur = 0;
        for (int i = posi ? 0 : 1; i < chas.length; i++) {
            cur = '0' - chas[i];
            if ((res < minq) || (res == minq && cur < minr)) {
                return 0; // can not convert
            }
            res = res * 10 + cur;
        }
        if (posi && res == Integer.MIN_VALUE) {
            return 0; // can not convert
        }
        return posi ? -res : res;
    }

    public static boolean isValid(char[] chas) {
        if (chas[0] != '-' && (chas[0] < '0' || chas[0] > '9')) {
            return false;
        }
        if (chas[0] == '-' && (chas.length == 1 || chas[1] == '0')) {
            return false;
        }
        if (chas[0] == '0' && chas.length > 1) {
            return false;
        }
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] < '0' || chas[i] > '9') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String test1 = "2147483647"; // max in java
        System.out.println(convert(test1));
        System.out.println(convert1(test1));

        String test2 = "-2147483648"; // min in java
        System.out.println(convert(test2));
        System.out.println(convert1(test2));

        String test3 = "2147483648"; // overflow
        System.out.println(convert(test3));
        System.out.println(convert1(test3));

        String test4 = "-2147483649"; // overflow
        System.out.println(convert(test4));
        System.out.println(convert1(test4));

        String test5 = "-123";
        System.out.println(convert(test5));
        System.out.println(convert1(test5));
        System.out.println("######################");
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

    //##########################################################
    //返回0表示无法转换,但是如果有个数就是0呢???
    public static int convert1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chas = str.toCharArray();
        if (!isValid1(chas)) {
            return 0;
        }
        return 0;

    }

    public static boolean isValid1(char[] chas) {
        if (chas[0] == '-' && (chas.length == 1 || chas[1] == '0')) {
            return false;
        }
        if (chas[0] != '-' && (chas[0] < '0' || chas[0] > '9')) {
            return false;
        }

        if (chas[0] == '0' && chas.length > 1) {
            return false;
        }

        for (int i = 1; i < chas.length; i++) {
            if (chas[i] < '0' || chas[i] > '9') {
                return false;
            }
        }
        return true;
        /*String MaxInt = String.valueOf(Integer.MAX_VALUE);
        String MinInt = String.valueOf(Integer.MIN_VALUE);
        if (str.charAt(0) == '-' && str.length() > MinInt.length()) {
            return false;
        }

        if (str.charAt(0) == '-' && str.length() == MinInt.length() && str.compareTo(MinInt) > 0) {
            return false;
        }

        if (str.charAt(0) != '-' && str.length() > MaxInt.length()){
            return false;
        }
        if (str.charAt(0) != '-' && str.length() == MaxInt.length() && str.compareTo(MaxInt) > 0) {
            return false;
        }
        return true;*/
    }
}
