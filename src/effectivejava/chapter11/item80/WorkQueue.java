package effectivejava.chapter11.item80;

import java.util.LinkedList;
import java.util.List;

/**
 * 第1版本第49条队列代码：避免过度同步
 */
public abstract class WorkQueue {

    private final List queue = new LinkedList();
    private boolean stopped = false;

    protected WorkQueue() {
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

    // Broken - invokes alien method from synchronized block!
	// 损坏-从同步块调用外来方法！
    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (true) {
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
                    try {
                        processItem(workItem); // Lock held! 锁住了！
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }
    }
}
