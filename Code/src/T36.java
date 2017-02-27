/**
 * Author: zhangxin
 * Time: 2016/11/23 0023.
 * Desc:数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。
 * 并将P对1000000007取模的结果输出。 即输出P%1000000007
 *
 * NOTE:不明白这个题的点在哪? 归并排序原理要熟练掌握
 */

/*归并排序的改进，把数据分成前后两个数组(递归分到每个数组仅有一个数据项)，
合并数组，合并时，出现前面的数组值array[i]大于后面数组值array[j]时；则前面
数组array[i]~array[mid]都是大于array[j]的，count += mid+1 - i
参考剑指Offer，但是感觉剑指Offer归并过程少了一步拷贝过程。
还有就是测试用例输出结果比较大，对每次返回的count mod(1000000007)求余
需要注意的一点是在merge中求出count后,每次都要count = count%1000000007,否则count在叠加的过程中会溢出;
*/

public class T36 {
    int count = 0;
    int[] aux;

    public int InversePairs(int[] array) {
        int length = array.length;
        aux = new int[length];
        sort(array, 0, length - 1);
        return count % 1000000007;
    }

    private void sort(int[] a, int lo, int hi) {
        // System.out.println(lo+"-"+hi);
        if (hi <= lo) return;  //注意返回的条件;
        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public void merge(int[] a, int lo, int mid, int hi) {
        //分别代表两个子数组的起点;
        int i = lo;
        int j = mid + 1;

        //这一步可以优化?
        //每次都要把之前的数据拷贝一下,在下面的比较中再拷贝回去,不值
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[i] > aux[j]) {
                a[k] = aux[j++];
                count += mid - i + 1;
                count = count % 1000000007;
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        int i = 493330277;
        System.out.println(i % 1000000007);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1000000007);
    }
}



/*
int InversePairs(vector<int> data) {
        int length  = data.size();
        return mergeSort(data, 0, length-1);
    }

    int mergeSort(vector<int>& data, int start, int end) {
        // 递归终止条件
        if(start >= end) {
            return 0;
        }

        // 递归
        int mid = (start + end) / 2;
        int leftCounts = mergeSort(data, start, mid);
        int rightCounts = mergeSort(data, mid+1, end);

        // 归并排序，并计算本次逆序对数
        vector<int> copy(data); // 数组副本，用于归并排序
        int foreIdx = mid;      // 前半部分的指标
        int backIdx = end;      // 后半部分的指标
        int counts = 0;         // 记录本次逆序对数
        int idxCopy = end;      // 辅助数组的下标
        while(foreIdx>=start && backIdx >= mid+1) {
            if(data[foreIdx] > data[backIdx]) {
                copy[idxCopy--] = data[foreIdx--];
                counts += backIdx - mid;
            } else {
                copy[idxCopy--] = data[backIdx--];
            }
        }
        while(foreIdx >= start) {
            copy[idxCopy--] = data[foreIdx--];
        }
        while(backIdx >= mid+1) {
            copy[idxCopy--] = data[backIdx--];
        }
        for(int i=start; i<=end; i++) {
            data[i] = copy[i];
        }

        return (leftCounts+rightCounts+counts);
    }
*/

/*
public class Solution {
   public int InversePairs(int [] array) {
            if(array==null || array.length<=0){
                return 0;
            }
            int pairsNum=mergeSort(array,0,array.length-1);
            return pairsNum;
        }

        public int mergeSort(int[] array,int left,int right){
            if(left==right){
                return 0;
            }
            int mid=(left+right)/2;
            int leftNum=mergeSort(array,left,mid);
            int rightNum=mergeSort(array,mid+1,right);
            return (Sort(array,left,mid,right)+leftNum+rightNum)%1000000007;
        }

        public int Sort(int[] array,int left,int middle,int right){
            int current1=middle;
            int current2=right;
            int current3=right-left;
            int temp[]=new int[right-left+1];
            int pairsnum=0;
            while(current1>=left && current2>=middle+1){
                if(array[current1]>array[current2]){
                    temp[current3--]=array[current1--];
                    pairsnum+=(current2-middle);     //这个地方是current2-middle！！！！
                    if(pairsnum>1000000007)//数值过大求余
                    {
                        pairsnum%=1000000007;
                    }
                }else{
                    temp[current3--]=array[current2--];
                }
            }
            while(current1>=left){
                temp[current3--]=array[current1--];
            }
            while(current2>=middle+1){
                temp[current3--]=array[current2--];
            }
            //将临时数组赋值给原数组
            int i=0;
            while(left<=right){
                array[left++]=temp[i++];
            }
            return pairsnum;
        }
}
*/
