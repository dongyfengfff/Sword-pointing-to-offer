package extended.chapter_9_others;

/**
 * Author: zhangxin
 * Time: 2017/3/20 0020.
 * Desc:折纸问题;改变的中序遍历,满二叉树;
 * NOTE:神级遍历,不需要创建二叉树,就把二叉树遍历了;
 */
public class Problem_06_PaperFolding {

    public static void printAllFolds(int N) {
        printProcess(1, N, true);
    }

    //还是不会,假装会,实现的功能是"中序"遍历一棵树;那么分三步走;首先遍历右树,然后打印自己,然后遍历左树;
    //结束条件是:当传入的i>N时,就停止;

    /**
     * @param i    第i层
     * @param N    一共N层;
     * @param down 根节点是down?,是的话打印down,都则打印up;
     */
    public static void printProcess(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        printProcess(i + 1, N, true); //右子树,down;
        System.out.println(down ? "down " : "up ");
        printProcess(i + 1, N, false); //左子树,up;
    }

    public static void main(String[] args) {
        int N = 3;
        printAllFolds(N);

    }

}
