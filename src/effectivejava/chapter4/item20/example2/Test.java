package effectivejava.chapter4.item20.example2;

/**
 * 公开的接口，如IFoo是不能修改的，以后我发现还有些公共的东西能加进来，怎么办呢？
 * 我可以在骨架类（AbstractFoo）中设置新的方法，让子类继承就ok了，如例子中的
 * setVal()和getVal()方法.
 *
 * 骨架类（AbstractFoo）是个抽象类，它实现了IFoo接口，但是可以选择地实现它的方法，
 * 并不需要全部实现，因为接口本质上也是抽象类。另外，抽象类可以定义实现的方法，
 * 所以就可以增加一些“基础方法”，供子类调用。
 */
//https://blog.csdn.net/jiafu1115/article/details/6736063
public class Test {
    public static void main(String[] args) {
        FooBar fb = new FooBar();
        fb.setVal("val");
        System.out.println(fb.getVal());
        fb.foo();
    }
}
/* Output:
val
AbstarctFoo
 */