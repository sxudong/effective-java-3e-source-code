package effectivejava.chapter5.item32;

import java.util.ArrayList;
import java.util.List;

/**
 * 第32条：合理的结合泛型和可变参数
 */
// Safe method with a generic varargs parameter (page 149)
// 具有泛型可变参数的安全方法
public class FlattenWithVarargs {
    // 如果不想使用@SafeVarargs可以用一个List参数代替可变参数（这是一个伪装的数组）参见 FlattenWithList.java
    // 用@SafeVarargs注解过的可变参数方法是安全的
    @SafeVarargs
    //static <T> List<Integer> flatten(List<? extends T>... lists) {
    static <T> List<T> flatten(List<? extends T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists)
            result.addAll(list);
        //return (List<Integer>) result;
        return result;
    }

    // 去掉@SafeVarargs，在命令行下编译提示“使用了未经检查或不安全的操作”
//    PS F:\code\effective-java-3e-source-code\src\effectivejava\chapter5\item32> javac FlattenWithVarargs.java
//    注: FlattenWithVarargs.java使用了未经检查或不安全的操作。
//    注: 有关详细信息, 请使用 -Xlint:unchecked 重新编译。

    public static void main(String[] args) {
        // 可变参数泛型方法，需要存和取两端都定义好参数类型，存和取的参数类型要一致，不然取出来的数据转换就容易失败，不知道是什么了？
        List<Integer> flatList = flatten(
                List.of(1, 2), List.of(3, 4, 5), List.of(6, 7)); // 编译器将自动获取到List<Object>转换为List<Integer>
        System.out.println(flatList);
    }
}
/* Output:
[1, 2, 3, 4, 5, 6, 7]
 */
