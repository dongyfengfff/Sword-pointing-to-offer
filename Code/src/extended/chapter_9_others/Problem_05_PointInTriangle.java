package extended.chapter_9_others;

/**
 * Author: zhangxin
 * Time: 2017/3/20 0020.
 * Desc:一个点是否在三角形内
 * <p>
 * 解法1:面积法
 * 解法2:叉乘法
 */
public class Problem_05_PointInTriangle {

    //面积法1,使用的方法太复杂了;
    public static boolean isInside1(double x1, double y1, double x2, double y2,
                                    double x3, double y3, double x, double y) {
        double area1 = getArea(x1, y1, x2, y2, x, y);
        double area2 = getArea(x1, y1, x3, y3, x, y);
        double area3 = getArea(x2, y2, x3, y3, x, y);
        double allArea = getArea(x1, y1, x2, y2, x3, y3);
        return area1 + area2 + area3 <= allArea;
    }

    public static double getArea(double x1, double y1, double x2, double y2,
                                 double x3, double y3) {
        double side1Len = getSideLength(x1, y1, x2, y2);
        double side2Len = getSideLength(x1, y1, x3, y3);
        double side3Len = getSideLength(x2, y2, x3, y3);
        double p = (side1Len + side2Len + side3Len) / 2;
        return Math.sqrt((p - side1Len) * (p - side2Len) * (p - side3Len) * p);
    }

    public static double getSideLength(double x1, double y1, double x2,
                                       double y2) {
        double a = Math.abs(x1 - x2);
        double b = Math.abs(y1 - y2);
        return Math.sqrt(a * a + b * b);
    }


    //面积法2,比较简单;
    public static boolean isInside1_1(double x1, double y1, double x2, double y2,
                                      double x3, double y3, double x, double y) {
        //首先求所给的三角形abc的面积;
        //然后再求p与abc所围成的pab,pac,pbc的面积是否和abc相等;
        double s = triangleArea(x1, y1, x2, y2, x3, y3);
        double s1 = triangleArea(x, y, x1, y1, x2, y2);
        double s2 = triangleArea(x, y, x2, y2, x3, y3);
        double s3 = triangleArea(x, y, x3, y3, x1, y1);

        return s == s1 + s2 + s3;
    }

    private static double triangleArea(double x1, double y1, double x2, double y2, double x3, double y3) {
        double res = Math.abs((x1 * y2 + x2 * y3 + x3 * y1 - x2 * y1 - x3 * y2 - x1 * y3) / 2.0D);
        return res;
    }


    //面积方法精读不够,下面采用向量积的方法
//NOTE:向量积没看懂...

}
