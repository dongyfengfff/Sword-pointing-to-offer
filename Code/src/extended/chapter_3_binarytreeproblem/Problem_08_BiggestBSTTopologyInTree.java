package extended.chapter_3_binarytreeproblem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Author: zhangxin
 * Time: 2016/12/13 0013.
 * Desc:给定一棵二叉树,找到这棵二叉树中符合搜索二叉树的最大拓扑的节点数;
 */
public class Problem_08_BiggestBSTTopologyInTree {

    //#####################方法1 思路简单,实现复杂,递归里面套着递归,时间复杂度高########################################

    //使用前序遍历的方式,对每一个当前的节点为根节点,判定以该节点为根节点的最大拓扑数;
    public static int bstTopoSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int max = maxTopo(head, head);  //这个max是以head为根节点得到的数量;
        max = Math.max(bstTopoSize1(head.left), max);//这个max是以head.left为根节点得到的数量;
        max = Math.max(bstTopoSize1(head.right), max);//这个max是以head.right为根节点得到的数量;
        //最后的到的数量就是max;  因为在递归中返回的总是最大的max,所以不用担心;
        return max;
    }

    //递归里面嵌套递归,太复杂了;
    public static int maxTopo(Node h, Node n) {
        if (h != null && n != null && isBSTNode(h, n, n.value)) {
            return maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
        }
        return 0;
    }

    //判断所给的两个子节点(左/右)是否满足左小于value,右大于value;
    public static boolean isBSTNode(Node h, Node n, int value) {
        if (h == null) {
            return false;
        }
        if (h == n) {
            return true;
        }
        return isBSTNode(h.value > value ? h.left : h.right, n, value);
    }

    //########################方法1_2#####################################
    //用我的理解方式实现简单的思路;这里是对每一个head返回一个值,比较最大值;这个方法只是实现了对于单个节点为根的情况下的最大拓扑数
    //完整版的还需要对整棵树进行遍历;也就出现了上述方法1中的递归套着递归的方法;
    public static int bstTopoSize0(Node head) {
        if (head == null) {
            return 0;
        }
        int max = 1;
        Queue<Node> queue = new LinkedList<Node>();
        Node node = head;
        if (node.left != null) {
            queue.offer(node.left);
        }
        if (node.right != null) {
            queue.offer(node.right);
        }
        while (!queue.isEmpty()) {
            node = queue.poll();
            //只要认定这个值比head小,就从左子树开始找,否则就从右子树开始找;从根源上杜绝;
            if (node.value < head.value && findNode(head.left, node)) {
                System.out.println(node.value);
                max++;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            } else if (node.value > head.value && findNode(head.right, node)) {
                System.out.println(node.value);
                max++;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return max;
    }

    //按照搜索二叉树的方式去遍历;
    static boolean findNode(Node node, Node target) {
        if (node == null) {
            return false;
        }

        if (node.value == target.value) {
            return true;
        } else if (target.value < node.value) {
            return findNode(node.left, target);
        } else {
            return findNode(node.right, target);
        }
    }


    //########################方法2 思路复杂,时间复杂度低吗#####################################
    public static class Record {
        public int l;
        public int r;

        public Record(int left, int right) {
            this.l = left;
            this.r = right;
        }

        @Override
        public String toString() {
            return "[" + l + "<--->" + r + "]";
        }
    }

    public static int bstTopoSize2(Node head) {
        Map<Node, Record> map = new HashMap<Node, Record>();
        return posOrder(head, map);
    }

    public static int posOrder(Node h, Map<Node, Record> map) {
        if (h == null) {
            return 0;
        }
        int ls = posOrder(h.left, map);
        int rs = posOrder(h.right, map);
        modifyMap(h.left, h.value, map, true);
        modifyMap(h.right, h.value, map, false);
        Record lr = map.get(h.left);
        Record rr = map.get(h.right);
        int lbst = lr == null ? 0 : lr.l + lr.r + 1;
        int rbst = rr == null ? 0 : rr.l + rr.r + 1;
        map.put(h, new Record(lbst, rbst));
        return Math.max(lbst + rbst + 1, Math.max(ls, rs));
    }

    public static int modifyMap(Node n, int v, Map<Node, Record> m, boolean s) {
        if (n == null || (!m.containsKey(n))) {
            return 0;
        }
        Record r = m.get(n);
        if ((s && n.value > v) || ((!s) && n.value < v)) {
            m.remove(n);
            return r.l + r.r + 1;
        } else {
            int minus = modifyMap(s ? n.right : n.left, v, m, s);
            if (s) {
                r.r = r.r - minus;
            } else {
                r.l = r.l - minus;
            }
            m.put(n, r);
            return minus;
        }
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
        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);
        //printTree(head);

        //System.out.println(bstTopoSize1(head));
        //System.out.println(bstTopoSize2(head));

        System.out.println(bstTopoSize2_1(head.right));
    }


    //###################针对方法2,我的实现################################
    /*
    感觉不用修改节点的值,修改值增加了代码量,但是效率上并没有什么提高;
    使用这个方法,只需要一次向上的后续遍历就可以完成;
     */
    static int max = 0;

    static int bstTopoSize2_1(Node head) {
        HashMap<Node, Record> map = new HashMap<Node, Record>();
        func(head, map);
        return max;
    }

    private static void func(Node head, HashMap<Node, Record> map) {
        if (head == null) {
            return;
        }

        func(head.left, map);
        func(head.right, map);

        //System.out.println(map);

        //表明head是叶子节点;
        if (head.left == null && head.right == null) {
            map.put(head, new Record(0, 0));
            return;
        }


        /*
        当前head,找左子树时,
         */
        int left = 0;
        int right = 0;
        if (head.left != null && head.left.value < head.value) {
            //left = map.get(head.left).l + map.get(head.left).r + 1;
            left = getLeft(head, map);
        }

        if (head.right != null && head.right.value > head.value) {
            //right = map.get(head.right).l + map.get(head.right).r + 1;
            right = getRight(head, map);
        }
        System.out.println(head.value + ":" + left + "-" + right);
        map.put(head, new Record(left, right));
        max = Math.max(max, left + right + 1);
    }

    //精髓就在下面这两个函数;
    //传进来的是相对根节点;
    private static int getLeft(Node head, HashMap<Node, Record> map) {
        int left = 0;
        Node node = head.left;
        //可用
        if (node != null && node.value < head.value) {
            left += 1 + map.get(node).l;
            Node pre;
            while (node.right != null) {
                pre = node;
                node = node.right;
                if (head.value > node.value && node.value > pre.value) {
                    left += 1 + map.get(node).l;
                } else {
                    break;
                }
            }
        }
        return left;
    }

    private static int getRight(Node head, HashMap<Node, Record> map) {
        int right = 0;
        Node node = head.right;
        if (node != null && node.value > head.value) {
            right += 1 + map.get(node).r;

            Node pre;
            while (node.left != null) {
                pre = node;
                node = node.left;
                if (head.value < node.value && node.value < pre.value) {
                    right += 1 + map.get(node).r;
                } else {
                    break;
                }
            }
        }
        return right;
    }

}
