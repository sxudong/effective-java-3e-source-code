package effectivejava.chapter9.item61;

/**
 * 第61条：基本数据类型优于包装类
 */
// What does this program do? - Page 274
// 这个程序做什么？
public class Unbelievable {
    static Integer i;

    public static void main(String[] args) {
        // 问题在于i是一个Integer对象，而不是int，像所有对象引用域一样，它的初始值为null
        // 在操作中混合使用基本类型和包装类型时，包装类型就会自动拆箱。
        // 如果一个空对象引用自动拆箱，那么你将得到一个 NullPointerException
        if (i == 42)
            System.out.println("Unbelievable");
    }
}
/* Output:
Exception in thread "main" java.lang.NullPointerException
	at effectivejava.chapter9.item61.Unbelievable.main(Unbelievable.java:12)
 */