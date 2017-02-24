package extended.chapter_7_bitoperation;

/**
 * Author: zhangxin
 * Time: 2017/1/21
 * Desc:
 */
public class Problem_01_SwapWithoutTmp {

    public static void main(String[] args) {
        int a = 3, b = 4;
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println(a);
        System.out.println(b);


        //###############使用位运算之异或操作符####################
        a = 3;
        b = 4;
        a = a ^ b; //此时a就是之前a与b中所有对应位不同的位组成的数,我们还是把它当一个二进制的数看比较方便
        b = a ^ b;//此时b就是a ^ b ^ b,所以就是a
        a = a ^ b;//此时a就是a ^ b ^ a,所以就是b;
        System.out.println(a);
        System.out.println(b);
    }
}
