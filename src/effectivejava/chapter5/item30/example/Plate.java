package effectivejava.chapter5.item30.example;

import java.util.ArrayList;
import java.util.List;

class Plate<T> {
    private T item;

    public Plate(T t) {
        item = t;
    }

    public void set(T t) {
        item = t;
    }

    public T get() {
        return item;
    }

    public static void main(String[] args) {
        Plate<? super Fruit> p = new Plate<Fruit>(new Fruit());
        Plate<? super Fruit> p2 = new Plate<Foot>(new Foot()); // Foot是Fruit的父类
        //存入元素正常
        p.set(new Fruit());
        p.set(new Apple());
        p2.set(new Fruit());
        //p2.set(new Foot()); // Plate<? super Fruit> 不能保存Foot，只能插入Fruit子类的对象和Fruit本身

        //读取出来的东西只能存放在object类里。
//        Apple newFruit3 = p.get(); //Error
//        Fruit newFruit1 = p.get(); // Error
        Object newFruit2 = p.get();

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
}