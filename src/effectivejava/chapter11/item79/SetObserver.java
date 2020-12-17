package effectivejava.chapter11.item79;

/**
 * 第79条：避免过度同步
 */
// Set obeserver callback interface - Page 266
// 设置观察者回调接口
public interface SetObserver<E> {
    // Invoked when an element is added to the observable set
    // 将元素添加到“可观察集”中时调用
    void added(ObservableSet<E> set, E element);
}
