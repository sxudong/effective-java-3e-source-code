package effectivejava.chapter8.item52;
import java.util.*;
import java.math.*;

/**
 * 第52条: 明智审慎的使用重载
 */
// Broken! - What does this program print?  (Page 238)
// 坏了的！ -该程序打印什么？
public class CollectionClassifier {
    public static String classify(Set<?> s) {
        return "Set";
    }

    public static String classify(List<?> lst) {
        return "List";
    }

    public static String classify(Collection<?> c) {
        return "Unknown Collection";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        for (Collection<?> c : collections)
            // 选择是在编译完成的，完全基于参数的编译时类型
            System.out.println(classify(c)); // 每一个调用的都是重载方法classify(Collection<?> c)
    }
}
/* Output:
Unknown Collection
Unknown Collection
Unknown Collection
 */