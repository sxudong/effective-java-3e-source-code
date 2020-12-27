package effectivejava.chapter3.item10;

import java.util.Objects;

/**
 * 第10条: 重写 equals 方法时遵守通用约定
 */
// 简单不变二维整数点类
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override public boolean equals(Object o) {
        // 首先这要是一个Point才能比较
        if (!(o instanceof Point))
            return false;
        Point p = (Point)o;
        return p.x == x && p.y == y;
    }

//    // 违反了里氏替代原则
//    @Override public boolean equals(Object o) {
//        // 类型校验，两个类类型不等返回false
//        if (o == null || o.getClass() != getClass())
//            return false;
//        Point p = (Point) o;
//        return p.x == x && p.y == y;
//    }

    // See Item 11 覆盖equals必需覆盖hashCode
    @Override public int hashCode()  {
        return 31 * x + y;
    }
}
