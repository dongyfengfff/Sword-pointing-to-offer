package extended.chapter_3_binarytreeproblem;

/**
 * Author: zhangxin
 * Time: 2016/12/15 0015.
 * Desc:t1树是否包含t2的全部拓扑结构
 */
public class Problem_11_T1ContainsT2Topology {
    public static boolean contains(Node t1, Node t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        return check(t1, t2) || contains(t1.left, t2) || contains(t1.right, t2);
    }

    //首先确定一点,使用前序遍历,但是不一定完全能匹配上,因为t2小,所以这时候要判断如果t2==null
    public static boolean check(Node h, Node t2) {
        //这时候认为是可以的
        if (t2 == null) {
            return true;
        }
        //值不相等,当然不匹配;
        if (h == null || h.value != t2.value) {
            return false;
        }
        //当前的头结点已经匹配上了,递归匹配头结点的左子树/右子树
        return check(h.left, t2.left) && check(h.right, t2.right);
    }

    public static void main(String[] args) {
        Node t1 = new Node(1);
        t1.left = new Node(2);
        t1.right = new Node(3);
        t1.left.left = new Node(4);
        t1.left.right = new Node(5);
        t1.right.left = new Node(6);
        t1.right.right = new Node(7);
        t1.left.left.left = new Node(8);
        t1.left.left.right = new Node(9);
        t1.left.right.left = new Node(10);

        Node t2 = new Node(2);
        t2.left = new Node(4);
        t2.left.left = new Node(8);
        t2.right = new Node(5);

        System.out.println(contains(t1, t2));

    }

    //#####################我的理解,简单的方法#############################
    public static boolean contains2(Node root1, Node root2) {
        boolean result = false;
        if (root1 != null && root2 != null) {
            if (root1.value == root2.value) {
                result = DoesTree1HaveTree2(root1, root2);
            }
            //如果上一步匹配成功了,res==true,不会执行下面的逻辑;
            if (!result) {
                result = contains2(root1.left, root2);
            }
            if (!result) {
                result = contains2(root1.right, root2);
            }
        }
        return result;
    }

    //匹配上头结点,就开始进行匹配了;
    public static boolean DoesTree1HaveTree2(Node root1, Node root2) {
        if (root1 == null && root2 != null) return false;  //root2还没有结束,root1已经结束了;
        if (root2 == null) return true;   //root2匹配完了,匹配结束;
        if (root1.value != root2.value) return false; //匹配不成功结束;

        //匹配成功了一个节点,接着匹配该节点的左子节点/右子节点;
        return DoesTree1HaveTree2(root1.left, root2.left) && DoesTree1HaveTree2(root1.right, root2.right);
    }
}
