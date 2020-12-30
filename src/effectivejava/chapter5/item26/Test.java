package effectivejava.chapter5.item26;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private static void fun1(List list) { }
    private static void fun2(List<Object> list) { }

    /**
     * 泛型子类化规则：“List<String>”是原生类型“List”的一个子类型，
     * 而不是“List<Object>”的子类。
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        fun1(list);
        //fun2(list); //Error:(13, 14) java: 不兼容的类型: java.util.List<java.lang.String>无法转换为java.util.List<java.lang.Object>
    }
    /*
     *结论证明： 也就是说List<String>和List<Object>它们没有任何关系，是不同的类型。
     * 虽然String是Object的子类，但是不能说List<String>是List<Object>的子类！
     */
}
