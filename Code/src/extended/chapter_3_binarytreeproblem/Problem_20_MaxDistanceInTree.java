package extended.chapter_3_binarytreeproblem;

/**
 * Author: zhangxin
 * Time: 2016/12/19 0019.
 * Desc:二叉树节点间的最大距离(经过的边+1),这里求的不是给定两个节点的距离,而是整个树中最大的距离...
 * 首先确定大体思路:使用后序遍历;
 * 最大距离来自三方面:
 * 1. 左子树中两节点最大距离
 * 2. 右子树中两节点最大距离
 * 3. 左子树里根节点最远距离 + 右子树里根节点最远距离 + 1
 * <p>
 * 因此这里用到了一个函数返回两个值: 一个用return 返回,一个用全局变量
 * TODO:进阶:给定两个节点,求二者之间的距离
 */
public class Problem_20_MaxDistanceInTree {
    public static int maxDistance(Node head) {
        int[] record = new int[1]; //定义一个全局变量,数组长度为1,还不如用一个整型变量...
        return posOrder(head, record);
    }

    //后续遍历;
    public static int posOrder(Node head, int[] record) {
        //终止条件;
        if (head == null) {
            record[0] = 0;
            return 0;
        }
        //左子树上的最大距离;
        int lMax = posOrder(head.left, record);
        int maxFromLeft = record[0]; //左子树上最远的点离head的距离;也可以理解为head.left的最大深度;

        //右子树上的最大距离;
        int rMax = posOrder(head.right, record);
        int maxFromRight = record[0];//右子树上最远的点离head的距离;

        //跨当前head节点的情况下的最大距离;
        int curNodeMax = maxFromLeft + maxFromRight + 1;
        record[0] = Math.max(maxFromLeft, maxFromRight) + 1; //head的最大深度;

        return Math.max(Math.max(lMax, rMax), curNodeMax);
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);
        head1.left.left.left = new Node(8);
        head1.right.left.right = new Node(9);
        System.out.println(maxDistance(head1));

        Node head2 = new Node(1);
        head2.left = new Node(2);
        head2.right = new Node(3);
        head2.right.left = new Node(4);
        head2.right.right = new Node(5);
        head2.right.left.left = new Node(6);
        head2.right.right.right = new Node(7);
        head2.right.left.left.left = new Node(8);
        head2.right.right.right.right = new Node(9);
        System.out.println(maxDistance(head2));

    }

    static int record; //子树中离根节点最远的距离

    public static int maxDistance1(Node head) {

        return posOrderCore(head, record);
    }


    //使用后续遍历:不会用,我只会求空姐点的情况,剩余情况在分析吧;
    //返回子树中最大的距离
    static int posOrderCore(Node head, int record) {
        if (head == null) {
            record = 0;
            return 0;
        }

        //记录左边的
        int lMax = posOrderCore(head.left, record);
        int maxLeft = record;


        //记录右边的
        int rMax = posOrderCore(head.right, record);
        int maxRight = record;

        //处理自己的
        record = Math.max(maxLeft, maxRight) + 1; //别忘了+1;
        return Math.max(Math.max(lMax, rMax), (maxLeft + maxRight + 1));  //什么情况下,左右跨度的距离会比单侧的距离小???
        //答:在左子树繁茂,右子树稀疏的时候,会出现这种情况,每次返回的都是左子树范围内的最大值,而该左子树范围内的最大值,就是子树中相对节点的跨域最大值
    }
}
