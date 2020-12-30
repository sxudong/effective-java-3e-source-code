package effectivejava.chapter5.item26;

import java.util.*;

public class Test2 {
    //static int numElementsInCommon(Set s1, Set s2) {
    //安全的替代做法是使用无限制的通配符类型
    static int numElementsInCommon(Set<?> s1, Set<?> s2){
        int result = 0;
        for (Object o1 : s1) {
            if (s2.contains(o1)) {
                result++;
            }
        }
        return result;
    }

    private static void collectionTest(Collection<?> collection) {
    }

    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        numElementsInCommon(set1, set2);

        String s = "s";
        //collectionTest(s); //Error:(41, 24) java: 不兼容的类型: java.lang.String无法转换为java.util.Collection<?>
        List<String> list = new ArrayList();
        collectionTest(list); //而List<String>却可以
    }
}
