package effectivejava.chapter11.item78.fixedstopthread2;
import java.util.concurrent.*;

/**
 * 第78条：同步访问共享的可变数据
 */
// Cooperative thread termination with a volatile field
// 具有易变字段的协作线程终止
public class StopThread {
    //  volatile 修饰符不执行互斥访问，但它可以保证任何一个线程在读取该字段的时候都将看到最近刚刚被写入的值
    private static volatile boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested)
                i++;
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
} // 正常停止
