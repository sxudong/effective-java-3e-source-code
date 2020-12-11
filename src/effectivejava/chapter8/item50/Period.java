package effectivejava.chapter8.item50;
import java.util.*;

/**
 * 第50条: 必要时进行防御性拷贝
 */
// Broken "immutable" time period class (Pages 231-3)
// 坏了的“不变”时间段类
public final class Period {
    private final Date start;
    private final Date end;

    /**
     * @param  start the beginning of the period
     * @param  end the end of the period; must not precede start
     * @throws IllegalArgumentException if start is after end
     * @throws NullPointerException if start or end is null
     */
    public Period(Date start, Date end) {
        if (start.compareTo(end) > 0)
            throw new IllegalArgumentException(
                    start + " after " + end);
        this.start = start;
        this.end   = end;
    }

    public Date start() {
        return start;
    }
    public Date end() {
        return end;
    }

    public String toString() {
        return start + " - " + end;
    }

    // Repaired constructor - makes defensive copies of parameters (Page 232)
    // 修复的构造函数 -- 制作参数的防御性副本
//    public Period(Date start, Date end) {
//        this.start = new Date(start.getTime()); // 不使用原始对象
//        this.end   = new Date(end.getTime());
//
//        if (this.start.compareTo(this.end) > 0)
//            throw new IllegalArgumentException(
//                    this.start + " after " + this.end);
//    }
//
//    // Repaired accessors - make defensive copies of internal fields (Page 233)
//    // 修复的访问器 -- 内部字段的防御性副本
//    public Date start() {
//        //return start;
//        return new Date(start.getTime());
//    }
//
//    public Date end() {
//        //return end;
//        return new Date(end.getTime());
//    }

    // Remainder omitted

    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(start, end);
        System.out.println(p);

        //end.setYear(78);   // 修改可变Date
        p.end().setYear(78); // 修改p的内部！
        System.out.println(p);
        /*
        Thu Dec 10 10:35:06 CST 2020 - Thu Dec 10 10:35:06 CST 2020
        Thu Dec 10 10:35:06 CST 2020 - Sun Dec 10 10:35:06 CST 1978
         */
    }
}