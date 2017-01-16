package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/3 0003.
 * Desc:判断字符串中所有字符是否只出现了一次,要求空间复杂度O(1)
 * 如果没有空间复杂度的要求,我们可以构造一个hash数组,然后统计
 * <p>
 * 没有什么好的方法,只能排序,然后在遍历一下看看没有相邻的字符是相同的,
 * 这里符合要求的只有堆排序了;虽然堆排序是可行的方法,但是网上大多数答案都是基于递归的,这就不能保证空间复杂度O(1)了
 * 因此我们需要将堆排序的递归转换为非递归的
 * TODO:堆排序还需要再熟练一下;
 */
public class Problem_08_IsAllUnique {

    public static boolean isUnique1(char[] chas) {
        boolean[] hash = new boolean[256];
        for (char c : chas) {
            if (hash[c]) {
                return false;
            }

            hash[c] = true;
        }
        return true;
    }

    //#####################################################
    public static boolean isUnique2(char[] chas) {
        if (chas == null) {
            return true;
        }
        heapSort(chas);
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] == chas[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void heapSort(char[] chas) {
        //构造二叉堆
        for (int i = 0; i < chas.length; i++) {
            heapInsert(chas, i);
        }

        //进行堆排序;
        for (int i = chas.length - 1; i > 0; i--) {
            swap(chas, 0, i);
            heapify(chas, 0, i);
        }
    }

    //用来构造二叉堆
    public static void heapInsert(char[] chas, int i) {
        int parent = 0;
        while (i != 0) {
            parent = (i - 1) / 2;
            if (chas[parent] < chas[i]) {
                swap(chas, parent, i);
                i = parent;
            } else {
                break;
            }
        }
    }

    //进行堆排序
    public static void heapify(char[] chas, int i, int size) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        while (left < size) {
            if (chas[left] > chas[i]) {
                largest = left;
            }
            if (right < size && chas[right] > chas[largest]) {
                largest = right;
            }
            if (largest != i) {
                swap(chas, largest, i);
            } else {
                break;
            }
            i = largest;
            left = i * 2 + 1;
            right = i * 2 + 2;
        }
    }

    public static void swap(char[] chas, int index1, int index2) {
        char tmp = chas[index1];
        chas[index1] = chas[index2];
        chas[index2] = tmp;
    }

    //###############################################我的方法,应该比左老师的方法更快吧,在比较的过程中就完成了判断.
    public static boolean isUnique3(char[] chas) {
        int n = chas.length;
        for (int k = n / 2; k >= 1; k--) {
            if (!sink(chas, k, n)) {
                return false;
            }
        }

        while (n > 1) {
            exch(chas, 1, n--);
            if (!sink(chas, 1, n)) {
                return false;
            }
        }
        return true;
    }

    private static boolean sink(char[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n) {
                int tmp = compare(pq, j, j + 1);
                if (tmp < 0) {
                    j++;
                } else if (tmp == 0) {
                    return false;
                }
            }

            int cmp = compare(pq, k, j);
            if (cmp == 0) {
                return false;
            }
            if (cmp > 0) {
                break;
            }
            //pq[k] < pq[j]
            exch(pq, k, j);
            k = j;
        }

        return true;
    }

    private static int compare(char[] pq, int i, int j) {
        if (pq[i - 1] < pq[j - 1]) {
            return -1;
        } else if (pq[i - 1] > pq[j - 1]) {
            return 1;
        } else {
            return 0;
        }
    }

    private static void exch(char[] pq, int i, int j) {
        char swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

    public static void main(String[] args) {
        //char[] chas = {'1', '1', '3', 'a', 'b', 'c', 'd', '4', '5', '6'};
        char[] chas = {'1', '1', '3', '4', '5', '6','a', 'b', 'c','d','z'};
        chas = "11234;lsag;ajgjg'ajh'ajhjhjhkldjhkdfjhkdjfhjdhd'hdkhpoego egrowipgdjjhjdhpegehhdahjbkljbnkjgjgrehgogh".toCharArray();
        System.out.println(isUnique1(chas));
        long t1 = System.nanoTime();
        System.out.println(isUnique2(chas));
        long t2 = System.nanoTime();
        System.out.println(isUnique3(chas));
        long t3 = System.nanoTime();

        System.out.println((t2-t1));
        System.out.println((t3-t2));
    }
}
