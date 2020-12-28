package effectivejava.chapter4.item16;

/**
 * 第16条：要在公有类而非公有域中使用访问方法
 */
// Public class with exposed immutable fields - questionable   (Page 64)
// 具有公有的不可变字段域 - 可疑
public final class Time { // 不可变的类
    private static final int HOURS_PER_DAY    = 24; // 不可变的域
    private static final int MINUTES_PER_HOUR = 60;

    public final int hour;    // 公有final不可变域
    public final int minute;

    public Time(int hour, int minute) {
        if (hour < 0 || hour >= HOURS_PER_DAY)
            throw new IllegalArgumentException("Hour: " + hour);
        if (minute < 0 || minute >= MINUTES_PER_HOUR)
            throw new IllegalArgumentException("Min: " + minute);
        this.hour = hour;
        this.minute = minute;
    }

    // Remainder omitted

    public static void main(String[] args) {
        System.out.println(Time.HOURS_PER_DAY);
    }
}
