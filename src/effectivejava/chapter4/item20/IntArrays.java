package effectivejava.chapter4.item20;
import java.util.*;

/**
 * 第20条：接口优于抽象类
 */
// 在骨架实现之上构建具体实现(Page 81)
public class IntArrays {

    static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);

        // Diamond运算符仅在Java 9和更高版本中才是合法的
        // 如果使用的是早期版本，请指定<Integer>
        // 使用骨架类构筑返回一个自定义的完整的List实现
        return new AbstractList<>() {
            @Override public Integer get(int i) {
                return a[i];  //自动装箱
            }

            @Override public Integer set(int i, Integer val) {
                int oldVal = a[i];
                a[i] = val;     //自动拆箱
                return oldVal;  //自动装箱
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