import java.util.ArrayList;

/**
 * Author: zhangxin
 * Time: 2016/11/14 0014.
 * Desc:输入一个链表，从尾到头打印链表每个节点的值。
 * 使用递归来解决这个问题:首先定义一个全局的list,用来存放各个节点的value
 *
 */
public class T05 {

    private ArrayList<Integer> list = new ArrayList<Integer>();

    //假装已经实现了该方法,调用func(node)就可以实现该功能,首先讨论终止条件,这是最简单的,就是node已经是null了,没有下一个节点了,所以要把全局的list返回,但是此时list中一个数据也没有;
    //接下来,把问题分为两步,链表分为两部分,第一部分:只包含头结点;第二部分包含头结点以后的节点;逻辑是:先用func(node.next)方法实现第二部分的翻转;然后在向list中添加head.val,集合
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode  == null) {
            return list;
        }
//        printListFromTailToHead(listNode.next).add(listNode.val); //下面是这句话分开的写法,可能更好理解
        printListFromTailToHead(listNode.next);
        list.add(listNode.val);
        return list;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode h2 = new ListNode(2);
        ListNode h3 = new ListNode(3);
        ListNode h4 = new ListNode(4);
        head.next = h2;
        h2.next=h3;
        h3.next=h4;
        h4.next = null;

        System.out.println(new T05().printListFromTailToHead(head));
//        new T05().func(head);
    }

    //从尾到头打印节点;
    void func(ListNode node){
        if (node == null){
            return ;
        }

        func(node.next);
        System.out.println(node.val);
    }
}
