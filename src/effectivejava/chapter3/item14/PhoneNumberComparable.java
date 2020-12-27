package effectivejava.chapter3.item14;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Comparator.comparingInt;

// Making PhoneNumber comparable (Pages 69-70)
public final class PhoneNumberComparable implements Comparable<PhoneNumberComparable> {
    private final short areaCode, prefix, lineNum;

    public PhoneNumberComparable(int areaCode, int prefix, int lineNum) {
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
        if (!(o instanceof effectivejava.chapter3.item11.PhoneNumber))
            return false;
        PhoneNumberComparable pn = (PhoneNumberComparable)o;
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

    // 方法一 比较法
//    @Override
//    public int compareTo(PhoneNumberComparable o) {
//        if(areaCode < o.areaCode)
//            return -1;
//        if(areaCode > o.areaCode)
//            return 1;
//        if(prefix < o.prefix)
//            return -1;
//        if(prefix > o.prefix)
//            return 1;
//        if(lineNum < o.lineNum)
//            return -1;
//        if(lineNum > o.lineNum)
//            return 1;
//        return 0; // 值相等返回0
//    }

    // 方法二 相减法，节省代码量，如果计算比较大，比较花时间的时候使用“比较法”
//    @Override
//    public int compareTo(PhoneNumberComparable o) {
//        int areaDiff = areaCode - o.areaCode;
//        if ( areaDiff != 0)
//            return areaDiff;
//
//        int prefixDiff = prefix - o.prefix;
//        if ( prefixDiff != 0)
//            return prefix;
//
//        int lineNumberDiff = lineNum - o.lineNum;
//        if (lineNumberDiff != 0)
//            return lineNum;
//
//        return 0; // 值相等返回0
//    }

    // 方法三 多字段比较
//    @Override
//    public int compareTo(PhoneNumberComparable pn) {
//        int result = Short.compare(areaCode, pn.areaCode);
//        if (result == 0)  {
//            result = Short.compare(prefix, pn.prefix);
//            if (result == 0)
//                result = Short.compare(lineNum, pn.lineNum);
//        }
//        return result;
//    }

    // 方法四：比较构造器方法 (page 70)
    private static final Comparator<PhoneNumberComparable> COMPARATOR =
            comparingInt((PhoneNumberComparable pn) -> pn.areaCode)
                    .thenComparingInt(pn -> pn.prefix)
                    .thenComparingInt(pn -> pn.lineNum);
    @Override
    public int compareTo(PhoneNumberComparable pn) {
        return COMPARATOR.compare(this, pn);
    }


    private static PhoneNumberComparable randomPhoneNumber() {
        Random rnd = ThreadLocalRandom.current();
        return new PhoneNumberComparable((short) rnd.nextInt(1000),
                               (short) rnd.nextInt(1000),
                               (short) rnd.nextInt(10000));
    }

    public static void main(String[] args) {
        NavigableSet<PhoneNumberComparable> s = new TreeSet<PhoneNumberComparable>();
        for (int i = 0; i < 10; i++)
            s.add(randomPhoneNumber());
        System.out.println(s);
    }
}
/* Output:
[010-254-1849, 286-260-4661, 336-770-7859, 423-339-4588, 447-933-5791, 490-964-1969, 645-232-3371, 688-425-7694, 695-713-7481, 799-843-7474]
 */