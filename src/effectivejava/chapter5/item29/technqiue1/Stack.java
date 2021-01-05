package effectivejava.chapter5.item29.technqiue1;
import effectivejava.chapter5.item29.EmptyStackException;

import java.util.Arrays;

/**
 * 第29条：优先考虑泛型
 * @param <E>
 */
// Generic stack using E[] (Pages 103)
// 方案一：泛型栈使用E[]
public class Stack<E> {
    //这里会导致堆污染，在运行时，E 会被擦除为 Object，elements 数组就是 Object[] 类型
    //除非传入的 E 正好是Object
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    // elements数组包含的E来自push(E)方法中的数组引用指向对象
    // 这足以确保类型安全，但是数组的运行时类型将不是E[]；它永远是Object []！
    @SuppressWarnings("unchecked")
    public Stack() {
        // Object[]转换为E[]
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
        elements[size] = null; //消费过期索引
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

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