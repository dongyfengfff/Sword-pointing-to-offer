/**
 * Author: zhangxin
 * Time: 2016/11/21 0021.
 * Desc:输入一个链表，反转链表后，输出链表的所有元素。
 */
public class T16 {
    public ListNode ReverseList(ListNode head) {
        ListNode node1, node2, node3;  //为了在反抓的时候保证能找到下一个节点,必须保持三个相邻node的引用;
        node1 = head;

        if (node1 == null) {  //输入链表为空的情况;
            return null;
        }
        node2 = node1.next;

        if (node2 == null) {  //链表只有一个节点的情况;
            return head;
        }
        node3 = node2.next;
      /*  if (node3 == null) {
            node2.next = node1;
        }*/

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

    //使用递归的方法做;
    public ListNode ReverseList1(ListNode head) {
        if(head==null){  //疏忽了所有程序第一步要检查的空情况,如果输入的链表本身为空,那么直接返回null;
            return null;
        }

        //首先解决最后的情况;如果是最后一个节点,直接返回该节点;
        if (head.next==null) {
            return head;
        }

        //能执行到这一步,说明head不是最后一个节点,其还有next;
        ListNode next = head.next;
        ListNode node =  ReverseList(head.next);
        next.next = head;
        head.next = null;  //注意每次都将其置0,最终原始的head的next也会是0;
        return node;
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
        head = t.ReverseList1(head);

         while (head!=null){
            System.out.print(head.val+"->");
            head = head.next;
        }
    }
}
