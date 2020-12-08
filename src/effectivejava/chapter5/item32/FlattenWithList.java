package effectivejava.chapter5.item32;

import java.util.ArrayList;
import java.util.List;

/**
 * 第32条：合理的结合泛型和可变参数
 */
// List as a typesafe alternative to a generic varargs parameter (page 149)
public class FlattenWithList {

    static <T> List<T> flatten(List<List<? extends T>> lists) { // List参数代替可替代可变参数
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists)
            result.addAll(list);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> flatList = flatten(List.of(
                List.of(1, 2), List.of(3, 4, 5), List.of(6,7)));
        System.out.println(flatList);
    }

    // JDK9 List.of()源码
//    @SafeVarargs
//    static <E> List<E> of(E... elements) {
//        switch(elements.length) {
//            case 0:
//                return ImmutableCollections.emptyList();
//            case 1:
//                return new ImmutableCollections.List12(elements[0]);
//            case 2:
//                return new ImmutableCollections.List12(elements[0], elements[1]);
//            default:
//                return new ImmutableCollections.ListN(elements);
//        }
//    }
}
/* Output:
[1, 2, 3, 4, 5, 6, 7]
 */