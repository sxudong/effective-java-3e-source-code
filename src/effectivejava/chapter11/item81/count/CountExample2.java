package effectivejava.chapter11.item81.count;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 4-1 原子性 线程安全
 */

// 线程安全
public class CountExample2 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    /** 使用原子性类，线程安全类 */
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        // 定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量，允许并发的数
        final Semaphore semaphore = new Semaphore(threadTotal);
        // CountDownLatch(int clientTotal) 实例化一个倒计数器，clientTotal指定计数个数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal ; i++) {
            // 将请求放入到线程池里面
            executorService.execute(() -> { // 使用lambda表达式
                try {
                    semaphore.acquire(); // add()方法之前引入“信号量”判断当前进程是否被允许执行？如果达到一定并发数后 add()会被临时阻塞掉，当acquire()返回值之后add()才可以执行
                    add();
                    semaphore.release(); // 执行完之后释放当前进程
                } catch (Exception e) {
                    System.out.println("exception:" + count);
                }
                countDownLatch.countDown(); // 引入闭锁,每执行一个，它里面对应的数据就会减1
            });
        }
        countDownLatch.await(); // CountDownLatch.await() 方法在倒计数为0之前会阻塞当前线程.
        executorService.shutdown();
        System.out.println("count: " + count);
    }

    private static void add() {
        count.incrementAndGet(); // 先做添加操作，再获取值
        // count.getAndIncrement(); // 先获取当前值，再做添加操作
    }
}
