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


    //##################使用递归的方式进行遍历###################

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


    //**************************非递归的方法,逻辑比较清晰**********************************


    /*
    中序遍历方法解读:
    1. 申请一个新的栈stack,初始时令 cur = head;
    2. 把 cur 压入栈中,对以 cur 为头的整棵树来说,依次把 cur.left 压入栈中,再令 cur = cur.left
    3. 不断重复2步骤,直到 cur == null,此时从 stack 中弹出一个节点 赋值给 cur,打印 cur 的值,然后领 cur = cur.right,重复步骤2
    4. 当整个 stack 为空,且 cur == null ,结束
    */
    void inOrder2(TreeNode node) {
        if (node == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = node;

        while (!(stack.isEmpty() && cur == null)) {
            if (cur != null) {
                //不停的将当前 cur 的左节点压栈,直到左节点为空;
                stack.push(cur);
                cur = cur.left;
            } else {
                //左节点到了尽头,此时 cur 为 null;将 cur 复制为 stack 的栈顶弹出的节点;此时的 cur 为最左边的子节点;
                cur = stack.pop();
                System.out.print(cur.val + " ");
                cur = cur.right;
            }
        }

    }

    /*
    前序遍历方法解读:
    1.先申请一个 stack,将头结点压入stack中
    2.从栈中弹出栈顶节点cur,打印cur的值,将cur的右子节点(如果有的话)压入栈中,再将cur的左子节点(如果有的话压入栈中)
    3. 不断重复步骤2,直到stack为空
    总结:最开始先把根节点push进去(初始化);接下来在循环中,先pop,再push(right),再push(left)
    */
    void preOrder2(TreeNode node) {
        if (node == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        //先把根节点压入,占个位置
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            System.out.print(cur.val + " ");

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }


    /*
    后序遍历方法1解读:
    1. 创建两个栈 s1,s2,作为初始化,s1先将跟节点压栈
    2. 开始循环,只要s1不为空,将s1弹出一个节点压入s2中,并将弹出的节点的左右子节点分别添加到s1中
    3. 将s2中的节点一次弹出就是后续遍历的顺序
    */
    void postOrder2_1(TreeNode node) {
        if (node == null) {
            return;
        }

        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        TreeNode cur = node;
        s1.push(cur);// 初始化
        while (!s1.isEmpty()) {
            cur = s1.pop();
            s2.push(cur);
            if (cur.left != null) {
                s1.push(cur.left);
            }
            if (cur.right != null) {
                s1.push(cur.right);
            }
        }

        while (!s2.isEmpty()) {
            System.out.print(s2.pop().val + " ");
        }
    }


    /*
    后序遍历方法2解读:
    h的含义是:最近一次弹出并打印的节点;
	c代表stack的栈顶节点,新压入栈的节点;

    */
    void postOrder2_2(TreeNode node) {
        if (node == null) {
            return;
        }

        //作为初始化,先将根节点压入栈中;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(node);

        TreeNode now = null;  //表示栈顶的元素,也就是当前遍历的元素;
        TreeNode last = null; //上一次从栈顶弹出的,这里只是初始化时这样,并不是说node是刚从栈里弹出;

        while (!stack.isEmpty()) {
            now = stack.peek();
            if (now.left != null && last != now.left && last != now.right) {
                stack.push(now.left);
            } else if (now.right != null && last != now.right) {
                stack.push(now.right);
            } else {
                last = stack.pop();
                System.out.print(last.val + " ");
            }
        }
    }

    /*
    左老师的源码,上面的是我为了便于理解自己改变了一下;万一出错,备用;
	h的含义是:最近一次弹出并打印的节点;但是h的初始化不是null,而是根节点;
	c代表stack的栈顶节点,新压入栈的节点;
	*/
    void postOrder2_2_1(TreeNode h) {
        System.out.print("pos-order: ");
        if (h != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(h);
            TreeNode c = null;
            while (!stack.isEmpty()) {
                //每插入一个元素或者弹出一个元素,都会更新栈顶,在这里执行一次c的更新操作;
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {
                    //若h等于c的左/右孩子,说明c的左/右已经打印完毕了;此时不应再将c的左孩子放入stack中;否则说明左子树还没处理过
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {
                    //c的右节点不为null,h也不是c的右节点,那么把c的右节点加入栈中
                    stack.push(c.right);
                } else {
                    //此时说明c的左右子树都已经处理过,那么从栈中弹出c打印,并令 h = c;
                    System.out.print(stack.pop().val + " ");
                    h = c;
                }
            }
        }
    }

    public static void main(String[] args) {
        /*TreeNode node0 = new TreeNode(10);
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(14);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(8);
        TreeNode node5 = new TreeNode(12);
        TreeNode node6 = new TreeNode(16);*/
        TreeNode node0 = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);

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
       /* System.out.print("中序遍历:");
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
        System.out.println();*/
        /*t.preOder1(node0);
        System.out.println();
        t.preOrder2(node0);*/

        t.postOrder2_1(node0);
        System.out.println();
        t.postOrder2_2(node0);


    }
}
