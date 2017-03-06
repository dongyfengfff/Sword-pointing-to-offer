import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 * 1. 对于序列化：使用前序遍历，递归的将二叉树的值转化为字符，并且在每次二叉树的结点不为空时，
 * 在转化val所得的字符之后添加一个','作为分割。对于空节点则以 '#' 代替。
 * <p>
 * 2. 对于反序列化：按照前序顺序，递归的使用字符串中的字符创建一个二叉树
 * 特别注意： 在递归时，递归函数的参数一定要是char ** ，这样才能保证每次递归后指向字符串的指针会随着递归的进行而移动！！！
 * <p>
 * 题目指定了使用前序遍历的方法,如果么有指定,单纯的实现序列化,可以使用层遍历的方法,更为便捷;
 * TODO:对于该题目的一个拓展:题目指定的是前序遍历,如果是后续呢?如果是中序呢?另外要熟练<<算法4th>>中关于二叉树的讲解,练习递归;这种递归好像和之前的想法不一样,疑惑的地方是不知道怎么就返回了,原来是在这判断节点为null的情况下返回;
 */
public class T62 {

    StringBuilder sb = new StringBuilder();

    //使用前序遍历
    String Serialize(TreeNode root) {
        if (root == null) {
            sb.append("#,");
            return sb.toString();
        }

        sb.append(root.val).append(",");
        Serialize(root.left);
        Serialize(root.right);
        return sb.toString();
    }


    //正确的使用方法:递归
    TreeNode Deserialize(String str) {
        String[] nodes = str.split(",");
        Queue<String> queue = new LinkedList<String>();

        for (int i = 0; i < nodes.length; i++) {
            //note:offer与add方法的区别是:前者在容量不够用时自动扩容,后者直接报错;
            queue.offer(nodes[i]);
        }
        return deserializeCore(queue);
    }

    /*
    * 使用递归来构建,不会
    * 你让我用递归构建反序列化,我不会,但是我假装我会,我提供了如下的方法TreeNode func(Queue<String> queue);
    * 什么时候终止?如果拿到的字符串是"#",返回null;
    * 否则我就创建一个节点,从queue中剩下的节点中构建该节点的左子树;左子树构建完了,构建右子树;
    * */
    TreeNode deserializeCore(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;  //核心在这,在这里返回null了,所以一次递归就结束
        }

        //这个head就是queue中的首节点,最外层的节点;
        TreeNode head = new TreeNode(Integer.valueOf(value));
        head.left = deserializeCore(queue);
        head.right = deserializeCore(queue);
        return head;
    }

    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);

        node0.left = node1;
        node0.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        node3.left = null;
        node3.right = null;
        node4.left = null;
        node4.right = null;
        node5.left = null;
        node5.right = null;
        node6.left = null;
        node6.right = null;

        T62 t = new T62();
        t.Serialize(node0);
        System.out.println(t.sb);
        TreeNode node = t.Deserialize(t.sb.toString());
        t.sb = new StringBuilder();
        System.out.println(t.Serialize(node));
    }
}

/*
反序列化的过程:1,2,4,#,#,5,#,#,3,6,#,#,7,#,#,
head:1
    head:2
        head:4
            # return null
            # return null  //4的左右都是null,返回4了
        return 4
        head:5
            # return null
            # return null  //5的左右都是null,返回5了
        return 5
    return 2 //4,5都是2的左右节点,安置好,返回2,作为1的left;

    head:3
        head:6
            # return null
            # return null  //
        return 6
        head:7
            # return null
            # return null  //
        return 7
    return 3
return 1
 */


 /*
        //失败的尝试
        TreeNode Deserialize(String str) {
            String[] nodes = str.split(",");
            for (String node : nodes) {
                System.out.println(node);
            }
            if (nodes.length < 1) {
                return null;
            }
            Stack<TreeNode> stack = new Stack<TreeNode>();
            int index = 0;
            TreeNode head = new TreeNode(Integer.valueOf(nodes[index]));
            stack.push(head);
            boolean flag = false;
            *//*while (!stack.isEmpty()){


            if(flag){

            }

        }*//*

        for (index = 1; index < nodes.length; index++) {
            String s = nodes[index];
            //遇到的第一个#,并且flag是false,那么将flag设为true,下一个节点就是右节点
            if (s.equals("#") && !flag) {
                flag = true;
                stack.peek().left = null;
                continue;
            } else if (!s.equals("#")) {
                int i = Integer.valueOf(s);
                TreeNode node = new TreeNode(i);
                stack.peek().left = node;
                stack.push(node);
            }

            if (flag) {
                flag = false;
                if (s.equals("#")) {
                    stack.peek().right = null;
                    stack.pop();
                }
                //stack.pop();
            }

        }
        return head;
    }*/
