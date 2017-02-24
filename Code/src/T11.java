/**
 * Author: zhangxin
 * Time: 2017/2/21 0021.
 * Desc:给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * 这个题目简单中却带着对于多种情况的讨论; exponent>=<0???
 * (⊙o⊙)…还用到了递归...
 */
public class T11 {
    public double Power(double base, int exponent) {

        if (exponent == 0) {
            return 1;
        }

        return getPower(base, exponent);
    }

    private double getPower(double base, int exponent) {

        double res = 1;

        if (exponent > 0) {
            for (int i = 0; i < exponent; i++) {
                res *= base;
            }
        } else if (exponent < 0) {
            if (base * 1 == 0) {
                throw new RuntimeException("基数不能为0");
            }
            base = 1.0 / base;
            for (int i = 0; i < -exponent; i++) {
                res *= base;
            }
        }
        return res;
    }
}
