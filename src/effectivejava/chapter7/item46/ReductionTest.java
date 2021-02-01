package effectivejava.chapter7.item46;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 聚合归约
 *
 * https://zhuanlan.zhihu.com/p/144228930?from_voters_page=true
 */
public class ReductionTest {
    public static void main(String[] args) {
        Student studentA = new Student("20190001", "小明");
        Student studentB = new Student("20190002", "小红");
        Student studentC = new Student("20190003", "小丁");

        /**
         * 1、Collectors.joining()，拼接，有三个重载方法，底层实现是StringBuilder，
         * 通过append方法拼接到一起，并且可以自定义分隔符（这个感觉还是很有用的，
         * 很多时候需要把一个list转成一个String，指定分隔符就可以实现了，非常方便）、
         * 前缀、后缀。
         */
        //使用分隔符：201900012019000220190003
        String joining1 = Stream.of(studentA, studentB, studentC)
                .map(Student::getId)
                .collect(Collectors.joining());
        System.out.println(joining1);

        //使用^_^ 作为分隔符
        //20190001^_^20190002^_^20190003
        String joining2 = Stream.of(studentA, studentB, studentC)
                .map(Student::getId)
                .collect(Collectors.joining("^_^"));
        System.out.println(joining2);

        //使用^_^ 作为分隔符
        //[]作为前后缀
        //[20190001^_^20190002^_^20190003]
        String joining3 = Stream.of(studentA, studentB, studentC)
                .map(Student::getId)
                .collect(Collectors.joining("^_^", "[", "]"));
        System.out.println(joining3);


        /**
         * 2、Collectors.counting() 统计元素个数，这个和Stream.count() 作用都是一样的，
         * 返回的类型一个是包装Long，另一个是基本long，但是他们的使用场景还是有区别的。
         */
        // Long 8
        Long collect = Stream.of(1, 0, -10, 9, 8, 100, 200, -80)
                .collect(Collectors.counting());
        System.out.println(collect); // 8

        //如果仅仅只是为了统计，那就没必要使用Collectors了，那样更消耗资源
        // long 8
        long count = Stream.of(1, 0, -10, 9, 8, 100, 200, -80)
                .count();
        System.out.println(count); // 8

        /**
         * 3、Collectors.minBy()、Collectors.maxBy() 和Stream.min()、Stream.max()
         * 作用也是一样的，只不过Collectors.minBy()、Collectors.maxBy()适用于高级场景。
         */
        // maxBy 200
        Stream.of(1, 0, -10, 9, 8, 100, 200, -80)
                .collect(Collectors.maxBy(Integer::compareTo)).ifPresent(System.out::println);

        // max 200
        Stream.of(1, 0, -10, 9, 8, 100, 200, -80)
                .max(Integer::compareTo).ifPresent(System.out::println);

        // minBy -80
        Stream.of(1, 0, -10, 9, 8, 100, 200, -80)
                .collect(Collectors.minBy(Integer::compareTo)).ifPresent(System.out::println);

        // min -80
        Stream.of(1, 0, -10, 9, 8, 100, 200, -80)
                .min(Integer::compareTo).ifPresent(System.out::println);

        /**
         * 4、Collectors.summingInt()、Collectors.summarizingLong()、Collectors.summarizingDouble()
         * 这三个分别用于int、long、double类型数据一个求总操作，返回的是一个SummaryStatistics(求总)，
         * 包含了数量统计count、求和sum、最小值min、平均值average、最大值max。
         */
        //IntSummaryStatistics{count=10, sum=55, min=1, average=5.500000, max=10}
        IntSummaryStatistics collect1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .collect(Collectors.summarizingInt(Integer::valueOf));
        System.out.println(collect1.toString());

        //DoubleSummaryStatistics{count=10, sum=55.000000, min=1.000000, average=5.500000, max=10.000000}
        DoubleSummaryStatistics collect2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .collect(Collectors.summarizingDouble(Double::valueOf));
        System.out.println(collect2.toString());

        //LongSummaryStatistics{count=10, sum=55, min=1, average=5.500000, max=10}
        LongSummaryStatistics collect3 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .collect(Collectors.summarizingLong(Long::valueOf));
        System.out.println(collect3.toString());

        /**
         * 5、Collectors.averagingInt()、Collectors.averagingDouble()、Collectors.averagingLong() 求平均值
         */
        // 55
        int sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).mapToInt(Integer::valueOf)
                .sum();
        System.out.println(sum);

        // 55.0
        double sum1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).mapToDouble(Double::valueOf)
                .sum();
        System.out.println(sum1);

        // 55
        long sum2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).mapToLong(Long::valueOf)
                .sum();
        System.out.println(sum2);


        /**
         * 6、Collectors.reducing() 好像也和Stream.reduce()差不多，也都是规约操作。
         * 其实Collectors.counting() 就是用reducing()实现的。
         */
        //Optional[6]
        Optional<Integer> collect4 = Stream.of(studentA, studentB, studentC)
                .map(student -> student.getName().length())
                .collect(Collectors.reducing(Integer::sum));
        System.out.println(collect4);

        //6
        //或者这样，指定初始值，这样可以防止没有元素的情况下正常执行
        Integer collect5 = Stream.of(studentA, studentB, studentC)
                .map(student -> student.getName().length())
                .collect(Collectors.reducing(0, (i1, i2) -> i1 + i2));
        System.out.println(collect5);

        //6
        //更或者先不转换，规约的时候再转换
        Integer collect6 = Stream.of(studentA, studentB, studentC)
                .collect(Collectors.reducing(0, s -> ((Student) s).getName().length(), Integer::sum));
        System.out.println(collect6);
    }
}
/* Output:
201900012019000220190003
20190001^_^20190002^_^20190003
[20190001^_^20190002^_^20190003]
8
8
200
200
-80
-80
IntSummaryStatistics{count=10, sum=55, min=1, average=5.500000, max=10}
DoubleSummaryStatistics{count=10, sum=55.000000, min=1.000000, average=5.500000, max=10.000000}
LongSummaryStatistics{count=10, sum=55, min=1, average=5.500000, max=10}
55
55.0
55
Optional[6]
6
6
 */