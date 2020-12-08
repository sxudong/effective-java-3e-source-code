package effectivejava.chapter4.item23.hierarchy;

/**
 * 第23条：类层次优于标签类
 */
// Class hierarchy replacement for a tagged class  (Page 110-11)
// 标记类的类层次结构替换
class Rectangle extends Figure { // 长方形
    final double length; // final的域
    final double width;  // final的域

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }
}
