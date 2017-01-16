package extended.chapter_3_binarytreeproblem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhangxin
 * Time: 2016/12/19 0019.
 * Desc:
 * 1.判断一棵树是否为搜索二叉树:使用中序遍历,要做到性能最好,使用Morris遍历,但是别忘了不管是不是搜索二叉树,最终都要恢复原树;
 * 但是这样做的一个缺点是:需要把整棵树都遍历一遍,中间无法停止,和递归一样,无法中途停止,因此可能也不是一个很好的解决方案
 * 还是用非递归的方法实现吧;
 * <p>
 * 2.判断一棵树是否为完全二叉树
 * <p>
 * 完全二叉树的定义:除了最底下的一层,上面的层必须是满的,不能有空,最底下的一层可以满,如果不满,所有叶子节点都在最左边;
 * 对于完全二叉树的判定:一个思路是修改二叉树按层遍历的方法,实现判断;
 * 具体方案是:
 * 1.按层遍历二叉树,从左到右,使用队列
 * 2.如果当前节点有右子节点没有左子节点,直接返回false;
 * 3.如果当前节点只有左子节点,后面的节点必须全部是叶子节点,否则返回false
 * 4.返回true;
 */
public class Problem_15_IsBSTAndCBT {

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        boolean res = true;
        Node pre = null; //记录之前遍历的一个节点,这样好和现在的节点比较大小,现在的节点是cur1;
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            //但是这样做什么时候停止呢,还是会全遍历一遍啊;中途不能停止;
            if (pre != null && pre.value > cur1.value) {
                //前一个节点的值比后一个大,返回false;
                res = false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }
        return res;
    }

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<Node>();
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;

            //此时要求所有节点都是叶子节点,那么l和r必须全是null  
            // FIXME: 2016/12/19 0019 后面的这个判定条件是否多余;
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.offer(l);
            }

            //如果没有右子节点,那么后面的节点必须全是叶子节点;
            if (r != null) {
                queue.offer(r);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        printTree(head);
        System.out.println(isBST(head));
        System.out.println(isCBT(head));

    }
}
