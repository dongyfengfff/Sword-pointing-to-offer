package extended.chapter_9_others;

import java.util.HashMap;

/**
 * Author: zhangxin
 * Time: 2017/3/23 0023.
 * Desc:
 * A:1
 * B:2
 * C:3
 * AA:1*3+1
 * AAAA:27+9+3+1 = 40;
 */
public class Problem_15_NumberAndString {

    public static String getString(char[] chs, int n) {
        int bit = chs.length;  //几进制;

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < chs.length; i++) {
            map.put(chs[i], i + 1);
        }

        int tmp = n;
        int count = 0;
        while (tmp > 0) {
            tmp -= Math.pow(bit, count);
            count++;
        }
        count--;
        return "";
    }

    public static int getNum(char[] chs, String str) {
        int len = str.length();
        int bit = chs.length;

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < chs.length; i++) {
            map.put(chs[i], i + 1);
        }

        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            sum += map.get(c) * Math.pow(bit, len - i - 1);

        }
        return sum;
    }

    public static void main(String[] args) {
        char[] cs = {'a', 'b', 'c'};

        //System.out.println(getNum(cs, "aaaa"));
        System.out.println(Math.pow(2,3));
    }
}
