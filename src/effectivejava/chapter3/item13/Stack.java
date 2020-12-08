package effectivejava.chapter3.item13;
import java.util.Arrays;

/**
 * 第13条：谨慎地覆盖clone
 * 如果对象中包含的域（属性字段）引用了“可变对象”，使用简单的clone实现可能会导致灾难性的后果。
 */
// A cloneable version of Stack (Pages 60-61)
public class Stack implements Cloneable {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference 消除过期的引用
        return result;
    }

    public boolean isEmpty() {
        return size ==0;
    }

    // Clone method for class with references to mutable state 引用可变状态的类的克隆方法
    @Override public Stack clone() {
        try {
            Stack result = (Stack) super.clone();
            result.elements = elements.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    // Ensure space for at least one more element. 扩容空间
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
    
    // To see that clone works, call with several command line arguments
    public static void main(String[] args) {
        Stack stack = new Stack();
        //for (String arg : args)
        for (String arg : new String[]{"A","B","C"})
            stack.push(arg);

        Stack copy = stack.clone();
        while (!stack.isEmpty())
            System.out.print(stack.pop() + " ");
        System.out.println();
        while (!copy.isEmpty())
            System.out.print(copy.pop() + " ");
    }
}
/* Output:
C B A
C B A
 */