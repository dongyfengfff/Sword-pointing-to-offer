package extended.chapter_3_binarytreeproblem;

/**
 * Author: zhangxin
 * Time: 2016/12/16 0016.
 * Desc:判断一棵树是否是平衡二叉树(左右子树的高度差不超过1)
 * 整体思路:使用后续遍历;
 * 只要|lH - rH|>1,立即返回,这个返回其实不是立即的,而是需要反遍历到头,才能结束,并不能在自己点直接跳出递归;
 */
public class Problem_13_IsBalancedTree {
    public static boolean isBalance(Node head) {
        boolean[] res = new boolean[1];
        res[0] = true; //相当于一个全局的数组;
        getHeight(head, 1, res);
        return res[0];
    }

    /***
     * 后续遍历;
     * @param head :当前头结点;
     * @param level:当前深度
     * @param res:全局结果
     * @return 返回当前子树的深度;
     */
    public static int getHeight(Node head, int level, boolean[] res) {
        if (head == null) {
            return level;
        }
        int lH = getHeight(head.left, level + 1, res);
        if (!res[0]) {
            return level;
        }
        int rH = getHeight(head.right, level + 1, res);
        if (!res[0]) {
            return level;
        }
        if (Math.abs(lH - rH) > 1) {
            res[0] = false;
        }
        return Math.max(lH, rH);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBalance(head));

    }
}
