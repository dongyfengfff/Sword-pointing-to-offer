import java.util.ArrayList;

/**
 * Author: zhangxin
 * Time: 2016/11/22 0022.
 * Desc: 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * TODO:目前并没有什么很好的方法;
 */
public class T25 {
    ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> list = new ArrayList<Integer>();

    //我感觉这个版本 写的已经很好了,但是不满足路径的定义,原题目中路径是从跟节点到叶子节点;而这个功能实现的是从根节点到任意节点的路径;
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return lists;
        }
        findCore(root, target);
        return lists;
    }

    private void findCore11111(TreeNode root, int target) {
        if (root == null) {
            return;
        }

        list.add(root.val);
        if (root.val == target) {
            ArrayList<Integer> list0 = new ArrayList<Integer>();
            list0.addAll(list);
            lists.add(list0);
        }

        findCore11111(root.left, target - root.val);
        if (root.left != null) {
            list.remove(list.size() - 1);
        }
        findCore11111(root.right, target - root.val);
        if (root.right != null) {
            list.remove(list.size() - 1);
        }
    }


    private void findCore(TreeNode root, int target) {
        if (root == null) {
            return;
        }

        list.add(root.val);
        //叶子节点,在这里判断;
        if (root.left == null && root.right == null) {
            if (root.val == target) {
                ArrayList<Integer> list0 = new ArrayList<Integer>();
                list0.addAll(list);
                lists.add(list0);
            }
        }


        findCore(root.left, target - root.val);
        if (root.left != null) {
            list.remove(list.size() - 1);
        }
        findCore(root.right, target - root.val);
        if (root.right != null) {
            list.remove(list.size() - 1);
        }
    }

    public void test(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println("当前的节点 " + node.val);
        list.add(node.val);
        System.out.println(list);
        test(node.left);
        if (node.left != null) {
            list.remove(list.size() - 1);
        }
        test(node.right);
        if (node.right != null) {
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        /*List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        int len = list.size();
        System.out.println(len);
        list.remove(len-1);
        System.out.println(list.size());
        list.add(4);
        System.out.println(list);*/
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);

        T25 t25 = new T25();
        System.out.println(t25.FindPath(root, 22));
    }
}
