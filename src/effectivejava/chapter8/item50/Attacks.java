package effectivejava.chapter8.item50;
import java.util.*;

/**
 * 第50条: 必要时进行防御性拷贝
 */
// Two attacks on the internals of an "immutable" period (232-3)
// 对“不变”时期内部的两次攻击
public class Attacks {
    public static void main(String[] args) {
        // Attack the internals of a Period instance  (Page 232)
        // 攻击Period实例的内部
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(start, end);
        end.setYear(78);  // Modifies internals of p!
        System.out.println(p);

        // Second attack on the internals of a Period instance  (Page 233)
        // 对Period实例内部的第二次攻击
        start = new Date();
        end = new Date();
        p = new Period(start, end);
        p.end().setYear(78);  // Modifies internals of p! 修改p的内部！
        System.out.println(p);
    }
}
