package LintCode.dp;

/**
 * Author: zhangxin
 * Time: 2017/9/9 0009.
 * Desc: 给出 n，问由 1...n 为节点组成的不同的二叉查找树有多少种？
 * 虽说可以用动态规划，但是这直接就是一个卡特兰数问题；
 */
public class numTrees {
    /**
     * @paramn n: An integer
     * @return: An integer
     */
    public int numTrees(int n) {
        // write your code here
        long res1 = 1;
        long res2 = 1;

        for (int i = 1; i <= n; i++) {
            res1 *= i;
        }

        System.out.println(res1);

        for (int i = n + 2; i <= 2 * n; i++) {
            res2 *= i;
        }

        System.out.println(res2);

        return (int)(res2 / (res1) );
    }

    public static void main(String[] args) {
        System.out.println(new numTrees().numTrees(3));
    }
}
