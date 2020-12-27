package effectivejava.chapter3.item10.composition;

import effectivejava.chapter3.item10.Color;
import effectivejava.chapter3.item10.Point;

import java.util.Objects;

// Adds a value component without violating the equals contract (page 44)
// 在不违反平等合约的情况下添加价值成分
public class ColorPointC {
    private final Point point; // 私有的Point域
    private final Color color;

    public ColorPointC(int x, int y, Color color) {
        point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    /**
     * Returns the point-view of this color point.
     */
    public Point asPoint() { // 公有的视图
        return point;
    }

    @Override public boolean equals(Object o) {
        if (!(o instanceof ColorPointC))
            return false;
        ColorPointC cp = (ColorPointC) o;
        return cp.point.equals(point) && cp.color.equals(color);
    }

    @Override public int hashCode() {
        return 31 * point.hashCode() + color.hashCode();
    }

    public static void main(String[] args) {
        ColorPointC p1 = new ColorPointC(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPointC p3 = new ColorPointC(1, 2, Color.BLUE);
        // 没有违反传递性
        System.out.printf("%s %s %s%n",
                p1.equals(p2), p2.equals(p3), p1.equals(p3)); // false false false
        // 没有违反非空性
        System.out.println(p1.equals(null)); // false
    }
}