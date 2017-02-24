/**
 * Author: zhangxin
 * Time: 2017/2/20 0020.
 * Desc:现在要求输入一个整数n，请你输出斐波那契数列的第n项。(n<=39)
 * f(0)=0,f(1)=1;
 *
 *
 * 拓展:一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * 这个问题相当于斐波那契数列; f(n) = f(n-1)+f(n-2); 跳n级可以看成前一步是从n-1跳过来的还是从n-2跳过来的;
 * 可能会后所n-2到n有两种可能,一种是到n-1,再到n;一种是直接到n;可是前一种情况先跳到n-1,已经包含在从n-1直接到n的情况里了;
 * 与斐波那契不同的是:f(1) = 1;f(2)=2;  为了计算方便,我们认为把f(0)=1;
 *
 *
 * 拓展:我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法?
 * 这里f(1) = 1;f(2)= 2; 注意初始值;
 */
public class T09 {
    /*
    首先,我们肯定可以用地归来实现的,但是效率极低,所以不建议采用该方法;
     */


    /*
    采用遍历的方式,巧妙的使用数组的形式来保存;
     */
    public int Fibonacci(int n) {
        if (n<0){
            return 0;
        }
        int[] array = new int[]{0, 1};
        if (n < 2) {
            return array[n];
        }

        for (int i = 2; i <= n; i++) {
            array[i % 2] = array[0] + array[1];
        }
        return array[n % 2];
    }




    //拓展1
    public int JumpFloor(int target) {
        if (target<=0){
            return 0;
        }

        if (target==1){
            return 1;
        }
        if(target==2){
            return 2;
        }

        int t1 = 1;
        int t2 = 2;

        int tmp = 0;

        for (int i = 3; i <= target; i++) {
            tmp = t2;
            t2 = t1+t2;
            t1 = tmp;
        }
        return t2;
    }
}
