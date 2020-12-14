package effectivejava.chapter9.item62;

/**
 * 第62条：如果其他类型更合适，则尽量避免使用字符串
 */
//public class ThreadLocal {
//    private ThreadLocal() {
//    } // Noninstantiable 不可实例化
//
//    public static class Key { // (Capability)
//        Key() {
//        }
//    }
//
//    // Generates a unique, unforgeable key 生成唯一的，不可伪造的密钥
//    public static Key getKey() {
//        return new Key();
//    }
//
//    public static void set(Key key, Object value);
//
//    public static Object get(Key key);
//}

/**
 * 你不再真正需要静态方法。它们可以变成键上的实例方法，此时键不再是线程局部变量：
 * 而是线程局部变量。此时，顶层类不再为你做任何事情，所以你可以删除它.
 * @param <T>
 */
public final class ThreadLocal<T> {
    public ThreadLocal() {
    }

    public void set(T value) {
    }

    public T get() {
        return null;
    }

}