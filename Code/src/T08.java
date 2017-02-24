/**
 * Author: zhangxin
 * Time: 2017/2/20 0020.
 * Desc:旋转数组中最小的数字;把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出该旋转数组的最小元素。
 * <p>
 * 核心做法:二分查找;
 */
public class T08 {
    public int minNumberInRotateArray(int[] array) {
        if (array.length==0){
            return 0;
        }
        if (array.length==1){
            return array[0];
        }
        int start = 0;
        int end = array.length - 1;
        int mid = 0;
        //注意判断条件:
        while (end - start > 1) {
            mid = (start + end) / 2;
            if (array[mid] > array[start] && array[mid] > array[end]) {
                start = mid;
            } else if (array[mid] < array[start] && array[mid] < array[end]) {
                end = mid;
            } /*else {
                return array[start];
            }*/
        }

        return Math.min(array[start], array[end]);
    }



    //更简洁的写法:
    public int minNumberInRotateArray1(int [] array) {
        //注意开始条件的判断;
        if (array.length==0){
            return 0;
        }
        if (array.length==1){
            return array[0];
        }

        int low = 0 ; int high = array.length - 1;
        while(low < high){
            int mid = low + (high - low) / 2;

            //由于本题的特殊性,只需要跟array[high]比较就可以了;
            if(array[mid] > array[high]){  //mid大
                low = mid + 1;  //那么范围肯定在mid+1到high之间了;
            }else if(array[mid] == array[high]){
                high = high - 1;
            }else{    //mid小;
                high = mid;  //这里没有将high设为mid-1,因为mid很可能就是最小值,此时的最小值一定在low到mid之间;
            }
        }
        return array[low];
    }
}
