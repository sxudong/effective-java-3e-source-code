package effectivejava.chapter11.item78.fixedstopthread1;
import java.util.concurrent.*;

/**
 * 第78条：同步访问共享的可变数据
 */
// Properly synchronized cooperative thread termination 正确同步协作线程终止
public class StopThread {
    private static boolean stopRequested;

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static synchronized boolean stopRequested() {
        return stopRequested;
    }

    // 读和写操作都被同步，否则无法保证同步能起作用。
    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested())
                i++;
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }
} // 正常停止