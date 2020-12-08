package effectivejava.chapter5.item31;
import java.util.*;
import java.util.concurrent.ScheduledFuture;

/**
 * 第31条：利用有限制通配符来提升API的灵活性
 */
// Generic union method with wildcard types for enhanced flexibility (Pages 142-3)
// 具有通配符类型的泛型union方法可增强灵活性
public class Union {
    // public static <E> Set<E> union(Set<E> s1, Set<E> s2)
    // 再个参数都是生产者，因此根据PECS助词符，这个声明应该是：
    public static <E> Set<E> union(Set<? extends E> s1,
                                   Set<? extends E> s2) {
        Set<E> result = new HashSet<E>(s1);
        result.addAll(s2);
        return result;
    }

    // Simple program to exercise flexible generic staticfactory
    // 简单的程序，可以灵活地应用通用静态工厂
    public static void main(String[] args) {
        Set<Integer> integers = Set.of(1, 3, 5); // JDK9之后新添加方法
        Set<Double> doubles = Set.of(2.0, 4.0, 6.0);
        Set<Number> numbers = union(integers, doubles);

//        Set<Integer> integers = new HashSet<>();
//        integers.add(1);
//        integers.add(3);
//        integers.add(5);
//
//        Set<Double> doubles =  new HashSet<>();
//        doubles.add(2.0);
//        doubles.add(4.0);
//        doubles.add(6.0);
//
//        Set<Number> numbers = union(integers, doubles);

//      // Explicit type parameter - required prior to Java 8
//      // 显式类型参数-Java 8之前需要显示声明类型参数
//      Set<Number> numbers = Union.<Number>union(integers, doubles);

        System.out.println(numbers);

        //List<ScheduledFuture<?>> scheduledFutures = ...
    }
}
/* Output:
[2.0, 4.0, 1, 3, 5, 6.0]
 */
