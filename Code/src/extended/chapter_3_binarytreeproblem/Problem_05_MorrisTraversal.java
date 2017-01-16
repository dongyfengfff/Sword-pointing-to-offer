package extended.chapter_3_binarytreeproblem;

/**
 * Author: zhangxin
 * Time: 2016/11/14 0014.
 * Desc: 二叉树的神级遍历方法;时间复杂度O(n);空间复杂度 O(1)
 * 不用栈结构实现遍历,充分利用二叉树节点中left/right==null的指针,这就是 Morris 遍历方法;
 * 复习时先看中序遍历,这个最简单,然后再看前序遍历,最后再看后序遍历;
 */

public class Problem_05_MorrisTraversal {

 /*   public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }*/

    //中序遍历;
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {

                //在寻找以cur1为根的左子树的最右节点cur2, 后一个限制条件是说:cur2还没有指向cur1;
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }


                //如果还没有指过,就将cur2指向cur1
                //那么cur1的指向就完成了,接下来要完成cur1的下一层,继续该步骤,实现cur1左子树的最右节点的指向;
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;  //继续建链;不做任何打印什么的;
                } else {
                    //如果指定过了(部分区域已经指定完了),那么还将cur2的right指向null,之后就开始打印了
                    cur2.right = null;
                }

            }
            //cur1 目前是局部树的根
            System.out.print(cur1.value + " ");
            //按照 cur1的right方法走;
            cur1 = cur1.right;
        }
        System.out.println();
    }

    //前序遍历;
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
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
                    //打印的时机放在了cur1与cur2建链的时候,就打印cur1;
                    System.out.print(cur1.value + " ");
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            } else {
                System.out.print(cur1.value + " ");
            }
            //注:每次的移动都是向其right节点移动,然后要么建链要么拆链;
            cur1 = cur1.right;
        }
        System.out.println();
    }

    //后序遍历
    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
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
                    printEdge(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        printEdge(head);
        System.out.println();
    }


    public static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }
    //该函数的功能是以传入的节点为起始节点,以该节点和其右子节点形成的一条链,然后逆转该链.返回的是该链逆序后的头节点,也就是原本的尾节点;
    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
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
        head.right.right = new Node(7);
        //printTree(head);
       // morrisIn(head);
    	//morrisPre(head);
        morrisPos(head);
		//printTree(head);

    }

}


/*
中序遍历的步骤:
cur1 = 4,cur2 = 3, 3->4; cur1 = cur1.left;继续
cur1 = 2,cur2 = 1, 1->2; cur1 = cur1.left;继续
cur1 = 1,cur2 = null; 直接打印(1) cur1,cur1 = cur1.right;
cur1 = 2,cur2 = 1,但是 cur2.right = cur1,因此拆掉连接,直接打印cur1(2),cur1 = cur1.right;
cur1 = 3.cur2 = null; 直接打印(3),cur1 = cur1.right;
cur1 = 4;cur2 = 3,但是cur2.right = cur1,因此拆掉连接,直接打印cur1(4),cur1 = cur1.right;
cur1 = 6,cur2 = 5, 5->6; cur1 = cur1.left;继续
cur1 = 5,cur2 = null,直接打印cur1(5),cur1 = cur1.right;
cur1 = 6,cur2 = 5,但是cur2.right = cur1,因此拆掉连接,直接打印cur1(6),cur1 = cur1.right;
cur1 = 7,cur2 = null,直接打印cur1(7),cur1 = cur1.right;
cur1 = null,结束;

*/

/*
前序遍历:就是上述中序遍历的简单修改:
中->左->右:
步骤改为:依然是先建立左子树的最右节点与跟节点的连接,建立完成后便可立即回到根节点,打印
打印节点的时机是:不断向左子节点移动的过程;
 */