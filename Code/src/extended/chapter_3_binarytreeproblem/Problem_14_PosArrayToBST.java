package extended.chapter_3_binarytreeproblem;

/**
 * Author: zhangxin
 * Time: 2016/12/16 0016.
 * Desc:给出一个整形数组(其中的数均不重复),判断是否是搜索二叉树后续遍历的结果
 * 进阶:根据该整型数组,重组搜索二叉树
 * 搜索二叉树:左子树均小于根节点,右子树均大于根节点,并无层数要求;
 */
public class Problem_14_PosArrayToBST {
    public static boolean isPostArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        return isPost(arr, 0, arr.length - 1);
    }

    //没有我的方法直观;
    public static boolean isPost(int[] arr, int start, int end) {
        if (start == end) {
            return true;
        }
        int less = -1;
        int more = end;
        //也是从头到尾遍历一遍;
        for (int i = start; i < end; i++) {
            //找到分割线,左子树部分
            if (arr[end] > arr[i]) {
                less = i;
            } else {
                //查看右子树是否比more小;这个more只在初始时赋值一次,如果后面遇到比arr[end]小的元素,就还原回end了
                //可是中间遇到一个小元素,会指向上述的if分支,more也就后移了;怎么处理的?
                more = more == end ? i : more;
            }
        }
        if (less == -1 || more == end) {
            return isPost(arr, start, end - 1);
        }
        //原来是在这执行了一次判断;
        if (less != more - 1) {
            return false;
        }
        return isPost(arr, start, less) && isPost(arr, more, end - 1);
    }

    //fixme:并没有进行合法性判断,直接默认就是搜索二叉树,这样是不合理的.
    public static Node posArrayToBST(int[] posArr) {
        if (posArr == null) {
            return null;
        }
        return posToBST(posArr, 0, posArr.length - 1);
    }

    public static Node posToBST(int[] posArr, int start, int end) {
        if (start > end) {
            return null;
        }
        Node head = new Node(posArr[end]);
        int less = -1;
        int more = end;
        for (int i = start; i < end; i++) {
            if (posArr[end] > posArr[i]) {
                less = i;
            } else {
                more = more == end ? i : more;
            }
        }
        //这干脆连判断都没有直接默认就是合理的搜索二叉树;
        head.left = posToBST(posArr, start, less);
        head.right = posToBST(posArr, more, end - 1);
        return head;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 6, 0, 7, 4};
        System.out.println(isPost(arr, 0, arr.length - 1));
        // printTree(posArrayToBST(arr));
        System.out.println(isPostCore(arr, 0, arr.length - 1));
        System.out.println(isPost(arr));
        System.out.println();

        System.out.println("你的");
        printBST(posArrayToBST(arr));
        System.out.println();
        printBST(buildBST(arr));

    }

    //###################我的解法,采用这个,比书上简单;##############
    static boolean isPost(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        return isPostCore(arr, 0, arr.length - 1);
    }

    static boolean isPostCore(int[] arr, int start, int end) {
        if (start >= end) {
            return true;
        }
        int key = arr[end];
        boolean flag = true;//用来判断右子树是否合法;
        int index = start;

        //就要分两个for循环来做;
        for (; index < end; index++) {
            if (arr[index] > key) {
                break;
            }
        }
        //还要检测一下右边是不是比key都大
        for (int i = index; i < end; i++) {
            if (arr[i] < key) {
                flag = false;
                break;
            }
        }
        //System.out.println(start + "<--->" + (index - 1) + ":" + index + "<--->" + end);
        //注意界限的划分啊;
        return flag && isPostCore(arr, start, index - 1) && isPostCore(arr, index, end - 1);
    }

    static Node buildBST(int[] arr) {
        if (isPost(arr)) {
            return buildBSTCore(arr, 0, arr.length - 1);
        }
        return null;
    }

    static Node buildBSTCore(int[] arr, int start, int end) {
        //System.out.println(start + "<>" + end);
        if (start > end || start < 0 || end < 0) {
            return null;
        }

        int index = start;
        for (; index < end; index++) {
            if (arr[index] > arr[end]) {
                index = index;
                break;
            }
        }

        Node head = new Node(arr[end]);
        head.left = buildBSTCore(arr, start, index - 1);
        head.right = buildBSTCore(arr, index, end - 1);
        return head;
    }

    static void printBST(Node head) {
        if (head == null) {
            return;
        }

        printBST(head.left);
        System.out.print(head.value + "  ");
        printBST(head.right);
    }
}
