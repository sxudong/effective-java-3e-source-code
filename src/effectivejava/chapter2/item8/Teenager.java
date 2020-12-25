package effectivejava.chapter2.item8;

import java.util.concurrent.TimeUnit;

// 行为不端的客户，清理安全网 (Page 33)
public class Teenager {
    public static void main(String[] args) {
        new Room(99);
        System.out.println("Peace out");

        // 取消注释下一行并重新测试行为，但是请注意，您一定不要依赖此行为！
        System.gc();  // 清洁room
    }
}
/* Output:
Peace out
Cleaning room
 */