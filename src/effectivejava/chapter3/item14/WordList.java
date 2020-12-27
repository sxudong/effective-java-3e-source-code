package effectivejava.chapter3.item14;
import java.util.*;

/**
 * 第14条：考虑实现Comparable接口
 */
// The benefits of implementing Comparable 实施可比性的好处 (Page 53)
public class WordList {
    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        // 在IDEA的Edit Configurations->Program arguments输入参数
        //Collections.addAll(s, args);
        Collections.addAll(s, new String[]{"d","c","b","a"});
        System.out.println(s);
    }
}
/* Output:
[a, b, c, d]
 */