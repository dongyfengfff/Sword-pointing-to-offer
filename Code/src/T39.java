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
    //判断是不是平衡二叉树,|left-right|<=1;注意返回方式啊;
    //这个递归怎么写?假设已经实现了该功能,那么我就返回左右是不是否是平衡的,
    //但是还有个问题是,即使左右都是平衡的,但是如果左右的差超过1的话依然是不平衡的
    //因此判定条件是:
    // IsBalanced_Solution(root.left)&&IsBalanced_Solution(root.right)&&(Math.abs(l-r)<=1),只有这三个条件都满足才算;
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
