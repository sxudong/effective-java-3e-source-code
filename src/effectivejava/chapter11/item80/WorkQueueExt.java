package effectivejava.chapter11.item80;

import java.util.LinkedList;
import java.util.List;

/**
 * 第1版本第49条队列代码：避免过度同步
 * 两个线程均需要获取对象锁，造成死锁的解决方法是修改workqueue中的同步方法：
 *
 * 修改之后如下：
 */
abstract class WorkQueueExt {

    private final List queue = new LinkedList();
    private boolean stopped = false;

    protected WorkQueueExt() {
        new WorkerThread().start();
    }


    public final void enqueue(Object workItem) {
        synchronized (queue) {
            queue.add(workItem);
            queue.notify();
        }
    }

    public final void stop() {
        synchronized (queue) {
            stopped = true;
            queue.notify();
        }
    }

    public abstract void processItem(Object workItem) throws InterruptedException;

    // Alien method outside synchronized block - "Open call"
	// 同步块外的外来方法-“打开呼叫”
    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (true) { // Main loop 主循环
                Object workItem = null;
                synchronized (queue) {
                    while (queue.isEmpty() && !stopped) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    if (stopped) {
                        return;
                    }
                    workItem = queue.remove(0);
                }
                //将代码放在同步之外，解除死锁
                try {
                    processItem(workItem); // No lock held! 没有锁！
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

}