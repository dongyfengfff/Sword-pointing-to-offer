package extended.chapter_9_others;

/**
 * Author: zhangxin
 * Time: 2017/3/25 0025.
 * Desc:
 */
public class Problem_33_ArtistProblem {
    public static int solution0(int[] arr, int num) {
        //行:画匠;    列:时间段;
        //其中dp[i][j]代表:i个画匠搞定arr[0..j]这些画所需的时间;  那么最后求的就是num个画匠搞定[0...arr.length-1]所需的时间;
        int dp[][] = new int[num + 1][arr.length]; //多出来一行;
        int sum = 0;
        int[] sums = new int[arr.length];

        //初始化第一行...
        for (int i = 0; i < dp[0].length; i++) {
            sum += arr[i];
            dp[1][i] = sum;//或者第0行干脆不初始化,直接初始话第一行;
            sums[i] = sum;
        }

        //初始化第一列;不管几个画匠,都是一个画布,所以都是arr[0];
        for (int i = 2; i <= num; i++) {
            dp[i][0] = arr[0];
        }


        for (int i = 2; i <= num; i++) {
            for (int j = 1; j < arr.length; j++) {
//                dp[i][j] = getMax();
                int max = Integer.MIN_VALUE;
                for (int k = 0; k < j; k++) {
                    max = Math.max(dp[i][k], sums[j] - sums[k]);
                }
                dp[i][j] = max;
            }
        }
        return 0;
    }



    //arr:时间段  num:画匠数量;
    public static int solution1(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 1) {
            throw new RuntimeException("err");
        }


        int[] sumArr = new int[arr.length];
        int[] map = new int[arr.length];
        sumArr[0] = arr[0];
        map[0] = arr[0];
        for (int i = 1; i < sumArr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
            map[i] = sumArr[i];
        }

        for (int i = 1; i < num; i++) {
            for (int j = map.length - 1; j > i - 1; j--) {
                int min = Integer.MAX_VALUE;
                for (int k = i - 1; k < j; k++) {
                    int cur = Math.max(map[k], sumArr[j] - sumArr[k]);
                    min = Math.min(min, cur);
                }
                map[j] = min;
            }
        }
        return map[arr.length - 1];
    }
}
