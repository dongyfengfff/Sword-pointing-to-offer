package extended.chapter_3_binarytreeproblem;

import java.util.Stack;

/**
 * Author: zhangxin
 * Time: 2016/12/15 0015.
 * Desc:给定一个搜索二叉树,但是其中两个节点被调换了,二叉树中所有节点的值都不一样,请返回这两个错误的节点;
 * 进阶:恢复原本的搜索二叉树;
 * <p>
 * 思路1:使用 **中序遍历** 该二叉树,原本应该是升序的,如果出错了,那么就会出现降序的情况;
 * 思路2:进阶问题:可以将两个节点的值进行交换,这是最简单的交换,但是未免太简单
 * 思路3:进阶问题:找到这两个错误节点的parent节点;然后再进行交换;但是交换两个错误的节点,要讨论的情况是在太多;
 * 需要思考的问题:
 * 1. e1,e2是否有一个是根节点,若有,谁是?
 * 2. e1,e2是否相邻,若相邻,谁是谁的父节点
 * 3. e1,e2分别是其父节点的左子节点/右子节点
 */
public class Problem_10_RecoverBST {


    //使用了非递归的方式来进行中序遍历,这样可以避免pre传值的问题;
    public static Node[] getTwoErrNodes(Node head) {
        Node[] errs = new Node[2];
        if (head == null) {
            return errs;
        }
        Stack<Node> stack = new Stack<Node>();
        Node pre = null;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                //比较晦涩,不建议这么写
                //本应该是 pre<head,中序遍历,递增的,如果不是,就要把pre放入error中,放在第一个还是第二个;
                //第一次应是pre放错位置了,第二次应是head放错位置了;
                if (pre != null && pre.value > head.value) {
                    errs[0] = errs[0] == null ? pre : errs[0];
                    errs[1] = head;
                }
                pre = head;
                head = head.right;
            }
        }
        return errs;
    }

    /*
    传入的是两个错误的节点,根据这两个错误的节点,找到这两个错误节点的parent;
     */
    public static Node[] getTwoErrParents(Node head, Node e1, Node e2) {
        Node[] parents = new Node[2];
        if (head == null) {
            return parents;
        }
        //这里依然是使用非递归的方法进行;
        Stack<Node> stack = new Stack<Node>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.left == e1 || head.right == e1) {
                    parents[0] = head;
                }
                if (head.left == e2 || head.right == e2) {
                    parents[1] = head;
                }
                head = head.right;
            }
        }
        return parents;
    }

    public static Node recoverTree(Node head) {
        Node[] errs = getTwoErrNodes(head);
        Node[] parents = getTwoErrParents(head, errs[0], errs[1]);

        Node e1 = errs[0];
        Node e1P = parents[0];
        Node e1L = e1.left;
        Node e1R = e1.right;

        Node e2 = errs[1];
        Node e2P = parents[1];
        Node e2L = e2.left;
        Node e2R = e2.right;

        if (e1 == head) {
            if (e1 == e2P) { // 情况一
                e1.left = e2L;
                e1.right = e2R;
                e2.right = e1;
                e2.left = e1L;
            } else if (e2P.left == e2) { // 情况二
                e2P.left = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            } else { // 情况三
                e2P.right = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            }
            head = e2;
        } else if (e2 == head) {
            if (e2 == e1P) { // 情况四
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2;
                e1.right = e2R;
            } else if (e1P.left == e1) { // 情况五
                e1P.left = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e1R;
            } else { // 情况六
                e1P.right = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e1R;
            }
            head = e1;
        } else {
            if (e1 == e2P) {
                if (e1P.left == e1) { // 情况七
                    e1P.left = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                } else { // 情况八
                    e1P.right = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                }
            } else if (e2 == e1P) {
                if (e2P.left == e2) { // 情况九
                    e2P.left = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                } else { // 情况十
                    e2P.right = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                }
            } else {
                if (e1P.left == e1) {
                    if (e2P.left == e2) { // 情况十一
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.left = e2;
                        e2P.left = e1;
                    } else { // 情况十二
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.left = e2;
                        e2P.right = e1;
                    }
                } else {
                    if (e2P.left == e2) { // 情况十三
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.right = e2;
                        e2P.left = e1;
                    } else { // 情况十四
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.right = e2;
                        e2P.right = e1;
                    }
                }
            }
        }
        return head;
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

    // for test
    public static boolean isBST(Node head) {
        if (head == null) {
            return false;
        }
        Stack<Node> stack = new Stack<Node>();
        Node pre = null;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (pre != null && pre.value > head.value) {
                    return false;
                }
                pre = head;
                head = head.right;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(7);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.right.left = new Node(6);
        head.right.right = new Node(8);
        head.left.left.left = new Node(1);
        printTree(head);
        System.out.println(isBST(head));

        // 情况1, 7 -> e1, 5 -> e2
        System.out.println("situation 1");
        Node head1 = new Node(7);
        head1.left = new Node(3);
        head1.right = new Node(5);
        head1.left.left = new Node(2);
        head1.left.right = new Node(4);
        head1.right.left = new Node(6);
        head1.right.right = new Node(8);
        head1.left.left.left = new Node(1);
        printTree(head1);
        System.out.println(isBST(head1));
        Node res1 = recoverTree(head1);
        printTree(res1);
        System.out.println(isBST(res1));

        // 情况2, 6 -> e1, 5 -> e2
        System.out.println("situation 2");
        Node head2 = new Node(6);
        head2.left = new Node(3);
        head2.right = new Node(7);
        head2.left.left = new Node(2);
        head2.left.right = new Node(4);
        head2.right.left = new Node(5);
        head2.right.right = new Node(8);
        head2.left.left.left = new Node(1);
        printTree(head2);
        System.out.println(isBST(head2));
        Node res2 = recoverTree(head2);
        printTree(res2);
        System.out.println(isBST(res2));

        // 情况3, 8 -> e1, 5 -> e2
        System.out.println("situation 3");
        Node head3 = new Node(8);
        head3.left = new Node(3);
        head3.right = new Node(7);
        head3.left.left = new Node(2);
        head3.left.right = new Node(4);
        head3.right.left = new Node(6);
        head3.right.right = new Node(5);
        head3.left.left.left = new Node(1);
        printTree(head3);
        System.out.println(isBST(head3));
        Node res3 = recoverTree(head3);
        printTree(res3);
        System.out.println(isBST(res3));

        // 情况4, 5 -> e1, 3 -> e2
        System.out.println("situation 4");
        Node head4 = new Node(3);
        head4.left = new Node(5);
        head4.right = new Node(7);
        head4.left.left = new Node(2);
        head4.left.right = new Node(4);
        head4.right.left = new Node(6);
        head4.right.right = new Node(8);
        head4.left.left.left = new Node(1);
        printTree(head4);
        System.out.println(isBST(head4));
        Node res4 = recoverTree(head4);
        printTree(res4);
        System.out.println(isBST(res4));

        // 情况5, 5 -> e1, 2 -> e2
        System.out.println("situation 5");
        Node head5 = new Node(2);
        head5.left = new Node(3);
        head5.right = new Node(7);
        head5.left.left = new Node(5);
        head5.left.right = new Node(4);
        head5.right.left = new Node(6);
        head5.right.right = new Node(8);
        head5.left.left.left = new Node(1);
        printTree(head5);
        System.out.println(isBST(head5));
        Node res5 = recoverTree(head5);
        printTree(res5);
        System.out.println(isBST(res5));

        // 情况6, 5 -> e1, 4 -> e2
        System.out.println("situation 6");
        Node head6 = new Node(4);
        head6.left = new Node(3);
        head6.right = new Node(7);
        head6.left.left = new Node(2);
        head6.left.right = new Node(5);
        head6.right.left = new Node(6);
        head6.right.right = new Node(8);
        head6.left.left.left = new Node(1);
        printTree(head6);
        System.out.println(isBST(head6));
        Node res6 = recoverTree(head6);
        printTree(res6);
        System.out.println(isBST(res6));

        // 情况7, 4 -> e1, 3 -> e2
        System.out.println("situation 7");
        Node head7 = new Node(5);
        head7.left = new Node(4);
        head7.right = new Node(7);
        head7.left.left = new Node(2);
        head7.left.right = new Node(3);
        head7.right.left = new Node(6);
        head7.right.right = new Node(8);
        head7.left.left.left = new Node(1);
        printTree(head7);
        System.out.println(isBST(head7));
        Node res7 = recoverTree(head7);
        printTree(res7);
        System.out.println(isBST(res7));

        // 情况8, 8 -> e1, 7 -> e2
        System.out.println("situation 8");
        Node head8 = new Node(5);
        head8.left = new Node(3);
        head8.right = new Node(8);
        head8.left.left = new Node(2);
        head8.left.right = new Node(4);
        head8.right.left = new Node(6);
        head8.right.right = new Node(7);
        head8.left.left.left = new Node(1);
        printTree(head8);
        System.out.println(isBST(head8));
        Node res8 = recoverTree(head8);
        printTree(res8);
        System.out.println(isBST(res8));

        // 情况9, 3 -> e1, 2 -> e2
        System.out.println("situation 9");
        Node head9 = new Node(5);
        head9.left = new Node(2);
        head9.right = new Node(7);
        head9.left.left = new Node(3);
        head9.left.right = new Node(4);
        head9.right.left = new Node(6);
        head9.right.right = new Node(8);
        head9.left.left.left = new Node(1);
        printTree(head9);
        System.out.println(isBST(head9));
        Node res9 = recoverTree(head9);
        printTree(res9);
        System.out.println(isBST(res9));

        // 情况10, 7 -> e1, 6 -> e2
        System.out.println("situation 10");
        Node head10 = new Node(5);
        head10.left = new Node(3);
        head10.right = new Node(6);
        head10.left.left = new Node(2);
        head10.left.right = new Node(4);
        head10.right.left = new Node(7);
        head10.right.right = new Node(8);
        head10.left.left.left = new Node(1);
        printTree(head10);
        System.out.println(isBST(head10));
        Node res10 = recoverTree(head10);
        printTree(res10);
        System.out.println(isBST(res10));

        // 情况11, 6 -> e1, 2 -> e2
        System.out.println("situation 11");
        Node head11 = new Node(5);
        head11.left = new Node(3);
        head11.right = new Node(7);
        head11.left.left = new Node(6);
        head11.left.right = new Node(4);
        head11.right.left = new Node(2);
        head11.right.right = new Node(8);
        head11.left.left.left = new Node(1);
        printTree(head11);
        System.out.println(isBST(head11));
        Node res11 = recoverTree(head11);
        printTree(res11);
        System.out.println(isBST(res11));

        // 情况12, 8 -> e1, 2 -> e2
        System.out.println("situation 12");
        Node head12 = new Node(5);
        head12.left = new Node(3);
        head12.right = new Node(7);
        head12.left.left = new Node(8);
        head12.left.right = new Node(4);
        head12.right.left = new Node(6);
        head12.right.right = new Node(2);
        head12.left.left.left = new Node(1);
        printTree(head12);
        System.out.println(isBST(head12));
        Node res12 = recoverTree(head12);
        printTree(res12);
        System.out.println(isBST(res12));

        // 情况13, 6 -> e1, 4 -> e2
        System.out.println("situation 13");
        Node head13 = new Node(5);
        head13.left = new Node(3);
        head13.right = new Node(7);
        head13.left.left = new Node(2);
        head13.left.right = new Node(6);
        head13.right.left = new Node(4);
        head13.right.right = new Node(8);
        head13.left.left.left = new Node(1);
        printTree(head13);
        System.out.println(isBST(head13));
        Node res13 = recoverTree(head13);
        printTree(res13);
        System.out.println(isBST(res13));

        // 情况14, 8 -> e1, 4 -> e2
        System.out.println("situation 14");
        Node head14 = new Node(5);
        head14.left = new Node(3);
        head14.right = new Node(7);
        head14.left.left = new Node(2);
        head14.left.right = new Node(8);
        head14.right.left = new Node(6);
        head14.right.right = new Node(4);
        head14.left.left.left = new Node(1);
        printTree(head14);
        System.out.println(isBST(head14));
        Node res14 = recoverTree(head14);
        printTree(res14);
        System.out.println(isBST(res14));

    }
}
