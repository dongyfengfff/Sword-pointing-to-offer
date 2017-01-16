package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/3 0003.
 * Desc:统计字符串中每个字符的次数,仔细看eg:"aaabbadddffc"=>"a_3_b_2_a_1_d_3_f_2_c_1"
 * 进阶:给出"a_1_b_100",并给出index=50,得到原Str中index位的字符
 */
public class Problem_07_ConvertStringToCount {
    public static void main(String[] args) {
        String s = "a_1_b_100";
        String[] ss = s.split("_");
        for (String s1 : ss) {
            System.out.println(s1);
        }
    }
}
