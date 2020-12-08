package effectivejava.chapter5.item29.technqiue1;
import effectivejava.chapter5.item29.EmptyStackException;

import java.util.Arrays;

/**
 * 第29条：优先考虑泛型
 * @param <E>
 */
// Generic stack using E[] (Pages 103)
// 泛型栈使用E[]
public class Stack<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    // The elements array will contain only E instances from push(E).
    // This is sufficient to ensure type safety, but the runtime
    // type of the array won't be E[]; it will always be Object[]!
    // elements数组包含的E来自push(E)方法
    // 这足以确保类型安全，但是数组的运行时类型将不是E []； 它永远是Object []！
    @SuppressWarnings("unchecked")
    public Stack() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference 消费过期索引
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
    // 锻炼我们泛型堆栈的小程序
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