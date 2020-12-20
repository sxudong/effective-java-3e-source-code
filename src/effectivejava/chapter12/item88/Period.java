package effectivejava.chapter12.item88;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * 第88条: 保护性的写readObject方法
 */
// Immutable class that uses defensive copying
// 使用防御性复制的不可变类
public final class Period implements Serializable {
//    private final Date start;
//    private final Date end;
    private Date start;
    private Date end;

    /**
     * @param start the beginning of the period
     * @param end the end of the period; must not precede start
     * @throws IllegalArgumentException if start is after end
     * @throws NullPointerException if start or end is null
     */
    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if (this.start.compareTo(this.end) > 0)
            throw new IllegalArgumentException(start + " after " + end);
    }

    public Date start () {
        return new Date(start.getTime());
    }

    public Date end () {
        return new Date(end.getTime());
    }

    public String toString() {
        return start + " - " + end;
    }

    // readObject method with validity checking - insufficient!
    // 具有有效性检查的readObject方法 - 不够！
//    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
//        s.defaultReadObject();
//        // Check that our invariants are satisfied
//        // 检查我们的不变量是否满足
//        if (start.compareTo(end) > 0)
//            throw new InvalidObjectException(start +" after "+ end);
//    }

    // readObject method with defensive copying and validity checking
    // 具有防御性复制和有效性检查的readObject方法
//    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
//        s.defaultReadObject();
//        // Defensively copy our mutable components 防御性地复制我们的可变组件
//        start = new Date(start.getTime());
//        end = new Date(end.getTime());
//        // Check that our invariants are satisfied 检查我们的不变量是否满足
//        if (start.compareTo(end) > 0)
//            throw new InvalidObjectException(start +" after "+ end);
//    }
}