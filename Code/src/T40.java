/**
 * Author: zhangxin
 * Time: 2016/11/18 0018.
 * Desc:一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字
 * num1,num2分别为长度为1的数组。传出参数
 * 将num1[0],num2[0]设置为返回结果
 * <p>
 * <p>
 * 解题思路:先考虑只有一个数字出现了一次,其余都出现了两次,如何处理???  使用位运算...
 * 整体进行异或一次,结果就是那两个不同的数的异或值,肯定不为0,然后从右到左找到第一个为1的位,表明两个不同的数在该位一个是0一个是1;
 * 按照这个把所有的数分成两组,那么相同的数肯定在一组,并且两个不同的数肯定不在一组,然后每一组内部进行异或,得到结果;
 */
public class T40 {
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        int res = array[0];
        for (int i = 1; i < array.length; i++) {
            res ^= array[i];
        }

        int i = 1;
        while (true) {
            if ((res & i) != 0) {
                break;
            } else {
                i = i << 1;
            }
        }
        //求出来的 i 是类似与 100 的结构,表明其实右边第一个为 1 的数字;
        int res1 = 0,res2 = 0;
        for (int j = 0; j < array.length; j++) {
            if ((array[j]&i)!=0){
                res1 ^= array[j];
            }else{
                res2 ^= array[j];
            }
        }
        num1[0] = res1;
        num2[0] = res2;
    }

    public static void main(String[] args) {
        int i = 1;
        System.out.println(i << 1);
        System.out.println(1^3);
    }
}
