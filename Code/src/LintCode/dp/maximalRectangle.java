package LintCode.dp;

import java.util.Stack;

/**
 * Author: zhangxin
 * Time: 2017/9/10 0010.
 * Desc:
 */
public class maximalRectangle {
    /*
     * @param matrix: a boolean 2D matrix
     * @return: an integer
     */
    public int maximalRectangle(boolean[][] matrix) {
        // write your code here
        int m = matrix.length;
        int n = m == 0 ? 0 : matrix[0].length;
        //生成辅助数组，只不过比之前的数组多了一列；
        int[][] height = new int[m][n + 1];

        int maxArea = 0;
        for (int i = 0; i < m; i++) { //外层循环按照行
            for (int j = 0; j < n; j++) { // 内存循环按照列；
                // 如果原数组是flase，辅助数组对应的值为0；
                if (!matrix[i][j]) {
                    height[i][j] = 0;
                } else {

                    height[i][j] = i == 0 ? 1 : height[i - 1][j] + 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n + 1; j++) {
                System.out.print(height[i][j] + " ");
            }
            System.out.println();
        }

        //针对每一行
        for (int i = 0; i < m; i++) {
            int area = maxAreaInHist(height[i]);
            if (area > maxArea) {
                maxArea = area;
            }
        }

        return maxArea;
    }

    private int maxAreaInHist(int[] height) {
        Stack<Integer> stack = new Stack<>();

        int i = 0;
        int max = 0;

        while (i < height.length) {
            if (stack.isEmpty() || height[stack.peek()] <= height[i]) {
                stack.push(i++);
            } else {
                int t = stack.pop();
                max = Math.max(max, height[t] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        boolean[][] arr = {
                {true, true, false, false, true},
                {false, true, false, false, true},
                {false, false, true, true, true},
                {false, false, true, true, true},
                {false, false, false, false, true},
        };
        System.out.println(new maximalRectangle().maximalRectangle(arr));
    }
}
