package extended.chapter_3_binarytreeproblem;

import java.util.HashMap;

/*先参考如何用数组解决这个问题,求数组中子数组的和为给定值的最长子数字问题;使用了一个 HashMap来解决问题;
在二叉树中也采用类似的方法;
采用前序遍历的方法
*/

public class Problem_06_LongestPathSum {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int getMaxLength(Node head, int sum) {
        HashMap<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
        sumMap.put(0, 0); // important,放一个 0,0就是为了在算出值的时候进行相减,然后得到level;
        // head,sum,preSum,level,maxLen,sumMap;
        // 注:根节点为第一层
        return preOrder(head, sum, 0, 1, 0, sumMap);
    }

    /***
     * 前序遍历
     * @param head 局部根节点
     * @param sum 当前的sum
     * @param preSum 之前的sum
     * @param level 当前的深度
     * @param maxLen 当前最大长度
     * @param sumMap 保存值的 hash map
     * @return
     */
    public static int preOrder(Node head, int sum, int preSum, int level,
                               int maxLen, HashMap<Integer, Integer> sumMap) {
        System.out.println("map:" + sumMap);
        if (head == null) {
            return maxLen;
        }
        System.out.print(head.value + "->");
        int curSum = preSum + head.value;

        //如果当前节点的值是 0 ,那么 curSum 的值不变,但是 level 增加,可是我们要找的是最长的路径,所以map中应该记录最小的level;
        if (!sumMap.containsKey(curSum)) {
            sumMap.put(curSum, level);
        }
        if (sumMap.containsKey(curSum - sum)) {
            maxLen = Math.max(level - sumMap.get(curSum - sum), maxLen);
        }

        //求 head 左子树的最大;
        maxLen = preOrder(head.left, sum, curSum, level + 1, maxLen, sumMap);

        //求 head 右子树的最大;
        maxLen = preOrder(head.right, sum, curSum, level + 1, maxLen, sumMap);

//note:与数组不同的是,二叉树是多个分支的,所以在找完一条路径之后,防止干扰其它路径的寻找,把当前路径当前level的删除掉;
        if (level == sumMap.get(curSum)) {
            System.out.println("remove:" + head.value + "->" + curSum);
            sumMap.remove(curSum);
        } else {
            System.out.println("not remove:" + head.value + "->" + curSum);
        }
        return maxLen;
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
        Node head = new Node(-3);
        head.left = new Node(3);
        head.right = new Node(-9);
        head.left.left = new Node(1);
        head.left.right = new Node(0);
        head.left.right.left = new Node(1);
        head.left.right.right = new Node(6);
        head.right.left = new Node(2);
        head.right.right = new Node(1);
        //printTree(head);
        //System.out.println(getMaxLength(head, 6));
        System.out.println(getMaxLength(head, 4));
        //System.out.println(getMaxLength(head, -9));

    }

}
