package effectivejava.chapter4.item17;

public class Test {
    public static void main(String[] args) {
        Complex complex1 = Complex.valueOf(10, 20);
        System.out.println(complex1);
        System.out.println(Complex.valueOf(50,50).plus(complex1));

        StringBuilder s = new StringBuilder(); // 可变类
        s.append("Hello");
        s.append(" World");
        //把耗资源的操作拿出来单独处理，最后把它变成一个“不可变类”
        String s1 = s.toString();
        System.out.println(s1);
    }
}
/* Output:
(10.0 + 20.0)
(60.0 + 70.0)
Hello World
 */