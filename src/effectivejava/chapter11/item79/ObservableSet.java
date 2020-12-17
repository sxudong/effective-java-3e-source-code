package effectivejava.chapter11.item79;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 第79条：避免过度同步
 * @param <E>
 */
// Broken - invokes alien method from synchronized block!
// 损坏-从同步块调用外来方法！
public class ObservableSet<E> extends ForwardingSet<E> {
    public ObservableSet(Set<E> set) { super(set); }

    // Test2中测试会抛出java.util.ConcurrentModificationException
//    private final List<SetObserver<E>> observers  = new ArrayList<>();
//
//    public void addObserver(SetObserver<E> observer) {
//        synchronized(observers) {
//            observers.add(observer);            // 从同步区域中调用外来方法
//        }
//    }
//
//    public boolean removeObserver(SetObserver<E> observer) {
//        synchronized(observers) {
//            return observers.remove(observer); // 从同步区域中调用外来方法
//        }
//    }
//
//    private void notifyElementAdded(E element) {
//        synchronized(observers) {
//            for (SetObserver<E> observer : observers)
//                observer.added(this, element); // 从同步区域中调用外来方法
//        }
//    }

    // Alien method moved outside of synchronized block - open calls
    // 外来方法移出同步块-打开调用 （方法改进，不会再抛出java.util.ConcurrentModificationException）
//    private void notifyElementAdded(E element) {
//        List<SetObserver<E>> snapshot = null;
//        synchronized(observers) {
//            snapshot = new ArrayList<>(observers);
//        }
//        for (SetObserver<E> observer : snapshot)
//            observer.added(this, element);
//    }



    // Thread-safe observable set with CopyOnWriteArrayList
    // 使用CopyOnWriteArrayList进行线程安全的可观察集
    private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<>(); // 采用并发集合

    public void addObserver(SetObserver<E> observer) {
        observers.add(observer);
    }

    public boolean removeObserver(SetObserver<E> observer) {
        return observers.remove(observer);
    }

    private void notifyElementAdded(E element) {
        for (SetObserver<E> observer : observers)
            observer.added(this, element);
    }


    // ================================================================================

    @Override public boolean add(E element) {
        boolean added = super.add(element);
        if (added)
            notifyElementAdded(element);
        return added;
    }

    @Override public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E element : c)
            result |= add(element);  // Calls notifyElementAdded 调用notifyElementAdded
        return result;
    }
}
