package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2016/12/22 0022.
 * Desc:两种解决思路,第一种更直观,但是空间复杂度高,时间复杂度也高
 * 第二种思路:空间压缩算法;
 */
public class Problem_02_MinPathSum {
    public static int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length; //行
        int col = m[0].length; //列
        int[][] dp = new int[row][col]; //生成一个和原矩阵等大的矩阵

        //对矩阵进行初始化
        dp[0][0] = m[0][0];
        //dp矩阵的第一列就是原矩阵的第一列的累加和
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        //dp矩阵的第一行就是原矩阵的第一行的累加和
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        //接下来的元素就是考虑从上边进入还是从左边进入哪种代价更小;
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    //压缩空间的动态规划算法;一般我们是习惯从上往下的扫描,也就是列数小一些;
    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int more = Math.max(m.length, m[0].length); // 行数与列数较大的那个为more,more作为外层循环;
        int less = Math.min(m.length, m[0].length); // 行数与列数较小的那个为less,less作为内层循环;
        boolean rowmore = more == m.length; // 行数是不是大于等于列数,用来判断在遍历时是使用行还是列的序号;

        int[] arr = new int[less]; // 辅助数组的长度仅为行数与列数中的最小值
        arr[0] = m[0][0];

        //初始化arr的值,要么为行的累加,要么为列的累加;
        for (int i = 1; i < less; i++) {
            arr[i] = arr[i - 1] + (rowmore ? m[0][i] : m[i][0]);
        }

        for (int i = 1; i < more; i++) {
            arr[0] = arr[0] + (rowmore ? m[i][0] : m[0][i]);
            //注意起始从1开始,
            for (int j = 1; j < less; j++) {
                arr[j] = Math.min(arr[j - 1], arr[j])
                        + (rowmore ? m[i][j] : m[j][i]);
            }
        }
        return arr[less - 1];
    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // int[][] m = generateRandomMatrix(3, 4);
        int[][] m = {
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}
        };
        printMatrix(m);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));

    }
}
