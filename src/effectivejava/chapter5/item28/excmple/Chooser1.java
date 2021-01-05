package effectivejava.chapter5.item28.excmple;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 方案一：对象数组
 * 缺点：获取元素需要自动转型，万一转错了呢？
 */
// 选择器 -- 非常需要泛型的类
public class Chooser1 {
    private final Object[] choiceArray;

    public Chooser1(Collection choices) {
        choiceArray = choices.toArray();
    }

    // 使用choose充当游戏用的色子
    // 缺点：将choose()的返回值，从Object转换成每次调用该方法时想要的类型，如果搞错类型，转换就会在运行时失败。
    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }
}
