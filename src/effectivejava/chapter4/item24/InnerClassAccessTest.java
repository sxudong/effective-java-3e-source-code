package effectivejava.chapter4.item24;

/**
 * 普通的内部类的字段与方法，只能放在类的外部层次上？《Java编程思想》P201
 * https://www.zhihu.com/question/63507789
 */
public class InnerClassAccessTest {
    public static void main(String[] args) {
        OuterClassForTest outer = new OuterClassForTest();
        // 外部类访问内部类无悬念        
        outer.showInnerClass();

        // 其他类无法访问外部类的内部类，只能通过内部类所属的外部类来访问。
        // InnerClassForTest test = new InnerClassForTest(); // 这个语句无法执行
        // outer.new InnerClassForTest();
        outer.new InnerClassForTest().getClassName();
    }
}
/* Output:
Inner Class
 */