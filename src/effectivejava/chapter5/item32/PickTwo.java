package effectivejava.chapter5.item32;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 第32条：合理的结合泛型和可变参数
 */
// Subtle heap pollution (Pages 147-8)
// 微小的堆污染
public class PickTwo {
    // UNSAFE - Exposes a reference to its generic parameter array!
    // UNSAFE - 公开对其泛型参数数组的引用！
    static <T> T[] toArray(T... args) {
        return args;
    }

    static <T> T[] pickTwo(T a, T b, T c) {
        switch(ThreadLocalRandom.current().nextInt(3)) {
            case 0: return toArray(a, b);
            case 1: return toArray(a, c);
            case 2: return toArray(b, c); // 实际导致污染的方法
        }
        throw new AssertionError(); // Can't get here
    }

    public static void main(String[] args) {
        String[] attributes = pickTwo("Good", "Fast", "Cheap");
        System.out.println(Arrays.toString(attributes));
    }
}
/* 编译时不会有任何警告，但是在运行的时候，它会抛出一个ClassCastException，编译器在pickTwo返回值上产生了一个隐藏的String[]转换：
Exception in thread "main" java.lang.ClassCastException: class [Ljava.lang.Object; cannot be cast to class [Ljava.lang.String; ([Ljava.lang.Object; and [Ljava.lang.String; are in module java.base of loader 'bootstrap')
	at effectivejava.chapter5.item32.PickTwo.main(PickTwo.java:28)
 */