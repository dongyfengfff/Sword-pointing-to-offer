import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhangxin
 * Time: 2016/11/21 0021.
 * Desc:操作给定的二叉树，将其变换为源二叉树的镜像。
 * 二叉树的镜像定义：源二叉树
 * 8
 * /  \
 * 6   10
 * / \  / \
 * 5  7 9 11
 * 镜像二叉树
 * 8
 * /  \
 * 10   6
 * / \  / \
 * 11 9 7  5
 */
public class T19 {
    //使用一个队列,将节点按顺序添加能进去
    //取出时执行swap操作;直到队列为null;
    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            swapNode(t);
            //list.add(t.val);
            if (t.left != null) {
                queue.add(t.left);
            }
            if (t.right != null) {
                queue.add(t.right);
            }
        }
    }

    void swapNode(TreeNode root) {
        if (root != null) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
        }
    }

    // 递归的版本;
    public void Mirror1(TreeNode root) {
        if (root == null){
            return ;
        }

        Mirror1(root.left);
        Mirror1(root.right);
        swapNode(root);
    }


    //后来写的递归的版本,主要是分步骤走;
    public void Mirror2(TreeNode root) {
        //终止条件
        if (root == null) {
            return ;
        }
        Mirror2(root.left);
        Mirror2(root.right);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }
}
