package extended.chapter_7_bitoperation;

/**
 * Author: zhangxin
 * Time: 2017/1/21
 * Desc:不用任何比较,找出两个数中较大的数
 */
public class Problem_02_GetMax {
    //如果n为0,返回1;如果n不为0,返回0;
    public static int flip(int n) {
        return n ^ 1;
    }

    //返回n的符号,>=0,返回1,<0返回0;
    public static int sign(int n) {
        return flip((n >> 31) & 1);
    }
    public static int getMax2(int a, int b) {
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb;
        int sameSab = flip(difSab);
        int returnA = difSab * sa + sameSab * sc;
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }
}
