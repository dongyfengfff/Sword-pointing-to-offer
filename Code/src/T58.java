/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:二叉树的下一个节点
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 *
 * 要分析的情况很多,很容易在面试中问到,这个很费时间...
 */
class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}

public class T58 {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        //(1) 先看有没有右子节点,如果有,是最简单的一种形式
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }

        //执行到这一步,说明pNode没有右子节点;
        //(2), 没有右子节点,找父节点,先考虑一种特殊情况,本身是根节点,又没有右子节点,
        TreeLinkNode parent = pNode.next;
        if (parent == null) {
            //本身是根节点,有没有右子节点,返回null
            return null;
        }

        //(3)没有右子节点,本身是左节点,直接返回父节点
        if (parent.left == pNode) {
            return parent;
        }

        //(4)没有右子节点,本身还是右子节点,不断往上找
        TreeLinkNode pre = parent;
        parent = parent.next;
        while (parent != null) {
            if (parent.left == pre) {
                return parent;
            }
            pre = parent;
            parent = parent.next;
        }
        return null;
    }
}
