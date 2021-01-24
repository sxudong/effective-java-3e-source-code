package effectivejava.chapter3.item11;

import java.util.HashMap;
import java.util.Map;

/**
 * 第11条：覆盖equals时总要覆盖hashcode
 */
// Shows the need for overriding hashcode when you override equals (Pages 50-53 )
// 当您覆盖等于时显示需要覆盖hashCode
public final class PhoneNumber1 { //这是一个不变的类
    private final short areaCode, prefix, lineNum;


    public PhoneNumber1(int areaCode, int prefix, int lineNum) {
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
        if (!(o instanceof PhoneNumber1))
            return false;
        PhoneNumber1 pn = (PhoneNumber1)o;
        return pn.lineNum == lineNum && pn.prefix == prefix
                && pn.areaCode == areaCode;
    }

    // 第一种方法，始终返回相同的值
//    @Override public int hashCode() {
//        return 42;
//    }

    //如果一个类是不可变的，并且计算哈希码的代价很大，那么可以考虑在对象中缓存哈希码，而不是在每次请求时
    //重新计算哈希码。 如果你认为这种类型的大多数对象将被用作哈希键，那么应该在创建实例时计算哈希码。 否则，
    //可以选择在首次调用 hashCode 时延迟初始化（lazily initialize）哈希码。 需要注意确保类在存在延迟初始
    //化属性的情况下保持线程安全（项目 83）。
    private volatile int hashCode;

    // 第二种方法，根据计算返回不同的值
    @Override public int hashCode() {
        int result = hashCode; // 为了性能考量
        if (result == 0) {
            result = 17;
            result = 31 * result + areaCode;
            result = 31 * result + prefix;
            result = 31 * result + lineNum;
            hashCode = result; // 缓存哈希码（用在不可变类上，在new初始化缓存哈希码，再用时不用计算）
        }
        return result;
    }

    public static void main(String[] args) {
        Map<PhoneNumber1, String> map = new HashMap<>();
        map.put(new PhoneNumber1(707, 867, 5309), "Jenny");
        // 如果不覆盖hoshCode方法，输出null。覆盖后输出Jenny
        System.out.println(map.get(new PhoneNumber1(707, 867, 5309)));
        PhoneNumber1 phoneNumber11 = new PhoneNumber1(1, 1, 1);
        System.out.println(new PhoneNumber1(1,1,1));
        PhoneNumber1 phoneNumber1 = new PhoneNumber1(2, 3, 4);
        System.out.println(phoneNumber1);
        System.out.println(phoneNumber1); // 第二次没有计算哈希值,直接返回缓存的哈希值
        System.out.println(phoneNumber11.equals(phoneNumber1));

    }
}
/*
无覆盖hashCode输出：
null
effectivejava.chapter3.item11.PhoneNumber1@16b98e56
effectivejava.chapter3.item11.PhoneNumber1@7ef20235
effectivejava.chapter3.item11.PhoneNumber1@7ef20235

第一种方法输出,equals不同的对象输出的对象都一样：
Jenny
effectivejava.chapter3.item11.PhoneNumber1@2a
effectivejava.chapter3.item11.PhoneNumber1@2a
effectivejava.chapter3.item11.PhoneNumber1@2a

第二种方法输出,equals不同的对象输出不一样:
Jenny
effectivejava.chapter3.item11.PhoneNumber1@7be30
effectivejava.chapter3.item11.PhoneNumber1@7c232
effectivejava.chapter3.item11.PhoneNumber1@7c232
 */