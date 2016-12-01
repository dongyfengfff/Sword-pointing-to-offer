import java.util.ArrayList;

/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:滑动窗口最大值
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3
 * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 * <p>
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
 * {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 */
public class T65 {
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (size<1){
            return list;
        }
        int[] help = new int[num.length];
        help[0] = 0;
        for (int i = 1; i < help.length; i++) {
            // 后一个数比前一个数大,直接把自己的索引放给自己;
            if (num[i] >= num[help[i - 1]]){
                help[i] = i;
            }else {
                //后一个数没前一个大,分两种, 距离小于3,设置前面的索引,距离大于3,找新的索引;
                if (i-help[i-1]<size){
                    help[i] = help[i-1];
                }else {
                    help[i] = findMaxIndex(num,i,size);
                }
            }
        }

        for (int i = size-1; i < help.length; i++) {
            list.add(num[help[i]]);
        }
        return list;
    }

    int findMaxIndex(int[] num, int index,int size) {
        int maxIndex = index;
        for (int i = index-1; i >index-size ; i--) {
            if (num[i]>num[maxIndex]){
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        T65 t = new T65();
        int[] a = {2,3,4,2,6,2,5,1};
        System.out.println(t.maxInWindows(a,3));
    }
}
