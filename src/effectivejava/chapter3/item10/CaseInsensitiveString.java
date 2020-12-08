package effectivejava.chapter3.item10;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Broken - violates symmetry!  (Page 32)
// 损坏-违反对称性！
public final class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    // Broken - violates symmetry! 损坏-违反对称性！
    @Override
    public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString)
            return s.equalsIgnoreCase(
                    ((CaseInsensitiveString) o).s);
        if (o instanceof String)  // One-way interoperability! 单向互操作性
            return s.equalsIgnoreCase((String) o); // 忽略大小写
        return false;
    }

    // Fixed equals method 固定等于方法 (Page 33)
    // 为了解决“对称性”为问题，重构该方法，使它变成一条单独的返回语句
//    @Override public boolean equals(Object o) {
//        return o instanceof CaseInsensitiveString &&
//                ((CaseInsensitiveString) o).s.equalsIgnoreCase(s); // CaseInsensitiveString.s equalsIgnoreCase(s)
//    }

    // Demonstration of the problem 问题的证明(Page 32)
    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";

        // 自返性
        System.out.println(cis.equals(cis)); // true

        // 违反了对称性
        System.out.println(cis.equals(s)); // true
        System.out.println(s.equals(cis)); // false

        List<CaseInsensitiveString> list = new ArrayList<>();
        list.add(cis);

        // 在其它实现中，它有可能返回true，或者抛出一个运行时异常。
        // 一旦违反了equals约定，当其他对象面对你的对象时，你完全不知道这些对象的行为会怎么样。
        System.out.println(list.contains(s));
    }

}
/*
false
 */