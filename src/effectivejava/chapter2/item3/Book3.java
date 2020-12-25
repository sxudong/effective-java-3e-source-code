package effectivejava.chapter2.item3;

/**
 * 懒汉模式（不推荐）
 */
public class Book3 {
    private static Book3 EFFECITVE_3AVA = null;

    private String name;
    private String author;

    private Book3(String name, String author) {
        this.name = name;
        this.author = author;
    }

    @Override
    public String toString() {
        return "name :" + name + "; author: " + author + ";";
    }

    // 类加载时不加载对象，当获取对象时才实例化对象。
    public static Book3 getEffectlveJavaInstance() {
        if (EFFECITVE_3AVA == null) {
            //线程不安全
            EFFECITVE_3AVA = new Book3("effective java", "joshua bloch");
        }
        return EFFECITVE_3AVA;
    }
}