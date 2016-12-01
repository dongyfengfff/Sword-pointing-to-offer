/**
 * Author: zhangxin
 * Time: 2016/11/25 0025.
 * Desc:传入一个数组,包含五个数,判断其是不是顺子,大小王为0,但是可以当做任意数
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
        int[] a = {0, 2, 4, 7, 0};
        //t.isContinuous(a);
        System.out.println(t.isContinuous1(a));
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
}
