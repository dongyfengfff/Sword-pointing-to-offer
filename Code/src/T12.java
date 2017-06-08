/**
 * Author: zhangxin
 * Time: 2017/2/21 0021.
 * Desc:打印1到最大的n位数,eg输入n=3,那么最大的3位数是999,那么打印1一直到999
 * 这里有个缺陷是:如果n很大,那么可能直接用int可能会溢出;这是这个题要处理的问题;
 * <p>
 * TODO:
 * 思路1：使用一个字符数组char[] 来模拟，末位遇到9，前一位进1，但是效率并不高吧；
 * 思路2：递归；数字的每一位都可能是0~9中的一个数；
 */
public class T12 {
    int end;

    void Print1ToMaxOfNDigits(int n) {
        if (n <= 0) {
            return;
        }
        end = n;
        char[] nums = new char[n];
        func(nums, 0);
    }

    void func(char[] nums, int index) {
        //表明需要打印了
        if (index == nums.length) {
            printChars(nums);
            return;
        }

        for (int i = 0; i < 10; i++) {
            nums[index] = (char) ('0' + i);
            func(nums, index + 1);
        }

    }

    void printChars(char[] nums) {
        if (end > 0 && nums[end - 1] != '0') {
            end--;
        }

        if (end >= nums.length) {
            return;
        }

        System.out.println(end + ">>>" + new String(nums, end, nums.length - end));
    }

    void printChars(char[] nums, int index) {
        System.out.println(new String(nums, index, nums.length - index));
    }

    public static void main(String[] args) {
       /* char[] b = {'1', '2'};
        System.out.println(new String(b, 1, 1));*/

        new T12().Print1ToMaxOfNDigits(3);
    }
}
