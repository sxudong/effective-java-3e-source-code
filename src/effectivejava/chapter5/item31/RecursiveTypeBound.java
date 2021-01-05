package effectivejava.chapter5.item31;
import java.util.*;

/**
 * 第31条：利用有限制通配符来提升API的灵活性
 */
// Using a recursive type bound with wildcards (Page 143)
// 使用与通配符绑定的递归类型 （P108 递归类型限制）
public class RecursiveTypeBound { // 非泛型类
    // 泛型方法
    public static <E extends Comparable<? super E>> E max(List<? extends E> list) {
        if (list.isEmpty())
            throw new IllegalArgumentException("Empty list");

        E result = null;
        for (E e : list)
            if (result == null || e.compareTo(result) > 0) // E的Comparable消费E实例
                result = e;

        return result;
    }

    public static void main(String[] args) {
//        List<String> argList = Arrays.asList(args);
        List<String> argList = Arrays.asList("1","2","3");
        System.out.println(max(argList)); // 3
    }
    public static void swap(List<?> list, int i, int j) {
        //list.set(i, list.set(j, list.get(i)));//Error:(45, 39) java: 不兼容的类型: java.lang.Object无法转换为capture#1
        // List<E> 中的set方法：E set(int var1, E var2);
    }
}