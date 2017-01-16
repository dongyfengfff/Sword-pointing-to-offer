package extended.chapter_3_binarytreeproblem;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhangxin
 * Time: 2016/12/14 0014.
 * Desc: 按照层次打印二叉树/按照之字打印二叉树;
 * 与之前不同的是,这里需要每打印一层换一行,这就有些麻烦;
 * 对于按照之字打印的情况,如果不需要换行,直接使用两个stack分别盛放奇数行/偶数行就可以解决问题
 * 如果只能用一个数据结构,那么就使用双端队列,然后设置两个last标记,cLast/nLast两个标记才能解决问题;
 * 之字打印的顺序是:上一行第一个节点的第一个字节是下一行的末尾节点;因此nLast的初始化时机是在本行第一个节点时判断其孩子节点,然后标记
 */
public class Problem_09_PrintBinaryTreeByLevelAndZigZag {
    public static void printByLevel(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        int level = 1;
        Node last = head;
        Node nLast = null;
        queue.offer(head);
        System.out.print("Level " + (level++) + " : ");
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.value + " ");
            if (head.left != null) {
                queue.offer(head.left);
                nLast = head.left;
            }
            if (head.right != null) {
                queue.offer(head.right);
                nLast = head.right;
            }
            if (head == last && !queue.isEmpty()) {
                System.out.print("\nLevel " + (level++) + " : ");
                last = nLast;
            }
        }
        System.out.println();
    }

    public static void printByZigZag(Node head) {
        if (head == null) {
            return;
        }
        Deque<Node> dq = new LinkedList<Node>();
        int level = 1;
        boolean lr = true;
        Node last = head;
        Node nLast = null;
        dq.offerFirst(head);
        pringLevelAndOrientation(level++, lr);
        while (!dq.isEmpty()) {
            if (lr) {
                head = dq.pollFirst();
                if (head.left != null) {
                    nLast = nLast == null ? head.left : nLast;
                    dq.offerLast(head.left);
                }
                if (head.right != null) {
                    nLast = nLast == null ? head.right : nLast;
                    dq.offerLast(head.right);
                }
            } else {
                head = dq.pollLast();
                if (head.right != null) {
                    nLast = nLast == null ? head.right : nLast;
                    dq.offerFirst(head.right);
                }
                if (head.left != null) {
                    nLast = nLast == null ? head.left : nLast;
                    dq.offerFirst(head.left);
                }
            }
            System.out.print(head.value + " ");
            if (head == last && !dq.isEmpty()) {
                lr = !lr;
                last = nLast;
                nLast = null;
                System.out.println();
                pringLevelAndOrientation(level++, lr);
            }
        }
        System.out.println();
    }

    public static void pringLevelAndOrientation(int level, boolean lr) {
        System.out.print("Level " + level + " from ");
        System.out.print(lr ? "left to right: " : "right to left: ");
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
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.right.left.left = new Node(7);
        head.right.left.right = new Node(8);

        /*printTree(head);

        System.out.println("===============");
        printByLevel(head);

        System.out.println("===============");
        printByZigZag(head);*/
        //printByLevel1(head);
        printByZigZag1(head);

       /* Deque<Integer> dq = new LinkedList<Integer>();
        dq.addFirst(1);
        dq.addFirst(2);
        dq.addLast(3);
        System.out.println(dq.pollFirst());
        System.out.println(dq.pollLast());
        System.out.println(dq);*/
    }

    public static void printByLevel1(Node head) {
        if (head == null) {
            return;
        }
        Node last = head;
        int level = 1;
        System.out.print(level++ + ":");
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.value);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (node == last) {
                System.out.println();
                System.out.print(level++ + ":");
                last = node.right == null ? node.left : node.right;
            }
        }

    }

    //设置为每次都是正着读,只是添加的时候循序不一样;
    public static void printByZigZag1(Node head) {
        if (head == null) {
            return;
        }

        int level = 1;
        Deque<Node> dq = new LinkedList<Node>();
        dq.addFirst(head);
        System.out.print(level++ + ":");
        boolean flag = true; //true:左右;false:右左;
        Node cLast = head;
        Node nLast = null;
        while (!dq.isEmpty()) {
            Node node;
            if (flag) {
                // ----->
                node = dq.pollFirst();
                if (nLast == null) {
                    nLast = node.left == null ? node.right : node.left;
                }
                System.out.print(node.value);

                if (node.left != null) {
                    dq.addLast(node.left);
                }
                if (node.right != null) {
                    dq.addLast(node.right);
                }

                if (cLast == node) {
                    cLast = nLast;
                    nLast = null;
                    flag = false;
                    if (cLast != null) {
                        System.out.println();
                        System.out.print(level++ + ":");
                    }
                }
            } else {
                //  <---------------
                node = dq.pollLast();
                if (nLast == null) {
                    nLast = node.right == null ? node.left : node.right;
                }
                System.out.print(node.value);
                if (node.right != null) {
                    dq.addFirst(node.right);
                }
                if (node.left != null) {
                    dq.addFirst(node.left);
                }

                if (cLast == node) {
                    flag = true;
                    cLast = nLast;
                    nLast = null;
                    if (cLast != null) {
                        System.out.println();
                        System.out.print(level++ + ":");
                    }
                }
            }


        }
    }
}
