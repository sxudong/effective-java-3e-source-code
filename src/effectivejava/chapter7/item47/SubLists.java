package effectivejava.chapter7.item47;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 第47条：Stream要优先用Collection作为返回类型
 */
// Two ways to generate a stream of all the sublists of a list (Pages 219-20)
// 两种方法生成列表的所有子列表流
public class SubLists {
    // Returns a stream of all the sublists of its input list (Page 219)
    // 返回其输入列表的所有子列表的流
    public static <E> Stream<List<E>> of(List<E> list) {
        return Stream.concat(Stream.of(Collections.emptyList()),
                prefixes(list).flatMap(SubLists::suffixes));
    }

    private static <E> Stream<List<E>> prefixes(List<E> list) {
        return IntStream.rangeClosed(1, list.size())
                .mapToObj(end -> list.subList(0, end));
    }

    private static <E> Stream<List<E>> suffixes(List<E> list) {
        return IntStream.range(0, list.size())
                .mapToObj(start -> list.subList(start, list.size()));
    }

//    // Returns a stream of all the sublists of its input list, excluding the empty list
//    // 返回其输入列表的所有子列表的流，不包括空列表
//    // This version is derived from the obvious iterative code 该版本源自明显的迭代代码 (Page 220)
//    public static <E> Stream<List<E>> of(List<E> list) {
//        return IntStream.range(0, list.size())
//                .mapToObj(start ->
//                        IntStream.rangeClosed(start + 1, list.size()) // 不包含空更表
//                        //IntStream.rangeClosed(start + (int) Math.signum(start), list.size()) // 包含空更表
//                                .mapToObj(end -> list.subList(start, end)))
//                .flatMap(x -> x);
//    }

    public static void main(String[] args) {
        //List<String> list = Arrays.asList(args);
        List<String> list = Arrays.asList(new String[]{"a","b","c"});
        SubLists.of(list).forEach(System.out::println);
    }
}
/* Output:
[]
[a]
[a, b]
[b]
[a, b, c]
[b, c]
[c]
 */