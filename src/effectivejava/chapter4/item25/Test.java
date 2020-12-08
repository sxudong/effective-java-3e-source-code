package effectivejava.chapter4.item25;

/**
 * 第25条：限制源文件成为单个顶级类
 */
// Static member classes instead of multiple top-level classes (Page 116)
public class Test {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME); //pancake
    }

    private static class Utensil {
        static final String NAME = "pan";
    }

    private static class Dessert {
        static final String NAME = "cake";
    }
}
