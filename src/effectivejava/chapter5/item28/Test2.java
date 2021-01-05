package effectivejava.chapter5.item28;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String [] args) {
        List<Integer> l2 = new ArrayList<Integer>();
        List <?> l3 = l2;
        test(l2);
        test(l3);
    }

    public static void test(List <?> l) {
        if (l instanceof List<?>)
            System.out.println("true");
        //无论您使用List它们都会生成绝对相同的字节码.或在instanceof中列出.
        //这是不使用原始类型(列表)引用生成类型的唯一方法
        if (l instanceof List)
            System.out.println("true");
    }
}
/*
true
true
true
true
 */