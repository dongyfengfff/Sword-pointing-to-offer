/**
 * Author: zhangxin
 * Time: 2016/11/23 0023.
 * Desc:返回从 1~n中,所有位上1出现的次数;
 */
public class T32 {
    // 所有数字中个位上显示为1,十位上显示为1,百位上显示为1;
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        int a, b;
        for (int i = 1; i <= n; i *= 10) {
            a = n / i;
            b = n % i;
            count += (a + 8) / 10 * i + (a % 10 == 1 ? b + 1 : 0);
        }
        return count;
    }

    public static void main(String[] args) {
        T32 t = new T32();
        System.out.println(t.NumberOf1Between1AndN_Solution(5));
    }
}
