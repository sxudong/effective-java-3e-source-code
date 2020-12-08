package effectivejava.chapter4.item25;

/**
 * 第25条：限制源文件成为单个顶级类
 */
//  (Page 91)
public class Main {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME); // pancake
    }
}