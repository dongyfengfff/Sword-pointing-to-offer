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
