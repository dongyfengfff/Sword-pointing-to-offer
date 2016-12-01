import java.util.ArrayList;

/**
 * Author: zhangxin
 * Time: 2016/11/14 0014.
 * Desc:输入一个链表，从尾到头打印链表每个节点的值。
 */
public class T05 {

    private ArrayList<Integer> list = new ArrayList<Integer>();

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode  == null) {
            return list;
        }
        printListFromTailToHead(listNode.next).add(listNode.val);
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

    }
}
