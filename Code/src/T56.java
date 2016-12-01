/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:链表中环的入口
 */
public class T56 {
    public ListNode EntryNodeOfLoop(ListNode pHead) {
       /* if (pHead==null){
            return null;
        }*/
        ListNode p1 = pHead, p2 = pHead;

        //进入到圈中,到底有没有环还不一定
        while (p1 != null) {
            p1 = p1.next;
            if (p2.next != null) {
                p2 = p2.next.next;
            } else {
                return null;
            }
            if (p1 == p2) {
                break;
            }
        }

        //没环...
        if (p1 == null) {
            return null;
        }


        //拿到圈的个数
        int count = getCircleCount(p1);

        //让p1先走count个,然后一起走,相遇的地方就是入口;
        p1 = pHead;
        p2 = pHead;
        for (int i = 0; i < count; i++) {
            p1 = p1.next;
        }

        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    int getCircleCount(ListNode p1) {
        ListNode p2 = p1.next;
        int count = 1;
        while (p2 != p1) {
            p2 = p2.next;
            count++;
        }
        return count;
    }
}
