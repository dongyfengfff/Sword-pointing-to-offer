import java.util.ArrayList;

/**
 * Author: zhangxin
 * Time: 2016/11/21 0021.
 * Desc: 顺时针打印矩阵
 * 实现思路:
 * 遇到的坑:在遍历列的时候,把x,y坐标写反了 => a[y][x]
 */
public class T20 {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int x1 = 0, y1 = 0, x2 = matrix.length - 1, y2 = matrix[0].length-1;
        System.out.println(x1+"<>"+y1);
        System.out.println(x2+"<>"+y2);



        while (x1 < x2 && y1 < y2) {
            for (int i = y1; i < y2; i++) {
                list.add(matrix[x1][i]);
            }
            for (int i = x1; i < x2; i++) {
                list.add(matrix[i][y2]);
            }
            for (int i = y2; i > y1; i--) {
                list.add(matrix[x2][i]);
            }
            for (int i = x2; i > x1; i--) {
                list.add(matrix[i][y1]);
            }
            x1++;
            y1++;
            x2--;
            y2--;
        }

        if (x1 < x2 && y1 == y2) {
            for (int i = x1; i <= x2; i++) {
                list.add(matrix[i][y1]);
            }
        } else if (x1 == x2 && y1 < y2) {
            for (int i = y1; i <= y2; i++) {
                list.add(matrix[x1][i]);
            }
        }else if (x1==x2&&y1==y2){
            list.add(matrix[x1][y1]);
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] a = {{1},{2},{3}};
        System.out.println(new T20().printMatrix(a));

    }

}
