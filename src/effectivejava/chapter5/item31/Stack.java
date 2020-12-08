package effectivejava.chapter5.item31;
import java.util.*;

/**
 * 第31条：利用有限制通配符来提升API的灵活性
 * @param <E>
 */
// Generic stack with bulk methods using wildcard types (Pages 109)
// 具有通配符类型的批量方法的泛型堆栈
public class Stack<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    // The elements array will contain only E instances from push(E).
    // This is sufficient to ensure type safety, but the runtime
    // type of the array won't be E[]; it will always be Object[]!
    @SuppressWarnings("unchecked")
    public Stack() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size==0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference 清除过期索引
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

//    // pushAll static factory without wildcard type - deficient!
//    public void pushAll(Iterable<E> src) {
//        for (E e : src)
//            push(e);
//    }

     // Wildcard type for parameter that serves as an E producer
     // 对E为生产者的输入参数使用通配符 <? extends E>
    public void pushAll(Iterable<? extends E> src) { // src参数产生E实例代Stack使用
        for (E e : src)
            push(e);
    }

//    // popAll static factory without wildcard type - deficient!
//    public void popAll(Collection<E> dst) {
//        while (!isEmpty())
//            dst.add(pop());
//    }

    // Wildcard type for parameter that serves as an E consumer
    // 对E为消费者的输入参数使用通配符 <? super E>
    public void popAll(Collection<? super E> dst) { // dst参数通过Stack消费E实例
        while (!isEmpty())
            dst.add(pop());
    }

    // Little program to exercise our generic Stack 练习我们通用堆栈的小程序
    public static void main(String[] args) {
        Stack<Number> numberStack = new Stack<>();
        Iterable<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);
        numberStack.pushAll(integers);

        Collection<Object> objects = new ArrayList<>();
        numberStack.popAll(objects);

        System.out.println(objects);
    }
}
/* Output:
[9, 5, 1, 4, 1, 3]
 */