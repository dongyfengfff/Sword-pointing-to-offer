package extended.chapter_3_binarytreeproblem;

/**
 * Author: zhangxin
 * Time: 2016/12/21 0021.
 * Desc:给出完全二叉树的头结点,返回该二叉树的节点总数;
 * note:这个题目最大的坑你知道在哪吗?  就是 + 的优先级比 << 的优先级要大
 * 所以:return (1 << (height - level)) + getCountCore(node.right, level + 1);
 * 和   return 1 << (height - level) + getCountCore(node.right, level + 1);
 * 两者的结果完全不同;
 */
public class Problem_24_CompleteTreeNodeNumber {
    public static int height = 0; //树的总高度

    public static int getCount(Node head) {
        if (head == null) {
            return 0;
        }

        Node node = head;
        height = getLevel(node);

        return getCountCore(node, 1);
    }

    //用递归处理,此时我不知道终止条件是什么,但是我知道怎么遍历...懵逼
    //考虑最终的情况是遍历到一个根节点,这个根节点只有一个左子节点,最后遍历到了这个左子节点,其他情况下不会到最后一层,就被公式计算了
    private static int getCountCore(Node node, int level) {
        if (level == height) {
            return 1;
        }

        int deep = getLevel(node.right);
        if (level + deep == height) {
            //说明node的左子树的满二叉树
            return (1 << (height - level)) + getCountCore(node.right, level + 1);
        } else {
            //说明node的右子树是满二叉树
            return  (1 << (height - level - 1)) + getCountCore(node.left, level + 1);
        }
    }

    //返回以node为根节点的高度(包括node所在的那一层)
    private static int getLevel(Node node) {
        int level = 0;
        while (node != null) {
            level++;
            node = node.left;
        }
        return level;
    }

    public static void main(String[] args) {
        Node root = new Node(1);

        root.left = new Node(2);

        root.left.left = new Node(4);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);

        root.left.right = new Node(5);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(11);

        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root.right.left.left = new Node(12);

        System.out.println(getCount(root));

    }
}
