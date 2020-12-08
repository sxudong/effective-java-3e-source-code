package effectivejava.chapter5.item33;

import java.lang.reflect.*;


/**
 * 第33条：优先考虑类型安全的异构容器
 */
// Use of asSubclass to safely cast to a bounded type token (Page 155)
// 使用 asSubclass 安全地强制转换为有界类型令牌
public class PrintAnnotation {

    static Annotation getAnnotation(AnnotatedElement element,
                                    String annotationTypeName) {
        Class<?> annotationType = null; // Unbounded type token 无限类型令牌
        try {
            annotationType = Class.forName(annotationTypeName);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
        return element.getAnnotation(
                annotationType.asSubclass(Annotation.class));
    }

    // Test program to print named annotation of named class
    // 测试程序以打印命名类的命名注释
    public static void main(String[] args) throws Exception {
//        if (args.length != 2) {
//            System.out.println(
//                "Usage: java PrintAnnotation <class> <annotation>");
//            System.exit(1);
//        }
//        String className = args[0];
//        String annotationTypeName = args[1];
//        Class<?> klass = Class.forName(className);
//        System.out.println(getAnnotation(klass, annotationTypeName));

        // 获取自定义注解信息
        String[] argss =  new String[]{"effectivejava.chapter5.item33.Test", "effectivejava.chapter5.item33.Annotation"};

        if (argss.length != 2) {
            System.out.println(
                    "Usage: java PrintAnnotation <class> <annotation>");
            System.exit(1);
        }
        String className = argss[0];
        String annotationTypeName = argss[1];
        Class<?> klass = Class.forName(className);
        System.out.println(getAnnotation(klass, annotationTypeName));
    }
}
/* Output:
@effectivejava.chapter5.item33.Annotation(key="GFG", value="GeeksForGeeks")
 */