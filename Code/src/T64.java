import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: zhangxin
 * Time: 2016/11/27 0027.
 * Desc:数据流中的中位数
 * 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * <p>
 * 使用最大堆;
 * Java的PriorityQueue 是从JDK1.5开始提供的新的数据结构接口，默认内部是自然排序，结果为小顶堆，也可以自定义排序器，比如下面反转比较，完成大顶堆
 * 所以这里并不需要我们手动构建最大堆或最小堆,只需要使用该类即可;
 * 思路：
 * 为了保证插入新数据和取中位数的时间效率都高效，这里使用大顶堆+小顶堆的容器，并且满足：
 * 1、两个堆中的数据数目差不能超过1，这样可以使中位数只会出现在两个堆的交接处；
 * 2、大顶堆的所有数据都小于小顶堆，这样就满足了排序要求。
 */
public class T64 {

    int count;
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(); //默认是最小堆,使用poll就把最小的取出来;

    //不明白这里传入的初始大小是11是为什么??
    //使用最大堆,使用poll就把最大的取出来;
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11,new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            //PriorityQueue默认是小顶堆，实现大顶堆，需要反转默认排序器            
            return o2.compareTo(o1);
        }
    });


    public void Insert(Integer num) {
        count++;
        if ((count&1)!=0){ //奇数,最终向maxHeap中提供一个数;
            if (!minHeap.isEmpty()&&num > minHeap.peek()){  //这个数比最小堆的最小值大,把这个数弄进去,然后把最小堆的数取出来,放进最大堆;
                minHeap.offer(num);
                num = minHeap.poll();
            }
            maxHeap.offer(num);
        }else{ //偶数,最终向minHeap中提供一个数;
            if (!maxHeap.isEmpty()&&num<maxHeap.peek()){
                maxHeap.offer(num);
                num = maxHeap.poll(); //取出来的是最大值
            }
            minHeap.offer(num);
        }
    }

    //mad,你是获取一个数据输出一次啊++;
    public Double GetMedian() {
        if (count==0){
            throw new RuntimeException("no available number!");
        }
        if ((count&1)==0){
            return (minHeap.peek()+maxHeap.peek())/2.0;
        }else { //奇数
            return maxHeap.peek()*1.0;
        }
    }

    public static void main(String[] args) {
        T64 t =new T64();
        int[] a = {5,2,3,4,1,6,7,0,8};
        for (int i:a){
            t.Insert(i);
        }
        System.out.println(t.GetMedian());
    }
}
