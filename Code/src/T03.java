/**
 * Author: zhangxin
 * Time: 2016/11/14 0014.
 * Desc:在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class T03 {
    public boolean Find(int[][] array, int target) {
        //行
        int row = array.length;
        if (row < 1) {
            return false;
        }

        //列
        int column = array[0].length;
        int i = 0, j = column - 1;
        while (i < row && j >= 0) {
            if (array[i][j] < target) {
                i++;
            }else if(array[i][j] > target){
                j--;
            }else{
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        int[][] a = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        System.out.println(new T03().Find(a, 7));
    }
}
