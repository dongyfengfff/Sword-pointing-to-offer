/**
 * Author: zhangxin
 * Time: 2016/11/25 0025.
 * Desc:传入一个数组,包含五个数,判断其是不是顺子,大小王为0,但是可以当做任意数
 *
 *
 * 第一中方法居然上来先排序,这个想法极为愚蠢!!!
 *
 * 使用第二种,int[14],这里最优解是第三种,注意最后情况的考虑,不用过度考虑;
 */
public class T44 {
    public boolean isContinuous(int[] numbers) {
        //先排序吧
        for (int i = 0; i < numbers.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[min] > numbers[j]) {
                    min = j;
                }
            }
            swap(numbers, i, min);
        }


        int count0 = 0;
        int index = 0; //第一个不为0的位置;
        for (; index < numbers.length; index++) {
            if (numbers[index] == 0) {
                count0++;
            } else {
                break;
            }
        }


        return false;
    }

    private void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    public static void main(String[] args) {
        T44 t = new T44();
        int[] a = {1,3,2,5,4};
        //t.isContinuous(a);
        System.out.println(t.isContinuous3(a));
    }

    public boolean isContinuous1(int[] numbers) {
        if (numbers.length<5){
            return false;
        }
        int[] a = new int[14];
        int count0 = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                count0++;
            } else {
                if (a[numbers[i]]++ == 1) {
                    return false;
                }
            }
        }

        int index = 1;
        for (; index < a.length; index++) {
            if (a[index] != 0) {
                break;
            }
        }

        int end = index + 5;
        index++;
        for (; index < end; index++) {
            if (a[index]==0){
                if (count0-- <=0){
                    return false;
                }
            }
        }

        return true;
    }



    public boolean isContinuous3(int[] numbers) {
        if (numbers==null||numbers.length<5) {
            return false;
        }

        int[] nums = new int[14];

        //表示每张牌有几张;
        for (int i = 0; i< numbers.length; i++) {
            if (numbers[i]==0) {
                nums[0]++;
            }else if(nums[numbers[i]]++==1){
                return false;
            }
        }
        //能走到这一步,说明除了0之外,没有其它重复元素;

        int count0 = nums[0];
        int start = 1;
        //找到第一个不为0的开始位置
        for (; start<nums.length ;start++) {
            if (nums[start] >1) {
                return false;
            }

            if (nums[start] == 1) {
                break;
            }
        }

        int end = 13;
        for (;end>0 ;end--) {
            if (nums[end]==1) {
                break;
            }
        }
        //此时找到了start和end;
        int len = end -start +1;
        if (len>5) {
            return false;
        }
        //能走到这一步,说明len<=5;.下面的情况的考虑根本是多余的,只要此时len<=5,一定返回true;
    /*    if (count0==0) {
            return len==5;
        }
        if (count0==1) {
            return len>3;
        }
        if (count0==2) {
            return len>2;
        }*/

//        System.out.println("大小王一共就两个,走到这里说明程序本身有问题了...");
        //这个程序有问题,居然有count0=3的情况...
        return true;
    }
}
