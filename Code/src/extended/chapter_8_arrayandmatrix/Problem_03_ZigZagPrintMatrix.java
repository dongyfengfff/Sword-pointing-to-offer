package extended.chapter_8_arrayandmatrix;

/**
 * Author: zhangxin
 * Time: 2017/1/23
 * Desc:按之字打印矩阵,空间复杂度O(1)
 * * * * * * * * * * * * * * *  这个规律找的 不对
 * 如果先->,那么接下来↙方向
 * 如果先↓,那么接下来↗方向
 * * * * * * * * * * * * * * *
 * 正解规律:
 * 上坐标(x1,y1)初始值为(0,0),先沿着第一行移动,当到达第一行最右边的元素后,在沿着最后一列移动
 * 下坐标(x2,y2)初始值为(0,0),先沿着第一列移动,当到达第一列最下边的元素后,在沿着最后一行移动
 */
public class Problem_03_ZigZagPrintMatrix {
    public static void printMatrixZigZag(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[1].length;

        int x1 = 0, y1 = 0;
        int x2 = 0, y2 = 0;

        boolean fromUp = false; //区分是↙还是↗方向打印

        //终止条件,x1!=row,因为最开始的时候x1一直是0,y1一直在变,直到y1到了尽头,x1才开始++,等x1==row时,结束
        while (x1 != row) {
            printLevel(matrix, x1, y1, x2, y2, fromUp);

            //注意参考的y1都是上次的y1,是有值的,根据上次的值,来确定本次的值;
            x1 = y1 == column - 1 ? x1 + 1 : x1; //x1的变化:看y1是不是到了最右边,是的话,x1开始++;
            y1 = y1 == column - 1 ? y1 : y1 + 1; //y1的变化:看y1是不是到了最右边,是的话,y1不变,否则y1++;

            //同理,参开的x2是根据上次的x2,是有值的;
            x2 = x2 == row - 1 ? x2 : x2 + 1;
            y2 = x2 == row - 1 ? y2 + 1 : y2;
            //一次↙一次↗方法打印,交错进行;
            fromUp = !fromUp;
        }
        System.out.println();
    }
    public static void printLevel(int[][] m, int x1, int y1, int x2, int y2, boolean f) {
        if (f) {// 打印↙方向
            while (x1 <= x2) {
                System.out.print(m[x1++][y1--] + " ");
            }
        } else {// 打印↗方向
            while (x2 >= x1) {
                System.out.print(m[x2--][y2++] + " ");
            }
        }
    }
}
