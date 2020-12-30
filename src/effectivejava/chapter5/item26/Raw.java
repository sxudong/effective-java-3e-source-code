package effectivejava.chapter5.item26;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 第26条：请不要使用原生态类型
 *
 * 出错之后应尽快发现，最好是编译时就发现。
 * 总结：使用泛型可以在编译时就发现错误。而使用原生类型，
 * 逃过了类型检查，编译可以通过，运行时才抛出错误。
 */
// 运行时失败-unsafeAdd1方法使用原始类型(List)，unsafeAdd1方法使用泛型的参数类型 (Page 119)
public class Raw {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        unsafeAdd1(strings, Integer.valueOf(42)); // 类型是String，故意放一个Integer
        String s = strings.get(0); // 调用结果Integer转换成String时,抛出ClassCastException
        // unsafeAdd2(strings, Integer.valueOf(42)); // 编译报错（其实IDEA在未编译时就已经提示错误）
        // Error:(17, 20) java: 不兼容的类型:
        // java.util.List<java.lang.String>无法转换为java.util.List<java.lang.Object>
    }

    // 原生类型接收，逃过类型检查，失掉类型安全
    private static void unsafeAdd1(List list, Object o) {
        list.add(o);
        if (o instanceof Set<?>) { // 原始类型
            Set<?> s = (Set<?>) o; // 通配符类型
        }
    }

    // 使用List<Object>接收list，在编译时就检查出错误
    private static void unsafeAdd2(List<Object> list, Object o) {
        list.add(o);
        if (o instanceof Set<?>) { // Raw type
            Set<?> s = (Set<?>) o; // Wildcard type
        }
    }
}
/* Output:
Exception in thread "main" java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')
	at effectivejava.chapter5.item26.Raw.main(Raw.java:9)
 */
