package extended.chapter_3_binarytreeproblem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhangxin
 * Time: 2016/12/10 0010.
 * Desc:这里着重练习按照层遍历的方法实现二叉树的序列化;按照前序遍历的方法参照剑指OfferT62.java
 */
public class Problem_04_SerializeAndReconstructTree {

    StringBuilder sb = new StringBuilder();

    String Serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        sb.append(root.val);
        sb.append(",");
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null) {
                sb.append("#,");
            } else {
                sb.append(node.left.val);
                sb.append(",");
                queue.offer(node.left);
            }

            if (node.right == null) {
                sb.append("#,");
            } else {
                sb.append(node.right.val);
                sb.append(",");
                queue.offer(node.right);
            }

        }

        return sb.toString();
    }


    TreeNode Deserialize(String str) {
        String[] nodes = str.split(",");
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        int index = 0;
        TreeNode head = DeserializeCore(nodes[index++]);
        queue.offer(head);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = DeserializeCore(nodes[index++]);
            TreeNode right = DeserializeCore(nodes[index++]);

            node.left = left;
            node.right = right;
            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
        }
        return head;
    }

    TreeNode DeserializeCore(String s) {
        if (s.equals("#")) {
            return null;
        }

        return new TreeNode(Integer.valueOf(s));
    }

    TreeNode DeserializeCore(TreeNode node, Queue<String> queue) {
        String s = queue.poll();
        if (s.equals("#")) {
            return null;
        }

        return new TreeNode(Integer.valueOf(s));
    }


    public static void main(String[] args) {
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

        Problem_04_SerializeAndReconstructTree p = new Problem_04_SerializeAndReconstructTree();
        String s = p.Serialize(node0);
        System.out.println(s);
        p.printInorder(p.Deserialize(s));
    }

    void printInorder(TreeNode root){
        if (root == null){
            return ;
        }
        System.out.println(root.val);
        printInorder(root.left);
        printInorder(root.right);
    }
}

