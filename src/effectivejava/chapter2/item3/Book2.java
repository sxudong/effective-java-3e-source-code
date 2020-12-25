package effectivejava.chapter2.item3;

public enum Book2 {
    //枚举
    INSTANCE("effective java", "joshua bloch");
    private String name;
    private String author;

    //枚举构造器
    private Book2(String name, String author) {
        this.name = name;
        this.author = author;
    }

    // 局限性：不能重载toString()方法
    public String tostringX() {
        return "name : " + name + "; author : " + author + "";
    }
}
