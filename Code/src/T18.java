/**
 * Author: zhangxin
 * Time: 2016/11/21 0021.
 * Desc:输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * 整体思路:这里用到了两个递归,第一个递归是使用前序遍历,找到对应的头结点
 * 第二个递归是找到对应的头结点后,使用前序遍历一次进行结点的匹配;
 */
public class T18 {
    //整个使用的就是递归的前序遍历;
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                result = DoesTree1HaveTree2(root1, root2);
            }
            //如果上一步匹配成功了,res==true,不会执行下面的逻辑;
            if (!result) {
                result = HasSubtree(root1.left, root2);
            }
            if (!result) {
                result = HasSubtree(root1.right, root2);
            }
        }
        return result;
    }

    //匹配上头结点,就开始进行匹配了;
    public boolean DoesTree1HaveTree2(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 != null) return false;  //root2还没有结束,root1已经结束了;
        if (root2 == null) return true;   //root2匹配完了,匹配结束;
        if (root1.val != root2.val) return false; //匹配不成功结束;

        //匹配成功了一个节点,接着匹配该节点的左子节点/右子节点;
        return DoesTree1HaveTree2(root1.left, root2.left) && DoesTree1HaveTree2(root1.right, root2.right);
    }
}
