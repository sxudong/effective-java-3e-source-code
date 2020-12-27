package effectivejava.chapter3.item13;

/**
 * 第13条：谨慎地覆盖clone
 */
public class MyHashTable {
    private Entry[] buckets; // 篮子

    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        // 递归拷贝
        Entry deepCopy() {
            // 重新new了一次
            return new Entry(key, value, next == null ? null : next.deepCopy());
        }

    }

    // 错误，buckets数组元素是一个内部类对象，它是一个引用的引用
    // Entry内部有自已的值，没有实现clone，所以调它的clone没有用
//    @Override
//    protected MyHashTable clone() {
//        try {
//            MyHashTable result = (MyHashTable) super.clone();
//            result.buckets = buckets.clone();
//            return result;
//        } catch (CloneNotSupportedException e) {
//            throw new AssertionError();
//        }
//    }

    // 第二种正确方法
    @Override
    protected MyHashTable clone() {
        try {
            MyHashTable result = (MyHashTable) super.clone();
            // 定义了buckets.length长度的数组
            result.buckets = new Entry[buckets.length];
            // 循环这个数组，每一个赋值，赋值时调用的是自已的deepCopy
            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != null)
                    result.buckets[i] = buckets[i].deepCopy();
            }
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
