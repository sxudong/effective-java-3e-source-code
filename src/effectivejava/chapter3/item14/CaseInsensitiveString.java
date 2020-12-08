package effectivejava.chapter3.item14;

import java.util.*;

/**
 * 第14条：考虑实现Comparable接口
 */
// Single-field Comparable with object reference field  单例属性比较对象的引用属性(Page 56)
public final class CaseInsensitiveString implements Comparable<CaseInsensitiveString> {
    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    // Fixed equals method (Page 40)
    @Override public boolean equals(Object o) {
        return o instanceof CaseInsensitiveString &&
                ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
    }

    @Override public int hashCode() {
        return s.hashCode();
    }

    @Override public String toString() {
        return s;
    }

    // Using an existing comparator to make a class comparable 使用现有的比较器，使类具有可比性
    public int compareTo(CaseInsensitiveString cis) {
        return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s); // 使用一个比较器
    }

    public static void main(String[] args) {
        Set<CaseInsensitiveString> s = new TreeSet<>();
        //for (String arg : args)
        for (String arg : new String[]{"d","c","b","a"})
            s.add(new CaseInsensitiveString(arg));
        System.out.println(s);
    }
}
/* Output:
[a, b, c, d]
 */