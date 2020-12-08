package effectivejava.chapter5.item27;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 第27条：消除非受检警告
 *
 * 添加局部变量以减小@SuppressWarnings的范围
 */
public class unSafeToArray {

    public static void main(String[] args) {
        // 往 ArrayList 中添加10个数字
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i< 10; i++) {
            list.add(i);
        }

        System.out.println("Integer[] 类型对象: ");
        // ArrayList.toArray(T[] var1)
        // 这种转换是正确的，因为我们正在创建的数组与传入的数组具有相同的类型T[]
        Integer[] integers = list.toArray(new Integer[list.size()]);
        System.out.println(Arrays.toString(integers));
        System.out.println();

    }

//    ArrayList#toArray(T[] a)
//    public <T> T[] toArray(T[] a) {
//        if (a.length < this.size) {
//            return Arrays.copyOf(this.elementData, this.size, a.getClass());
//        } else {
//            System.arraycopy(this.elementData, 0, a, 0, this.size);
//            if (a.length > this.size) {
//                a[this.size] = null;
//            }
//
//            return a;
//        }
//    }

//    Arrays#copyOf()
//    public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
//        @SuppressWarnings("unchecked") // 添加局部变量以缩小范围
//        T[] copy = ((Object)newType == (Object)Object[].class)
//                ? (T[]) new Object[newLength]
//                : (T[]) Array.newInstance(newType.getComponentType(), newLength);
//        System.arraycopy(original, 0, copy, 0,
//                Math.min(original.length, newLength));
//        return copy;
//    }
}
/* Output:
Integer[] 类型对象:
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 */