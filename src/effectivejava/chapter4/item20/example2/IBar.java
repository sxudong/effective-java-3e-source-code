package effectivejava.chapter4.item20.example2;

// 具有IFoo的属性，又有一些自己的个性
public interface IBar extends IFoo {
    // 增加自己独有的方法bar()
    void bar();
}