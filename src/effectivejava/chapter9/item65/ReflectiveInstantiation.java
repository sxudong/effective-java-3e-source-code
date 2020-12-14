package effectivejava.chapter9.item65;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

/**
 * 第65条：接口优于反射
 */
// Reflective instantiaion demo 反射式实例演示 (Page 283)
public class ReflectiveInstantiation {
    // Reflective instantiation with interface access 具有接口访问的反射式实例化
    public static void main(String[] args) {
        // Translate the class name into a Class object
        // 将类名称转换为Class对象
        Class<? extends Set<String>> cl = null;
        try {
            //cl = (Class<? extends Set<String>>) Class.forName(args[0]); // Unchecked cast! 未经检查的转换
            //cl = (Class<? extends Set<String>>) Class.forName("java.util.HashSet"); // Unchecked cast! 未经检查的转换 (它们显然是随机排列的)
            cl = (Class<? extends Set<String>>) Class.forName("java.util.TreeSet"); // Unchecked cast! 未经检查的转换 (它们是按字母顺序打印的，因为 TreeSet 中的元素是有序的)

        } catch (ClassNotFoundException e) {
            fatalError("Class not found.");
        }

        // Get the constructor 获取构造函数
        Constructor<? extends Set<String>> cons = null;
        try {
            cons = cl.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            fatalError("No parameterless constructor");
        }

        // Instantiate the set 实例化集合
        Set<String> s = null;
        try {
            s = cons.newInstance();
        } catch (IllegalAccessException e) {
            fatalError("Constructor not accessible");
        } catch (InstantiationException e) {
            fatalError("Class not instantiable.");
        } catch (InvocationTargetException e) {
            fatalError("Constructor threw " + e.getCause());
        } catch (ClassCastException e) {
            fatalError("Class doesn't implement Set");
        }

        // Exercise the set 练习集合
        //s.addAll(Arrays.asList(args).subList(1, args.length));
        s.addAll(Arrays.asList("b","c","a","i","h"));
        System.out.println(s);
    }

    private static void fatalError(String msg) {
        System.err.println(msg);
        System.exit(1);
    }
}
/* Output:
[a, b, c, h, i]
 */