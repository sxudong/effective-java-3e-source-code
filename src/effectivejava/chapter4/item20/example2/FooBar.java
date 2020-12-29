package effectivejava.chapter4.item20.example2;

// 继承AbstractFoo，并实现IBar
// 这样不用重新一一定义IFoo接口中的方法,因为骨架类（AbstractFoo）
// 已经有基础的方法了，只需要实现不共用的方法即可。
public class FooBar extends AbstractFoo implements IBar {

    @Override public void bar() {
        // yingkhtodo Auto-generated method stub
    }

    @Override public void add() {
        // yingkhtodo Auto-generated method stub
    }

    @Override public void del() {
        // yingkhtodo Auto-generated method stub
    }
}