/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:链表中环的入口
 *
 * 首先:指定两个指针,一个走一步,一个走两步,如果最终能相遇,那么一定是在环中相遇的,如果一个为null了,那么表示没有环
 * 然后:加入此时有环,并在环中相遇,接下来,在从相遇位置开始走,直到走到相遇的位置,计算出环的长度
 * 然后:再制定两个指针,一个先走环的长度,然后两个指针在同时开始走,相遇的位置就是环的入口;
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

    //####第二次复习
    public ListNode EntryNodeOfLoop2(ListNode pHead){
        if (pHead==null) {
            return null;
        }

        // step1: 看能否相遇
        ListNode node1 = pHead,node2 = pHead.next;
        if (node2==null) {
            return null;
        }
        while(node1!=node2){
            //此时,node1,node2都不是null;
            node1 = node1.next;
            if(node2.next==null){
                return null;
            }else{
                node2 = node2.next.next;
            }
            if (node1 == null || node2 ==null) {
                return null;
            }
        }

        //能走到这一步,说明此时node1==node2了
        //step2:得到环的长度;
        ListNode node3 = node2.next;
        int count = 1;
        while(node3!=node2){
            count++;
            node3 = node3.next;
        }

        //step3,让一个指针先走count
        node1 = pHead;
        for (int i = 0; i< count; i++) {
            node1 = node1.next;
        }

        node2 = pHead;
        while(node1!=node2){
            node1 = node1.next;
            node2 = node2.next;
        }

        return node2;
    }
}
