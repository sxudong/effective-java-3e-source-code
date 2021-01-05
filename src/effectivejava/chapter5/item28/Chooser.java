package effectivejava.chapter5.item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 第28条 列表优于数组
 * @param <T>
 */
// Chooser - a class badly in need of generics! 选择器-非常需要泛型的类！
//public class Chooser {
//    private final Object[] choiceList;
//
//    public Chooser(Collection choices) {
//        choiceList = choices.toArray();
//    }
//
//    public T choose() {
//        Random rnd = ThreadLocalRandom.current();
//        return choiceList.get(rnd.nextInt(choiceList.length));
//    }
//
//}

// A first cut at making Chooser generic - won't compile
// 制作的第一步Chooser泛型-无法编译
//public class Chooser<T> {
//    private final T[] choiceArray;
//    public Chooser(Collection<T> choices) {
//        choiceArray = choices.toArray();
//    }
//    // choose method unchanged
//}

// List-based Chooser - typesafe (Page 101)
// 基于列表的选择器-类型安全
public class Chooser<T> {
    private final List<T> choiceList;

    public Chooser(Collection<T> choices) {
        choiceList = new ArrayList<>(choices);
    }

    public T choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceList.get(rnd.nextInt(choiceList.size()));
    }

    public static void main(String[] args) {

//        // Why generic array creation is illegal - won't compile! 为什么创建泛型数据是非法的？不能编译
//        List<String>[] stringLists = new List<String>[1]; // (1)new List<String>[1]是非法的，假设它合法
//        List<Integer> intList = List.of(42);              // (2)初始合包含单个元素的List<Integer>擦除后是List
//        Object[] objects = stringLists;                   // (3)将List<String>[]保存到一个Object[]变量中，这是合法的。
//        objects[0] = intList;                             // (4)将List<Integer>保存到Object[]里的唯一元素中，这是可以的，因为泛型是通过擦除实现的。
//        String s = stringLists[0].get(0);                 // (5)从这个数组里唯一的列表中获取一个元素。编译器自动将获取到的元素转换成String，但它是一个Integer。因此在运行时得到了一个ClassCastException异常。

        List<Integer> intList = List.of(1, 2, 3, 4, 5, 6);

        Chooser<Integer> chooser = new Chooser<>(intList);

        for (int i = 0; i < 10; i++) {
            Number choice = chooser.choose();
            System.out.println(choice);
        }
    }
}
/* Output:
6
3
2
2
3
3
4
2
5
2
 */