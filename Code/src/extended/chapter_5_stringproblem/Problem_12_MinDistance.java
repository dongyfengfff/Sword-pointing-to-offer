package extended.chapter_5_stringproblem;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: zhangxin
 * Time: 2017/1/10 0010.
 * Desc:数组中两个字符串的最小距离
 * 进阶:如果发生多次查询,如何将时间复杂度将为O(1);
 * TODO:进阶的看了一下源码,简单理解了一下,自己写不出来;
 */
public class Problem_12_MinDistance {
    public static int minDistance(String[] strs, String str1, String str2) {
        if (strs == null || strs.length == 0 || str1 == null || str2 == null) {
            return -1;
        }

        int last1 = -1;
        int last2 = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(str1)) {
                last1 = i;
                min = Math.min(last2 == -1 ? min : i - last2, min);
            } else if (strs[i].equals(str2)) {
                last2 = i;
                min = Math.min(last1 == -1 ? min : i - last1, min);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }


    //##############################################################
    /*
    进阶题目的思考:要实现O(1)的时间复杂度的查询是有代价的,需要提前建好表,而这个建表的时间/空间复杂度都很高;
    需要一个HashMap来穷举所有的情况
    */
    public static class Record {
        private HashMap<String, HashMap<String, Integer>> record;  //穷举表

        //构造方法,直接在里边初始化穷举表;
        public Record(String[] strArr) {
            record = new HashMap<String, HashMap<String, Integer>>();
            HashMap<String, Integer> indexMap = new HashMap<String, Integer>();
            for (int i = 0; i != strArr.length; i++) {
                String curStr = strArr[i];
                update(indexMap, curStr, i);
                indexMap.put(curStr, i);
            }
        }

        /***
         * 更新穷举表中的信息
         * @param indexMap:保存的是之前已经扫描的strs数组中的str和index;可以利用之前的信息吧;
         * @param str 穷举表的外键key;
         * @param i str在strs中的索引;
         */
        private void update(HashMap<String, Integer> indexMap, String str, int i) {

            if (!record.containsKey(str)) {
                record.put(str, new HashMap<String, Integer>());
            }

            HashMap<String, Integer> strMap = record.get(str); //拿到strMap,我们就是要往strMap中更新数据;

            for (Map.Entry<String, Integer> lastEntry : indexMap.entrySet()) {
                String key = lastEntry.getKey();
                int index = lastEntry.getValue();
                if (!key.equals(str)) {
                    HashMap<String, Integer> lastMap = record.get(key);
                    int curMin = i - index;
                    if (strMap.containsKey(key)) {
                        int preMin = strMap.get(key);
                        if (curMin < preMin) {
                            strMap.put(key, curMin);
                            lastMap.put(str, curMin);
                        }
                    } else {
                        strMap.put(key, curMin);
                        lastMap.put(str, curMin);
                    }
                }
            }
        }

        public int minDistance(String str1, String str2) {
            if (str1 == null || str2 == null) {
                return -1;
            }
            if (str1.equals(str2)) {
                return 0;
            }
            if (record.containsKey(str1) && record.get(str1).containsKey(str2)) {
                return record.get(str1).get(str2);
            }
            return -1;
        }

    }


    /*##############################################################
    必须得有一个构造函数,在前期准备阶段将穷举表初始化完毕;
    之前想着用动态规划做,但是之前扫描的数据对当前数据并有什么影响吧;
    */
    public static int myMinDistance(String[] strs, String str1, String str2) {
        HashMap<String, HashMap<String, Integer>> totalMap = new HashMap<String, HashMap<String, Integer>>();
        for (int i = 0; i < strs.length; i++) {
            String curStr = strs[i];
            if (totalMap.get(curStr) == null) {
                HashMap<String, Integer> indexMap = new HashMap<String, Integer>();
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        String[] strArr = new String[]{"4", "2", "2", "3", "2", "2", "3", "1", "1", "3"};
        System.out.println(minDistance(strArr, "4", "3"));
        System.out.println(minDistance(strArr, "2", "3"));
        System.out.println(minDistance(strArr, "2", "0"));

        System.out.println("=======");
    }
}

