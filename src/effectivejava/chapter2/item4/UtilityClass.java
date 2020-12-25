package effectivejava.chapter2.item4;

// Noninstantiable utility class (Page 19)
// 不可实例化的实用程序类
public class UtilityClass {
    // Suppress default constructor for noninstantiability
    // 禁止默认构造函数实例化
    private UtilityClass() {
        // 抛出异常，避免类的内部调用构造方法
        throw new AssertionError();
    }

    public static void showMyself() {
        UtilityClass utility = new UtilityClass();
    }
}
