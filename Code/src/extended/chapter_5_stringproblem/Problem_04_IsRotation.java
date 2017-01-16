package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/2 0002.
 * Desc:两个字符串a,b是否互为旋转词
 * 解决思路是:生成字符串aa = a+a,然后判断aa中是否包含b
 */
public class Problem_04_IsRotation {
    public static boolean isRotation(String a, String b) {
        if (a == null || b == null || a.length() == 0 || b.length() == 0) {
            return false;
        }

        String aa = a + a;
        // TODO: 2017/1/3 0003 正规的解法应该是使用KMP算法 
        return aa.contains(b);
    }
}
