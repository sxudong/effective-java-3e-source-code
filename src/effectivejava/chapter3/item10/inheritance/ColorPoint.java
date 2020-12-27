package effectivejava.chapter3.item10.inheritance;

import effectivejava.chapter3.item10.Color;
import effectivejava.chapter3.item10.Point;

/**
 * 第10条: 重写 equals 方法时遵守通用约定
 */
// 尝试向Point添加值组件 (Page 33)
public class ColorPoint extends Point {
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    // 1.-----违反对称-----start
    @Override public boolean equals(Object o) {
        if (!(o instanceof ColorPoint))
            return false;
        return super.equals(o) && ((ColorPoint) o).color == color;
    }
    // 1.-----违反对称-----end

    // 2.-----违反传递性-----start
    // 这种方法提供了对称性，但是却牺牲了传递性
//    @Override public boolean equals(Object o) {
//        if (!(o instanceof Point))      // 是不是一个Point
//            return false;
//
//        // If o is a normal Point, do a color-blind comparison. 如果o是Point，则进行Color比较
//        if (!(o instanceof ColorPoint)) // 如果是Point，是不是ColorPoint
//            return o.equals(this);
//
//        // o is a ColorPoint; do a full comparison. o是一个ColorPoint； 进行全面比较
//        return super.equals(o) && ((ColorPoint) o).color == color;
//    }
    // 2.-----违反传递性-----end

    public static void main(String[] args) {
        // 1.违反对称性
        Point p = new Point(1, 2);
        ColorPoint cp = new ColorPoint(1, 2, Color.RED);
        System.out.println(p.equals(cp)); // true
        System.out.println(cp.equals(p)); // false

        // 2.违反传递性
        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
        System.out.printf("%s %s %s%n",
                          p1.equals(p2),  // false
                          p2.equals(p3),  // true  Point没有Color属性
                          p1.equals(p3)); // false
    }
}
