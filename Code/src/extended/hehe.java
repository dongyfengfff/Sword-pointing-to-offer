package extended;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Author: zhangxin
 * Time: 2016/12/12 0012.
 * Desc:
 */
public class hehe {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> result = new ArrayList<String>();
        if (str == null || str.length() == 0) {
            return result;
        }

        char[] chars = str.toCharArray();
        TreeSet<String> temp = new TreeSet<String>();
        Permutation(chars, 0, temp);
        result.addAll(temp);
        return result;
    }

    public void Permutation(char[] chars, int begin, TreeSet<String> result) {
        if (chars == null || chars.length == 0 || begin < 0 || begin > chars.length - 1) {
            return;
        }

        //如果begin是最后一个字符,那么直接生成字符串;
        if (begin == chars.length - 1) {
            result.add(String.valueOf(chars));
        } else {
            //这里从begin开始,然后找全排列,然后从begin+1开始,找全排列;
            for (int i = begin; i <= chars.length - 1; i++) {
                swap(chars, begin, i);

                Permutation(chars, begin + 1, result);

                swap(chars, begin, i);
            }
        }
    }

    public void swap(char[] x, int a, int b) {
        char t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    public static void main(String[] args) {
//        System.out.println(new hehe().Permutation("abbc"));
        //[abc, acb, bac, bca, cab, cba]
        //[abc, acb, bac, bca, cab, cba]
        //[abbc, abcb, acbb, babc, bacb, bbac, bbca, bcab, bcba, cabb, cbab, cbba]
        //[abbc, abcb, acbb, babc, bacb, bbac, bbca, bcba, bcab, bbac, bbca, babc, bacb, bcab, bcba, cbba, cbab, cabb]
        //[abbc, abcb, acbb, babc, bacb, bbac, bbca, bcba, bcab, bbac, bbca, babc, bacb, bcab, bcba, cbba, cbab, cabb]
       /* short c = 2017;
        c = (short) (c<<5);
        c = (short) (c>>5);
        System.out.println(c);*/

        try {
            test("100", "abc");
            test("100", "0");
        } catch (Exception e) {
            System.out.println("E");;
        }
    }

    static int test(String a,String b) throws Exception{
        int il,iR,iRet = -1;
        try {
            il = Integer.parseInt(a);
            iR = Integer.parseInt(b);
        }catch (NumberFormatException e){
            System.out.println("N");
        }catch (ArithmeticException e){
            System.out.println("A");
            throw new Exception("aaa");
        }finally {
            System.out.println("F");
        }

        return iRet;
    }
}
















