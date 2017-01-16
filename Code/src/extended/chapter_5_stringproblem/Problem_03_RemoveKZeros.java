package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/2 0002.
 * Desc:去掉字符串中连续的k个0,只需去掉恰好是k个的,多余K的连续0不去掉;  str = "a000b00c00",k=2, res = "a000bc"
 */
public class Problem_03_RemoveKZeros {

    public static String removeKZeros(String str, int k) {
        if (str == null || k < 1) {
            return str;
        }

        //NOTE:左老师说这是个空间复杂度为O(1)的算法,其实不是的,下面这一句已经是O(n)了;
        char[] chas = str.toCharArray();
        int count = 0; //目前出现的连续0的个数
        int start = -1;  //出现连续0的起始位置

        for (int i = 0; i != chas.length; i++) {
            if (chas[i] == '0') {
                count++;
                start = start == -1 ? i : start;
            } else {
                if (count == k) {
                    while (count-- != 0)
                        chas[start++] = 0; //这里的做法是将找到的连续k个'0'赋值为0,这样的话,在String.value时,char[] 中间的0代表什么也不显示;
                }
                count = 0;
                start = -1;
            }
        }
        if (count == k) {
            while (count-- != 0)
                chas[start++] = 0;
        }
        return String.valueOf(chas);
    }

    public static void main(String[] args) {
        String test1 = "0A0B0C00D0";
        System.out.println(removeKZeros(test1, 1));

        String test2 = "00A00B0C00D0";
        System.out.println(removeKZeros(test2, 2));

        String test3 = "000A00B000C0D00";
        System.out.println(removeKZeros(test3, 3));

        String test4 = "0000A0000B00C000D0000";
        System.out.println(removeKZeros(test4, 4));

        String test5 = "00000000";
        System.out.println(removeKZeros(test5, 5));


        char[] cs = {0,97,0,98};
        System.out.println(String.valueOf(cs));
    }

    private static String myRemoveKZeros(String test1, int i) {
        if (test1 == null || test1.length() == 0 || i < 1) {
            return test1;
        }

        char[] ss = new char[test1.length()];
        int index = 0; //代表的是ss中的索引;
        int start = -1; //拷贝0开始的ss中的索引
        int len0 = 0; //当前拷贝的0的个数;

        for (int j = 0; j < test1.length(); j++) {
            char ch = test1.charAt(j);
            if (ch == '0') {
                if (len0 == 0) {
                    //遇到的第一个局部的0;
                    ss[index] = ch;
                    start = index;
                    index++;
                    len0 = 1;
                } else {
                    //遇到的不是第一个0,接着拷贝
                    ss[index] = ch;
                    len0++;
                    index++;
                }
            } else {
                //用len0==i来判断是否是第一个非0,如果是,下次拷贝直接从start处开始;
                if (len0 == i) {
                    index = start;
                    ss[index] = ch;
                    index++;
                } else {
                    ss[index] = ch;
                    index++;
                }
                len0 = 0;
            }
        }
        if (len0 == i) {
            index -= i;
        }
        return new String(ss, 0, index);
    }
}
