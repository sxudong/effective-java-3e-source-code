package effectivejava.chapter5.item26;
import java.util.*;

/**
 * 第26条：请不要使用原生态类型
 */
// Fails at runtime - unsafeAdd method uses a raw type (List)!  (Page 119)
// 运行时失败-unsafeAdd方法使用原始类型
public class Raw {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        unsafeAdd(strings, Integer.valueOf(42));
        // strings.get(0)的调用结果Integer转换成String,收到一个ClassCastException
        String s = strings.get(0); // Has compiler-generated cast
    }

    // private static void unsafeAdd(List<Object> list, Object o) {
    private static void unsafeAdd(List list, Object o) {
        list.add(o);
        if (o instanceof Set<?>) { // Raw type 原始类型
            Set<?> s = (Set<?>) o; // Wildcard type 通配符类型
        }
    }

    // 使用List<Object>接收list
    // Error:(8, 19) java: 不兼容的类型: java.util.List<java.lang.String>无法转换为java.util.List<java.lang.Object>
//    private static void unsafeAdd(List list, Object o) {
//        list.add(o);
//        if (o instanceof Set<?>) { // Raw type
//            Set<?> s = (Set<?>) o; // Wildcard type
//        }
//    }
}
/* Output:
Exception in thread "main" java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')
	at effectivejava.chapter5.item26.Raw.main(Raw.java:9)
 */
