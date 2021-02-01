package effectivejava.chapter7.item46;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingByTest {
    public static void main(String[] args) {
        /**
         * 1、Collectors.groupingBy()和Collectors.groupingByConcurrent()，
         * 这两者区别也仅是单线程和多线程的使用场景。为什么要groupingBy归类
         * 为前后处理呢？groupingBy 是在数据收集前分组的，再将分好组的数据
         * 传递给下游的收集器。
         *
         * classifier 是分类器
         * mapFactory map的工厂
         * downstream下游的收集器，正是downstream 的存在，可以在数据传递个下游之前做很多的骚操作。
         */
        //groupingBy(classifier)方法用来返回按照类别分组的元素, 分类器(classifier function)返回元素应属于的类别(category), 作为map的key.
        //Map<String,List<Integer>>
        Stream.of(-6, -7, -8, -9, 1, 2, 3, 4, 5, 6)
                .collect(Collectors.groupingBy(integer -> {
                    if (integer < 0) {
                        return "小于";
                    } else if (integer == 0) {
                        return "等于";
                    } else {
                        return "大于";
                    }
                }));

        //groupingBy(classifier, downstream): downstream将对分类中的所有元素进一步处理, 可以toSet(), toCollection()或者counting().
        //Map<String,Set<Integer>>
        //自定义下游收集器
        Stream.of(-6, -7, -8, -9, 1, 2, 3, 4, 5, 6)
                .collect(Collectors.groupingBy(integer -> {
                    if (integer < 0) {
                        return "小于";
                    } else if (integer == 0) {
                        return "等于";
                    } else {
                        return "大于";
                    }
                }, Collectors.toSet()));

        //groupingBy(classifier, mapFactory, downstream): 可以同时控制map和集合的实现类型, 比如一个TreeMap, 包含TreeSet.
        //Map<String,Set<Integer>>
        //自定义map容器 和 下游收集器
        Stream.of(-6, -7, -8, -9, 1, 2, 3, 4, 5, 6)
                .collect(Collectors.groupingBy(integer -> {
                    if (integer < 0) {
                        return "小于";
                    } else if (integer == 0) {
                        return "等于";
                    } else {
                        return "大于";
                    }
                }, LinkedHashMap::new, Collectors.toSet()));

        /**
         * 2、Collectors.partitioningBy() 字面意思话就叫分区好了，
         * 但是partitioningBy最多只能将数据分为两部分，因为partitioningBy分区的依据Predicate，
         * 而Predicate只会有true 和false 两种结果，所有partitioningBy最多只能将数据分为两组。
         * partitioningBy除了分类器与groupingBy 不一样外，其他的参数都相同。
         */
        //Map<Boolean,List<Integer>>
        Stream.of(0,1,0,1)
                .collect(Collectors.partitioningBy(integer -> integer==0));

        //Map<Boolean,Set<Integer>>
        //自定义下游收集器
        Stream.of(0,1,0,1)
                .collect(Collectors.partitioningBy(integer -> integer==0,Collectors.toSet()));

        /**
         * 3、Collectors.mapping() 可以自定义要收集的字段。
         */
        Student studentA = new Student("20190001", "小明");
        Student studentB = new Student("20190002", "小红");
        Student studentC = new Student("20190003", "小丁");
        //List<String>
        Stream.of(studentA, studentB, studentC)
                .collect(Collectors.mapping(Student::getName,Collectors.toList()));

        /**
         * 4、Collectors.collectingAndThen()收集后操作，如果你要在收集数据后再做一些操作，那么这个就非常有用了。
         */
        //示例：这里在收集后转成了listIterator，只是个简单的示例，具体的实现逻辑非常有待想象。
        //listIterator
        Stream.of(studentA,studentB,studentC)
                .collect(Collectors.collectingAndThen(Collectors.toList(), List::listIterator));
    }
}
