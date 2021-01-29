package effectivejava.chapter2.item7;
import java.util.*;

/**
 * 第7条：消除过期的对象引用
 */
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

//    public Object pop() {
//        if (size == 0)
//            throw new EmptyStackException();
//        return elements[--size];
//    }

    /**
     * Ensure space for at least one more element, roughly
     * doubling the capacity each time the array needs to grow.
     */
    private void ensureCapacity() {
        if (elements.length == size) // size到16后扩容
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

    // Corrected version of pop method 修正的弹出方法版本(Page 22)
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        // 消除过期的引用,等待回收
        elements[size] = null;
        return result;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        //for (String arg : args)
        for (String arg : new String[]{"a","b","c"})
            stack.push(arg);

        while (true)
            System.err.println(stack.pop());
    }
}
