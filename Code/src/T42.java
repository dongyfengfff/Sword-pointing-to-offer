/**
 * Author: zhangxin
 * Time: 2016/11/24 0024.
 * Desc:
 * 1. 翻转单词顺序,hello world => world hello
 * 1. 左旋字符串, (abcde,2) => cdeab
 * NOTE:经典题目,一定要会啊;需要注意的是:不知道两边是否有空格,那么手动给两边都加上一个空格;
 * 首先将整个字符串进行翻转,然后将每个单词在进行反转...
 */
public class T42 {

    public String ReverseSentence0(String str) {
        if (str.length() == 0) {
            return "";
        }
        char[] array = (str + " ").toCharArray();  //手动加个空格,防止后面数组溢出;
        //如果传进来的字符串本来末尾就是空格怎么??
        int start = 0, end = 0;
        while (end < str.length()) {
            for (; start < str.length(); start++) {
                if (array[start] != ' ') {
                    break;
                }
            }
            //此时得到的 start 就是第一个单词的头

            if (start >= str.length()) {
                break;
            }

            end = start;
            for (; end < str.length(); end++) {
                if (array[end] == ' ') {
                    break;
                }
            }
            // 此时得到的end 就是单词后的第一个空格;
            reverse(array, start, end - 1);
            start = end;
        }
        reverse(array, 0, str.length() - 1);
        str = new String(array, 0, str.length());
        return str;
    }

    void reverse(char[] array, int start, int end) {
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }

    void swap0(char[] array, int start, int end) {
        char c = array[start];
        array[start] = array[end];
        array[end] = c;

    }


    public static void main(String[] args) {
        T42 t = new T42();
        System.out.println(t.ReverseSentence("ab cd"));
//        System.out.println(t.LeftRotateString("abcdef",3));
    }

    public String LeftRotateString(String str, int n) {
        if (n <= 0 || n > str.length()) {
            return str;
        }
        char[] array = str.toCharArray();
        reverse(array,0,n-1);
        reverse(array,n,str.length()-1);
        reverse(array,0,str.length()-1);
        return new String(array);
    }



    public String ReverseSentence(String str) {
        if(str==null||str.length()<2){
            return str;
        }
        char[] cs = (" "+str+" ").toCharArray();
        swap(cs,0,cs.length-1);
        int start = findFirst(cs,0);
        int end = findLast(cs,start);
        while(start>0){
            swap(cs,start,end-1);
            start = findFirst(cs,end);
            end = findLast(cs,start);
        }
        return new String(cs,1,str.length());
    }

    //第一个不为空格的位置;
    private int findFirst(char[] cs,int start){
        for(int i = start;i<cs.length;i++){
            if(cs[i]!=' '){
                return i;
            }
        }
        return -1;
    }

    //第一个为空格的位置;
    private int findLast(char[] cs,int start){
        for(int i = start+1;i<cs.length;i++){
            if(cs[i]==' '){
                return i;
            }
        }
        return cs.length;
    }

    private void swap(char[] cs,int start,int end){
        while(start<end){
            char tmp = cs[start];
            cs[start] = cs[end];
            cs[end] = tmp;
            start++;
            end--;
        }
    }
}
