package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2016/12/31 0031.
 * Desc:
 * <p>
 * 之前做递归好像返回的值都会被叠加,这次递归返回的值就是中间某一层的值
 */
public class Problem_12_NumberToLetter {


    //###################################################
    public static int myNum(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chs = str.toCharArray();
        return process(chs, 0);
    }

    private static int process(char[] chs, int i) {
        //这是最终的终止条件
        if (i == chs.length) {
            return 1;
        }

        //中途终止条件
        if (chs[i] == '0') {
            return 0;
        }

        //能走到这一步,说明i是在1~9之间的数据,接下来有两种情况
        //第一种:取接下来的一个字符进行转换
        //第二种:去接下来的两个字符进行转换(两个字符能转换的前提是看两个字符组成的数字是否是在 10~26 之间)
        int res = process(chs, i + 1);

        //值判断<27就行了,不用判断是不是大于10,因为前面已经判断i不可能是0,所以两个字符组成的数字肯定是>=10的
        if (i + 1 < chs.length && ((chs[i] - '0') * 10 + chs[i + 1] - '0') < 27) {
            res += process(chs, i + 2);
        }
        return res;
    }

    //###################################################
    /*
    我们看到,上面的是一个递归,但是这个递归有点不一样,普通的递归是 p(i) = p(i-1)+p(i-2)
    但是这个题目中,递归的表达式是: p(i) = p(i+1)+p(i+2)
    我们最终的目的是求p(0),于是我们倒着求斐波那契数列;
     */

    public static int num2(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chs = str.toCharArray();
        //定义三个指针,cur,tmp,next 分别代表当前位置,上一次位置,上上次位置;
        int cur = chs[chs.length - 1] == '0' ? 0 : 1; //最终要返回的值;

        //注意这个next保存的是cur上上次得到的数据,初始化为 1,因为第一次合并两个字符,cur+next的时候,next必须得有个值,那肯定就是1;
        int next = 1; //这个初始化的1只用了一次,后面的next都是拿cur的值;next是cur上上次的值

        int tmp = 0; //用来保存临时变量;tmp是cur上次的值;

        for (int i = chs.length - 2; i >= 0; i--) {
            if (chs[i] == '0') {
                next = cur;
                cur = 0;
            } else {
                tmp = cur; //保存cur的数据,下次循环,tmp就是cur的上次的数据;
                if ((chs[i] - '0') * 10 + chs[i + 1] - '0' < 27) {
                    //这里所 + 的next的值,其实是 cur上上次遍历的值;就是这么理解;
                    cur += next;  //cur = cur + next //满足条件,右边的cur表示之前的结果,当前不为0,自己作为一位, next 表示 i与i+1组合后的情形;
                }
                next = tmp;
            }
        }
        return cur;
    }


    /*
    这里有一个难点是如何保存cur上上次的值,之前我们可以通过tmp变量保存cur上次的值,现在要保存上上次的,怎么做?
     */
    public static int myNum2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] ss = str.toCharArray();
        int cur = ss[ss.length - 1] == '0' ? 0 : 1;
        int next = 1;
        int tmp;


        for (int i = ss.length - 2; i >= 0; i--) {
            if (ss[i] == '0') {
                //处理等于0的情况;
                next = cur; //起码当前位自己是用不成了,先把next付给cur,一个理想的情况是期待在往前一步,能组成10或者20,否则直接玩完
                cur = 0;//必须置为0;当前位已经无效;

            } else {
                //当前i不等于0,那么起码自己转变成一个字符是没有问题的;
                // 所以这一步的话其实不用写代码,cur=cur
                if ((ss[i] - '0') * 10 + ss[i + 1] < 27) {
                    cur = cur + next;
                }
            }
        }


        return cur;
    }

    public static void main(String[] args) {
        String str = "3161018231";
        // str  ="9229";
        System.out.println(num2(str));

    }
}
