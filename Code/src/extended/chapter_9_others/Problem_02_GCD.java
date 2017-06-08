package extended.chapter_9_others;

/**
 * Author: zhangxin
 * Time: 2017/3/20 0020.
 * Desc:一行代码求最大公约数
 * 关于该问题的一个拓展:求1~N之间的质数的数量
 */
public class Problem_02_GCD {
    //求m和n的最大公约数,欧几里得算法(辗转相除算法)
    public static int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    public static void main(String[] args) {

        System.out.println(gcd(18, 27));

    }
}
