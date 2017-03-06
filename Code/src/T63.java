import java.util.Stack;

/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:二叉搜索树中第K个节点
 * 使用中序遍历可否?  要使用非递归版本的方法,才能及时终止方法
 */
public class T63 {
    TreeNode node;
    int i = 1;
    int K = 0;

    TreeNode KthNode(TreeNode pRoot, int k) {
        K = k;
        inorder(pRoot);
        return node;
    }

    void inorder(TreeNode pRoot) {
        if (i > K) {
            return;
        }
        if (pRoot == null) {
            return;
        }

        inorder(pRoot.left);
        node = pRoot;
        System.out.println(node.val + "---" + i);
        i++;
        inorder(pRoot.right);
    }

//######################################################################

    TreeNode KthNode1(TreeNode pRoot, int k) {
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
                k--;
                if (k == 0) {
                    return node;
                }
                //进入右子树，开始新的一轮左子树遍历(这是递归的自我实现)
                node = node.right;
            }
        }
        return null;
    }

    void inOrder1(TreeNode node, int k) {
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
                k--;

                System.out.println(node.val + "-->"+k);
                if (k == 0) {
                    return;
                }
                //进入右子树，开始新的一轮左子树遍历(这是递归的自我实现)
                node = node.right;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(8);
        TreeNode n2 = new TreeNode(6);
        TreeNode n3 = new TreeNode(10);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(7);
        TreeNode n6 = new TreeNode(9);
        TreeNode n7 = new TreeNode(11);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        n4.left = null;
        n4.right = null;

        n5.left = null;
        n5.right = null;

        n6.left = null;
        n6.right = null;

        n7.left = null;
        n7.right = null;

        T63 t = new T63();
        //System.out.println(t.KthNode(n1, 1).val);
//        t.inOrder1(n1,2);
        System.out.println(t.findK(7,n1).val);
    }


    //######################使用的递归二叉树的方法做的,不是很好实现;
    boolean flag ;
    int count ;
    TreeNode nodeK = null;
    public TreeNode findK(int k,TreeNode root){
        count = k;
        findCore(root);
        return nodeK;
    }

    public void findCore(TreeNode root){
        if (root==null) {
            return ;
        }
        if (!flag) {
            findCore(root.left);
        }else{
            return;
        }
        count--;
        if (count==0) {
            nodeK = root;
            flag = true;
        }
        if (!flag) {
            findCore(root.right);
        }else{
            return;
        }
    }
}
