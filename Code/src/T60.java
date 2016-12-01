import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:把二叉树打成多行
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
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
        System.out.println(t.Print(n1));
    }
}
