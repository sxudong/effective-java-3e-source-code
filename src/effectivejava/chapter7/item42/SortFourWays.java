package effectivejava.chapter7.item42;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static java.util.Comparator.*;

import static java.util.Comparator.comparingInt;

/**
 * 第42条：Lambda优先于匿名类
 */
// Sorting with function objects (Pages 193-4)
public class SortFourWays {
    public static void main(String[] args) {
//        List<String> words = Arrays.asList(args);
        List<String> words = Arrays.asList("1","2","3");
        // Anonymous class instance as a function object - obsolete! (Page 193)
        Collections.sort(words, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });
        System.out.println(words);
        Collections.shuffle(words);

        // Lambda expression as function object (replaces anonymous class) (Page 152)
        // Lambda表达式作为函数对象(取代匿名)
        Collections.sort(words,
                (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println(words);
        Collections.shuffle(words);

        // Comparator construction method (with method reference) in place of lambda (Page 152)
        // 比较器构造方法（方法引用）代替lambda
        Collections.sort(words, comparingInt(String::length));
        System.out.println(words);
        Collections.shuffle(words);

        // Default method List.sort in conjunction with comparator construction method (Page 152)
        // 默认方法List.sort和比较器构造方法
        words.sort(comparingInt(String::length));
        System.out.println(words);
    }
}
/* Output:
[1, 2, 3]
[2, 1, 3]
[2, 3, 1]
[3, 2, 1]
 */