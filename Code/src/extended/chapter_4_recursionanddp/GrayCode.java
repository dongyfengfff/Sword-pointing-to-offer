package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2017/4/2 0002.
 * Desc:
 */
public class GrayCode {
    /***
     * 递归版本的实现
     * @param n
     * @return
     */
    public String[] getGray(int n) {
        //数组的大小是2的n次方，因为n位的格雷码有2的n次方种排列,首先生成2^n的数组,备用;
        String[] grayCodeArr = new String[(int) Math.pow(2, n)];

        //终止条件;
        if (n < 1) {
            System.out.println("你输入的格雷码位数有误！");
        }

        if (1 == n) {
            grayCodeArr[0] = "0";
            grayCodeArr[1] = "1";
            return grayCodeArr;
        }

        //n-1 位格雷码的生成方式
        String[] before = getGray(n - 1);

        for (int i = 0; i < before.length; i++) {
            grayCodeArr[i] = "0" + before[i];
            grayCodeArr[grayCodeArr.length - 1 - i] = "1" + before[i];
        }

        return grayCodeArr;
    }
    /**
     * 非递归生成二进制格雷码
     * 思路:1、获得n-1位生成格雷码的数组
     *      2、由于n位生成的格雷码位数是n-1的两倍，故只要在n为格雷码的前半部分加0，后半部分加1即可。
     * @param n 格雷码的位数
     * @return 生成的格雷码数组
     */
    public static String[] GrayCode2(int n)
    {

        int num = (int)Math.pow(2, n);//根据输入的整数，计算出此Gray序列大小
        String[] s1 = {"0","1"};//第一个Gray序列

        if(n < 1)
        {
            System.out.println("你输入的格雷码位数有误！");
        }

        for(int i=2;i<=n;i++){//循环根据第一个Gray序列，来一个一个的求
            int p = (int)Math.pow(2, i);//到了第几个的时候，来计算出此Gray序列大小
            String[] si = new String[p];
            for(int j=0;j<p;j++){//循环根据某个Gray序列，来一个一个的求此序列
                if(j<(p/2)){
                    si[j] = "0" + s1[j];//原始序列前面加上"0"
                }else{
                    si[j] = "1" + s1[p-j-1];//原始序列反序，前面加上"1"
                }
            }
            s1 = si;//把求得的si，附给s1,以便求下一个Gray序列
        }

        return s1;
    }
}
