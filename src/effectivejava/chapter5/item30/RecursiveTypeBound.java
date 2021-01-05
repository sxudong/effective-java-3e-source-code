package effectivejava.chapter5.item30;
import java.util.*;

/**
 * 第30条：优先考虑泛型方法
 */
// Using a recursive type bound to express mutual comparability (Pages 108)
// 使用递归类型来表示相互可比较性
public class RecursiveTypeBound { // 普通类（非泛型方法）

    // 泛型方法
    // 返回集合中的最大值-使用递归类型限定
    // 类型限制 <E extends Comparable<E>> 可以读作“任何可以与自已比较的类型E” P108
    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if (c.isEmpty())
            throw new IllegalArgumentException("Empty collection");

        E result = null;
        for (E e : c)
            // e.compareTo(result) Comparable<T>接口递规类型限制
            // 1>0 2>1 3>2 递归与result比较所有数字
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        return result; // 返回最大数字
    }

    public static void main(String[] args) {
        //List<String> argList = Arrays.asList(args);
        List<String> argList = Arrays.asList("1","2","3");
        String max = max(argList);
        System.out.println(max); //3
    }
}