package effectivejava.chapter11.item79;
import java.util.*;

/**
 * 第79条：避免过度同步
 */
// Simple test of ObservableSet - Page 318
// 简单测试ObservableSet
public class Test1 {
    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        set.addObserver((s, e) -> System.out.println(e));

        for (int i = 0; i < 100; i++)
            set.add(i);
    }
}
