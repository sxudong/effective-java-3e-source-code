package effectivejava.chapter5.item31;
import java.util.*;

/**
 * 第31条：利用有限制通配符来提升API的灵活性
 * @param <E>
 */
// Generic stack with bulk methods using wildcard types (Pages 109)
// 使用“有限制的通配符类型”的批量方法的泛型堆栈
public class Stack<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    // The elements array will contain only E instances from push(E).
    // This is sufficient to ensure type safety, but the runtime
    // type of the array won't be E[]; it will always be Object[]!
    // elements数组将仅包含来自push（E）的E个实例。
    // 这足以确保类型安全，但是数组的运行时类型将不是E []；它永远是Object []！
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

//    // pushAll 没有通配符类型的静态工厂 - 不灵活！
//    public void pushAll(Iterable<E> src) {
//        for (E e : src)
//            push(e);
//    }

     // Wildcard type for parameter that serves as an E producer
     // 对E为生产者的输入参数使用通配符 <? extends E>
    public void pushAll(Iterable<? extends E> src) { // src参数产生E实例给Stack使用,通配符 ？是 E或E的子类
        for (E e : src)
            push(e);
    }

//    // popAll 没有通配符类型的静态工厂 - 不灵活！
//    public void popAll(Collection<E> dst) {
//        while (!isEmpty())
//            dst.add(pop());
//    }

    // Wildcard type for parameter that serves as an E consumer
    // 对E为消费者的输入参数使用通配符 <? super E>
    public void popAll(Collection<? super E> dst) { // dst参数通过Stack消费E实例,通配符 ？是 E或E的父类
        while (!isEmpty())
            dst.add(pop()); // public Number pop(), Collection<E>接口里的add(E var1)方法使用的是泛型，所以只能add添加E或E的子类
    }

    public static void main(String[] args) {
        Stack<Number> numberStack = new Stack<>(); //这里的Stack<E>里的E是Number
        Iterable<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);
        numberStack.pushAll(integers); //Iterable<? extends E> E是Number，？是Integer，Integer是Number的子类

        //Collection<? super Number> dst = new ArrayList<Object>();
        Collection<Object> objects = new ArrayList<>();
        numberStack.popAll(objects); //Collection<? super Number>, ？是Object，Object是Number的父类

        System.out.println(objects);
    }
}
/* Output:
[9, 5, 1, 4, 1, 3]
 */