package extended.chapter_9_others;

/**
 * Author: zhangxin
 * Time: 2017/3/23 0023.
 * Desc:永远先找上坡,在找下坡;
 * 进阶问题:如果相邻二人数字一样,拿一样数量的糖果,改变爬坡策略,之前的 >该位 >=,< 改为<=,但是目前的方法是需要遍历两次数组,第一次算数量,第二次算总和;
 *
 */
public class Problem_22_CandyProblem {

    //至少拿一个;
    public static int candy11(int[] arr){
        int left = 0,right = 0;
        int index =0;
        int sum = 0;
        boolean first = true;
        while (index<arr.length){
            left = getLeft(index,arr);
//            System.out.print(index+"---");
            index = index+left-1;
//            System.out.println(index);
            right = getRight(index, arr);
            int max = Math.max(left, right);
            int min = Math.min(left, right);
            if (first){
                sum += (1+max)*max/2;
                sum += (1+min-1)*(min-1)/2;
                first = false;
            }else {
                if (max==left){
                    sum += (2+(1+max))*max/2;
                    sum += (1+min-1)*(min-1)/2;
                }else {
                    //max是右的话;size = min -1;
                    sum += (2 + (1 + min - 1)) * (min - 1) / 2;
                    sum += (1+max)*max/2;
                }
            }

//            System.out.println(left+"---"+right);
            index += right;
        }
        return sum;
    }

    static int getLeft(int index,int[] arr){
        int count = 1;
        //还有后继;
        while (index+1<arr.length&&arr[index+1]>arr[index]){
            index++;
            count++;
        }
        return count;
    }

    static int getRight(int index,int[] arr){
        int count = 1;
        //还有后继;
        while (index+1<arr.length&&arr[index+1]<arr[index]){
            index++;
            count++;
        }
        return count;
    }

    //################################################
    public static int candy1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int index = nextMinIndex1(arr, 0);
        int res = rightCands(arr, 0, index++);
        int lbase = 1;
        int next = 0;
        int rcands = 0;
        int rbase = 0;
        while (index != arr.length) {
            if (arr[index] > arr[index - 1]) {
                res += ++lbase;
                index++;
            } else if (arr[index] < arr[index - 1]) {
                next = nextMinIndex1(arr, index - 1);
                rcands = rightCands(arr, index - 1, next++);
                rbase = next - index + 1;
                res += rcands + (rbase > lbase ? -lbase : -rbase);
                lbase = 1;
                index = next;
            } else {
                res += 1;
                lbase = 1;
                index++;
            }
        }
        return res;
    }
    public static int nextMinIndex1(int[] arr, int start) {
        for (int i = start; i != arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
                return i;
            }
        }
        return arr.length - 1;
    }
    public static int rightCands(int[] arr, int left, int right) {
        int n = right - left + 1;
        return n + n * (n - 1) / 2;
    }

    //################################################

    public static void main(String[] args) {
        int[] test1 = {3, 0, 5, 5, 4, 4, 0};
        System.out.println(candy1(test1));
        System.out.println(candy11(test1));
    }
}
