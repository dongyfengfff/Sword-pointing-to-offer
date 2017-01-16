package extended.chapter_3_binarytreeproblem;

/**
 * Author: zhangxin
 * Time: 2016/12/13 0013.
 * Desc: 找到二叉树中的最大搜索二叉树,返回该搜索二叉树的子节点;
 */
public class Problem_07_BiggestSubBSTInTree {

    public static Node biggestSubBST(Node head) {
        int[] record = new int[3]; // 0->size, 1->min, 2->max
        return posOrder(head, record);
    }

    //使用后续遍历的方式;依旧是用递归;
    /*
    你让我用递归来寻找最大的搜索二叉树,我不会,但是我假装我会,我先提供一个函数func(Node head,int[] record)
    这个函数传入的是以head为头结点开始,搜索的二叉树,record数组中有三个数
    第一个数字代表以head为首的搜索二叉树的节点数,第二个代表最小值,第三个代表最大值
    返回的是以该 head 为根节点中,最大节点数的头结点;
    然后我就开始递归了,首先处理一下终止情况,如果结点是空,这个我会,然后[0,100,-100],返回null;
    接着我开始后续遍历了,首先是左子树,然后是右子树,
     */
    public static Node posOrder(Node head, int[] record) {

        //递归的终止条件;
        if (head == null) {
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }

        int value = head.value;
        Node left = head.left;
        Node right = head.right;

        Node lBST = posOrder(left, record);
        int lSize = record[0];
        int lMin = record[1];
        int lMax = record[2];

        Node rBST = posOrder(right, record);
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];

        //正常情况下,取的就是lMin和rMax,但是如果是叶子节点的话,就是value本身了;
        record[1] = Math.min(lMin, value);
        record[2] = Math.max(rMax, value);
        //这个判断条件很重要,主要一项不满足,那么就不能完美上移;
        if (left == lBST && right == rBST && lMax < value && value < rMin) {
            record[0] = lSize + rSize + 1;
            return head;
        }
        record[0] = Math.max(lSize, rSize);
        return lSize > rSize ? lBST : rBST;
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

       /* Node head = new Node(6);
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
        head.right.right.right = new Node(16);*/

        Node head = new Node(6);
        head.left = new Node(3);
        head.left.left = new Node(2);
        head.left.right = new Node(7);
        head.right = new Node(9);
        head.right.left = new Node(8);
        head.right.right = new Node(10);

        // printTree(head);
        Node bst = biggestSubBST(head);
        System.out.println(bst.value);
        // printTree(bst);

    }
}


/*
思路解析:
首先使用后序遍历,从后往前找
理想情况下,顺次找上来,直接就可以用了
但是有可能会出现不满足的情况,那么每次往上返的时候,返回的就不是理想的节点了,这时候会出现左或右节点不等于返回的节点
那么就不能简单的 left+right+1,而是返回数量最大的那一个;

完美上移的条件是:(left == lBST && right == rBST && lMax < value && value < rMin)三个条件均满足,否则就不能完美上移;
前两个条件是检查是否还原来的节点;
后一个条件是检查 是否满足搜索二叉树的条件;
关于终止条件是如何处理的?
针对于叶子节点其数组时[1,本身,本身]
针对于叶子节点的子节点--> null;[0,int.max,int.min]这样的设置,就可以保证传给叶子节点时,其数组为[1,本身,本身]
 */