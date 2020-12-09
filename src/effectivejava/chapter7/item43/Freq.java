package effectivejava.chapter7.item43;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

/**
 * 第43条：方法引用优于Lambda
 */
// Frequency table implemented with map.merge, using lambda and method reference (Page 197)
// 使用lambda和方法引用，通过map.merge实现的频率表
public class Freq {
    public static void main(String[] args) {
        Map<String, Integer> frequencyTable = new TreeMap<>();
        
//        for (String s : args)
        for (String s : new String[]{"1","2","3"})
            frequencyTable.merge(s, 1, (count, incr) -> count + incr); // Lambda
        System.out.println(frequencyTable);

        frequencyTable.clear();
//        for (String s : args)
        for (String s : new String[]{"1","2","3"})
            frequencyTable.merge(s, 1, Integer::sum); // Method reference 方法引用
        System.out.println(frequencyTable);

        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 10);
        map.put("key2", 20);
        // 当key存在时，进行old+value；当key不存在时，将key的值设置为1
        map.merge("key", 1, Integer::sum);
        // 当key1存在时，进行old+value；当key1不存在时，将key1的值设置为20
        map.merge("key1", 20, Integer::sum);
        map.forEach((k, v) -> System.out.println("k:" + k + "---" + "v:" + v));
    }
}
/* Output:
{1=1, 2=1, 3=1}
1
{1=1, 2=1, 3=1}
1
 */