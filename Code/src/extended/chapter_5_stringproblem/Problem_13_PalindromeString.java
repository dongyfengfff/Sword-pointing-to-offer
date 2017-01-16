package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/11 0011.
 * Desc:添加最少字符,使字符串整体成为回文字符串
 * <p>
 * 进阶问题:给定一个字符串,并给定该字符串的最长子序列回文字符串,添加最少字符串,使字符串整体成为回文字符串
 */
public class Problem_13_PalindromeString {

    /*
    首先,我想到的方法是两个指针,一个指向头一个指向尾,两个指针不一样,添加一个左或者右,一样的话,共同缩进,但是这样无法做到添加最小的字符串;
    所以使用动态规划的方法:动态规划无法求出最终的回文字符串,但是可以求出最终的回文字符串的最小长度;
    */
    public static String getPalindrome1(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }

        char[] chas = str.toCharArray();
        int[][] dp = getDP(chas);
        char[] res = new char[chas.length + dp[0][chas.length - 1]]; //创建一个字符数组,长度就是dp[0][len-1],这是最短回文的长度;

        int i = 0;
        int j = chas.length - 1;

        int resl = 0;
        int resr = res.length - 1;

        //同样是采用两个指针策略,左右夹击;
        while (i <= j) {
            if (chas[i] == chas[j]) {
                //相等的情况是最好处理的;
                res[resl++] = chas[i++];
                res[resr--] = chas[j--];
            } else if (dp[i][j - 1] < dp[i + 1][j]) {
                //左侧代价小===>先组一个左侧的,把右侧的j放在左侧,同时j--,i不变,再次组成;
                //eg:i...j->aab;i+1..j=ab;i...j-1=aa;
                res[resl++] = chas[j];
                res[resr--] = chas[j--];
            } else {
                //右侧代价小;
                res[resl++] = chas[i];
                res[resr--] = chas[i++];
            }
        }

        return String.valueOf(res);
    }

    //动态规划,计算出最终回文字符串的最小长度
    // dp[i][j]代表str[i...j]之间的字符串要变成回文字符串所要添加的最小字符,分为以下几种情况:
    // dp[i][j]:i=j,代表只有一个字符,本身就是回文,所需添加的字符为0
    // dp[i][j]:|j-i|=1,代表只有两个字符,如果两个字符相等,那么所需添加的字符为0,否则所需添加的字符数是1
    // dp[i][j]:|j-i|>1,代表多于两个字符,那么看str[i]和str[j]是否相等,如果相等,所需添加的字符数是:dp[i+1][j-1];
    //否则,先将str[i+1...j]组成字符串,或者先将str[i...j-1]组成回文字符串,然后再+1,所以dp[i][j]的取值是:min{dp[i][j-1],dp[i+1][j]}+1;
    //其实对我们最有用的就是这个dp[0][len-1]这个数
    public static int[][] getDP(char[] str) {
        //(1)生成动态规划数组
        int[][] dp = new int[str.length][str.length];
        //(2)初始化数组,dp[i][i]=0;但是数组初始的默认值全是0,所以这一步可以不做了;

        //(3)
        for (int j = 1; j < str.length; j++) { //最外层循环,以列,从上到下
            dp[j - 1][j] = str[j - 1] == str[j] ? 0 : 1;
            for (int i = j - 2; i > -1; i--) { //最内层循环,以行,从左到右
                if (str[i] == str[j]) { //两头相等,去两头;
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    //两头不相等,找一头最小值然后+1;
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp;
    }

    /*
    进阶问题的思考:给出了字符串的最长子序列回文字符,有什么用?
    首先:上面求dp的算法,时间/空间复杂度都是O(n^2);如果提供了最长子序列的回文字符串,那么时间复杂度可以加速到O(N)
    如果str长度为N,strlps长度为M,那么最终最短回文字符串的长度是 2N-M;
    */
    public static String getPalindrome2(String str, String strlps) {
        if (str == null || str.equals("")) {
            return "";
        }
        char[] res = new char[str.length() * 2 - strlps.length()];

        int i = 0;
        int j = strlps.length() - 1;
        int lPart = 0;
        int rPart = str.length() - 1;

        int lResPart = 0;
        int rResPart = res.length - 1;

        while (i <= j) {
            char c = strlps.charAt(i);
            //返回的是c在str中的left位置;
            int leftEndIndex = getLeftEndIndex(lPart, c, str);
            int rightStartIndex = getRightStartIndex(rPart, c, str);

            int len = leftEndIndex - lPart + rightStartIndex - rPart;

            //把原始的左边抄过来,留着lPart还有用;
            for (int k = lPart; k <leftEndIndex ; k++,lResPart++) {
                res[lResPart] = str.charAt(k);
            }

            //把原始的右边逆序补过来
            for (int k = rPart; k > rightStartIndex; k--,rResPart--) {
                res[rResPart] = str.charAt(k);
            }

        /*    for (int k = 0; k < ; k++) {
                res[lResPart] = str.charAt(k);
            }

            for (int k = 0; k < ; k++) {
                res[rResPart] = str.charAt(k);
            }*/

            i++;
            j--;
        }
        return null;
    }

    public static int getLeftEndIndex(int start, char c, String str) {
        for (int i = start; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                return i;
            }
        }
        return 0;
    }

    public static int getRightStartIndex(int end, char c, String str) {
        for (int i = end; i >= 0; i--) {
            if (str.charAt(i) == c) {
                return i;
            }
        }
        return 0;
    }

    static void setRes(char[] res) {

    }

    public static void main(String[] args) {
        String str = "AB1CD2EFG3H43IJK2L1MN";
        System.out.println(getPalindrome1(str));
    }

}
