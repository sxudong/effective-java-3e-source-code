package effectivejava.chapter4.item23.hierarchy;

/**
 * 第23条：类层次优于标签类
 */
// Class hierarchy replacement for a tagged class 标记类的类层次结构替换(Page 110-11)
class Circle extends Figure { // 圆
    final double radius; // final的域

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * (radius * radius);
    }
}
