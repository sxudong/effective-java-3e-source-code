package effectivejava.chapter5.item31;

import java.util.*;

/**
 * 一般来说，<?>主要用于方法上，<T>主要用于类或方法上。
 */
// <?>可以用于List的删除和遍历
public class Test {
    public static void main(String[] args) {
        List<?>[] list = new List<?>[1];
        test03();

        HashSet<Integer> s1 = new HashSet<Integer>(Arrays.asList(1, 2, 3));
        printSet(s1);

        HashSet<String> s2 = new HashSet<String>(Arrays.asList("a", "b", "c"));
        printSet(s2);
    }

    private static <T> void test03() {
        List<T> list = new ArrayList<T>();
        User user = new User("111",12);
        list.add((T) user);
        list.add((T)"2");

        test04(list);
        System.out.println("================================");
        test04(list, user);
    }

    public static<T> void test04(List<T> list){
        System.out.println("新增删除前：" + list);
        User user = new User("112",13);
        list.add((T) user);

        System.out.println("新增后：" + list);
        list.remove(user);
        System.out.println("删除后：" + list);
    }

    // <?>任何事物，泛型参数可以持有任何类型
    // List<?>用于List的删除和遍历, List<?>等价于List<Object>,只不过这个Object等于任意类型
    public static void test04(List<?> list, User user){
        System.out.println("删除前：" + list);
        list.remove(user); // boolean remove(Object var1);
        System.out.println("删除后：" + list);
    }

    public static void test05() {
        List l = new ArrayList();
        l.add(1);
        l.add(2);
        l.add(3);
        Collections.swap(l, 0, 2); //作用：将传入的list中的下标为i和下标为j的元素交换
        System.out.println(l); // [3, 2, 1]
    }

    // <?> 可读数据，不能添加数据，因为Set的add(E var1) E不知道？是什么类型
    public static void printSet(Set<?> s) {
        //s.add(10);// 本行代码报错
        for (Object o : s) {
            System.out.println(o);
        }
    }
}
/* Output:
新增删除前：[User{username='111', age=12}, 2]
新增后：[User{username='111', age=12}, 2, User{username='112', age=13}]
删除后：[User{username='111', age=12}, 2]
================================
删除前：[User{username='111', age=12}, 2]
删除后：[2]
1
2
3
a
b
c
 */