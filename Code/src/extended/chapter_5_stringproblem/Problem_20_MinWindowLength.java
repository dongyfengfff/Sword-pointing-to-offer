package extended.chapter_5_stringproblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhangxin
 * Time: 2017/1/14 0014.
 * Desc:滑动窗口问题;最小包含子串的长度;
 * eg:s1="abcde",s2="ca",返回3;s2="af",返回0;
 */
public class Problem_20_MinWindowLength {

    //参考左老师的讲解吧,但是思路还是没有leetcode上的版本号;
    public static int minLength(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < str2.length()) {
            return 0;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();

        //初始化map,s2中的字符出现一次就++;
        int[] map = new int[256];
        for (int i = 0; i != chas2.length; i++) {
            map[chas2[i]]++;
        }

        int left = 0;
        int right = 0;
        int match = chas2.length;
        int minLen = Integer.MAX_VALUE;

        while (right < str1.length()) {
            map[chas1[right]]--;
            if (map[chas1[right]] >= 0) {
                match--;
            }
            if (match == 0) {
                while (map[chas1[left]] < 0) {
                    map[chas1[left++]]++;
                }
                minLen = Math.min(minLen, right - left + 1);
                match++;
                map[chas1[left++]]++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }


    //参考左老师的版本修改简易理解版
    public static int minLength2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < str2.length()) {
            return 0;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();

        //初始化map,s2中的字符出现一次就++;
        int[] map = new int[256];
        for (int i = 0; i != chas2.length; i++) {
            map[chas2[i]]++;
        }

        int left = 0;
        int right = 0;
        int match = chas2.length;
        int minLen = Integer.MAX_VALUE;

        char lChar, rChar;

        while (right < str1.length()) {
            rChar = chas1[right];//right位置上的字符
            map[rChar]--; //right的职责,不管能不能匹配,当前字符的hash都-1;

            if (map[rChar] >= 0) {
                //如果是s2中的字符,match才--;
                match--;
            }

            //match==0,表明已经匹配到一个序列,但可能不是最小的,需要left向右缩进试试;
            if (match == 0) {
                lChar = chas1[left];
                //map[lChar]<0,说明还可以缩进,==0是最好的状态,>0的话就要停止左->,需要右->了;
                while (map[lChar] < 0) {
                    map[lChar]++;
                    left++;
                    lChar = chas1[left];
                }
                //此时左边已经无法在缩进了,此时计算一波minLen;
                minLen = Math.min(minLen, right - left + 1);

                //计算完此次的minLen之后,此时left肯定是s2中的一个字符,我们有意的让left++,这样就导致又不平衡了,需要让right++,来寻找下一波平衡;
                map[lChar]++;
                left++;
                match++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    //leetcode版本;
    public static int minLength1(String s, String p) {
        //List<Integer> list = new ArrayList<Integer>();
        int minLen = Integer.MAX_VALUE;
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return 0;
        }

        int[] hash = new int[26];

        for (char c : p.toCharArray()) {
            hash[c - 'a']++;
        }

        int left = 0, right = 0;
        int count = p.length(); //diff数量

        while (right < s.length()) {
            //right扫描的区域,如果是p中的字符,首先count--;表示匹配到了一个
            if (hash[s.charAt(right) - 'a'] > 0) {
                count--;
            }
            //接下来不管是不是p中的字符,hash中对应的字符都--;
            hash[s.charAt(right) - 'a']--;
            right++; //right右移;

            //如果count此时==0,说明已经匹配完整了;
            if (count == 0) {
                minLen = Math.min(minLen, right - left);
            }

            //如果left和right之间的距离等于了p.length,那么left缩进;
            if (right - left == p.length()) {
                if (hash[s.charAt(left) - 'a'] >= 0) {
                    count++;
                }
                hash[s.charAt(left) - 'a']++;
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }


    public static void main(String[] args) {
        String str1 = "adabbca";
        String str2 = "db";
        System.out.println(minLength(str1, str2));
        System.out.println(minLength2(str1, str2));
        System.out.println(findAnagrams(str1, str2));
    }

    //########################TEST####################################
    public static List<Integer> findAnagrams(String s, String p) {

        List<Integer> ans = new ArrayList<Integer>();
        if (p.length() > s.length()) {
            return ans;
        }

        int[] charCounts = new int[26];

        for (char c : p.toCharArray()) {
            charCounts[toInt(c)]++;
        }

        int left = 0;
        int right = 0;
        int numDiff = p.length();

        for (right = 0; right < p.length(); right++) {
            char c = s.charAt(right);
            if (charCounts[toInt(c)] > 0) {
                numDiff--;
            }

            charCounts[toInt(c)]--;
        }

        if (numDiff == 0) {
            ans.add(0);
        }

        while (right < s.length()) {
            char leftChar = s.charAt(left++);
            if (charCounts[toInt(leftChar)] >= 0) {
                numDiff++;
            }
            charCounts[toInt(leftChar)]++;
            char rightChar = s.charAt(right++);
            charCounts[toInt(rightChar)]--;
            if (charCounts[toInt(rightChar)] >= 0) {
                numDiff--;
            }
            if (numDiff == 0) {
                ans.add(left);
            }

        }

        return ans;
    }

    private static int toInt(char c) {
        return c - 'a';
    }
}
