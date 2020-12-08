package effectivejava.chapter2.item7;
import java.util.*;

// Can you spot the "memory leak"?  您可以发现“内存泄漏”吗？(Pages 20-23)
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

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }

    /**
     * Ensure space for at least one more element, roughly
     * doubling the capacity each time the array needs to grow.
     */
    private void ensureCapacity() {
        if (elements.length == size) // size到16后扩容
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

//    // Corrected version of pop method 修正的弹出方法版本(Page 22)
//    public Object pop() {
//        if (size == 0)
//            throw new EmptyStackException();
//        Object result = elements[--size];
//        elements[size] = null; // Eliminate obsolete reference 消除过期的引用
//        return result;
//    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        //for (String arg : args)
        for (String arg : new String[]{"a","b","c"})
            stack.push(arg);

        while (true)
            System.err.println(stack.pop());
    }
}
