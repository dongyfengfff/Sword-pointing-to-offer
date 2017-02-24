/**
 * Author: zhangxin
 * Time: 2017/2/21 0021.
 * Desc:O(1)时间删除链表节点;单向链表,给出头结点和待删除的节点;
 * 最基本的思路:从头结点开始遍历,找到该节点,然后删除,可是时间复杂度不满足条件;
 * 思路的转换:每个节点值保存了下一个节点的位置,题目中给出了两个节点,这里我们可以拿到待删除节点的下一个节点
 * 此时我们把下一个节点的内容复制给待删除的节点位置,然后把下一个节点删掉...
 * NOTE:缺点:无法删除最后的一个节点;对于头节点还需要特殊处理(不需要特殊处理...);
 */
public class T13 {

    void removeNode(ListNode head, ListNode node) {
        ListNode next = node.next;
        if (next != null) {
            node.val = next.val;
            node.next = next.next;
        }

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);

        T13 t13 = new T13();
//        t13.removeNode(head, head.next.next.next.next.next);  //把3删掉;
        t13.removeNode(head, head);
        ListNode node = head;
        while (node != null) {
            System.out.print(node.val + "-->");
            node = node.next;
        }

    }
}
