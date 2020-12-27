package effectivejava.chapter2.item6;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 第6条：避免创建不必要的对象
 * 改进后 -- 定义“常量”和“静态代码块”的方式重用对象，
 * Boow_START 和 BooN_END 只创建了一次，不会创建多次。
 */
public class PersonStatic { // 查询是否人口大爆炸时出生的
    private Date birthDate;

    private static final Date Boow_START; // 使用常量重用对象
    private static final Date BooN_END;

    // 初始化赋值
    static {
        Calendar gmtcal = Calendar.getInstance(TimeZone.getTimeZone("GNT"));
        gmtcal.set(1964, Calendar.JANUARY,1, 0, 0,0);
        Boow_START = gmtcal.getTime();
        gmtcal.set(1965, Calendar.JANUARY,1, 0, 0, 0);
        BooN_END = gmtcal.getTime();
    }

    public boolean isBabyBoomer() {
    return birthDate.compareTo(Boow_START) >= 0 &&birthDate.compareTo(BooN_END) < 0;
    }
}