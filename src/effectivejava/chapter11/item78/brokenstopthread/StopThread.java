package effectivejava.chapter11.item78.brokenstopthread;
import java.util.concurrent.*;

/**
 * 第78条：同步访问共享的可变数据
 */
// Broken! - How long would you expect this program to run?  (Page 312)
// 损坏！ - 您希望该程序运行多长时间？
public class StopThread {
    private static boolean stopRequested; // 初始默认为false

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
} // 程序运行不停此
/*
问题在于，由于没有同步，就不能保证后台线程何时‘看到’主线程对 stopRequested 的值所做的改变。
没有同步，虚拟机将以下代码：
    while (!stopRequested)
       i++;

转变成这样：
    if (!stopRequested)
      while (true)
        i++;
 */