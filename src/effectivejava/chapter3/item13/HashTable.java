package effectivejava.chapter3.item13;

/**
 * 第13条：谨慎地覆盖clone
 */
// Recursive clone method for class with complex mutable state
// 具有复杂可变状态的类的递归克隆方法
public class HashTable implements Cloneable {
    private Entry[] buckets;

    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        // Recursively copy the linked list headed by this Entry
        // 递归复制此条目标题的链接列表
//    Entry deepCopy() {
//      return new Entry(key, value, next == null ? null : next.deepCopy()); // 重新new了一次
//    }

        // Iteratively copy the linked list headed by this Entry
        // 迭代复制以该条目为标题的链接列表
        Entry deepCopy() {
            Entry result = new Entry(key, value, next);
            for (Entry p = result; p.next != null; p = p.next)
                p.next = new Entry(p.next.key, p.next.value, p.next.next);
            return result;
        }
    }

    @Override
    public HashTable clone() {
        try {
            HashTable result = (HashTable) super.clone();
            // 定义了buckets.length长度的数组
            result.buckets = new Entry[buckets.length];
            // 循环这个数组，每一个赋值，赋值时调用的是自已的deepCopy
            for (int i = 0; i < buckets.length; i++)
                if (buckets[i] != null)
                    result.buckets[i] = buckets[i].deepCopy();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}