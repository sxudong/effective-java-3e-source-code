package effectivejava.chapter5.item28.excmple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 方案三：全部用泛型，不用数组
 * 缺点：慢一些
 */
public class Chooser3<T> {
    private final List<T> choiceList;

    public Chooser3(Collection<T> choices) {
        choiceList = new ArrayList<>(choices);
    }

    public T choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceList.get(rnd.nextInt(choiceList.size()));
    }
}
