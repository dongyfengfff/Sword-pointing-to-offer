/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:矩阵中的路径
 * 分析：回溯算法
 * 这是一个可以用回朔法解决的典型题。首先，在矩阵中任选一个格子作为路径的起点。如果路径上的第i个字符不是ch，
 * 那么这个格子不可能处在路径上的 第i个位置。如果路径上的第i个字符正好是ch，那么往相邻的格子寻找路径上的第i+1个字符。
 * 除在矩阵边界上的格子之外，其他格子都有4个相邻的格子。重复这个过程直到路径上的所有字符都在矩阵中找到相应的位置。
 * 由于回朔法的递归特性，路径可以被开成一个栈。
 * 当在矩阵中定位了路径中前n个字符的位置之后，在与第n个字符对应的格子的周围都没有找到第n+1个字符，
 * 这个时候只要在路径上回到第n-1个字符，重新定位第n个字符。
 * 由于路径不能重复进入矩阵的格子，还需要定义和字符矩阵大小一样的布尔值矩阵，用来标识路径是否已经进入每个格子。
 * 当矩阵中坐标为（row,col）的格子和路径字符串中相应的字符一样时，
 * 从4个相邻的格子(row,col-1),(row-1,col),(row,col+1)以及(row+1,col)中去定位路径字符串中下一个字符,
 * 如果4个相邻的格子都没有匹配字符串中下一个的字符，表明当前路径字符串中字符在矩阵中的定位不正确，我们需要回到前一个，然后重新定位。
 * 一直重复这个过程，直到路径字符串上所有字符都在矩阵中找到合适的位置
 */
public class T66 {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix.length != rows * cols) {
            return false;
        }

        return true;
    }

    //#################################################################
    public boolean hasPath1(char[] matrix, int rows, int cols, char[] str) {
        //matrix也可以定义成二维字符数组的形式，并没有什么问题
        //ch==null || str==null这俩个条件已经不需要再判断了，因为入口如果是null会报错
        if (rows < 1 || cols < 1) return false;
        boolean[][] visitFlags = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println(i + " " + j);
                //这两层循环的目的是找到入口,入口找到后就进入递归了
                if (hasPathCore(matrix, rows, cols, i, j, str, visitFlags, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param ch         矩阵
     * @param rows       矩阵行数
     * @param columns    矩阵列数
     * @param i          矩阵行号索引
     * @param j          矩阵列号索引
     * @param str        查找字符串
     * @param visitFlags 矩阵对应的布尔矩阵，标识有无访问过节点
     * @param index      str下标，从0开始比较
     * @return
     */
    private static boolean hasPathCore(char[] ch, int rows, int columns, int i, int j, char[] str,
                                       boolean[][] visitFlags, int index) {
        if (i < 0 || j < 0 || i >= rows || j >= columns || visitFlags[i][j] || ch[i * columns + j] != str[index]) {
            return false;
        }

        //设置为true,代表访问过,以后不能再访问;
        visitFlags[i][j] = true;
        if (index == str.length - 1) return true;
        index++;
        if (hasPathCore(ch, rows, columns, i + 1, j, str, visitFlags, index)
                || hasPathCore(ch, rows, columns, i, j + 1, str, visitFlags, index)
                || hasPathCore(ch, rows, columns, i - 1, j, str, visitFlags, index)
                || hasPathCore(ch, rows, columns, i, j - 1, str, visitFlags, index)) {
            return true;
        }
        // 如果找到这四便都没找到,回溯,将之前设置的 访问过的标记 还原回 false,在返回false;
        visitFlags[i][j] = false;
        return false;
    }
}

/* 你让我找这一个符合给出的字符串(len = n)的路径,我不会找,但是我会找字符串(n=1),(末尾判断条件)于是我接了;
然后你给我传过来一个字符串,那我也是先判断第一个字符行不行,行,继续,字符串长度减一,怎么继续:上下左右都找一次看行不行;
第二个字符不行,返回false,说明第一个字符在这个位置也不合适,将之前设置的标记还原;
*/