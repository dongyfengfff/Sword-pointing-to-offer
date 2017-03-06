/**
 * Author: zhangxin
 * Time: 2016/11/25 0025.
 * Desc: 不用 + - * / 做加法
 * NOTE:位操作的经典题目,需熟练掌握;
 *
 * 规律是:首先不考虑进位,用异或算一波值(a^b),然后值考虑进位算一波值(a&b <<1),然后将这两个值相加,又变成了求两个数的和的问题
 * 那么就一直循环,当进位为0的时候,返回a即可;
 */
public class T47 {
    //使用位运算,一位一位的加;
    //运行超时:您的程序未能在规定时间内运行结束，请检查是否循环有错或算法复杂度过大。
    public int Add(int num1, int num2) {
        boolean add = false;
        int res = 0;
        int i = 0;
        while (num1 != 0 && num2 != 0) {
            int b1 = num1 & 1;
            int b2 = num2 & 1;
            int b3 = 0;
            if (b1 == 1 && b2 == 1) {
                if (add) {
                    b3 = 1;
                } else {
                    b3 = 0;
                }
                add = true;
            } else if ((b1 | b2) == 1) {
                if (add) {
                    b3 = 0;
                    add = true;
                } else {
                    b3 = 1;
                    add = false;
                }
            } else {
                if (add) {
                    b3 = 1;
                } else {
                    b3 = 0;
                }
                add = false;
            }

            if (b3 != 0) {
                b3 = b3 << i;
                res = res | b3;
            }
            i++;

            num1 = num1 >> 1;
            num2 = num2 >> 1;
        }
        int num3 = Math.max(num1, num2);
        while (num3 != 0) {
            int b1 = num3 & 1;
            int b2 = 0;
            if (b1 == 1 && add) {
                add = true;
                b2 = 0;
            } else if (add || b1 == 1) {
                b2 = 1;
                add = false;
            }

            if (b2 != 0) {
                b2 = b2 << i;
                res = res | b2;
            }

            i++;
            num3 = num3 >> 1;
        }
        if (add) {
            res = res | 1 << i;
        }
        return res;
    }

    /*
    首先看十进制是如何做的： 5+7=12，三步走
    第一步：相加各位的值，不算进位，得到2。
    第二步：计算进位值，得到10. 如果这一步的进位值为0，那么第一步得到的值就是最终结果。

    第三步：重复上述两步，只是相加的值变成上述两步的得到的结果2和10，得到12。

    同样我们可以用三步走的方式计算二进制值相加： 5-101，7-111 第一步：相加各位的值，不算进位，得到010，二进制每位相加就相当于各位做异或操作，101^111。

    第二步：计算进位值，得到1010，相当于各位做与操作得到101，再向左移一位得到1010，(101&111)<<1。

    第三步重复上述两步， 各位相加 010^1010=1000，进位值为100=(010&1010)<<1。
         继续重复上述两步：1000^100 = 1100，进位值为0，跳出循环，1100为最终结果。
    13+11 = ？;
    13 的二进制      1 1 0 1                     -----a        13
    11 的二进制      1 0 1 1                     -----b        11

     (a&b) <<1  ->   1 0 0 1 0                         -----d         18
              a^b  ->     0 1 1 0                   -----e          6

     (d&e) <<1  ->   0 0 1 0 0                       ------f         4
              d^e  ->  1 0 1 0 0                  -----g        20

     (f&g) <<1  ->   0 1 0 0 0                       ------h        8
              f^g  ->  1 0 0 0 0                   ------i           16

     (h&i) <<1  ->   0 0 0 0 0                       ------h        0       ---- --------退出循环
          h^i  ->  1 1 0 0 0                  ------i           24
    */

    public int Add1(int num1, int num2) {

        int num3, num4;
        while (num1 != 0) {  //进位值为0(没有进位),返回各位相加的值,退出循环;
            System.out.println(num1 + "<>" + num2);
            num3 = (num1 & num2) << 1;  //进位值
            num4 = num1 ^ num2;         //相加各位的值，不算进位
            num1 = num3;
            num2 = num4;
        }
        return num2;
    }

    public static void main(String[] args) {
        T47 t = new T47();
        System.out.println(t.Add1(12, 11));
    }
}
