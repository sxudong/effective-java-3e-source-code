package effectivejava.chapter8.item55;

import java.util.*;

/**
 * 第55条：明智审慎的返回 Optional
 */
// Using Optional<T> as a return type (Pages 249-251)
// 使用Optional <T>作为返回类型
public class Max {
    // Returns maximum value in collection - throws exception if empty (Page 249)
    // 返回集合中的最大值-如果为空则抛出异常
//    public static <E extends Comparable<E>> E max(Collection<E> c) {
//        if (c.isEmpty())
//            throw new IllegalArgumentException("Empty collection");
//
//        E result = null;
//        for (E e : c)
//            if (result == null || e.compareTo(result) > 0)
//                result = Objects.requireNonNull(e);
//
//        return result;
//    }

    // Returns maximum value in collection as an Optional<E> (Page 250)
    // 返回集合中的最大值作为 Optional <E>
//    public static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
//        if (c.isEmpty())
//            return Optional.empty();
//
//        E result = null;
//        for (E e : c)
//            if (result == null || e.compareTo(result) > 0)
//                result = Objects.requireNonNull(e);
//
//        return Optional.of(result);
//    }

    // Returns max val in collection as Optional<E> - uses stream (Page 250)
    // 返回集合中的最大值作为 Optional <E> -- 使用流
    public static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
        return c.stream().max(Comparator.naturalOrder());
    }

    public static void main(String[] args) {
        //List<String> words = Arrays.asList(args);
        List<String> words = Arrays.asList("1", "2", "3", "4", "5");
        System.out.println(max(words));

        // Using an optional to provide a chosen default value (Page 251)
        // 使用可选项提供选定的默认值
        String lastWordInLexicon = max(words).orElse("No words...");
        System.out.println(lastWordInLexicon);
    }
}
/* Output:
Optional[5]
5
 */