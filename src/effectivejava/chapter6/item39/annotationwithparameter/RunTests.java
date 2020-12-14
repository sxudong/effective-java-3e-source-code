package effectivejava.chapter6.item39.annotationwithparameter;

import effectivejava.chapter6.item39.markerannotation.Test;
import java.lang.reflect.*;

/**
 * 第39条： 注解优于命名模式
 */
// Program to process marker annotations and annotations with a parameter (Page 184)
// 程序处理标记注释和带参数的注释
public class RunTests {
    // 运行测试程序，需要在IDEA 右上角锤子图标边的下拉菜单中找到“Edit Configurations”
    // 设置当前工个目录为"F:\code\effective-java-3e-source-code\target\classes"
    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;
        //Class<?> testClass = Class.forName(args[0]);
        Class<?> testClass = Class.forName("effectivejava.chapter6.item39.annotationwithparameter.Sample2");
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

            if (m.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                try {
                    m.invoke(null);
                    System.out.printf("Test %s failed: no exception%n", m);
                } catch (InvocationTargetException wrappedEx) {
                    Throwable exc = wrappedEx.getCause();
                    Class<? extends Throwable> excType =
                            m.getAnnotation(ExceptionTest.class).value();
                    if (excType.isInstance(exc)) {
                        passed++;
                    } else {
                        System.out.printf(
                                "Test %s failed: expected %s, got %s%n",
                                m, excType.getName(), exc);
                    }
                } catch (Exception exc) {
                    System.out.println("Invalid @ExceptionTest: " + m);
                }
            }
        }

        System.out.printf("Passed: %d, Failed: %d%n",
                passed, tests - passed);
    }
}
/* Output:
Test public static void effectivejava.chapter6.item39.annotationwithparameter.Sample2.m2() failed: expected java.lang.ArithmeticException, got java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 0
Test public static void effectivejava.chapter6.item39.annotationwithparameter.Sample2.m3() failed: no exception
Passed: 1, Failed: 2
 */