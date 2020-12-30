package effectivejava.chapter4.item25;

/**
 * 第25条：限制源文件成为单个顶级类
 */
// Static member classes instead of multiple top-level classes (Page 116)
// 静态成员类，而不是多个顶级类
public class Test {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME); //pancake
    }
    // 将它们做成静态成员类（嵌套类），增强了代码的可读性
    private static class Utensil {
        static final String NAME = "pan";
    }
    private static class Dessert {
        static final String NAME = "cake";
    }
}
