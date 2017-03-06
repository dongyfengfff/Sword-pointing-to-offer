import java.util.LinkedList;

/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:树中两个节点的公共祖先
 * <p>
 * 分情况讨论:
 * # 如果是搜索二叉树:根大于左边且小于右边,那么可以中序遍历即可;
 * # 如果每个节点中都包含一个指向父节点的指针,那么就可以直接转换成两个链表求公共节点的问题;
 * # 在普通不过的二叉树,使用两个链表用来保存从根节点到路径的节点,然后变成了连个链表找公共节点的问题;
 */
public class T50 {
    //首先明确使用中序遍历;
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    boolean flag; //判断找没找到

    //通过一个 栈用来保存遍历过的路径,最后返回;要是对比两个路径,根据长度,然后在找公共节点;
    //NOTE:这个方法有个致命的缺点:如果target不在树中,那么stack中最后还保留着根结点,所以你要额外的判断;
    void findPath(TreeNode root, TreeNode target) {
        if (root == null) {
            return;
        }
        stack.push(root);
        if (root == target) {
            flag = true;
        }

        if (!flag) {
            findPath(root.left, target);
        } else {
            return;
        }
        if (!flag&&root.left!=null) {
            stack.pop();
        }

        if (!flag) {
            findPath(root.right, target);
        } else {
            return;
        }
        if (!flag&&root.right!=null) {
            stack.poll();
        }
    }


    public static void main(String[] args) {
        T50 t = new T50();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        t.findPath(root,root.right.left);
        while (!t.stack.isEmpty()){
            System.out.println(t.stack.poll().val);
        }
    }
}
