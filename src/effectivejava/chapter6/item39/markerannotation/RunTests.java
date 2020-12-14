package effectivejava.chapter6.item39.markerannotation;

// Program to process marker annotations (Page 182)

import java.lang.reflect.*;

/**
 * 第39条： 注解优于命名模式
 */
// 运行测试程序，需要在IDEA 右上角锤子图标边的下拉菜单中找到“Edit Configurations”
// 设置当前工个目录为"F:\code\effective-java-3e-source-code\target\classes"
public class RunTests {
    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;
        //Class<?> testClass = Class.forName(args[0]);
        Class<?> testClass = Class.forName("effectivejava.chapter6.item39.markerannotation.Sample");
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
        }
        System.out.printf("Passed: %d, Failed: %d%n",
                passed, tests - passed);
    }
}
/* Output:
public static void effectivejava.chapter6.item39.markerannotation.Sample.m3() failed: java.lang.RuntimeException: Boom
public static void effectivejava.chapter6.item39.markerannotation.Sample.m7() failed: java.lang.RuntimeException: Crash
Invalid @Test: public void effectivejava.chapter6.item39.markerannotation.Sample.m5()
Passed: 1, Failed: 3
 */