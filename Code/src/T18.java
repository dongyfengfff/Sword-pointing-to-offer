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
            if (root1.val == root2.val) { //找到匹配的头,如果头相等,然后在展开匹配
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

    //NOTE:我的理解,更简洁的写法,注意的是提交时遇到了坑,我把返回语句中最后一个条件,root1.right写成了root1.left,调试了好久没看出问题...
    //此时根节点相同,用来判断两个根下的子树是不是相同了;
    //假设我这个方法能实现功能,首先判断跟是不是相等,根相等,然后判断左右子树是不是相等,如果相等,那么直接返回true;
    //分为三部分:根,左子树,右子树;
    //那么终止条件是什么呢?
    private boolean DoesTree1HaveTree2_(TreeNode root1,TreeNode root2){
        if (root2==null) {
            return true;
        }else if(root1==null) {
            return false;  //root2还没有结束,root1已经结束了;
        }
        return (root1.val==root2.val)&&DoesTree1HaveTree2_(root1.left,root2.left)&&DoesTree1HaveTree2_(root1.right,root2.right);
    }
}
