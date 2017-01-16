package extended.chapter_3_binarytreeproblem;

import java.util.HashMap;

/**
 * Author: zhangxin
 * Time: 2016/12/20 0020.
 * Desc:根据pre&in 和 post&in的组合重构二叉树的方法已经在剑指offer.T6中实现过,方法类似,但是使用 pre&post构建二叉树思路与之前并不同
 *
 */
public class Problem_21_PreInPosArrayToTree {
    // 每个节点的孩子数都为0或2的二叉树才能被先序与后序重构出来
    public static Node prePosToTree(int[] pre, int[] pos) {
        if (pre == null || pos == null) {
            return null;
        }

        //map中保存的是后序数组中的 pos[i] 和 对应的 i;这样省的在下面不断的for循环来找分割点了;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < pos.length; i++) {
            map.put(pos[i], i);
        }
        return prePos(pre, 0, pre.length - 1, pos, 0, pos.length - 1, map);
    }

    public static Node prePos(int[] p, int pi, int pj, int[] s, int si, int sj,
                              HashMap<Integer, Integer> map) {
        Node head = new Node(s[sj--]); //生成头结点;
        if (pi == pj) {
            return head;
        }
        int index = map.get(p[++pi]);//这里先进行++,是因为原本的那个是头结点,要跳过
        head.left = prePos(p, pi, pi + index - si, s, si, index, map);
        head.right = prePos(p, pi + index - si + 1, pj, s, index + 1, sj, map);
        return head;
    }

    public static void main(String[] args) {
        int[] in = {4, 2, 5, 1, 6, 3, 7};
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] post = {4, 5, 2, 6, 7, 3, 1};

        prePosToTree(pre,post);
    }
}


/*
思路解析: 确定什么样的二叉树可以用pre和post还原回来;
pre的第一个元素应该和post的最后一个元素相同,都是整棵树的根节点;
确定了root后,接下来,确定root的left和right,这里的map.get(p[++pi]),首先++是跳过pre的第一个元素,也就是当前的根节点,对pre进行++后就是子树的左根节点;
这个左子树的根节点在post中的相对位置是靠后的;于是这个index就是分割点了;
同时也要注意终止条件
 */