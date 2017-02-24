package extended.chapter_8_arrayandmatrix;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Author: zhangxin
 * Time: 2017/1/25
 * Desc:给定一个整形数组arr,打印其中出现次数大于一半的数,如果没有,提示打印信息;
 * 进阶:给定一个整型数组,在给定一个K,打印所有出现次数大于N/k的数,如果没有,提示打印信息;
 */
public class Problem_06_FindKMajority {
    public static void printHalfMajor(int[] arr) {
        int candy = arr[arr.length - 1];
        int time = 1;

        for (int i = arr.length - 2; i != -1; i--) {
            if (time == 0) {
                candy = arr[i];
                time++;
            } else if (arr[i] == candy) {
                time++;
            } else {
                time--;
            }

        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == candy) {
                count++;
            }
        }
        if (count > arr.length / 2) {
            System.out.println(candy);
        } else {
            System.out.println("not exist");
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1, 1, 2, 1};
        printHalfMajor(arr);
        int K = 4;
        printKMajor(arr, K);
    }

    // 参照超过一半的思路写,只不过这次candy的数量为k-1;
    private static void printKMajor(int[] arr, int k) {
        if (k < 2) {
            System.out.println("k is not validate");
        }


        HashMap<Integer, Integer> candy = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (candy.containsKey(arr[i])) {
                candy.put(arr[i], candy.get(arr[i]) + 1);
            } else if (candy.size() < k - 1) {
                candy.put(arr[i], 1);
            } else {
                allCandsMinusOne(candy);
            }
        }

        HashMap<Integer, Integer> reals = getReals(arr, candy);
        boolean hasPrint = false;
        for (Map.Entry<Integer, Integer> set : candy.entrySet()) {  //这里不用纠结用cands还是用reals的key set,因为二者的key set 是一样的;
            Integer key = set.getKey();
            if (reals.get(key) > arr.length / k) {
                hasPrint = true;
                System.out.print(key + " ");
            }
        }
        System.out.println(hasPrint ? "" : "no such number.");


    }

    //在遍历一遍数组,首先在candy中的,肯定是有可能是真正的,不在candy中的肯定不是;
    private static HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> candy) {
        HashMap<Integer, Integer> reals = new HashMap<Integer, Integer>();
        for (int i = 0; i != arr.length; i++) {
            int curNum = arr[i];
            if (candy.containsKey(curNum)) {
                if (reals.containsKey(curNum)) {
                    reals.put(curNum, reals.get(curNum) + 1);
                } else {
                    reals.put(curNum, 1);
                }
            }
        }
        return reals;
    }

    //candy中所有数据-1,如果减一后等于0,存入移除列表,等待删除;
    private static void allCandsMinusOne(HashMap<Integer, Integer> map) {
        List<Integer> removeList = new LinkedList<Integer>();
        for (Map.Entry<Integer, Integer> set : map.entrySet()) {
            Integer key = set.getKey();
            Integer value = set.getValue();
            if (value == 1) {
                removeList.add(key);
            }
            map.put(key, value - 1);
        }
        for (Integer removeKey : removeList) {
            map.remove(removeKey);
        }
    }
}
