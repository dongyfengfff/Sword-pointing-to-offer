package extended.chapter_9_others;

import java.util.HashMap;

/**
 * Author: zhangxin
 * Time: 2017/3/20 0020.
 * Desc:n!的结果末尾有几个0???
 */
public class Problem_03_FactorialProblem {
    //第一种思路:找到从1~N中,包含的5的因子的数量;
    //NOTE:不推荐11的方法,推荐1,因为1更快;
    public static int zeroNum1(int num) {
        if (num < 0) {
            return 0;
        }
        int res = 0;
        int cur = 0;
        for (int i = 5; i < num + 1; i = i + 5) {
            cur = i;
            while (cur % 5 == 0) {
                res++;
                cur /= 5;
            }
        }
        return res;
    }


    //这个方法虽然用了map做缓存,但是总感觉反而到慢了呢...
    public static int zeroNum11(int num) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(num/5);
        int sum = 0;
        for (int i = 5; i <= num; i=i+5) {
            if (i % 5 == 0) {
                int count = 1;
                if (map.containsKey(i / 5)) {
                    count += map.get(i / 5);
                }
                map.put(i, count);
                sum += count;
            }
        }

        return sum;
    }


    /*
    下面的代码不好理解:
    我们以n=127为例;
    首先 127/5 = 25,那么说明这里面有25个数是5的倍数,那么这25个数至少能贡献25个5
    然后 125/25 = 5,那么说明这里面有5个数是25的倍数,那么这5个数至少能贡献5*2个,但是这里只算5个,因为另外5个已经被第一次取走了;
     */
    public static int zeroNum2(int num) {
        if (num < 0) {
            return 0;
        }
        int res = 0;
        while (num != 0) {
            res += num / 5;
            num /= 5;
        }
        return res;
    }

    //进阶问题:如果n!用二进制表示,那么从右到左,1第一次出现在第几位?以右边第一位为0;eg:1!==>0;2!==>1;
    //解决这个问题,和上一个问题一样,要看包含多少个因子2,那么接下来的算法和计算5的方式一样;
    public static int rightOne1(int num) {
        if (num < 1) {
            return -1;
        }
        int res = 0;
        while (num != 0) {
            num >>>= 1;
            res += num;
        }
        return res;
    }


    public static void main(String[] args) {
        int num = 100000000;
        System.out.println(zeroNum11(num));
        System.out.println(zeroNum1(num));
    }
}
