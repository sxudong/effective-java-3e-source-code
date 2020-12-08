package effectivejava.chapter5.item29.technqiue2;

import java.util.Arrays;
import effectivejava.chapter5.item29.EmptyStackException;

/**
 * 第29条：优先考虑泛型
 * @param <E>
 */
// Generic stack using Object[] (Pages 130-3)
// 泛型栈使用Object[]
public class Stack<E> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    // Appropriate suppression of unchecked warning 适当抑制未经检查的警告
    public E pop() {
        if (size == 0)
            throw new EmptyStackException();

        // push requires elements to be of type E, so cast is correct
        // push要求元素的类型为E，因此强制转换正确
        @SuppressWarnings("unchecked")
        E result = (E) elements[--size]; // 每次读取的时候都要转换一次

        elements[size] = null; // Eliminate obsolete reference 消除过期的索引
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

    // Little program to exercise our generic Stack
    // 练习泛型堆栈的小程序
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //for (String arg : args)
        for (String arg : new String[]{"1","2","3"})
            stack.push(arg);
        while (!stack.isEmpty())
            System.out.println(stack.pop().toUpperCase());
    }
}
/* Output:
3
2
1
 */