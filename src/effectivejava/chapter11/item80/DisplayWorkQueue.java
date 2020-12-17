package effectivejava.chapter11.item80;

/**
 * 第1版本第49条队列代码：避免过度同步
 */
// 创建一个显示工作队列DisplayWorkQueue
public class DisplayWorkQueue extends WorkQueue {

    public void processItem(Object workItem) throws InterruptedException {
        System.out.println(workItem);
        Thread.sleep(1000);
    }

}