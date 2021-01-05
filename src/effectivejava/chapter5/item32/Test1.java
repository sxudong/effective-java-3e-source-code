package effectivejava.chapter5.item32;

import java.util.Set;
import java.util.TreeSet;

/**
 * 堆污染
 */
public class Test1 {
    public static void varagMethod(Set<Integer> objects) {
    }

    // 以上代码，我们将一个无泛型的Set传递给了varagMethod方法，此时就有可能造成堆污染。
    // 当发生这种情况时，编译器可以检测到，并给我们 Warning，如下
    public static void main(String[] args) {
        Set set = new TreeSet();
        set.add("abc");
        varagMethod(set); //IDEA警告：Unchecked assignment: 'java.util.Set' to 'java.util.Set<java.lang.Integer>'
    }
}
