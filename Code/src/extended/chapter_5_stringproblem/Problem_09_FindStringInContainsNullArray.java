package extended.chapter_5_stringproblem;

/**
 * Author: zhangxin
 * Time: 2017/1/9 0009.
 * Desc:给定一个字符串数组,其内是按照字典序排列的,但是中间存在null值,给定一个字符串,返回其在数组中最左的位置(可能存在重复的值);
 * <p>
 * 思路1:如果没有null值,那么直接使用二分查找最佳,存在null值,尽可能使用二分查找,改进二分查找,遇到中间值为null
 * 那么向左边或者右边找到一个不为null的值作为中值;
 * <p>
 * TODO:看别人写的代码如此精炼,意识到自己写代码的能力很弱啊;
 */
public class Problem_09_FindStringInContainsNullArray {
    public static int getIndex(String[] strs, String str) {
        if (strs == null || strs.length == 0 || str == null) {
            return -1;
        }

        int left = 0;
        int right = strs.length - 1;
        int mid;
        int res = -1; //最终返回的结果

        while (right >= left) {
            mid = (left + right) / 2;

            //最难处理的一种情况;
            if (strs[mid] == null) {
                int i = mid;
                //先向左找第一个不为null 的值;逻辑写的不好;
               /* while (strs[i] == null && --i >= left) {
                    // i--;
                }*/
                while (strs[i] == null) {
                    i--;
                    if (i <left) {   //这里不能写<=,因此如果此时left正好是null的话,i又==left,退出循环,那么下面的if分支中,strs[i]空指针异常;
                        break;
                    }
                }
                //说明左半区都是null或者左半区的值小于str;
                if (i < left || strs[i].compareTo(str) < 0) {
                    left = mid + 1;
                } else {
                    res = strs[i].equals(str) ? i : res;
                    right = i - 1;
                }
            } else {
                int tmp = strs[mid].compareTo(str);
                //相等;
                if (tmp == 0) {
                    res = mid;
                    right = mid - 1;
                } else if (tmp > 0) {//mid大
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{null, "a", null, "a", null, "b", null,
                null, null, "b", null, "c", null, "c", null, null, "d", null,
                null, null, null, null, "d", null, "e", null, null, "e", null,
                null, null, "f", null, "f", null};
        String str1 = "a";
        System.out.println(getIndex(strs, str1));
        String str2 = "b";
        System.out.println(getIndex(strs, str2));
        String str3 = "c";
        System.out.println(getIndex(strs, str3));
        String str4 = "d";
        System.out.println(getIndex(strs, str4));
        String str5 = "e";
        System.out.println(getIndex(strs, str5));
        String str6 = "f";
        System.out.println(getIndex(strs, str6));

    }
}
