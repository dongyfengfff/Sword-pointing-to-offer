package extended.chapter_8_arrayandmatrix;

/**
 * Author: zhangxin
 * Time: 2017/1/23
 * Desc:将N*N矩阵顺时针旋转90°,空间复杂度O(1)
 * 依然使用分圈的思想,只不过这时,采用对位的四个为一组,转转打印,跟着直觉
 * 已经说明是N*N的正方形矩阵,问题简单了一些
 */
public class Problem_02_RotateMatrix {
    public static void rotate(int[][] matrix) {
        int x1 = 0, y1 = 0;
        int x2 = matrix.length, y2 = matrix[0].length;

        while (x1 < x2 && y1 < y2) {
            rotateEdge(matrix, x1, y1, x2, y2);
            x1++;
            y1++;

            x2--;
            y2--;
        }
    }

    public static void rotateEdge(int[][] m, int x1, int y1, int x2, int y2) {
        //找对应位
        for (int i = 0; i < x2 - x1; i++) {
            int a = m[x1 + i][y1];
            int b = m[x1][y2 + i];
            int c = m[x2 - i][y2];
            int d = m[x2][y1 - i];

            int temp = a;

            a = d;
            d = c;
            c = b;
            b = temp;
        }
    }


}
