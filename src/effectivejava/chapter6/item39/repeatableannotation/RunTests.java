package effectivejava.chapter6.item39.repeatableannotation;

import effectivejava.chapter6.item39.markerannotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 第39条： 注解优于命名模式
 */
// Program to process marker annotations and repeatable annotations (Page 187)
// 程序处理标记注释和可重复注释
public class RunTests {
    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;
        //Class testClass = Class.forName(args[0]);
        Class<?> testClass = Class.forName("effectivejava.chapter6.item39.repeatableannotation.Sample4");
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
                    System.out.println("INVALID @Test: " + m);
                }
            }

            // Processing repeatable annotations 处理可重复的注释 (Page 187)
            if (m.isAnnotationPresent(ExceptionTest.class)
                    || m.isAnnotationPresent(ExceptionTestContainer.class)) {
                tests++;
                try {
                    m.invoke(null);
                    System.out.printf("Test %s failed: no exception%n", m);
                } catch (Throwable wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    int oldPassed = passed;
                    ExceptionTest[] excTests =
                            m.getAnnotationsByType(ExceptionTest.class);
                    for (ExceptionTest excTest : excTests) {
                        if (excTest.value().isInstance(exc)) {
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
Test public static void effectivejava.chapter6.item39.repeatableannotation.Sample4.m2() failed: java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 0
Test public static void effectivejava.chapter6.item39.repeatableannotation.Sample4.m3() failed: no exception
Passed: 2, Failed: 2
 */