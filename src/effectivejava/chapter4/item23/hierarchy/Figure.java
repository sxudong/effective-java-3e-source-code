package effectivejava.chapter4.item23.hierarchy;

/**
 * 第23条：类层次优于标签类
 */
// Class hierarchy replacement for a tagged class  (Page 110-11)
// 标记类的类层次结构替换
abstract class Figure {
    // 方法行为若依赖于标签值，则定义为抽象方法。
    // 方法行为若不依赖于标签值, 就把方法放在抽象类中。
    abstract double area();
}