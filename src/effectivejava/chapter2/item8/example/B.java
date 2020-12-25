package effectivejava.chapter2.item8.example;

public class B extends A {

    // 重载finalize()方法
    @Override
    public void finalize() {
        System.out.println("B finalize by the finalize method");
    }
}
