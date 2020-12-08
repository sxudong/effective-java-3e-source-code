package effectivejava.chapter5.item31;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * 第31条：利用有限制通配符来提升API的灵活性
 * 代码在第28条的基础上改进
 * @param <T>
 */
// Wildcard type for parameter that serves as an T producer (page 111)
// 对E为生产者的输入参数使用通配符 <? extends E>
public class Chooser<T> {
    private final List<T> choiceList;
    private final Random rnd = new Random();

    public Chooser(Collection<? extends T> choices) {
        choiceList = new ArrayList<>(choices);
    }

    public T choose() {
        return choiceList.get(rnd.nextInt(choiceList.size()));
    }

    public static void main(String[] args) {
        List<Integer> intList = List.of(1, 2, 3, 4, 5, 6);
        Chooser<Number> chooser = new Chooser<>(intList);
        for (int i = 0; i < 10; i++) {
            Number choice = chooser.choose();
            System.out.println(choice);
        }
    }
}
/* Output:
2
2
4
5
5
4
3
3
2
3
 */