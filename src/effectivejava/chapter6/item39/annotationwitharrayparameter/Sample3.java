package effectivejava.chapter6.item39.annotationwitharrayparameter;

import java.util.*;

// Program containing an annotation with an array parameter (Page 185)
// 程序包含带有数组参数的注释
public class Sample3 {
    // This variant can process annotations whose parameter is a single element (identical to those on page 183)
    // 此变体可以处理参数为单个元素的注释（与第183页的注释相同）
    @ExceptionTest(ArithmeticException.class)
    public static void m1() {  // Test should pass
        int i = 0;
        i = i / i;
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m2() {  // Should fail (wrong exception)
        int[] a = new int[0];
        int i = a[1];
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m3() { }  // Should fail (no exception)

    // Code containing an annotation with an array parameter (Page 185)
    // 包含带有数组参数的注释的代码
    @ExceptionTest({ IndexOutOfBoundsException.class,
                     NullPointerException.class })
    public static void doublyBad() {   // Should pass
        List<String> list = new ArrayList<>();

        // The spec permits this method to throw either
        // IndexOutOfBoundsException or NullPointerException
        // 规范允许此方法抛出IndexOutOfBoundsException或NullPointerException
        list.addAll(5, null);
    }
}
