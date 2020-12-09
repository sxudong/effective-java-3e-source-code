package effectivejava.chapter7.item45;

/**
 * 第45条：坚持使用标准的函数接口
 */
// Refrain from using streams to process char values (Page 206)
// 避免使用流来处理char值
public class CharStream {
    public static void main(String[] args) {
        // Does not produce the expected result 没有产生预期的结果
        "Hello world!".chars().forEach(System.out::print);
        System.out.println();

        // Fixes the problem 解决问题
        "Hello world!".chars().forEach(x -> System.out.print((char) x)); // 最好避免利用Stream来处理char值
        System.out.println();
    }
}
/* Output:
721011081081113211911111410810033
Hello world!
 */