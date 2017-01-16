package extended.chapter_3_binarytreeproblem;

import java.util.HashMap;

/**
 * Author: zhangxin
 * Time: 2016/12/20 0020.
 * Desc: 根据pre数组和in数组,不构建整棵树,直接构建post数组
 */
public class Problem_22_PreAndInArrayToPosArray {
    public static int[] getPosArray(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        int len = pre.length;
        //直接生成len长度的pos数组(后序遍历的数组)
        int[] pos = new int[len];

        //使用一个HashMap的结果来存储,避免后面不断的使用for来寻找分割点;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < len; i++) {
            map.put(in[i], i);  //map中存放的是 中序遍历数组的val和索引
        }

        /* 左老师的实现方法
        setPos(pre, 0, len - 1, in, 0, len - 1, pos, len - 1, map);
        return pos;
        */
        //#############################################################
        /*
        我的实现方法
        */
        setPosCore(pre, 0, len - 1, in, 0, len - 1, pos, len - 1, map);
        return pos;
    }

    // 从右往左依次填好后序数组s
    // si为后序数组s该填的位置
    // 返回值为s该填的下一个位置
    public static int setPos(int[] p, int pi, int pj, int[] n, int ni, int nj,
                             int[] s, int si, HashMap<Integer, Integer> map) {
        if (pi > pj) {
            return si;
        }
        s[si--] = p[pi];
        int i = map.get(p[pi]);
        si = setPos(p, pj - nj + i + 1, pj, n, i + 1, nj, s, si, map);
        return setPos(p, pi + 1, pi + i - ni, n, ni, i - 1, s, si, map);
    }


    public static int setPosCore(int[] pre, int prei, int prej, int[] in, int ini, int inj,
                                 int[] pos, int posi, HashMap<Integer, Integer> map) {

        //想一下什么时候终止,在一棵相对子树遍历完的时候,直接返回posi,那么终止条件是什么呢?怎么写?
        //之前想过用prei==prej的时候直接处理这个叶子节点,还少了一次递归,但是如果一个子树没有右子树呢?所以只能用下面的方法了;
        if (prei > prej) {
            return posi;
        }

        //把当前最后一位给安排了
        pos[posi] = pre[prei];
        posi--;//别忘了给posi--,给下一个元素放位置;


        //找到在中序数组中的左右子树的分割点
        int index = map.get(pre[prei]);
        prei++; // 根节点已经赋值了,找先序中的左右子树分界;


        //先找右子树的;
        //根据in得到长度 index+1 ~ inj 间距是: inj - (index + 1)
        // pre的prej依然是prej,prei = prej - (inj - (index + 1))
        posi = setPosCore(pre, prej - (inj - (index + 1)), prej, in, index + 1, inj, pos, posi, map);
        //最终返回的是posi,也就是右子树处理完了,当前可用的下标是 posi; 接下来处理左子树


        //还是先根据in得到长度:  ini ~ index-1 长度是:(index-1 - ini)
        //设置pre,prei依然是prei,prej = prei + index-1 -ini
        return setPosCore(pre, prei, prei + index - 1 - ini, in, ini, index - 1, pos, posi, map);
    }
}
