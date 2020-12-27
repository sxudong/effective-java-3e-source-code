package effectivejava.chapter3.item10;

/**
 * 第10条: 重写 equals 方法时遵守通用约定
 */
// Class with a typical equals method (Page 48)
// 实现高质量equals方法的诀窍
public final class PhoneNumber {
    private final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "area code");
        this.prefix   = rangeCheck(prefix,   999, "prefix");
        this.lineNum  = rangeCheck(lineNum, 9999, "line num");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max)
            throw new IllegalArgumentException(arg + ": " + val);
        return (short) val;
    }

    @Override public boolean equals(Object o) {
        // 1.使用 ==操作符 判断是不是当前对象
        if (o == this)
            return true;
        // 2.使用instanceof操作符检查“参数是否为正确的类型”
        if (!(o instanceof PhoneNumber))
            return false;
        // 3.把参数进行转型
        PhoneNumber pn = (PhoneNumber)o;
        // 4.对于类中的每个“关键”域，检查参数中的域是否与该对象中对应的域相匹配。
        return pn.lineNum == lineNum && pn.prefix == prefix
                && pn.areaCode == areaCode;
    }

    // 请注意，覆盖了equals，必需覆盖hashCode (Item 11)!
}
