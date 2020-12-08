package effectivejava.chapter4.item20.example;


/**
 * Effective Java之骨架实现类
 * 第20条：接口优由抽象类
 *
 * 先来阐述几个概念：
 *
 * 1、接口：定义了一组方法的集合；其实现类必须要实现接口内的方法，java8之后为接口提供了缺省方法，
 * 解决了在为接口添加方法的时候，实现类必须添加实现才能编译通过的问题。
 *
 * 2、抽象类：定义了子类的通用特性；
 *
 * 3、骨架实现类结合了二者的优点，他既是抽象类，同时实现了上层接口。
 *
 * 一个接口可能有很多个实现类，如果每个实现类都直接去实现接口的时候，每次我们都需要去重写接口的方法，
 * 这会造成很多的重复劳动，比如：接口DemoInterface，他包含一个add方法。他又两个实现类 Demo1和Demo2,
 * 这两个实现类都必须去分别实现add方法。
 */
public class TestDemo {

    public static void main(String[] args) {
        Demo1 d1 = new Demo1();
        d1.add();
        Demo2 d2 = new Demo2();
        d2.add();
    }

}