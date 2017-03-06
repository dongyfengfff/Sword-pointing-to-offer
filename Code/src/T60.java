import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:把二叉树打成多行
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 *
 *
 * 设计到按照行区分,两个思路:
 * 两个队列,一个队列放一行
 * 采用计数,pre,cur来计算;  更喜欢这个;
 */
public class T60 {
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        if (pRoot == null) {
            return lists;
        }
        Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
        queue1.add(pRoot);
        boolean flag = true;
        while (!(queue1.isEmpty() && queue2.isEmpty())) {
            if (flag) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                while (!queue1.isEmpty()) {
                    TreeNode node = queue1.poll();
                    list.add(node.val);
                    if (node.left != null) {
                        queue2.add(node.left);
                    }
                    if (node.right != null) {
                        queue2.add(node.right);
                    }
                }
                lists.add(list);
                flag = false;
            } else {
                ArrayList<Integer> list = new ArrayList<Integer>();
                while (!queue2.isEmpty()) {
                    TreeNode node = queue2.poll();
                    list.add(node.val);
                    if (node.left != null) {
                        queue1.add(node.left);
                    }
                    if (node.right != null) {
                        queue1.add(node.right);
                    }
                }

                lists.add(list);
                flag = true;
            }
        }
        return lists;
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

        T60 t = new T60();
        System.out.println(t.Print3(n1));
    }

    //方法2,比方法1好,使用了一个end变量来标记每一行的尾吧; xxxxxx
    //方法2的思路严重错误,设置了end,如果end的左右子节点都为null,此时下一行的end就错乱了,还是老老实实用两个queue吧...
    ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        if (pRoot==null) {
            return lists;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode end = pRoot;
        queue.offer(pRoot);
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node == end) {
                lists.add(list);
                list = new ArrayList<Integer>();
                if (node.left!=null) {
                    queue.offer(node.left);
                    end = node.left;
                }
                if (node.right!=null) {
                    queue.offer(node.right);
                    end = node.right;
                }
            }else{
                if (node.left!=null) {
                    queue.offer(node.left);
                }
                if (node.right!=null) {
                    queue.offer(node.right);
                }
            }
        }
        return lists;
    }

    //计数器的方法;pre,cur;
    ArrayList<ArrayList<Integer>> Print3(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        if (pRoot==null) {
            return lists;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(pRoot);
        int pre = 1,cur = 0;

        while (!queue.isEmpty()){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < pre; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);

                if (node.left!=null) {
                    queue.offer(node.left);
                    cur++;
                }
                if (node.right!=null) {
                    queue.offer(node.right);
                    cur++;
                }
            }
            lists.add(list);
            pre = cur;
            cur = 0;
        }

        return lists;
    }
}
