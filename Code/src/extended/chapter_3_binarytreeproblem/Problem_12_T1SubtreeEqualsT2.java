package extended.chapter_3_binarytreeproblem;

/**
 * Author: zhangxin
 * Time: 2016/12/15 0015.
 * Desc:t1树中是否包含与t2拓扑结构完全相同的子树,这与前一个题目不同的是,这个题目要求一旦头结点完全匹配上,那么二者子节点要求全部匹配上;
 * 思路:既然要求完全匹配,那么就序列化t1和t2,然后再t1的序列化字符串中中查找t2序列化的字符串是否存在;
 */
public class Problem_12_T1SubtreeEqualsT2 {
    public static boolean isSubtree(Node t1, Node t2) {
        String t1Str = serialByPre(t1);
        String t2Str = serialByPre(t2);
        return getIndexOf(t1Str, t2Str) != -1;
    }

    //使用前序遍历的递归方法实现二叉树的序列化
    public static String serialByPre(Node head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    // KMP
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int[] nextArr = getNextArray(ms);
        int index = 0;
        int mi = 0;
        while (index < ss.length && mi < ms.length) {
            if (ss[index] == ms[mi]) {
                index++;
                mi++;
            } else if (nextArr[mi] == -1) {
                index++;
            } else {
                mi = nextArr[mi];
            }
        }
        return mi == ms.length ? index - mi : -1;
    }

    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[] { -1 };
        }
        int[] nextArr = new int[ms.length];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < nextArr.length) {
            if (ms[pos - 1] == ms[cn]) {
                nextArr[pos++] = ++cn;
            } else if (cn > 0) {
                cn = nextArr[cn];
            } else {
                nextArr[pos++] = 0;
            }
        }
        return nextArr;
    }

    public static void main(String[] args) {
        Node t1 = new Node(1);
        t1.left = new Node(2);
        t1.right = new Node(3);
        t1.left.left = new Node(4);
        t1.left.right = new Node(5);
        t1.right.left = new Node(6);
        t1.right.right = new Node(7);
        t1.left.left.right = new Node(8);
        t1.left.right.left = new Node(9);

        Node t2 = new Node(2);
        t2.left = new Node(4);
        t2.left.right = new Node(8);
        t2.right = new Node(5);
        t2.right.left = new Node(9);

        System.out.println(isSubtree(t1, t2));

    }
}
