package effectivejava.chapter8.item52;

import java.math.BigInteger;
import java.util.*;

/**
 * 第52条: 明智审慎的使用重载
 */
// Repaired  static classifier method. (Page 240)
// 修复了静态分类器方法。
public class FixedCollectionClassifier {
    public static String classify(Collection<?> c) {
        // 显示的 instanceof
        return c instanceof Set  ? "Set" :
                c instanceof List ? "List" : "Unknown Collection";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        for (Collection<?> c : collections)
            System.out.println(classify(c));
    }
}
/* Output:
Set
List
Unknown Collection
 */