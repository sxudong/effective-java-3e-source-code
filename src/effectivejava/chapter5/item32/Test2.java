package effectivejava.chapter5.item32;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 堆污染造成的后果
 */
public class Test2 {
    public static void varagMethod(Set<Integer> objects) {
        objects.add(new Integer(10));
    }

    /**
     * 可以看到，程序报了一个 ClassCastException (类型转换错误)，这是因为Java允许我们
     * 将一个无泛型参数的 Set对象 传递给 varagMethod 方法，并且我们在 varagMethod 方法中，
     * 给传入的 Set对象 添加了一个 Integer 对象，之后我们遍历 set 集合时，将 Set 中第一个
     * 元素强转成 String，很明显 Integer 不能转化为 String 对象.
     * @param args
     */
    public static void main(String[] args) {
        Set set = new TreeSet();
        // 将一个无泛型的Set传递给了varagMethod方法，此时就有可能造成堆污染
        varagMethod(set);

        Iterator<String> iter = set.iterator();
        while (iter.hasNext()) {
            String str = iter.next();   // java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String
            System.out.println(str);
        }
    }

}
