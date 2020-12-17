package effectivejava.chapter11.item84;

/**
 * 第84条：不要依赖于线程调度器
 */
// Awful CountDownLatch implementation - busy-waits incessantly!  - Pages 336-7
// 糟糕的CountDownLatch实现-繁忙等待不断！
public class SlowCountDownLatch {
    private int count;

    public SlowCountDownLatch(int count) {
        if (count < 0)
            throw new IllegalArgumentException(count + " < 0");
        this.count = count;
    }

    public void await() {
        while (true) {
            synchronized(this) {
                if (count == 0)
                    return;
            }
        }
    }
    public synchronized void countDown() {
        if (count != 0)
            count--;
    }
}
