package effectivejava.chapter4.item21.defaultMothed;

// JDK8默认方法跟正常方法覆盖一样
public class Main {
    public static void main(String[] args) {
        HelloInterface hello = new My();
        hello.hello(); // hello melin
    }
}