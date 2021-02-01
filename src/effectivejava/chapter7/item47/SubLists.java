package effectivejava.chapter7.item47;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;
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
        //Stream提供concat()方法来连接两个流并返回一个流。传了一个空列表进去
        return Stream.concat(Stream.of(Collections.emptyList()),
                prefixes(list).flatMap(SubLists::suffixes));
    }

    // subList() 方法用于截取并返回动态数组中的一部分。
    // arraylist.subList(int fromIndex, int toIndex)
    // fromIndex - 截取元素的起始位置，包含该索引位置元素
    // toIndex - 截取元素的结束位置，不包含该索引位置元素
    private static <E> Stream<List<E>> prefixes(List<E> list) {
        return IntStream.rangeClosed(1, list.size())
                //peek 调试打印
               // .peek(x -> System.out.println("end" + x))
                .mapToObj(end -> list.subList(0, end)); //[a], [a,b], [a,b,c]返回3个list
    }

    private static <E> Stream<List<E>> suffixes(List<E> list) {
        return IntStream.range(0, list.size())
                .mapToObj(start -> list.subList(start, list.size()));

//                .mapToObj(start -> {
//                System.out.println("list: " + list + " " + "start: " + start + " " + list.size() + " " + list.subList(start, list.size()));
//                return list.subList(start, list.size());
//            });
            // [a] -> 0-1 [a]
            // [a, b]    -> 0-2 [a, b]
            //           -> 1-2 [b]
            // [a, b, c] -> 0-3 [a, b, c]
            // [a, b, c] -> 1-3 [b, c]
            // [a, b, c] -> 2-3 [c]
    }

//    // Returns a stream of all the sublists of its input list, excluding the empty list
//    // 返回其输入列表的所有子列表的流，不包括空列表
//    // This version is derived from the obvious iterative code 该版本源自明显的迭代代码 (Page 220)
//    public static <E> Stream<List<E>> of(List<E> list) {
//        return IntStream.range(0, list.size())
//                .mapToObj(start ->
//                        IntStream.rangeClosed(start + 1, list.size()) // 不包含空更表
//                        //IntStream.rangeClosed(start + (int) Math.signum(start), list.size()) // 包含空更表 signum(x) x>0返回1.0; x<0返回-1.0; x=0返回0.0
//                             .mapToObj(end -> list.subList(start, end)))
//                .flatMap(x -> x);
//    }

    public static void main(String[] args) {
        //List<String> list = Arrays.asList(args);
        List<String> list = Arrays.asList(new String[]{"a","b","c"});
        SubLists.of(list).forEach(System.out::println);
    }
}
/* Output:
[]              <== 这个空的list是Stream.concat()方法中传入的
[a]
[a, b]
[b]
[a, b, c]
[b, c]
[c]
 */