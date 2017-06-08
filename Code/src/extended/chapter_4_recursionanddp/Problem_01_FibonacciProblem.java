package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2016/12/22 0022.
 * Desc:
 * 要求:
 * 1. 矩阵的乘法代码会写;
 * 2. 乘方的加速运算会写;
 * 3. 如果根据递推找出矩阵会算;
 */
public class Problem_01_FibonacciProblem {
    //斐波那契数列的递归写法,看着好,但效率并不高,在n很大时会溢出;
    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }


    //斐波那契数列的遍历写法,也不难,而且效率,空间占用量都很好,推荐使用这种方法;
    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        int pre = 1; //代表 f(n-2)
        int res = 1; //代表 f(n-1)
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    public static int f22(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        //用一个数组岂不是更好;
        int[] tmp = {1, 1};
        int res = 0;
        for (int i = 3; i <= n; i++) {
            res = tmp[0] + tmp[1];
            tmp[i % 2] = res;
        }
        return res;
    }


    //使用矩阵乘法计算斐波那契数列的第n项;
    public static int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        //此时res只是对矩阵计算了乘方,还没有和响亮[1,1]相乘,成了之后第一个元素就是F(n),于是返回下面的结果就是;
        return res[0][0] + res[1][0];
    }

    //如果涉及到矩阵的p次方乘的话,那么该矩阵的行和列一定相等;
    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        // 先把res设为单位矩阵，相等于整数中的1。
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = m;
        //这一步是在拆分p,为什么拆分,参考解析中整数的乘法的p次方的快速乘积
        for (; p != 0; p >>= 1) { //更新的是将p右移1位
            if ((p & 1) != 0) {
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);//这一步的作用是每次不管其末位是不是1,都平方一次,供下次使用;
        }
        return res;
    }

    //要知道是m1的行和m2的列相乘,所以m1的行和m2的列的元素个数必定相等,这里遍历的是m2的行,因为第二层遍历的是m2;
    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length]; //res[i][j],i = m1.length,j = m2[0].length
        for (int i = 0; i < m1.length; i++) { //最外层,m1的行数
            for (int j = 0; j < m2[0].length; j++) { //第二层,m2的列数
                for (int k = 0; k < m2.length; k++) {  //第三层,m2的行数
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    // TODO: 2016/12/22 0022  这是自己写的一个整数的快速乘方
    private static double integerPower(int a, int p) {
        boolean flag = false;
        if (p < 0) {
            flag = true;
            p *= -1;
        }
        double res = 1;
        int temp = a;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = res * temp;
            }
            temp *= temp;
        }

        if (flag) {
            res = 1.0 / res;
        }
        return res;
    }


    //##########S系列,解决上楼梯问题#################
    public static int s1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return s1(n - 1) + s1(n - 2);
    }

    public static int s2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int res = 2;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    public static int s3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }


    //###############C系列,解决牛的数量问题###################

    //递归,效率不好,不推荐使用
    public static int c1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    //非递归的方法,效率好,推荐使用c22
    public static int c2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    public static int c22(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 0;
        //note:初始化的顺序一定要弄对,因为f(1) = 1,f(2)=2,f(3)=3,按照%3放入数组,初始化就应该是下面这样;
        int[] tmp = {3, 1, 2};
        for (int i = 4; i <= n; i++) {
            res = tmp[(i - 1) % 3] + tmp[(i - 3) % 3];
            tmp[(i) % 3] = res;
        }
        return res;
    }

    //思路错误,这是个三阶的,不要弄成4维数组;
    public static int c222(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 0;
        int[] tmp = {0, 1, 2,3};
        for (int i = 4; i <= n; i++) {
            tmp[i%4] = tmp[(i-1)%4]+tmp[(i-3)%4];
        }
        return tmp[n % 4];
    }

    //数学方法,使用矩阵加速;
    public static int c3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[][] base = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }

    public static void main(String[] args) {
        int n = 30;
/*        System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(f22(n));
        System.out.println(f3(n));
        System.out.println("===");

        System.out.println(s1(n));
        System.out.println(s2(n));
        System.out.println(s3(n));
        System.out.println("===");*/

        System.out.println(c1(n));
        System.out.println(c2(n));
        System.out.println(c22(n));
        System.out.println(c222(n));
        System.out.println(c3(n));
        System.out.println("===");

//        System.out.println(integerPower(3, 10));

    }
}
