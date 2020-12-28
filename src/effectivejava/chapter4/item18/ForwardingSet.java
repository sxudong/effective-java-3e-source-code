package effectivejava.chapter4.item18;

import java.util.*;

/**
 * 第18条：组合优先于继承
 *
 * @param <E>
 */
// Reusable forwarding class 可重复使用的“转发类” (Page 90)
public class ForwardingSet<E> implements Set<E> {
    // 私有域引用现有类的一个实例
    private final Set<E> s;

    public ForwardingSet(Set<E> s) { this.s = s; }

    public void clear() { s.clear(); }

    // 转发法
    public boolean contains(Object o) {
        // 调用被包含的现在类实例中对应的方法，并返回它的结果
        return s.contains(o);
    }
    public boolean isEmpty() {return s.isEmpty(); }
    public int size() {return s.size();}
    public Iterator<E> iterator() {return s.iterator(); }
    public boolean add(E e) {return s.add(e);}
    public boolean remove(Object o) {return s.remove(o); }
    public boolean containsAll(Collection<?> c) {return s.containsAll(c);}
    public boolean addAll(Collection<? extends E> c) {return s.addAll(c);}
    public boolean removeAll(Collection<?> c) {return s.removeAll(c);}
    public boolean retainAll(Collection<?> c) {return s.retainAll(c);}
    public Object[] toArray() {return s.toArray();}
    public <T> T[] toArray(T[] a) {return s.toArray(a);}
    @Override
    public boolean equals(Object o) {return s.equals(o);}
    @Override
    public int hashCode() {return s.hashCode();}
    @Override
    public String toString() {return s.toString();}
}
