package effectivejava.chapter4.item20;
import java.util.*;

/**
 * 第20条：接口优于抽象类
 *
 * 普通类 实现接口，必须将接口所有抽象方法重写
 * 抽象类 实现接口，则不必重写接口的方法。可以全部不重写或只重写一部分方法。
 */
// Skeletal implementation class 骨架实现类 (Pages 102-3)
public abstract class AbstractMapEntry<K,V> implements Map.Entry<K,V> { // 抽象类实现接口，可以只重写一部分方法。

    // Entries in a modifiable map must override this method
    // 可修改映射中的条目必须覆盖此方法
    @Override public V setValue(V value) {
        throw new UnsupportedOperationException();
    }
    
    // Implements the general contract of Map.Entry.equals
    // 实现Map.Entry.equals
    @Override public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Map.Entry))
            return false;
        Map.Entry<?,?> e = (Map.Entry) o;
        return Objects.equals(e.getKey(),   getKey())
                && Objects.equals(e.getValue(), getValue());
    }

    // Implements the general contract of Map.Entry.hashCode
    // 实现Map.Entry.hashCode
    @Override public int hashCode() {
        return Objects.hashCode(getKey())
                ^ Objects.hashCode(getValue());
    }

    @Override public String toString() {
        return getKey() + "=" + getValue();
    }
}
