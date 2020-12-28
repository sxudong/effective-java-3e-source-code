package effectivejava.chapter2.item6;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 第6条：避免创建不必要的对象
 * 改进前
 */
public class Person { // 查询是否人口大爆炸时出生的
    private Date birthDate;

    public boolean isBabyBoomer() {
        Calendar gmtcal = Calendar.getInstance(TimeZone.getTimeZone("GNT"));
        gmtcal.set(1964, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = gmtcal.getTime();
        gmtcal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomEnd = gmtcal.getTime();
        return birthDate.compareTo(boomStart) >= 0 && birthDate.compareTo(boomEnd) < 0;
    }
}