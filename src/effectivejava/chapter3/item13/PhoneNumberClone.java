package effectivejava.chapter3.item13;

import java.util.HashMap;
import java.util.Map;

/**
 * 第13条：谨慎地覆盖clone
 * 使用拷贝构造器替代clone方法
 */
public final class PhoneNumberClone {
    private final short areaCode, prefix, lineNum;

    public PhoneNumberClone(int areaCode, int prefix, int lineNum) {
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
        if (o == this)
            return true;
        if (!(o instanceof PhoneNumberClone))
            return false;
        PhoneNumberClone pn = (PhoneNumberClone)o;
        return pn.lineNum == lineNum && pn.prefix == prefix
                && pn.areaCode == areaCode;
    }

    @Override public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        return result;
    }

    @Override public String toString() {
        return String.format("%03d-%03d-%04d",
                areaCode, prefix, lineNum);
    }

    // 使用拷贝构造器替代clone方法
    public static PhoneNumberClone newInstance(PhoneNumberClone p) {
        return new PhoneNumberClone(p.areaCode, p.prefix, p.lineNum);
    }

    public static void main(String[] args) {
        PhoneNumberClone pn = new PhoneNumberClone(707, 867, 5309);
        System.out.println(PhoneNumberClone.newInstance(pn));
        Map<PhoneNumberClone, String> m = new HashMap<>();
        m.put(pn, "Jenny");
        System.out.println(m.get(PhoneNumberClone.newInstance(pn)));
    }
}
/* Output:
707-867-5309
Jenny
 */