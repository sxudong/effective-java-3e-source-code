//: generics/SuperTypeWildcards.java
package effectivejava.chapter5.item31;

import java.util.ArrayList;
import java.util.List;

class Fruit {}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Orange extends Fruit {}

public class SuperTypeWildcards {
  static void writeTo(List<? super Apple> apples) { // Apple或Apple的父类
    apples.add(new Apple());
    apples.add(new Apple());
    apples.add(new Jonathan());
    // apples.add(new Fruit()); // Error <? super Apple>只能插入Apple导出的子类, 因为ArrayList.add(E e)方法里用的是泛型E。
  }

  static void writeTo2(List<? super Apple> apples) { // Apple或Apple的父类
    apples.add(new Apple());
    apples.add(new Jonathan());
    // apples.add(new Fruit()); // Error <? super Apple>只能插入Apple导出的子类
  }

  public static void main(String[] args) {
    List<Apple> apples = new ArrayList<Apple>();
    List<Fruit> fruit = new ArrayList<Fruit>();
    List<Jonathan> jonathans = new ArrayList<Jonathan>();
    writeTo(apples);
    writeTo(fruit); // 传入Apple的父类list
    //! writeTo(jonathans);

    // Fruit 是Apple 的基类
    List<? super Apple> apples2 = new ArrayList<Fruit>();
    writeTo2(apples);

    // Integer is a "superclass" of Integer (in this context)
    // 整数是整数的“超类”（在这种情况下）
    List<? super Integer> foo1 = new ArrayList<Integer>();

    // Number is a superclass of Integer
    // Number是Integer的超类
    List<? super Integer> foo2 = new ArrayList<Number>();

    // Object is a superclass of Integer
    // Object是Integer的超类
    List<? super Integer> foo3 = new ArrayList<Object>();
  }
} ///:~
