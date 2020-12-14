package effectivejava.chapter6.item39.annotationwithparameter;

// Annotation type with a parameter 带参数的注释类型 (Page 183)
import java.lang.annotation.*;
/**
 * Indicates that the annotated method is a test method that
 * must throw the designated exception to succeed.
 * 指示带注释的方法是一种测试方法，必须抛出指定的异常才能成功。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
    Class<? extends Throwable> value();
}
