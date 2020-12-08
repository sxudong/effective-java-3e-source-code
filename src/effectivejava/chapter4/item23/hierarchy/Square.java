package effectivejava.chapter4.item23.hierarchy;

/**
 * 第23条：类层次优于标签类
 */
// Class hierarchy replacement for a tagged class  (Page 110-11)
// 标记类的类层次结构替换
class Square extends Rectangle { // 正方形
    // 构造器
    Square(double side) {
        super(side, side);
    }
}
