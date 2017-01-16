package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2017/1/1 0001.
 * Desc:
 * TODO:没看懂
 */
public class Problem_13_ExpressionNumber {

    //首先判断一下传入的字符串是否合法,这里传进来的是不包含( )的,需要我们自己添加括号
    public static boolean isValid(char[] exp) {
        //长度必须是奇数
        if ((exp.length & 1) == 0) {
            return false;
        }
        //偶数位上必须是0或者1;
        for (int i = 0; i < exp.length; i = i + 2) {
            if ((exp[i] != '1') && (exp[i] != '0')) {
                return false;
            }
        }

        //奇数位上必须是 & | ^ 中的一种;
        for (int i = 1; i < exp.length; i = i + 2) {
            if ((exp[i] != '&') && (exp[i] != '|') && (exp[i] != '^')) {
                return false;
            }
        }
        return true;
    }


    public static int num2(String express, boolean desired) {
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] exp = express.toCharArray();
        if (!isValid(exp)) {
            return 0;
        }

        //生成两个动态规划的数组
        int[][] t = new int[exp.length][exp.length];  //t[i][j]表示的是exp[i...j]组成true的种数
        int[][] f = new int[exp.length][exp.length]; //f[i][j]表示的是exp[i...j]组成false的种数

        //初始化;
        t[0][0] = exp[0] == '0' ? 0 : 1;
        f[0][0] = exp[0] == '1' ? 0 : 1;


        for (int i = 2; i < exp.length; i += 2) {
            //初始化自己,下面还会进行累加的,这里只是初始化;
            t[i][i] = exp[i] == '0' ? 0 : 1;
            f[i][i] = exp[i] == '1' ? 0 : 1;

            //从i-2开始,拿到上一个数字,再拿上一个数字...直到开始;
            for (int j = i - 2; j >= 0; j -= 2) {
                //从j开始,
                for (int k = j; k < i; k += 2) {
                    if (exp[k + 1] == '&') {
                        t[j][i] += t[j][k] * t[k + 2][i];
                        f[j][i] += (f[j][k] + t[j][k]) * f[k + 2][i] + f[j][k] * t[k + 2][i];
                    } else if (exp[k + 1] == '|') {
                        t[j][i] += (f[j][k] + t[j][k]) * t[k + 2][i] + t[j][k] * f[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i];
                    } else {
                        t[j][i] += f[j][k] * t[k + 2][i] + t[j][k] * f[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i] + t[j][k] * t[k + 2][i];
                    }
                }
            }
        }
        return desired ? t[0][t.length - 1] : f[0][f.length - 1];
    }

    public static void main(String[] args) {
        String express = "1^0&0|1&1^0&0^1|0|1&1";
        boolean desired = true;
//        System.out.println(num1(express, desired));
        System.out.println(num2(express, desired));

    }
}
