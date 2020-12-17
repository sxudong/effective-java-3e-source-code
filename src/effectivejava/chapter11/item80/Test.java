package effectivejava.chapter11.item80;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 第80条：executor 、task 和 stream 优先于线程
 */
public class Test {
    public static void main(String[] args) {
        // 列锁
        DeadLockQueue deadLockQueue = new DeadLockQueue();
        Object object = new Object();
        try {
            deadLockQueue.processItem(object);
        } catch (InterruptedException e) {
            deadLockQueue.stop();
        }
        deadLockQueue.stop();

//        DisplayWorkQueue displayWorkQueue = new DisplayWorkQueue();
//        Object object = new Object();
//        try {
//            displayWorkQueue.processItem(object);
//        } catch (InterruptedException e) {
//            displayWorkQueue.stop();
//        }
//        displayWorkQueue.stop();

        // Java 平台中已经增加了 java.util.concurrent 。这个字包中包含了一个 Executor
        // Framework 它是一个很灵活的基于接口的任务执行工具。它创建字了一个在各方面都比本书第一版更好的工作队列，
        // 却只需要这一行代码：
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(() -> System.out.println("ddddddddddddd")); // exec.execute(runnable);
        exec.shutdown(); // 优雅地终止
    }
}
