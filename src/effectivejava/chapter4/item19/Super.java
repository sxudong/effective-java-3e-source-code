package effectivejava.chapter4.item19;

/**
 *第19条：要么设计继承并提供文档说明，要么禁止继承
 */
// Class whose constructor invokes an overridable method. NEVER DO THIS! (Page 95)
// 其构造函数调用一个可重写方法的类。 绝对不要这样做！
public class Super {
    // Broken - constructor invokes an overridable method
    // 坏了的 - 构造函数调用一个可重写的方法
    public Super() {
        overrideMe();
    }

    public void overrideMe() {
    }
}
