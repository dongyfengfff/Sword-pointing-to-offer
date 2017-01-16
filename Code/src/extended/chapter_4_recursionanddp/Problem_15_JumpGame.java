package extended.chapter_4_recursionanddp;

/**
 * Author: zhangxin
 * Time: 2017/1/2 0002.
 * Desc:跳跃游戏,给定一个数组arr[] = {3,2,3,1,1,4},arr[i]代表:从位置i可以往后跳最多几步;那么从位置0出发,最少跳几次能跳到最后位置
 * 进阶1:时间复杂度(n),空间复杂度(1)
 * 进阶2:打印最优路径;
 */
public class Problem_15_JumpGame {

    public static int jump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int jump = 0; //代表目前跳了多少步(最终要返回)
        int cur = 0;  //当前只跳cur步,最远能到的位置(可以理解为cur就是当前位置,我们一下子就到了cur的位置,只不过是到了cur位置后,还要在对i进行遍历而已)
        int next = 0; //如果在多跳一步,最远能到的位置

        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                //因为cur>=i的话,说明跳jump步可以到达cur,不需要jump++;
                //cur<i,说明跳jump步不能到cur,必须多跳一步;
                jump++;
                cur = next;
            }
            //next是一直要更新的,一直取最大值,表示在cur范围内的i内,可以达到的最远下一步的位置;
            next = Math.max(next, i + arr[i]);
        }
        return jump;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 2, 3, 1, 1, 4 };
        System.out.println(jump(arr));

    }
}

/*

arr = {3 2 3 1 1 4}

|  i   | jump | cur  | next |      |      |      |
| :--: | :--: | :--: | :--: | :--: | :--: | :--: |
|  0   |  0   |  0   |  3   |      |      |      |
|  1   |  1   |  3   |  3   |      |      |      |
|  2   |  1   |  3   |  5   |      |      |      |
|  3   |  1   |  3   |  5   |      |      |      |
|  4   |  2   |  5   |  5   |      |      |      |
|  5   |  2   |  5   |  9   |      |      |      |

 */