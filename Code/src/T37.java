/**
 * Author: zhangxin
 * Time: 2016/11/23 0023.
 * Desc:两个链表的第一个公共点;
 */
public class T37 {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        int len1 = 0, len2 = 0;
        ListNode p1 = pHead1, p2 = pHead2;
        while (p1 != null) {
            len1++;
            p1 = p1.next;
        }
        while (p2 != null) {
            len2++;
            p2 = p2.next;
        }

        p1 = pHead1;
        p2 = pHead2;

        int detal = len1 - len2;
        if (len1 > len2) {
            for (int i = 0; i < detal; i++) {
                p1 = p1.next;
            }
        } else if (len1 < len2) {
            detal *= -1;
            for (int i = 0; i < detal; i++) {
                p2 = p2.next;
            }
        }
        while (p1 != null) {
            if (p1 == p2) {
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return null;
    }
}
