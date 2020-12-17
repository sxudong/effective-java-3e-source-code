package effectivejava.chapter11.item80;

/**
 * 第1版本第49条队列代码：避免过度同步
 * 创建一个死锁的工作队列类：
 */
public class DeadLockQueue extends WorkQueue {
//public class DeadLockQueue extends WorkQueueExt { // 将代码放在同步之外，解除死锁

    public void processItem(final Object workItem) throws InterruptedException {
        // Create a new thread that returns workItem to queue
		// 创建一个新线程，将workItem返回到队列
    	Thread child = new Thread() {
    	    @Override
            public void run() {
                enqueue(workItem);
            }
        };
        child.start();
        child.join(); // dead lock 死锁
    }

}