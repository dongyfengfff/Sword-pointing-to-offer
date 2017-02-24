/**
 * Author: zhangxin
 * Time: 2017/2/22 0022.
 * Desc: 输入一个链表，输出该链表中倒数第k个结点。
 *
 * 这个问题的难点在于如果给出的k值超过了链表的长度,该如何判断;
 */
public class T15 {
    public ListNode FindKthToTail(ListNode head,int k) {
        if (k<0||head==null) {
            return null;
        }

        ListNode node1 = head;
        for (int i = 0; i< k; i++) {
            node1 = node1.next;
            if (node1 == null) {
                if (i == k-1) {   //如果到了这里,表明返回的就是头结点;
                    return head;
                }
                //如果到了这一步,表明k值很大,已经超过了链表的长度;
                return null;
            }
        }

        ListNode node2 = head;
        while(node1!=null){
            node1 = node1.next;
            node2 = node2.next;
        }
        return node2;
    }
}
