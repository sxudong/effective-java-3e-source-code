package effectivejava.chapter11.item81;
import java.util.concurrent.*;

/**
 * 第81条：相比 wait 和 notify 优先使用并发工具
 */
// Concurrent canonicalizing map atop ConcurrentMap - Pages 273-274
// 在ConcurrentMap顶部并发规范化地图
public class Intern {
    // Concurrent canonicalizing map atop ConcurrentMap - not optimal
    // 在ConcurrentMap顶部并发规范化地图-不是最佳
    private static final ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

//    public static String intern(String s) {
//        String previousValue = map.putIfAbsent(s, s);
//        return previousValue == null ? s : previousValue;
//    }

    // Concurrent canonicalizing map atop ConcurrentMap - faster!
    // 在ConcurrentMap之上并发规范化地图-更快！
    public static String intern(String s) {
        // 先调get，为空才调putIfAbsent
        String result = map.get(s);
        if (result == null) {
            result = map.putIfAbsent(s, s);
            if (result == null)
                result = s;
        }
        return result;
    }
}