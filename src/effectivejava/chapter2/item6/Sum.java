package effectivejava.chapter2.item6;

import java.util.Comparator;

/**
 * 第6条：避免创建不必要的对象
 */
// Hideously slow program! Can you spot the object creation? (Page 20)
// 程序太慢了！ 您可以发现对象的创建吗？
public class Sum {
    private static long sum() {
        Long sum = 0L; // 自动装箱 8587.5688 ms.
        //long sum = 0; // 890.7956 ms.
        for (long i = 0; i <= Integer.MAX_VALUE; i++)
            sum += i;
        return sum;
    }

    public static void main(String[] args) {
        //int numSets = Integer.parseInt(args[0]);
        int numSets = 2;
        long x = 0;

        for (int i = 0; i < numSets; i++) {
            long start = System.nanoTime();
            x += sum();
            long end = System.nanoTime();
            System.out.println((end - start) / 1_000_000. + " ms.");
        }

        // Prevents VM from optimizing away everything. 防止VM优化一切。
        if (x == 42)
            System.out.println();
    }
}