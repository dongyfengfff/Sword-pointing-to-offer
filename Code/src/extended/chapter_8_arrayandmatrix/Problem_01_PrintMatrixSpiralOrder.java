package extended.chapter_8_arrayandmatrix;

/**
 * Author: zhangxin
 * Time: 2017/1/23
 * Desc: 转圈打印矩阵
 * 问题的核心在于确定当前这一圈的左上角和右下角的坐标,然后就好办了;
 */
public class Problem_01_PrintMatrixSpiralOrder {
    public static void spiralOrderPrint(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;

        int x1 = 0,y1 = 0;
        int x2 = row-1,y2 = column-1;

        while (x1<=x2&&y1<=y2){
            printEdge(matrix,x1,y1,x2,y2);
            x1++;
            y1++;

            x2--;
            y2--;
        }
    }

    public static void printEdge(int[][] m, int x1, int y1, int x2, int y2) {
        //需要考虑单行的情况
    }
}
