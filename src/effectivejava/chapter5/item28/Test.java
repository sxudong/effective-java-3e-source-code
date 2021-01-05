package effectivejava.chapter5.item28;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // 数组：运行时失败。在运行时才能发现问题
        Object[] objectArray = new Long[1];
        objectArray[0] = "I don't fit in"; // 抛出 ArrayStoreException 数组存储异常
        // Output:Exception in thread "main" java.lang.ArrayStoreException: java.lang.String

//        // 列表：不能编译。在编译时才能发现问题
//        List<Object> ol = new ArrayList<Long>(); // 不兼容的类型
//        ol.add("I don't fit in");
    }
}
