package effectivejava.chapter4.item19;

import java.time.Instant;
import java.util.ArrayList;

/**
 *第19条：要么设计继承并提供文档说明，要么禁止继承
 */
// Demonstration of what can go wrong when you override a method  called from constructor (Page 77)
// 演示重写构造函数调用的方法时可能出问题的地方
public final class Sub extends Super {
    // Blank final, set by constructor
    // 空白的final，由构造函数设置
    private final Instant instant;

    Sub() {
        instant = Instant.now(); // 当前时间日期
    }

    // Overriding method invoked by superclass constructor
    // 超类构造函数调用的重写方法
    @Override public void overrideMe() {
        System.out.println(instant);
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }
}
/* Output:
null
2020-12-01T00:07:07.968739600Z
 */