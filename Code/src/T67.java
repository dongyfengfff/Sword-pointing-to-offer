/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:机器人的运动范围
 * <p>
 * 地上有一个m行和n列的方格。
 * 一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
 * 请问该机器人能够达到多少个格子？
 */
public class T67 {
    int count = 0;

    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0) {
            return 0;
        }

        boolean[][] visitFlags = new boolean[rows][cols]; //默认值是 false;

        return count;
    }

    boolean movingCore(int i, int j, int rows, int cols, boolean[][] visitFlags, int threshold) {
        if (i < 0 || j < 0 || i >= rows || j >= cols || visitFlags[i][j] || !beyondHolds(i, j, threshold)) {
            return false;
        }

        count++;
        visitFlags[i][j] = true;

        if (movingCore(i + 1, j, rows, cols, visitFlags, threshold)
                || movingCore(i, j+1, rows, cols, visitFlags, threshold)
                || movingCore(i - 1, j, rows, cols, visitFlags, threshold)
                || movingCore(i, j-1, rows, cols, visitFlags, threshold)) {

        }

        return  false;
    }

    boolean beyondHolds(int i, int j, int hold) {
        int sum = 0;
        while (i != 0) {
            sum += i >> 1;
            i = i >> 1;
        }
        while (j != 0) {
            sum += j >> 1;
            j = j >> i;
        }
        if (sum > hold) {
            return false;
        }
        return true;
    }


    //********************************
    public int movingCount1(int threshold, int rows, int cols) {
        int[][] flag = new int[rows][cols];
        return moving(threshold, rows, cols, flag, 0, 0);
    }

    public int moving(int threshold, int rows, int cols, int[][] flag, int i, int j){
        if(threshold <= 0 || i >= rows || i< 0 || j >= cols || j < 0 || (flag[i][j] == 1) || (sum(i) + sum(j) > threshold)){
            return 0;
        }
        flag[i][j] = 1;
        return moving(threshold, rows, cols, flag, i - 1, j)
                +moving(threshold, rows, cols, flag, i + 1, j)
                +moving(threshold, rows, cols, flag, i, j - 1)
                +moving(threshold, rows, cols, flag, i, j + 1)
                + 1;
    }

    public int sum(int i ){
        if(i == 0){return i ;}
        int sum = 0;
        while(i != 0){
            sum += i % 10;
            i /= 10;
        }
        return sum;
    }
}
