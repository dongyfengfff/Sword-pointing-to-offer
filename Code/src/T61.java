import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:按之字顺序打印二叉树
 * 请实现一个函数按照之字形打印二叉树
 * 即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 *
 * 第二次想着用上次打印多行的,也用一个cur和pre,但是这个用之字,使用两个栈,这样就不用计数了,直接用栈的大小本身就可以计数;
 */
public class T61 {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        Stack<TreeNode> s1 = new Stack<TreeNode>(); //放奇数行,从左到右;
        Stack<TreeNode> s2 = new Stack<TreeNode>(); //放偶数行,从右到左;
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        if (pRoot == null) {
            return lists;
        }
        boolean flag = false; // true 用 s1;
        s1.push(pRoot);
        while (!(s1.isEmpty() && s2.isEmpty())) {
            if (flag) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                while (!s2.isEmpty()) {
                    TreeNode node = s2.pop();
                    list.add(node.val);
                    if (node.right != null) {
                        s1.push(node.right);
                    }
                    if (node.left != null) {
                        s1.push(node.left);
                    }
                }
                lists.add(list);
                flag = false;
            } else {
                ArrayList<Integer> list = new ArrayList<Integer>();
                while (!s1.isEmpty()) {
                    TreeNode node = s1.pop();
                    list.add(node.val);
                    if (node.left != null) {
                        s2.push(node.left);
                    }
                    if (node.right != null) {
                        s2.push(node.right);
                    }
                }
                lists.add(list);
                flag = true;
            }
        }
        LinkedList list = new LinkedList();
        return lists;
    }
}
