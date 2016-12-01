/**
 * Author: zhangxin
 * Time: 2016/11/21 0021.
 * Desc:输入一个链表，反转链表后，输出链表的所有元素。
 */
public class T16 {
    public ListNode ReverseList(ListNode head) {
        ListNode node1, node2, node3;
        node1 = head;

        if (node1 == null) {
            return null;
        }
        node2 = node1.next;

        if (node2 == null) {
            return head;
        }
        node3 = node2.next;
        if (node3 == null) {
            node2.next = node1;
        }

        while (node3 != null) {
            node2.next = node1;
            node1 = node2;
            node2 = node3;
            node3 = node3.next;
        }
        node2.next = node1;
        head.next = null;
        return node2;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;
        ListNode head = n1;


        T16 t = new T16();
        head = t.ReverseList(head);

         while (head!=null){
            System.out.print(head.val+"->");
            head = head.next;
        }
    }
}
