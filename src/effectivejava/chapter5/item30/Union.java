package effectivejava.chapter5.item30;
import java.util.*;

/**
 * 第30条：优先考虑泛型方法
 */
// Generic union method and program to exercise it  (Pages 135-6)
// 泛型联合方法和实施该程序的程序
public class Union { // 普通类（非泛型类）

    // Generic method 泛型方法
    public static <E> Set<E> union(Set<E> s1, Set<E> s2) { // <E>类型参数列表 Set<E>返回类型
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    // Simple program to exercise generic method 简单的程序来练习泛型方法
    public static void main(String[] args) {
        Set<String> guys = Set.of("Tom", "Dick", "Harry");
        Set<String> stooges = Set.of("Larry", "Moe", "Curly");
        Set<String> aflCio = union(guys, stooges);
        System.out.println(aflCio);
    }
}
/* Output:
[Moe, Tom, Harry, Larry, Curly, Dick]
 */