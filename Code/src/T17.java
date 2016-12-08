/**
 * Author: zhangxin
 * Time: 2016/11/21 0021.
 * Desc:输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class T17 {
    //这个方法中的逻辑有点乱,看下面的逻辑比较清晰
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode head0;
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        //重新定义了 head1,和head2,整个操作在head1上进行,hea2则为辅助插入;
        ListNode head1 = list1.val > list2.val ? list2 : list1;
        ListNode head2 = head1 == list1 ? list2 : list1;

        head0 = head1;  //最终要返回的 head0;
        //head1 = head1.next;

        ListNode t1, t2;
        while (head1.next != null) {
            if (head1.next.val < head2.val) {
                head1 = head1.next;
            } else {
                t1 = head1.next;
                head1.next = head2;
                head1 = head1.next;
                head2 = t1;
            }
        }
        head1.next = head2;
        return head0;
    }

    void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }


    //下面的逻辑清晰一点;
    public ListNode Merge2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        //最终要返回的 head;
        ListNode head = list1.val < list2.val ? list1 : list2;

        //总体的思路是 cur1是目前最小的链,以后就在cur1上进行操作,也就是说把cur2中的节点插入到cur1链中;
        //附带的 pre 节点是一个辅助节点,很有用;
        ListNode cur1 = (head == list1 ? list1 : list2);
        ListNode cur2 = (head == list1 ? list2 : list1);
        ListNode pre = null;
        ListNode next = null;

        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                pre = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }

        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }


    public static void main(String[] args) {
        T17 t = new T17();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = null;

        ListNode t1 = new ListNode(2);
        ListNode t2 = new ListNode(4);
        ListNode t3 = new ListNode(6);
        t1.next = t2;
        t2.next = t3;
        t3.next = null;

        t.Merge(n1, t1);
    }
}
