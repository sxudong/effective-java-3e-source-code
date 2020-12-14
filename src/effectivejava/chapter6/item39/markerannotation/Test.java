package effectivejava.chapter6.item39.markerannotation;
import java.lang.annotation.*;

// Marker annotation type declaration - Page 180
import java.lang.annotation.*;

// Marker annotation type declaration 标记注释类型声明 (Page 180)

/**
 * Indicates that the annotated method is a test method.
 * Use only on parameterless static methods.
 * 指示带注释的方法是测试方法。 仅在无参数静态方法上使用。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
}