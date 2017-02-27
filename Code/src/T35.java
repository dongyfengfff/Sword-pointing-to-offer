/**
 * Author: zhangxin
 * Time: 2017/2/26 0026.
 * Desc:在字符串中找到第一个只出现过一次的字符的索引,eg:abaccdeff,那么结果就是1;
 *
 * 思路:使用两次遍历即可完成,第一次遍历:出现次数保存在hash数组中,第二次遍历字符串,看其在hash中是不是==1
 * */
public class T35 {
    public int FirstNotRepeatingChar(String str){
        if (str==null||str.length()<1){
            return -1;
        }
        char[] hash = new char[256];
        for (int i = 0; i < str.length(); i++) {
            hash[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (hash[str.charAt(i)]==1){
                return i;
            }
        }
        return -1;
    }
}
