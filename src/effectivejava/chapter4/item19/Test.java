package effectivejava.chapter4.item19;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    // 测试clear()调用removeRange()方法
    public static void main(String[] args) {
        ArrayList<Integer> ints = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        // sublist从下标0开始，含头不含尾
        ints.subList(2, 4).clear();
        System.out.println(ints);
    }
}
