import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Author: zhangxin
 * Time: 2016/11/16 0016.
 * Desc: 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 字符串的全排列;注意对重复字符的判断,注意是按照字典序输出!!!
 * 使用递归,这里将字符串转为了一个字符数组,创建一个函数 collect,用来收集生成的字符串;
 * 问题是这样的,你让我把一个字符串的全排列打印出来,我不会,我只会打印包含一个字符的全排列(就是自己啊),
 * 但是我假装我会,我们的collect(char[] cs,int start,int end)方法一进去就先处理包含一个字符的情况,
 * 接下来,包含多个字符串的我不会处理,我先固定字符数组的第一个,然后求字符数组里出了第一个字符的其余字符的全排列;
 * 接下来,第一个字符固定的情况已经求出来了,其实这时候还不会呢,然后我们将第一个字符和第二个(第三个,第四个...)字符交换位置
 * 让这些字符作为首位,然后求其后续1~n的字符串的全排列;
 *
 * 所以核心的步骤就是交换位置,理解了这个原理,那么求子字符串的时候,也是先固定子字符串的第一个字符,求一篇,然后进行交换;
 *
 * fixme:按字典序输出并没有实现,全排列的实现方法还需要掌握
 * NOTE:按照字典序可是使用库函数将其排序,重要的是先收集所有可能的字符串,而这一步现在使用递归已经解决了,请参考最后的ByZX的方案,递归的思路很简单;但是这个思路依然有严重bug,无法避免一些重复结果;
 */
public class T28 {
    ArrayList<String> list = new ArrayList<String>();
    char[] cs;

    public ArrayList<String> Permutation(String str) {
        cs = str.toCharArray();
        collect(cs, 0, cs.length - 1);
        /*String[] ss = (String[]) list.toArray();  //这里类型强转不了???,那不行就for赋值吧;
        Arrays.sort(ss);*/
        return list ;
    }

    private void collect(char[] cs, int start, int end) {
        if (start == end) {
            list.add(new String(cs));
        }

        for (int i = start; i <= end; i++) {
            if (cs[start] == cs[i] && start != i) {
                continue;
            }
            swapChar(start, i);
            collect(cs, start + 1, end);
            swapChar(start, i);
        }
    }

    private void swapChar(int n1, int n2) {
        char tem = cs[n1];
        cs[n1] = cs[n2];
        cs[n2] = tem;

    }

    //----------------------网上找的一个例子-------------------------
    int n;//记录总的个数
    static int temp[];//记录自然数的数组  缓存数组;


    /**
     * 用于交换字符数组中位置n1和n2的数
     *
     * @param n1
     * @param n2
     */

    void swap(int n1, int n2) {
        int tem = temp[n1];
        temp[n1] = temp[n2];
        temp[n2] = tem;
    }

    /**
     * 递归方法，思想：
     * <p>
     * 求集合P={r1,r2,r3,...,rn}的全排列。设Pt(r1)是指在集合P中去除元素r1后的集合，也就是r1相对于P的补集
     * <p>
     * 则P的全排列Perm(P)=r1Perm(Pt(r1))+r2Perm(Pt(r2))+...+rnPerm(Pt(rn)).
     * <p>
     * 由此，将n规模的全排列降为了n-1规模的全排列，此为该递归的基本思想。直到n=1的时候一个元素的全排列是自己
     *
     * @param num 存集合的数组 本例中使用自然数
     * @param k   第一个元素在数组中的位置
     * @param m   最后一个元素在数组中的位置
     */

    void perm(int num[], int k, int m) {
        int i;
        if (k == m) {
            for (i = 0; i <= m; i++) {
                System.out.print(temp[i] + " ");
            }
            System.out.println();
            n++;
        } else {
            for (i = k; i <= m; i++) {
                if (temp[i] == temp[k] && i != k) {
                    continue;
                }
                swap(k, i); //先交换一次;
                perm(temp, k + 1, m);
                swap(k, i); //把上一步交换过的在交换回来,还原!!!
            }
        }
    }

    public static void main(String args[]) {
        int a[] = {1, 1, 2, 3};
        T28 t = new T28();
       /* t.temp = a;
        t.perm(temp, 0, 3);
        System.out.println(t.n);*/
        System.out.println("****************");
        String s = "abbc";
        System.out.println(t.Permutation(s));
        t.list.clear();
        //String s = "aac";
        System.out.println(new T28().Permutation_ByZX(s));
        //Arrays.sort();
    }


    //########我自己实现的递归方法#####################################################################

    public ArrayList<String> Permutation_ByZX(String str) {
        cs = str.toCharArray();
        Arrays.sort(cs);  //为了实现全排列,先将原始的字符串重新排列一下;下面的方法的输出就是字典序了;
        for (char c: cs){
            System.out.println(c);
        }
        func(0);
        System.out.println(list.size());
        TreeSet<String> set = new TreeSet<String>();
        for(String s:list){
            set.add(s);
        }
        System.out.println(set.size());
        return list;
    }

    //用递归的方法,实现全排列;假设这个func已经写好了,那你也得有参数的返回啊;
    private void func(int start){
        if (start==cs.length-1) {
            list.add(new String(cs));
            return ;
        }
        func(start+1);//表示首位固定啊;
        for (int i = start+1; i<cs.length; i++) {
            if (cs[start]!=cs[i]) {
                swap_ByZX(start,i);
                func(start+1);
                swap_ByZX(start,i);
            }
        }
    }

    void swap_ByZX(int i,int j){
        char tmp = cs[i];
        cs [i] = cs[j];
        cs[j] = tmp;
    }
}
