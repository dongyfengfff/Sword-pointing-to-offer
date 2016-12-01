import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Author: zhangxin
 * Time: 2016/11/23 0023.
 * Desc: 寻找数组中最小的 k 个数;
 * 解决思路:
 * 1. 堆排序(最佳);
 * 2. partition()算法;
 * 3. 使用 TreeSet,基于红黑树,不建议用...可以看到使用工具类,也并没有减轻代码量;
 */
public class T30 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (k >= input.length || k <= 0) {
            return list;
        }
        TreeSet<Integer> set = new TreeSet<Integer>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int index = 0;
        for (int i = 0; index < k; i++) {
            if (!set.add(input[i])) {
                if (map.containsKey(input[i])) {
                    int count = map.get(input[i]);
                    count++;
                    map.put(input[i], count);
                } else {
                    map.put(input[i], 1);
                }
            } else {
                index++;
            }
        }
        for (int i = index; i < input.length; i++) {
            int m = input[i];
            int n = set.last();
            if (input[i] < set.last()) {
                if (!set.add(input[i])) {
                    if (map.containsKey(input[i])) {
                        int count = map.get(input[i]);
                        count++;
                        map.put(input[i], count);
                    } else {
                        map.put(input[i], 1);
                    }
                }
            }
        }

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            int i = iterator.next();
            list.add(i);
            if (map.containsKey(i)) {
                int count = map.get(i);
                for (int j = 0; j < count; j++) {
                    list.add(i);
                }
            }
            if (list.size() >= k) {
                break;
            }
        }
        if (list.size() > k) {
            int c = list.size();
            for (int i = list.size() - k; i > 0; i--) {
                list.remove(c - 1);
                c--;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] a = {4, 5, 1, 6, 2, 7, 3, 8};
        T30 t = new T30();
        System.out.println(t.GetLeastNumbers_Solution(a, 4));

    }
}
