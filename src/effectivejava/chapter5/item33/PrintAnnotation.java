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
        // 直接就可以返回，用annotationType.asSubclass()多此一举
        // return element.getAnnotation(Annotation.class);
        return element.getAnnotation(
                // asSubclass方法读取未知的注解。
                // 它将调用它的Class对象转换成用其参数表示的一个子类。
                // 如果转换成功，该方法返回它的参数；
                // 如果失败，则抛出ClassCastException异常。
                annotationType.asSubclass(Annotation.class));
    }

    // Test program to print named annotation of named class
    // 测试程序以打印命名类的命名注释
    public static void main(String[] args) throws Exception {
        // 获取自定义注解信息
        String[] argss =  new String[]{"effectivejava.chapter5.item33.Test",
                "effectivejava.chapter5.item33.Annotation"};

        if (argss.length != 2) {
            System.out.println(
                    "Usage: java PrintAnnotation <class> <annotation>");
            System.exit(1);
        }
        String className = argss[0];
        String annotationTypeName = argss[1];
        Class<?> klass = Class.forName(className);
        // 获取Test类的Annotation注解信息
        System.out.println(getAnnotation(klass, annotationTypeName));
    }

      // java.lang.Class#asSubclass()
//    @SuppressWarnings("unchecked")
//    public <U> Class<? extends U> asSubclass(Class<U> clazz) {
//        if (clazz.isAssignableFrom(this))
//            // Class<?> 转换为 Class<? extends U>
//            return (Class<? extends U>) this; // 转换成其参数表示的一个子类
//        else
//            throw new ClassCastException(this.toString());
//    }
}
/* Output:
@effectivejava.chapter5.item33.Annotation(key="GFG", value="GeeksForGeeks")
 */