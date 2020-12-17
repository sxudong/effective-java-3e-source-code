package effectivejava.chapter11.item83;

/**
 * 第83条：慎用延迟初始化
 */
// Initialization styles 初始化样式 - Pages 333-
public class Initialization {

    // Normal initialization of an instance field4 - Page 282
    // 实例字段的正常初始化4
    private final FieldType field1 = computeFieldValue();

    // Lazy initialization of instance field4 - synchronized accessor - Page 333
    // 实例字段4的延迟初始化-同步访问器
    private FieldType field2;
    private synchronized FieldType getField2() {
        if (field2 == null)
            field2 = computeFieldValue();
        return field2;
    }

    // Lazy initialization holder class idiom for static fields - Page 334
    // 静态字段的惰性初始化持有人类习惯用法
    private static class FieldHolder {
        static final FieldType field = computeFieldValue();
    }

    private static FieldType getField() { return FieldHolder.field; }


    // Double-check idiom for lazy initialization of instance fields - Page 334
    // 仔细检查习惯用法以实例化实例字段的延迟初始化
    private volatile FieldType field4;

    // NOTE: The code for this method in the first printing had a serious error (see errata for details)!
    // 注意：第一次打印中此方法的代码存在严重错误
    private FieldType getField4() {
        FieldType result = field4;
        if (result != null)    // First check 第一次检查 (no locking 没有锁)
            return result;

        synchronized(this) {
            if (field4 == null) // Second check 第二次检查 (with locking 带锁)
                field4 = computeFieldValue();
            return field4;
        }
    }



    // Single-check idiom - can cause repeated initialization! - Page 334
    // 单项检查成语-可能导致重复初始化！
    private volatile FieldType field5;

    private FieldType getField5() {
        FieldType result = field5;
        if (result == null)
            field5 = result = computeFieldValue();
        return result;
    }

    private static FieldType computeFieldValue() {
        return new FieldType();
    }
}
