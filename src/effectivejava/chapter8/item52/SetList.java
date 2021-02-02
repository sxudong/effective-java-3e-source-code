package effectivejava.chapter8.item52;
import java.util.*;

/**
 * 第52条: 明智审慎的使用重载
 */
// What does this program print? 该程序打印什么？(Page 241)
public class SetList {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        List<Integer> list = new ArrayList<>();

        for (int i = -3; i < 3; i++) { // -3、-2、-1、0、1、2
            set.add(i);
            list.add(i);
        }
        System.out.println(set + " " + list);

        for (int i = 0; i < 3; i++) { // 0、1、2
            set.remove(i); // remove(E)
            // [-3, -2, -1, 0, 1, 2] 删除0号元素
            // [-2, -1, 0, 1, 2]     删除1号元素
            // [-2, 0, 1, 2]         删除2号元素
            // [-2, 0, 2]            最终结果
            //list.remove(i); // remove(int)
            // 将 list.remove 的参数转换成 Integer，迫使选择正确的重载方法
            list.remove((Integer) i); // or remove(Integer.valueOf(i));
        }
        System.out.println(set + " " + list); //[-3, -2, -1] [-2, 0, 2]
    }
}
