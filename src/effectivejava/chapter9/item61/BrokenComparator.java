package effectivejava.chapter9.item61;
import java.util.*;

/**
 * 第61条：基本数据类型优于包装类
 * 以下面这个比较器为例，它的设计目的是表示 Integer 值的升序排序。
 */
// Broken comparator - can you spot the flaw? - Page 273
// 坏的比较器-你可以发现缺陷吗？
public class BrokenComparator {
    public static void main(String[] args) {

        // 如果 i 和 j 引用表示相同 int 值的不同 Integer 实例，这个比较将返回 false，比较器将错误地返回 1
        // 将 == 操作符应用于包装类型几乎都是错误的
        Comparator<Integer> naturalOrder =
                (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1); // 返回1

       // Fixed Comparator 固定比较器 - Page 274
//        Comparator<Integer> naturalOrder = (iBoxed, jBoxed) -> {
//            int i = iBoxed, j = jBoxed; // Auto-unboxing 自动拆箱
//            return i < j ? -1 : (i == j ? 0 : 1);
//        };

        //int result = naturalOrder.compare(42, 42);
        int result = naturalOrder.compare(new Integer(42), new Integer(42));
        System.out.println(result); // 0
    }
}
