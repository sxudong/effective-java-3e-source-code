package effectivejava.chapter6.item39.repeatableannotation;

import java.lang.annotation.*;

// Repeatable annotation type 可重复注释类型 (Page 186)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTestContainer.class)
public @interface ExceptionTest {
    Class<? extends Throwable> value();
}