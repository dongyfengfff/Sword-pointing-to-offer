package extended.chapter_3_binarytreeproblem;

/**
 * Author: zhangxin
 * Time: 2016/12/13 0013.
 * Desc:
 */
public class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
        this.value = data;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
