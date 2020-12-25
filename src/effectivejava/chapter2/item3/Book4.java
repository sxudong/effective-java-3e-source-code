package effectivejava.chapter2.item3;

/**
 * 静态内部类模式
 */
public class Book4 {
    private String name;
    private String author;

    private Book4(String name, String author) {
        this.name = name;
        this.author = author;
    }

    @Override
    public String toString() {
        return "name :" + name + "; author: " + author + ";";
    }

    public static Book4 getEffectiveJavaInstance() {
        return Book4Holder.EFFECITVE_JAVA;
    }

    private static class Book4Holder {
        public static final Book4 EFFECITVE_JAVA = new Book4("effective java", "joshua bloch");
    }
}