package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/2 0002.
 * Desc:给定一个字符串,求其字符串中全部的数字的和,出现-,则视为负数,--则视为正数,连续的数字为一个多位数,a23e,那么就是23;
 */
public class Problem_02_AllNumbersSum {
    public static int numSum(String str) {
        if (str == null) {
            return 0;
        }
        char[] charArr = str.toCharArray();
        int res = 0;        //sum的结果
        int num = 0;        //得到的单个数据
        boolean posi = true; //是否是正数
        int cur = 0;        //当前字符表示的数

        for (int i = 0; i < charArr.length; i++) {
            cur = charArr[i] - '0';
            if (cur < 0 || cur > 9) {
                //表明当前不是数字,把上次的得到的num相加,并初始化为0,准备收集下一个数据
                res += num;
                num = 0;
                //这里在判断一下是不是 '-' ,不断的改变posi的boolean值,两个就成正了;
                if (charArr[i] == '-') {
                    //判断前一个字符是不是 '-'
                    if (i - 1 > -1 && charArr[i - 1] == '-') {
                        posi = !posi;
                    } else {
                        posi = false;
                    }
                } else {
                    posi = true;
                }
            } else {
                //表明是数字; *10???  其实主要解决的问题是: --100的问题;此时num=0,所以*10也没有什么作用;再到后面就posi的判断就用不到了
                num = num * 10 + (posi ? cur : -cur);
            }
        }
        res += num;
        return res;
    }

    public static int myNumSum(String str) {
        if (str == null) {
            return 0;
        }
        int sum = 0;
        int num = 0;
        boolean flag = true;
        for (int i = 0; i < str.length(); i++) {
            int cur = str.charAt(i) - '0';
            if (cur < 0 || cur > 9) {
                sum += num;
                num = 0;

                if (str.charAt(i) == '-') {
                    if (i - 1 >= 0 && str.charAt(i - 1) == '-') {
                        flag = !flag;
                    } else {
                        flag = false;
                    }
                } else {
                    flag = true;
                }
            } else {
                num = 10 * num + (flag ? cur : -cur);
            }
        }
        sum += num;
        return sum;
    }

    public static void main(String[] args) {
        String test = "1K-100ABC500D-T--100F200G!!100H---300";
        System.out.println(numSum(test));
        System.out.println(myNumSum(test));

    }
}
