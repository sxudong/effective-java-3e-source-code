package effectivejava.chapter5.item32;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 第32条：合理的结合泛型和可变参数
 */
// It is unsafe to store a value in a generic varargs array parameter (Page 146)
// 将值存储在通用varargs数组参数中是不安全的
public class Dangerous {
    // Mixing generics and varargs can violate type safety!
    // 混合泛型和可变参数会违反类型安全性！
    static void dangerous(List<String>... stringLists) {
        List<Integer> intList = List.of(42);
        Object[] objects = stringLists;
        objects[0] = intList; // Heap pollution 当一个参数化类型的变量引用不属于该类型的对象时会发生堆污染
        String s = stringLists[0].get(0); // ClassCastException 编译器将自动获取到元素转换为String
        // 可变参数泛型方法，需要存和取两端都定义好参数类型，存和取的参数类型要一致，不然取出来的数据转换就容易失败，不知道是什么了？
    }

    public static void main(String[] args) {
        dangerous(List.of("There be dragons!", "abc"));
    }
}
/* Output:
Exception in thread "main" java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')
	at effectivejava.chapter5.item32.Dangerous.dangerous(Dangerous.java:15)
	at effectivejava.chapter5.item32.Dangerous.main(Dangerous.java:19)
 */