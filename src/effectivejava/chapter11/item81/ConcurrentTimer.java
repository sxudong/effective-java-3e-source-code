package effectivejava.chapter11.item81;
import java.util.concurrent.*;

/**
 * 第81条：相比 wait 和 notify 优先使用并发工具
 */
// Simple framework for timing concurrent execution 327
// 定时并发执行的简单框架
public class ConcurrentTimer {
    private ConcurrentTimer() { } // Noninstantiable 不可实例化

    public static long time(Executor executor, int concurrency,
                            Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done  = new CountDownLatch(concurrency);

        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                ready.countDown(); // Tell timer we're ready 告诉计时器我们已经准备好
                try {
                    start.await(); // Wait till peers are ready 等到同伴准备好
                    action.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();  // Tell timer we're done 告诉计时器我们已经完成
                }
            });
        }

        ready.await();     // Wait for all workers to be ready 等待所有工人准备好
        long startNanos = System.nanoTime();
        start.countDown(); // And they're off! 他们离开了！
        done.await();      // Wait for all workers to finish 等待所有工人完成
        return System.nanoTime() - startNanos;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            ConcurrentTimer.time(executorService, 5, () -> System.out.println("1"));
        }catch (InterruptedException e) {
        }
        executorService.shutdownNow();
    }
}
