import java.util.ArrayList;

/**
 * Author: zhangxin
 * Time: 2016/11/24 0024.
 * Desc:输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
public class T41 {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        if (sum < 3) {
            return lists;
        }
        int small = 1;
        int big = 2;

        int count = 3;
        while (small <= sum / 2) {
            if (count < sum) {
                big++;
                count += big;
            } else if (count > sum) {
                count -= small;
                small++;
            } else {
                //count == sum;把数字添加进去;
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = small; i <= big; i++) {
                    list.add(i);
                }
                lists.add(list);
                count -= small;
                small++;

            }
        }
        return lists;
    }

    /*
    * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，是的他们的和正好是S，
    * 如果有多对数字的和等于S，输出两个数的乘积最小的。
    * */
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        int start = 0,end = array.length-1;
        while (start<end){
            int c = array[start]+array[end];
            if (c<sum){
                start++;
            }else if (c>sum){
                end--;
            }else {
                //return array[start]*array[end];
                list.add(array[start]);
                list.add(array[end]);
                return list;
            }
        }
        return list;
    }
    public static void main(String[] args) {
        T41 t = new T41();
        System.out.println(t.FindContinuousSequence(3));
    }
}
