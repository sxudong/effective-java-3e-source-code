package effectivejava.chapter3.item10.inheritance;
import effectivejava.chapter3.item10.Point;

import java.util.*;

// Test program that uses CounterPoint as Point
// 使用CounterPoint作为点的测试程序
public class CounterPointTest {
    // Initialize unitCircle to contain all Points on the unit circle  (Page 43)
    // 初始化 unitCircle 以包含单位圆上的所有点
    private static final Set<Point> unitCircle = Set.of(
            new Point( 1,  0), new Point( 0,  1),
            new Point(-1,  0), new Point( 0, -1));

    public static boolean onUnitCircle(Point p) {
        return unitCircle.contains(p);
    }

    public static void main(String[] args) {
        Point p1 = new Point(1,  0);
        Point p2 = new CounterPoint(1,  0);

        System.out.println(onUnitCircle(p1)); // true

        // Should print true, but doesn't if Point uses getClass-based equals
        // 应该打印true，但是如果Point使用基于getClass的equals则不打印
        System.out.println(onUnitCircle(p2)); // false
    }
}
