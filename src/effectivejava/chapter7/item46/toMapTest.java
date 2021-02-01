package effectivejava.chapter7.item46;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://zhuanlan.zhihu.com/p/144228930?from_voters_page=true
 */
public class toMapTest {
    public static void main(String[] args) {
        Student studentA = new Student("20190001","小明");
        Student studentB = new Student("20190002","小红");
        Student studentC = new Student("20190003","小丁");

        //Function.identity() 获取这个对象本身，那么结果就是Map<String, Student> 即 {id:student}
        //toMap(keyMapper, valueMapper): 参数的两个方法, 一个把元素转换到key, 一个转换到value. -> 冲突的时候会抛异常.
        //串行收集
        Map<String, Student> map1 = Stream.of(studentA, studentB, studentC)
                .collect(Collectors.toMap(Student::getId, Function.identity()));
        for (Map.Entry<String, Student> m : map1.entrySet()) {
            System.out.println("key:" + m.getKey() + " value:" + m.getValue().getId());
        }

        //并发收集
        ConcurrentMap<String, Student> map2 = Stream.of(studentA, studentB, studentC)
                .parallel()
                .collect(Collectors.toConcurrentMap(Student::getId, Function.identity()));
        for (Map.Entry<String, Student> m : map2.entrySet()) {
            System.out.println("key:" + m.getKey() + " value:" + m.getValue().getId());
        }
        System.out.println("################ toMap()第一个重载方法 ################");

        //Map<String,String> 即 {id:name}
        //串行收集
        Map<String, String> map3 = Stream.of(studentA, studentB, studentC)
                .collect(Collectors.toMap(Student::getId, Student::getName));
        for (Map.Entry<String, String> m : map3.entrySet()) {
            System.out.println("key:" + m.getKey() + " value:" + m.getValue());
        }

        //并发收集
        ConcurrentMap<String, String> map4 = Stream.of(studentA, studentB, studentC)
                .parallel()
                .collect(Collectors.toConcurrentMap(Student::getId, Student::getName));
        for (Map.Entry<String, String> m : map4.entrySet()) {
            System.out.println("key:" + m.getKey() + " value:" + m.getValue());
        }
        System.out.println("################ toMap()第二个重载方法 ################");

        // toMap(keyMapper, valueMapper, mergeFunction): 同样 key 的 value 会merge
        // 那么如果key重复的该怎么处理？这里我们假设有两个id相同Student，如果他们id相同，
        // 在转成Map的时候，取name大一个，小的将会被丢弃。

        //Map<String,Student>
        Map<String, Student> map5 = Stream.of(studentA, studentB, studentC)
                .collect(Collectors
                        .toMap(Student::getId, Function.identity(),
                                BinaryOperator.maxBy(Comparator.comparing(Student::getName))));

        for (Map.Entry<String, Student> m : map5.entrySet()) {
            System.out.println("key:" + m.getKey() + " value:" + m.getValue().getName());
        }

        //可能上面比较复杂，这编写一个命令式
        //Map<String,Student>
        Map<String, Student> map6 = Stream.of(studentA, studentB, studentC)
                .collect(Collectors
                        .toMap(Student::getId, Function.identity(),
                                (s1, s2) -> {
                                    //这里使用compareTo 方法 s1>s2 会返回1,s1==s2 返回0 ，否则返回-1
                                    if (((Student) s1).getName().compareTo(((Student) s2).getName()) < -1) {
                                        return s2;
                                    } else {
                                        return s1;
                                    }
                                }
                        ));

        for (Map.Entry<String, Student> m : map6.entrySet()) {
            System.out.println("key:" + m.getKey() + " value:" + m.getValue().getName());
        }
        System.out.println("################ toMap()第三个重载方法 ################");

        // toMap(keyMapper, valueMapper, mergeFunction, mapSupplier): mapSupplier是一个map工厂, 用来指定具体的map实现: EnumMap或TreeMap.
        //自定义LinkedHashMap
        //Map<String,Student>
        LinkedHashMap<String, Student> map7 = Stream.of(studentA, studentB, studentC)
                .collect(Collectors
                        .toMap(Student::getId, Function.identity(),
                                BinaryOperator.maxBy(Comparator.comparing(Student::getName)),
                                LinkedHashMap::new));
        for (Map.Entry<String, Student> m : map7.entrySet()) {
            System.out.println("key:" + m.getKey() + " value:" + m.getValue().getName());
        }
    }
}
/* Output:
key:20190003 value:20190003
key:20190002 value:20190002
key:20190001 value:20190001
key:20190003 value:20190003
key:20190002 value:20190002
key:20190001 value:20190001
################ toMap()第一个重载方法 ################
key:20190003 value:小丁
key:20190002 value:小红
key:20190001 value:小明
key:20190003 value:小丁
key:20190002 value:小红
key:20190001 value:小明
################ toMap()第二个重载方法 ################
key:20190003 value:小丁
key:20190002 value:小红
key:20190001 value:小明
key:20190003 value:小丁
key:20190002 value:小红
key:20190001 value:小明
################ toMap()第三个重载方法 ################
key:20190001 value:小明
key:20190002 value:小红
key:20190003 value:小丁
 */