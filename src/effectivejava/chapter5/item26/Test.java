package effectivejava.chapter5.item26;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private static void fun1(List list) { }         // Method fun1:(Ljava/util/List;)V
    private static void fun2(List<Object> list) { } // Method fun2:(Ljava/util/List;)V

    /**
     * 泛型子类化规则：
     * “List<String>”是原生类型“List”的一个子类型，而不是“List<Object>”的子类。
     * “List<Object>”也是原生类型“List”的一个子类型。
     */
    public static void main(String[] args) {
        // List<String> 定义了E是String
        List<String> list = new ArrayList();
        fun1(list);
        //fun2(list); //Error:(13, 14) java: 不兼容的类型: java.util.List<java.lang.String>无法转换为java.util.List<java.lang.Object>
        List<Object> listObject = new ArrayList();
        fun1(listObject);
        fun2(listObject);
    }
    /*
     *结论证明： 也就是说List<String>和List<Object>它们没有任何关系，是不同的类型。
     * 虽然String是Object的子类，但是不能说List<String>是List<Object>的子类！
     */
}
