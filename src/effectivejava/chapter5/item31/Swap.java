package effectivejava.chapter5.item31;
import java.util.*;

/**
 * 第31条：利用有限制通配符来提升API的灵活性
 */
// Private helper method for wildcard capture (Page 145)
public class Swap {
    public static void swap(List<?> list, int i, int j) {
        swapHelper(list, i, j);
    }

    // Private helper method for wildcard capture 私有的辅助方法来捕捉通配符
    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    public static void main(String[] args) {
        // Swap the first and last argument and print the resulting list
        // 交换第一个和最后一个参数并打印结果列表
        //List<String> argList = Arrays.asList(args);
        List<String> argList = Arrays.asList("第一个参数","第二个参数");
        swap(argList, 0, argList.size() - 1);
        System.out.println(argList);
    }
}
/* Output:
[第二个参数, 第一个参数]
 */