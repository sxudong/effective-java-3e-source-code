package effectivejava.chapter2.item8;

import java.io.IOException;

// 行为端正的客户，清理安全网
public class Adult {
    public static void main(String[] args) {
        // Room实现了AutoCloseable接口
        try (Room myRoom = new Room(7)) {
            System.out.println("Goodbye");
        }
    }
}
/* Output:
Goodbye
调用close方法
Cleaning room
 */