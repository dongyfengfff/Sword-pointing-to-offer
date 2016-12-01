import java.util.Stack;

/**
 * Author: zhangxin
 * Time: 2016/11/14 0014.
 * Desc:输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * 前序遍历，也叫先根遍历，遍历的顺序是: 根=>左子树=>右子树
 * 中序遍历，也叫中根遍历，遍历的顺序是: 左子树=>根=>右子树
 * 后序遍历，也叫后根遍历，遍历的顺序是: 左子树=>右子树=>根
 * 这里所说的 序 ,其实是按照根的先后来决定的;
 * 在理解这里的递归时,要注意 所有叶子节点也有左右子节点,只不过是null;
 * <p>
 * 本题重点考察对递归的理解以及对递归的使用场景!!!
 */
public class T06 {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {

        if (startPre > endPre || startIn > endIn)
            return null;
        //首先确定跟节点,第一次调用时传入的是0,肯定是整棵树的根节点,那么当递归调用时,root就成为了局部树的根节点!!!
        TreeNode root = new TreeNode(pre[startPre]);

        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
            }
        }


        return root;
    }

    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(10);
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(14);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(8);
        TreeNode node5 = new TreeNode(12);
        TreeNode node6 = new TreeNode(16);

        node0.left = node1;
        node0.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        node3.left = null;
        node3.right = null;
        node4.left = null;
        node4.right = null;
        node5.left = null;
        node5.right = null;
        node6.left = null;
        node6.right = null;

        T06 t = new T06();
        System.out.print("中序遍历:");
        t.inOrder(node0);
        System.out.println();
        System.out.print("中序遍历:");
        t.inOrder1(node0);
        System.out.println();


        System.out.print("前序遍历:");
        t.preOder(node0);
        System.out.println();
        System.out.print("前序遍历:");
        t.preOder1(node0);
        System.out.println();

        System.out.print("后序遍历:");
        t.postOrder(node0);
        System.out.println();
        System.out.print("后序遍历:");
        t.postOrder1(node0);
        System.out.println();


    }

    //中序遍历
    void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);

    }

    //前序遍历
    void preOder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preOder(node.left);
        preOder(node.right);
    }

    //后续遍历
    void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + " ");
    }

    //-----------非递归的方法-----------------
    //中序遍历
    void inOrder1(TreeNode node) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (!stack.isEmpty() || node != null) {
            //代码段(i)一直遍历到左子树最下边，边遍历边保存根节点到栈中
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            //代码段(ii)当p为空时，说明已经到达左子树最下边，这时需要出栈了
            if (!stack.isEmpty()) {
                node = stack.peek();
                stack.pop();
                System.out.print(node.val + " ");
                //进入右子树，开始新的一轮左子树遍历(这是递归的自我实现)
                node = node.right;
            }
        }
    }

    //前序遍历
    void preOder1(TreeNode node) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                System.out.print(node.val + " ");
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.peek();
                stack.pop();
                node = node.right;
            }
        }
    }

    //后续遍历
    /*
    * 后序遍历的非递归实现是三种遍历方式中最难的一种。因为在后序遍历中，要保证左孩子和右孩子都已被访问并且左孩子在右孩子前访问才能访问根结点，这就为流程的控制带来了难题。
    * pre 是一个标记,是某个根节点的子节点,只有pre满足这种条件才可以访问根节点!!!
    * */
    void postOrder1(TreeNode node) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur;
        TreeNode pre = null;
        stack.push(node);

        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (cur.left == null && cur.right == null || pre != null && (pre == cur.left || pre == cur.right)) {
                System.out.print(cur.val + " ");
                stack.pop();
                pre = cur;
            } else {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
    }
}
