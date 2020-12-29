package effectivejava.chapter4.item21;

import org.apache.commons.collections.collection.SynchronizedCollection;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 第21条：为后代设计接口
 *
 * 大多数情况都能工作得很好，但并不是永远都能写一个在任何情形下都适用的默认方法实现。
 * 比如Collection接口的默认方法removeIf()，来自Apache Commons类库的SynchronizedCollection
 */
public class SynchronizedCollectionDemo {
    public static void main(String[] args) {
        Collection collection = SynchronizedCollection.decorate(IntStream.of(1,2,3,4,5,6,7,8).boxed().
                collect(Collectors.toList()));
        while (true) {
            new Thread(() -> collection.add(10)).start();
            // 调用默认方法removeIf() 抛出异常 ConcurrentModificationException
            new Thread(() -> collection.removeIf(e-> (Integer) e % 2 == 0)).start();
        }
    }
}
/* Output:
Exception in thread "Thread-45" java.util.ConcurrentModificationException
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:909)
	at java.util.ArrayList$Itr.next(ArrayList.java:859)
	at java.util.Collection.removeIf(Collection.java:414)
	at com.xf.driver.modules.SynchronizedCollectionDemo.lambda$main$2(SynchronizedCollectionDemo.java:15)
	at java.lang.Thread.run(Thread.java:748)
 */