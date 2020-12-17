package effectivejava.chapter11.item79;
import java.util.*;

/**
 * 第79条：避免过度同步
 */
// More complex test of ObservableSet - Page 318-9
// 更复杂的ObservableSet测试
public class Test2 {
    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        set.addObserver(new SetObserver<>() {
            @Override
            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println(e);
                if (e == 23)
                    s.removeObserver(this); //  for (SetObserver<E> observer : observers)  observer.added(this, element); 在遍历列表的过程中，将一个元素从列表中删除是非法的
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
Exception in thread "main" java.util.ConcurrentModificationException
	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1042)
	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:996)
	at effectivejava.chapter11.item79.ObservableSet.notifyElementAdded(ObservableSet.java:30)
	at effectivejava.chapter11.item79.ObservableSet.add(ObservableSet.java:71)
	at effectivejava.chapter11.item79.Test2.main(Test2.java:22)
 */