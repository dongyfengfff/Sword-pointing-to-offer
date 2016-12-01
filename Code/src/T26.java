/**
 * Author: zhangxin
 * Time: 2016/11/22 0022.
 * Desc:
 * <p>
 * 注意空指针问题,random指针可能是 null;
 */

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

public class T26 {
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }


        RandomListNode currentNode = pHead;
        // 复制next指针
        while (currentNode != null) {
            RandomListNode node = new RandomListNode(currentNode.label);
            node.next = currentNode.next;
            currentNode.next = node;
            currentNode = node.next;
        }


        currentNode = pHead;
        // 复制 random 指针
        while (currentNode != null) {
            if (currentNode.random != null) {
                currentNode.next.random = currentNode.random.next;
            }
            currentNode = currentNode.next.next;
        }

        // 拆链表
        RandomListNode head = pHead.next;
        RandomListNode currentNode1 = head;
        currentNode = pHead;
        while (currentNode != null) {
            currentNode.next = currentNode.next.next;
            if (currentNode1.next != null) {
                currentNode1.next = currentNode1.next.next;
            }
            currentNode = currentNode.next;
            currentNode1 = currentNode1.next;
        }

        return head;
    }

    public RandomListNode Clone2(RandomListNode pHead) {
        if (pHead == null)
            return null;
        RandomListNode pCur = pHead;
        //复制next 如原来是A->B->C 变成A->A'->B->B'->C->C'
        while (pCur != null) {
            RandomListNode node = new RandomListNode(pCur.label);
            node.next = pCur.next;
            pCur.next = node;
            pCur = node.next;
        }
        pCur = pHead;
        //复制random pCur是原来链表的结点 pCur.next是复制pCur的结点
        while (pCur != null) {
            if (pCur.random != null)
                pCur.next.random = pCur.random.next;
            pCur = pCur.next.next;
        }
        RandomListNode head = pHead.next;
        RandomListNode cur = head;
        pCur = pHead;
        //拆分链表
        while (pCur != null) {
            pCur.next = pCur.next.next;
            if (cur.next != null)
                cur.next = cur.next.next;
            cur = cur.next;
            pCur = pCur.next;
        }
        return head;
    }
}
