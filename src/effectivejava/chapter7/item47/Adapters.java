package effectivejava.chapter7.item47;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 第47条：Stream要优先用Collection作为返回类型
 */
// Adapters from stream to iterable and vice-versa (Page 216)
// 从流到可迭代的适配器，反之亦然
public class Adapters {
    // Adapter from  Stream<E> to Iterable<E>
    // 从Stream<E> 到 Iterable<E> 的适配器
    public static <E> Iterable<E> iterableOf(Stream<E> stream) {
        // Stream接口中有iterator方法
        return stream::iterator;
    }

    // Adapter from Iterable<E> to Stream<E>
    // 从Iterable<E> 到 Stream<E> 的适配器
    public static <E> Stream<E> streamOf(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3);
        Iterable<Integer> integers1 = Adapters.iterableOf(integers.stream());
        integers1.forEach(System.out::println);
    }
}
/* Output:
1
2
3
 */