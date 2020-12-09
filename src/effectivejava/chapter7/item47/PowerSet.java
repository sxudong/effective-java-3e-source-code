package effectivejava.chapter7.item47;

import java.util.*;

/**
 * 第47条：Stream要优先用Collection作为返回类型
 */
public class PowerSet {
    // Returns the power set of an input set as custom collection (Page 218)
    // 返回输入集的幂集作为自定义集合
    public static final <E> Collection<Set<E>> of(Set<E> s) {
        List<E> src = new ArrayList<>(s);
        // 输入集合超过30个元素抛出异常
        if (src.size() > 30)
            throw new IllegalArgumentException("Set too big " + s);
        return new AbstractList<Set<E>>() {
            @Override public int size() {
                return 1 << src.size(); // 2 to the power srcSize 2到电源srcSize
            }

            @Override public boolean contains(Object o) {
                return o instanceof Set && src.containsAll((Set)o);
            }

            @Override public Set<E> get(int index) {
                Set<E> result = new HashSet<>();
                for (int i = 0; index != 0; i++, index >>= 1)
                    if ((index & 1) == 1)
                        result.add(src.get(i));
                return result;
            }
        };
    }

    public static void main(String[] args) {
        //Set s = new HashSet(Arrays.asList(args));
        Set s = new HashSet(Arrays.asList("a","b","c"));
        System.out.println(PowerSet.of(s));
    }
}
/* Output:
[[], [a], [b], [a, b], [c], [a, c], [b, c], [a, b, c]]
 */