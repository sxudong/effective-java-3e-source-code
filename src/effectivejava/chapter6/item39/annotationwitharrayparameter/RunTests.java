package effectivejava.chapter6.item39.annotationwitharrayparameter;
import effectivejava.chapter6.item39.markerannotation.Test;

import java.lang.reflect.*;

/**
 * 第39条： 注解优于命名模式
 */
// Program to process marker annotations and annotations with array parameter (Page 185)
// 程序处理标记注释和带有数组参数的注释
public class RunTests {
    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;
        //Class<?> testClass = Class.forName(args[0]);
        Class<?> testClass = Class.forName("effectivejava.chapter6.item39.annotationwitharrayparameter.Sample3");
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    m.invoke(null);
                    passed++;
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(m + " failed: " + exc);
                } catch (Exception exc) {
                    System.out.println("Invalid @Test: " + m);
                }
            }

            // Code to process annotations with array parameter (Page 185)
            // 用数组参数处理注释的代码
            if (m.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                try {
                    m.invoke(null);
                    System.out.printf("Test %s failed: no exception%n", m);
                } catch (Throwable wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    int oldPassed = passed;
                    Class<? extends Throwable>[] excTypes =
                            m.getAnnotation(ExceptionTest.class).value();
                    for (Class<? extends Throwable> excType : excTypes) {
                        if (excType.isInstance(exc)) {
                            passed++;
                            break;
                        }
                    }
                    if (passed == oldPassed)
                        System.out.printf("Test %s failed: %s %n", m, exc);
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n",
                passed, tests - passed);
    }
}
/* Output:
Test public static void effectivejava.chapter6.item39.annotationwitharrayparameter.Sample3.m2() failed: java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 0
Test public static void effectivejava.chapter6.item39.annotationwitharrayparameter.Sample3.m3() failed: no exception
Passed: 2, Failed: 2
 */