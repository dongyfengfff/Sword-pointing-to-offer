/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:对称二叉树
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class T59 {
    boolean isSymmetrical(TreeNode pRoot) {
        return isSymmetrical(pRoot, pRoot);
    }

    //使用递归来判断是否是对称的;
    //递归,首先明确一点,这个方法的功能传入两个节点,用来判断以这两个节点为根的**两棵树**是不是镜像对称的;
    //如何分步?首先传进来的肯定是两棵树的根,(现在要考虑成两棵树,不要想成一棵树)要判断的是node1的left和node2的right是否是;同时node1的right和node2的left是不是;
    //终止条件如下;
    private boolean isSymmetrical(TreeNode p1, TreeNode p2) {
        if(p1==null&&p2==null){
            return true;
        }

        if (p1==null||p2==null){
            return false;
        }

        if (p1.val!=p2.val){
            return false;
        }

        return isSymmetrical(p1.left,p2.right)&&isSymmetrical(p1.right,p2.left);
    }
}
