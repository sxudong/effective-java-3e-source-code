package effectivejava.chapter5.item30;

import java.util.function.UnaryOperator;

/**
 * 第30条：优先考虑泛型方法
 */
// Generic singleton factory pattern (Page 136-7)
// 泛型单例工厂模式
public class GenericSingletonFactory {
    // Generic singleton factory pattern 泛型单例工厂模式
    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    // 泛型方法
    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() { // 身份功能
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    // Sample program to exercise generic singleton 练习泛型单例的示例程序
    public static void main(String[] args) {
        String[] strings = { "jute", "hemp", "nylon" };
        UnaryOperator<String> sameString = identityFunction();
        for (String s : strings)
            System.out.println(sameString.apply(s));

        Number[] numbers = { 1, 2.0, 3L };
        UnaryOperator<Number> sameNumber = identityFunction();
        for (Number n : numbers)
            System.out.println(sameNumber.apply(n));
    }
}
/* Output:
jute
hemp
nylon
1
2.0
3
 */