/**
 * Author: zhangxin
 * Time: 2016/11/18 0018.
 * Desc:
 * (1)输出一个二叉树的深度;
 * (2)判断一棵树是否是平衡二叉树; |l - r|<=1;
 */
public class T39 {
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = TreeDepth(root.left);
        int r = TreeDepth(root.right);

        return Math.max(l, r) + 1;
    }

    //节点只遍历一次!!!
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        int diff = left - right;
        if (diff > 1 || diff < -1)
            return false;
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);

    }


    public static void main(String[] args) {
        T39 t = new T39();

    }
}
