package LintCode.dp;

import extended.chapter_3_binarytreeproblem.TreeNode;
import sun.awt.datatransfer.DataTransferer;

/**
 * Author: zhangxin
 * Time: 2017/9/10 0010.
 * Desc:给出一棵二叉树，寻找一条路径使其路径和最大，路径可以在任一节点中开始和结束
 * （路径和为两个节点之间所在路径上的节点权值之和）
 */
public class maxPathSum {
    /*
 * @param root: The root of binary tree.
 * @return: An integer
 */



    public int maxPathSum(TreeNode root) {
        // write your code here
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        maxPathSumCore(root, res);
        return res[0];
    }

    // 递归，假设实现了该功能，就是求某个节点的
    public int maxPathSumCore(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }

        int left = maxPathSumCore(root.left, res);
        int right = maxPathSumCore(root.right, res);

        res[0] = Math.max(res[0], Math.max(0, left) + Math.max(0, right) + root.val);
        //最后这里返回的是：max(left,right) + root.val，是因为最终是要找一条路径，返回的该root节点开始的单条路径的最大值；
        //但是上一步却不要计算最后的最大值，那么肯定要root + left + right 三者的和；
        return Math.max(0, Math.max(left, right) + root.val);
    }
}
