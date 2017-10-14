package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2016/12/29 0029.
 * Desc:最小编辑代价,ic->插入的代价,dc->删除的代价,rc->替换的代价
 *
 * NOTE:这里生成dp的方式和之前不同,之前dp的长度就是s1的长度,现在需要长度+1,需要一个前面的0,这样便于实现;其实你不加1也是可以实现的,但是在初始化行列时不方便;
 * fixme:下面有一个方法是不对的，原因在于我们默认字符串都是从后面追个字符追加的，忽略了：ab-》xab的情况，此时的代价其实是1，而不是2；
 * 具体实现请参考　 {@link LintCode.dp.minDistance }
 */
public class Problem_09_EditCost {

    //首先是经典动态规划的方案,生成dp,s1作为列伸展,s2作为行伸展;
    public static int minCost1(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int row = chs1.length + 1;
        int col = chs2.length + 1;
        //(1)生成dp;注意这里生成的是 m = s1.length+1, n=s2.length+1;比原来的都大1,因为可能会出现把s1/或者s2编辑成""的情况,所以要为 0 留出空间
        //dp[i][j]代表的是:s1[0~i-1] 编辑成s2[0~j-1]的代价
        int[][] dp = new int[row][col];

        //初始化第一列;
        for (int i = 1; i < row; i++) {
            dp[i][0] = dc * i;
        }

        //初始化第一行;
        for (int j = 1; j < col; j++) {
            dp[0][j] = ic * j;
        }

        /*
        dp[i][j]的值的来源有一下这三种:
        1. 由s1的(i-1)到s2的(j)的代价+dc ==>原本s(i-1)已经到达sj了,此时成了si,需要在原有的代价上+删除的代价
        2. 由s1的(i)到s2的(j-1)的代价+ic ==>原本si已经到达s(j-1)了,此时成了sj,需要在原有的代价上+插入的代价
        3. 由s1的(i-1)到s2的(j-1)的代价,此时要判断s[i]与s[j]是否相等,如果想等,那么代价直接就是dp[i-1][j-1],否则代价为dp[i-1][j-1]+rc
         */
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (chs1[i - 1] == chs2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
            }
        }
        return dp[row - 1][col - 1];
    }

    /*
    使用压缩空间的做法,上面用到了上,左,和左上的位置的信息,以前的做法是


     */
    public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
        char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
        if (chs1.length < chs2.length) { // str2较长就交换ic和dc的值
            int tmp = ic;
            ic = dc;
            dc = tmp;
        }
        int[] dp = new int[shorts.length + 1];
        for (int i = 1; i <= shorts.length; i++) {
            dp[i] = ic * i;
        }
        for (int i = 1; i <= longs.length; i++) {
            int pre = dp[0]; // pre表示左上角的值
            dp[0] = dc * i;
            for (int j = 1; j <= shorts.length; j++) {
                int tmp = dp[j]; // dp[j]没更新前先保存下来
                if (longs[i - 1] == shorts[j - 1]) {
                    dp[j] = pre;
                } else {
                    dp[j] = pre + rc;
                }
                dp[j] = Math.min(dp[j], dp[j - 1] + ic);
                dp[j] = Math.min(dp[j], tmp + dc);
                pre = tmp; // pre变成dp[j]没更新前的值
            }
        }
        return dp[shorts.length];
    }


    /*
    不想把两种混合起来,这样写代码漂亮,但是逻辑混乱,最好分成俩个方法;接下来以s1向s2迭代为例

    str1 = "abcdf";
    str2 = "";

    // FIXME: 2016/12/29 0029 最后一种测试样例无法通过

     */
    public static int minCost3(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }

        int len = str2.length() + 1;

        //(1)定义dp
        int[] dp = new int[len];
        int tmp = 0;

        //(2)初始化dp
        for (int i = 1; i < len; i++) {
            dp[i] = i * ic;
        }

        //(3)
        for (int i = 1; i <= str1.length(); i++) {
            tmp = dp[0];
            dp[0] = i * dc;
            //如果s2为""的话,这个内循环不会被执行;
            for (int j = 1; j < len; j++) {
                int over = dp[j];
                int left_over = tmp;
                int left = dp[j - 1];

                if (str1.charAt(i-1) != str2.charAt(j - 1)) {
                    left_over += rc;
                }
                dp[j] = getMin(over + dc, left_over, left + ic);
                tmp = over;
            }
        }
        return dp[len - 1];
    }

    public static int getMin(int i, int j, int k) {
        return Math.min(Math.min(i, j), k);
    }

    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(minCost1(str1, str2, 5, 3, 2));
        System.out.println(minCost2(str1, str2, 5, 3, 2));
        System.out.println(minCost3(str1, str2, 5, 3, 2));

        str1 = "abcdf";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 3, 2, 4));
        System.out.println(minCost2(str1, str2, 3, 2, 4));
        System.out.println(minCost3(str1, str2, 3, 2, 4));

        str1 = "";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 1, 7, 5));
        System.out.println(minCost2(str1, str2, 1, 7, 5));
        System.out.println(minCost3(str1, str2, 1, 7, 5));

        str1 = "abcdf";
        str2 = "";
        System.out.println(minCost1(str1, str2, 2, 9, 8));
        System.out.println(minCost2(str1, str2, 2, 9, 8));
        System.out.println(minCost3(str1, str2, 2, 9, 8));
    }
}
