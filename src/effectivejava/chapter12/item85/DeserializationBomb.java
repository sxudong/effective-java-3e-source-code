package effectivejava.chapter12.item85;

import effectivejava.chapter12.Util;

import java.util.HashSet;
import java.util.Set;

/**
 * 第85条：其他方法优先于Java序列化
 */
// Deserialization bomb - deserializing this stream takes forever - Page 340
// 反序列化炸弹 - 将流反序列化需要永远
public class DeserializationBomb {
    public static void main(String[] args) throws Exception {
        System.out.println(bomb().length); // 5744
        Util.deserialize(bomb()); // 反序列化
    }

    static byte[] bomb() {
        Set<Object> root = new HashSet<>();
        Set<Object> s1 = root;
        Set<Object> s2 = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            Set<Object> t1 = new HashSet<>();
            Set<Object> t2 = new HashSet<>();
            t1.add("foo"); // make it not equal to t2 使它不等于t2

            s1.add(t1);
            s1.add(t2);
            s2.add(t1);
            s2.add(t2);

            s1 = t1;
            s2 = t2;
            /**
             *                  t1 -> s1 ...
             *                /
             *       t1 -> s1
             *     /         \
             * s1             t2 -> s2 ...
             *     \          /t1 -> s1 ...
             *       t2 -> s2
             *               \t2 -> s2 ...
             */
        }

        return Util.serialize(root); // 序列化
    }
}
