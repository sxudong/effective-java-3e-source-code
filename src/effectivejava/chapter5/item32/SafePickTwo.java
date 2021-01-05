package effectivejava.chapter5.item32;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 第32条：合理的结合泛型和可变参数
 */
// Safe version of PickTwo using lists instead of arrays (Page 150)
// 使用列表而不是数组的PickTwo的安全版本
public class SafePickTwo {
    static <T> List<T> pickTwo(T a, T b, T c) {
        switch(ThreadLocalRandom.current().nextInt(3)) {
            case 0: return List.of(a, b); // 类型是安全的，因为它只使用泛型，没有用到数组
            case 1: return List.of(a, c);
            case 2: return List.of(b, c);
        }
        throw new AssertionError();
    }

    public static void main(String[] args) {
        List<String> attributes = pickTwo("Good", "Fast", "Cheap");
        System.out.println(attributes);
    }
}
