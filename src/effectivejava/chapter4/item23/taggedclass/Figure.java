package effectivejava.chapter4.item23.taggedclass;

/**
 * 第23条：类层次优于标签类
 */
// Tagged class - vastly inferior to a class hierarchy! (Page 109)
// 标记的类-远不及类层次结构！
class Figure {
    enum Shape { RECTANGLE, CIRCLE };

    // Tag field - the shape of this figure 标签字段-此图的形状
    final Shape shape;

    // These fields are used only if shape is RECTANGLE
    // 仅当形状为RECTANGLE时才使用这些字段
    double length;
    double width;

    // This field is used only if shape is CIRCLE
    // 仅当形状为CIRCLE时才使用此字段
    double radius;

    // Constructor for circle
    // 圆的构造函数
    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    // Constructor for rectangle 矩
    // 形的构造函数
    Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    double area() {
        switch(shape) {
            case RECTANGLE:
                return length * width;
            case CIRCLE:
                return Math.PI * (radius * radius);
            default:
                throw new AssertionError(shape);
        }
    }
}
