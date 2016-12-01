/**
 * Author: zhangxin
 * Time: 2016/11/23 0023.
 * Desc:数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 * 如果不存在则输出0。
 * <p>
 * <p>
 * 两个思路:
 * 1.partition()算法,缺点是:分割时的数影响整个查询的效率
 * 2.根据数组的特点,
 */
public class T29 {

    //user partition()算法
    public int MoreThanHalfNum_Solution(int[] array) {


        return 0;
    }

    //利用数组汇总超过原数的一半的特点;缺点是最后还要进行一次遍历,检验得出的结果是不是;防止出现测试用例 int[] a 中的情况
    public int MoreThanHalfNum_Solution2(int[] array) {
        int count = 0;
        int current = 0;
        for (int i = 0; i < array.length; i++) {
            if (count == 0) {
                current = array[i];
                count++;
            } else {
                // count > 0
                if (current != array[i]) {
                    count--;
                } else {
                    count++;
                }
            }
        }
        if (count > 0 && check(array, current)) {
            return current;
        }
        return 0;
    }

    boolean check(int[] a, int k) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == k) {
                count++;
            }
        }
        if (count * 2 < a.length) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        T29 t = new T29();
        System.out.println(t.MoreThanHalfNum_Solution2(a));
    }

}
