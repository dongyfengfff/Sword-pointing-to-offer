package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/9 0009.
 * Desc:翻转字符串, I love you => you love I,时间复杂度O(N),空间复杂度O(1)
 * 方法:先将字符串整体逆序,然后找到每个单词,对每个单词在做一次逆序,就得到了结果;
 * <p>
 * 进阶:给定一个字符串和一个size,把字符串的size左半区移动到右半区,abcde,2=>cdeab
 */
public class Problem_11_RotateString {
    public static void rotateWord(char[] chas) {
        if (chas == null || chas.length == 0) {
            return;
        }
        //做了一次整体逆序;
        reverse(chas, 0, chas.length - 1);

        //接下来开始找每一个字符串;
        int l = -1;
        int r = -1;
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] != ' ') {
                //NOTE:遍历的时候第一次赋值的技巧
                l = i == 0 || chas[i - 1] == ' ' ? i : l;
                r = i == chas.length - 1 || chas[i + 1] == ' ' ? i : r;
            }
            //left和right都不为-1,那么执行一次翻转;
            if (l != -1 && r != -1) {
                reverse(chas, l, r);
                l = -1;
                r = -1;
            }
        }
    }

    //对一个字符数组的逆序;
    public static void reverse(char[] chas, int start, int end) {
        char tmp = 0;
        while (start < end) {
            tmp = chas[start];
            chas[start] = chas[end];
            chas[end] = tmp;
            start++;
            end--;
        }
    }


    /*
    * 解决方案1:
    * 先将0~size-1逆转,再将size~len-1逆转,最后整体逆转;
    * */
    public static void rotate1(char[] chas, int size) {
        if (chas == null || size <= 0 || size >= chas.length) {
            return;
        }
        reverse(chas, 0, size - 1);
        reverse(chas, size, chas.length - 1);
        reverse(chas, 0, chas.length - 1);
    }


    /*
    * 方法2:思路比较复杂,交换好像也没有减少;
    *
    * */
    public static void rotate2(char[] chas, int size) {
        if (chas == null || size <= 0 || size >= chas.length) {
            return;
        }
        int start = 0;
        int end = chas.length - 1;
        int lpart = size; //左半区
        int rpart = chas.length - size; //右半区
        int s = Math.min(lpart, rpart); //哪个半区字符少;
        int d = lpart - rpart;  //dertar
        while (true) {
            //先交换一次,少的那一部分交换之后位置就固定了;
            exchange(chas, start, end, s);

            //终止条件;
            if (d == 0) {
                break;
            } else if (d > 0) {
                //之前的左半区大,交换之后左半区不变了,剩下的部分作为整体,start后移;
                start += s;
                lpart = d;
            } else {
                //之前的右半区大,交换之后右半区不变了,剩下的部分作为整体,right前移;
                end -= s;
                rpart = -d;
            }
            s = Math.min(lpart, rpart);
            d = lpart - rpart;
        }
    }

    /***
     * 字符串部分交换功能;
     * @param chas
     * @param start 字符数组要交换start位置
     * @param end   字符数组要交换start位置
     * @param size  要交换的长度,以左右半区最小的为size;
     */
    public static void exchange(char[] chas, int start, int end, int size) {
        int i = end - size + 1;
        char tmp = 0;
        while (size-- != 0) {
            tmp = chas[start];
            chas[start] = chas[i];
            chas[i] = tmp;
            start++;
            i++;
        }
    }

}
