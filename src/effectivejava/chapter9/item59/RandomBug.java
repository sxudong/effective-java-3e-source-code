package effectivejava.chapter9.item59;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 第59条：了解并使用类库
 */
// Random number generation is hard! - Page 215
// 随机数生成很难！
public class RandomBug {
    // Common but deeply flawed! 常见但存在严重缺陷！
    static Random rnd = new Random();

    static int random(int n) {
        return Math.abs(rnd.nextInt()) % n;
    }

    public static void main(String[] args) {
        int n = 2 * (Integer.MAX_VALUE / 3);
        int low = 0;
        for (int i = 0; i < 1000000; i++)
            if (random(n) < n / 2)
                low++;
        System.out.println(low); // 667050

        System.out.println();

        Random r = new Random();
        System.out.println(r.nextBoolean());
        System.out.println(r.nextInt(50));      //随机生成0~50的随机数，不包括50
        System.out.println(r.nextInt(20) + 30); //随机生成30~50的随机数，不包括50

        System.out.println();

        /**
         * ThreadLocalRandom:是JDK 7之后提供并发产生随机数，能够解决多个线程发生的竞争争夺。
         * ThreadLocalRandom不是直接用new实例化，而是第一次使用其静态方法current()。
         *
         * 从Math.random()改变到ThreadLocalRandom有如下好处：
         * 我们不再有从多个线程访问同一个随机数生成器实例的争夺。
         * 取代以前每个随机变量实例化一个随机数生成器实例，我们可以每个线程实例化一个。
         */
        ThreadLocalRandom t = ThreadLocalRandom.current();
        System.out.println(t.nextInt(50));             //随机生成0~50的随机数，不包括50
        System.out.println(t.nextInt(30, 50));  //随机生成30~50的随机数，不包括50
    }
}
/* Output:
666072

true
24
43

38
33
 */