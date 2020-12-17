package effectivejava.chapter11.item79;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 第79条：避免过度同步
 */
// Simple test of ObservableSet - Page 319
// 简单测试ObservableSet
public class Test3 {
    /**
     * 运行这个程序时，没有遇到异常，而是遭遇了死锁。后台线程调用 s .removeObserver，它企图锁定 observers，
     * 但它无法获得该锁，因为主线程调用addObserver()已经有锁了。在这期间，主线程一直在等待后台线程来完成对
     * 观察者的删除，这正是造成死锁的原因。
     * 从同步区域中调用外来方法，造成了死锁，
     */
    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        // Observer that uses a background thread needlessly
        // 不需要使用后台线程的观察者，用另一个线程来完成
        set.addObserver(new SetObserver<>() {
            @Override
            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println(e); // 打印
                if (e == 23) {
                    ExecutorService exec = Executors.newSingleThreadExecutor();
                    try {
                        // s.removeObserver企图锁定observers,但是它无法获得锁。
                        // 解决：修改ObservableSet.java使用并发集合CopyOnWriteArrayList进行线程安全的可观察集，并去除同步
                        exec.submit(() -> s.removeObserver(this)).get();
                    } catch (ExecutionException | InterruptedException ex) {
                        throw new AssertionError(ex);
                    } finally {
                        exec.shutdown();
                    }
                }
            }
        });

        for (int i = 0; i < 100; i++)
            set.add(i);
    }
}
/* Output:
0
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
 */