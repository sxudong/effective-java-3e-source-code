package effectivejava.chapter4.item18;
import java.util.*;

/**
 * 第18条：组合优先于继承
 * @param <E>
 */
// Broken - Inappropriate use of inheritance! (Page 70)
// 坏了的 - 继承使用不当！
public class InstrumentedHashSet<E> extends HashSet<E> {
    // The number of attempted element insertions 尝试插入元素的次数
    private int addCount = 0;

    public InstrumentedHashSet() {
    }

    public InstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(List.of("Snap", "Crackle", "Pop"));
        System.out.println(s.getAddCount()); // 6
    }
}
