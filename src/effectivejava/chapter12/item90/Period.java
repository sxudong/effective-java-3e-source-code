package effectivejava.chapter12.item90;

// Period class with serialization proxy 具有序列化代理的期间类 - Pages 363-364

import java.util.*;
import java.io.*;

/**
 * 第90条：考虑用序列化代理代替序列化实例
 */
// 外围类不需要serialVersionUID
// Immutable class that uses defensive copying 使用防御性复制的不可变类
public final class Period implements Serializable {
    private final Date start;
    private final Date end;

    /**
     * @param  start the beginning of the period
     * @param  end the end of the period; must not precede start
     * @throws IllegalArgumentException if start is after end
     * @throws NullPointerException if start or end is null
     */
    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end   = new Date(end.getTime());
        if (this.start.compareTo(this.end) > 0)
            throw new IllegalArgumentException(start + " after " + end);
    }

    public Date start () { return new Date(start.getTime()); }

    public Date end () { return new Date(end.getTime()); }

    public String toString() { return start + " - " + end; }


    // Serialization proxy for Period class
    // Period类的序列化代理
    private static class SerializationProxy implements Serializable { // 私有静态嵌套类
        private final Date start;
        private final Date end;

        SerializationProxy(Period p) { // 参数是外围类
            this.start = p.start;      // 复制外围类的数据
            this.end = p.end;
        }

        // Any number will do 任何数字都可以 (Item 87)
        private static final long serialVersionUID = 234098243823485285L;
    }

    // 在序列化之前，将外围类的实例转变成它的序列化代理
    // writeReplace method for the serialization proxy pattern
    // 序列化代理模式的writeReplace方法
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    // 防止被攻击者使用
    // readObject method for the serialization proxy pattern
    // 序列化代理模式的readObject方法
    private void readObject(ObjectInputStream stream)
            throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }
}