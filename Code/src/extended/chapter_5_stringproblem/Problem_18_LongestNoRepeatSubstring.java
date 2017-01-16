package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/13 0013.
 * Desc:给定一个字符串,找到其中最长无重复子串;要求时间复杂度为O(N)
 * 既然提示说要时间复杂度为O(N),那么也预示着我们只能从头到尾扫描一遍字符串,就要得出结果;
 * tip:是否可以使用滑动窗口算法?
 */
public class Problem_18_LongestNoRepeatSubstring {
    public static int maxUnique(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        int[] map = new int[256];
        //先构造一个hash表,初始其所有值为-1;在后面的使用中,存储的是该字符在字符串中的index;
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = 0;
        int pre = -1; //代表该字符之前出现的位置;
        int cur = 0; //
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            len = Math.max(len, cur);
            map[chas[i]] = i; //把hash表中当前字符的hash值,设置为该字符在字符串中的index
        }
        return len;
    }

    public static int maxUnique1(String s) {
        int[] hash = new int[256]; //构造初始的hash表,默认为0
        //首先找一个初始的长度
        int left = 0, right = 0, max = 0;
       /* for (; right < s.length(); right++) {
            char c = s.charAt(right);
            if (hash[c] == 0) {
                hash[c]--;
            } else {
                break;
            }
        }
        max = right;*/
//上面那个没有必要,直接跑下面的也能完成任务;
        //现在 right 在第一个重复元素的位置上,不过hash中该元素的值还是-1;
        for (; right < s.length(); right++) {
            char c = s.charAt(right);
            hash[c]--;
            //将left移动到相同元素的位置,重新开始;当前位置是-2;
            if (hash[c] == -2) { //如果right 当前位是-2,那么可定之前也有一位是-2,遇到相同的了,所以寻找一下left位置;
                max = Math.max(max, right - left);
                for (; left < right; left++) {
                    if (hash[s.charAt(left)] == -2) {
                        hash[s.charAt(left)] = -1;
                        left++;
                        break;
                    } else {
                        hash[s.charAt(left)] = 0;
                    }
                }
            }
        }
        max = Math.max(max, right - left);
        return max;
    }

    public static String getRandomString(int len) {
        char[] str = new char[len];
        int base = 'a';
        int range = 'z' - 'a' + 1;
        for (int i = 0; i != len; i++) {
            str[i] = (char) ((int) (Math.random() * range) + base);
        }
        return String.valueOf(str);
    }

    // for test
    public static String maxUniqueString(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        char[] chas = str.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = -1;
        int pre = -1;
        int cur = 0;
        int end = -1;
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            if (cur > len) {
                len = cur;
                end = i;
            }
            map[chas[i]] = i;
        }
        return str.substring(end - len + 1, end + 1);
    }

    public static void main(String[] args) {
        String str = getRandomString(20);
        str = "xyabca";
        System.out.println(str);
        System.out.println(maxUnique(str));
        System.out.println(maxUnique1(str));
        System.out.println(maxUniqueString(str));
    }
}

/*
说说 xyabca的情况

| i    | char | pre  | cur  | max_len | map[char] |
| ---- | ---- | ---- | ---- | ------- | --------- |
| 0    | x    | -1   | 1    | 1       | 0         |
| 1    | y    | -1   | 2    | 2       | 1         |
| 2    | a    | -1   | 3    | 3       | 2         |
| 3    | b    | -1   | 4    | 4       | 3         |
| 4    | c    | -1   | 5    | 5       | 4         |
| 5    | a    | 2    | 3    | 5       | 5         |
 */