package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/9 0009.
 * Desc:字符串的调整与替换,给定字符数组,右半区全是空字符(==0);左半区则不包含0,但是有空格,现在想将左半区的空格替换成字符串"%20",
 * 假设右半区足够大,不会导致数组溢出,完成该功能,要求时间复杂度O(n),空间复杂度O(1)
 * <p>
 * 进阶1:给定一个字符串,只包含数字和*,现在想将所有*移动到左边,数字移动到右边,同时保持原有数字的相对位置,时间复杂度O(n),空间复杂度O(1)
 */
public class Problem_10_ModifyAndReplace {

    public static void replace(char[] chas) {
        if (chas == null || chas.length == 0) {
            return;
        }

        int len = 0; //左半区的长度;
        int num = 0; //左半区空格的数量
        while (chas[len] != 0) {
            if (chas[len] == ' ') {
                num++;
            }
            len++;
        }

        //至此,拿到了左半区的len,如果要执行替换的话,左半区的长度就变为:len-num+3num;
        //就需要将左半区不为空格的字符移动到
        int len2 = len - num + 3 * num;

        while (len > 0) {
            char c = chas[--len];
            if (c != ' ') {
                chas[--len2] = c;
            } else {
                chas[--len2] = '0';
                chas[--len2] = '2';
                chas[--len2] = '%';
            }
        }
    }

    /*
    两个思路:
    (1)从左到右遍历,两个指针,一个指向第一个*,一个指向第一个数字,然后交换;
    (2)(⊙o⊙)…,想错了,不是那道题,从右向左扫描,遇到数字直接复制,遇到*跳过,然后直接将左半区设置为*即可...这个方法说着简单,但是效率不高吧...
    */
    public static void modify(char[] chas) {
        int index = chas.length - 1;
        for (int i = chas.length - 1; i >= 0; i--) {
            if (chas[i]!='*'){
                chas[index] = chas[i];
                index--;
            }
        }

        for ( ; index >=0 ; index--) {
            chas[index]='*';
        }
    }

    public static void main(String[] args) {
        char[] chas1 = {'a', ' ', 'b', ' ', ' ', 'c', 0, 0, 0, 0, 0, 0, 0, 0,};
        replace(chas1);
        System.out.println(String.valueOf(chas1));

        char[] chas2 = {'1', '2', '*', '*', '3', '4', '5'};
        modify(chas2);
        System.out.println(String.valueOf(chas2));

    }
}
