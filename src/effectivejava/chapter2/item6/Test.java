package effectivejava.chapter2.item6;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        // 方法一 keySet()没有值会new一个，之后每次返回都是同一个，而不是每次都创建一个KeySet。
        Map map = new HashMap<>();
        Set<String> set = map.keySet();
        for (String s : set) {
            System.out.println(s + "," + map.get(s));
        }
        Set<String> s = map.keySet(); // 第二次调用时不会再次创建KeySet

        // 自动装箱
        // Long sum = 0L;
        long sum = 0;
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 500_000_000; i++) {
            sum += i;
        }
        System.out.println(sum);
        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) /1000F;
        System.out.println(Float.toString(seconds) + " seconds.");
    }
}
/*
自动装箱输出:
124999999750000000
2.782 seconds.

基本类型输出：
124999999750000000
0.239 seconds.
 */