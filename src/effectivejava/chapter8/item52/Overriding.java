package effectivejava.chapter8.item52;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 第52条: 明智审慎的使用重载
 */
// Classification using method overrriding 使用重写方法进行分类 (Page 239)
public class Overriding {
    public static void main(String[] args) {
        List<Wine> wineList = List.of(
                new Wine(), new SparklingWine(), new Champagne());

        for (Wine wine : wineList)
            System.out.println(wine.name());

//        new Thread(System.out::println).start();
//        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.submit(System.out::println);
        // 第一个能编译过，第二个不能编译过。两个都带有Runnable的重载
        // 不同的是：submit方法还有一个Callable<T>的重载，println方法的重载都返回void，因此这个方法引用不可能是一个Callable
    }
}
/* Output:
wine
sparkling wine
champagne
 */