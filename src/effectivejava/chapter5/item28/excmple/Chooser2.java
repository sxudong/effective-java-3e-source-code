package effectivejava.chapter5.item28.excmple;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 方案二：数组转泛型
 * 这是类似 ArrayList 中 toArray 方法。缺点是，有未检查警告。
 */
public class Chooser2<T> {
    //private final Object[] choiceArray;
    private final T[] choiceArray;

    // 类型安全由泛型保证。
    @SuppressWarnings("unchecked")
    //public Chooser2(Collection choices) {
    public Chooser2(Collection<T> choices) {
        //choiceArray = choices.toArray(); // 编译时报错，不兼容的类型，Object[] 无法转化为 T[]
        choiceArray = (T[]) choices.toArray(); // Object数组转T[]
    }

    // 使用choose充当游戏用的色子
    //public Object choose() {
    public T choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }
}
