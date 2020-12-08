package effectivejava.chapter4.item20;
import java.util.*;

/**
 * 第20条：接口优于抽象类
 */
// Concrete implementation built atop skeletal implementation 在骨架实现之上构建具体实现(Page 81)
public class IntArrays {

    static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);

        // The diamond operator is only legal here in Java 9 and later
        // If you're using an earlier release, specify <Integer>
        // Diamond运算符仅在Java 9和更高版本中才是合法的
        // 如果使用的是早期版本，请指定<Integer>
        return new AbstractList<>() {  // 用匿名类的方式返回一个自已的List实现
            @Override public Integer get(int i) {
                return a[i];  // Autoboxing 自动装箱 (Item 6)
            }

            @Override public Integer set(int i, Integer val) {
                int oldVal = a[i];
                a[i] = val;     // Auto-unboxing 自动拆箱
                return oldVal;  // Autoboxing 自动装箱
            }

            @Override public int size() {
                return a.length;
            }
        };
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++)
            a[i] = i;

        List<Integer> list = intArrayAsList(a);
        Collections.shuffle(list); // 对集合进行重新打乱(随机排序)
        System.out.println(list);
    }
}
/* Output:
[2, 5, 9, 7, 0, 3, 4, 6, 1, 8]
 */