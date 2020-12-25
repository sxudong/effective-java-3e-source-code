package effectivejava.chapter2.item8.example;

/**
 * 第8条：避免使用终结方法和清除方法
 */
public class Client {
    public static void main(String[] args) {
        try {
            B b = new B();
            b = null;
            System.gc();
            Thread.sleep(500) ;
        } catch ( InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/* 没有调用父类的finalize()方法，继承时一定要调用super.finalize():
B finalize by the finalize method
A finalize by the finalizerGuardian
 */